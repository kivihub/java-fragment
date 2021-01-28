package pers.kivi.javafragment.io;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledDirectByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.junit.Test;

import java.util.Scanner;

/**
 * @author wangqiwei
 * @date 2021/01/28 9:59 PM
 */
public class NettyClientTest {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventExecutors)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("_".getBytes())));
                        ch.pipeline().addLast(new StringEncoder());
                        ch.pipeline().addLast(new ChatClientHandler());
                    }
                });
        ChannelFuture localhost = bootstrap.connect("localhost", 9000);
        localhost.await();
        System.out.println("client connect.");
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextLine()) {
//            String s = scanner.nextLine();
//            System.out.println("Console input:" + s);
//            ByteBuf byteBuf = Unpooled.copiedBuffer(s.getBytes());
//            localhost.channel().writeAndFlush(byteBuf);
//        }
//
        int i = 20;
        while (i-- >0) {
            ByteBuf byteBuf = Unpooled.copiedBuffer("kivi_".getBytes());
            localhost.channel().writeAndFlush(byteBuf);
        }

        localhost.channel().closeFuture().await();
    }

    static class ChatClientHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("Client Channel Active");
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("Client Channel InActive");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("Client accept: " + msg);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            System.out.println("Client读取完毕");
        }
    }
}
