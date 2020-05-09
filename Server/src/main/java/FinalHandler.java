import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FinalHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        byte[] arr = (byte[]) msg;
        Files.write(Paths.get("Output","HomeWork"), arr);
        System.out.println("Сообщение записано в файл");
        ctx.writeAndFlush("Java\n"); // от обработчика
        // ctx.channel().writeAndFlush("Java"); // от конца конвеера
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
