package pers.kivi.javafragment.io;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author wangqiwei
 * @date 2021/01/25 9:38 PM
 */
public class NettyServerTest {
    private static final int LISTEN_PORT = 9000;
    private static final ByteBuf DELIMITER = Unpooled.copiedBuffer("_".getBytes());
    private static final int MAX_FRAME_LENGTH = 1024;

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(MAX_FRAME_LENGTH, DELIMITER));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new StringEncoder());
                        ch.pipeline().addLast(new ChatServerHandler());
                    }
                });
        ChannelFuture channelFuture = serverBootstrap.bind(LISTEN_PORT).sync();
        System.out.println("ChatServer started.");
        channelFuture.channel().closeFuture().await();
    }

    static class ChatServerHandler extends SimpleChannelInboundHandler {
        private static ChannelGroup onlineClients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            Channel channel = ctx.channel();
            String msg = String.format("Client[%s] online", channel.remoteAddress());

            onlineClients.add(channel);
            System.out.println(msg);
            onlineClients.writeAndFlush(msg + "_", itemChannel -> !itemChannel.equals(channel));
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) {
            Channel channel = ctx.channel();
            String msg = String.format("Client[%s] offline", channel.remoteAddress());

            onlineClients.remove(channel);
            System.out.println(msg + "_");
            onlineClients.writeAndFlush(msg + "_", itemChannel -> !itemChannel.equals(channel));
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
            Channel channel = ctx.channel();
            msg = String.format("Client[%s] says: %s", channel.remoteAddress(), msg);

            System.out.println(msg);
            onlineClients.writeAndFlush(msg + "_", itemChannel -> !itemChannel.equals(channel));
        }
    }
}
