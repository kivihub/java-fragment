package pers.kivi.javafragment.io;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author wangqiwei
 * @date 2021/01/28 9:59 PM
 */
public class NettyClientTest {
    private static final int LISTEN_PORT = 9000;
    private static final ByteBuf DELIMITER = Unpooled.copiedBuffer("_".getBytes());
    private static final int MAX_FRAME_LENGTH = 1024;

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventExecutors)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(MAX_FRAME_LENGTH, DELIMITER));
                        ch.pipeline().addLast(new StringEncoder());
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new ChatClientHandler());
                    }
                });

        ChannelFuture channelFuture = bootstrap.connect("localhost", LISTEN_PORT);
        channelFuture.await();
        System.out.println("Client connected server.");

        sendMsgWithConsole(channelFuture.channel());
//        sendBatchMsg(channelFuture.channel());

        channelFuture.channel().closeFuture().await();
    }

    private static void sendBatchMsg(Channel channel) {
        int i = 20;
        while (i-- > 0) {
            ByteBuf byteBuf = Unpooled.copiedBuffer("I am here._".getBytes());
            channel.writeAndFlush(byteBuf);
        }
    }

    private static void sendMsgWithConsole(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            System.out.println("Console input:" + s);
            channel.writeAndFlush(s + "_");
        }
    }

    static class ChatClientHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            System.out.println("Client online");
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) {
            System.out.println("Client offline");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            System.out.println(msg);
        }
    }
}
