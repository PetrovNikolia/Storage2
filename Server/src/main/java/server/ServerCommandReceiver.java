package server;


import common.CommandReceiver;
import common.ProtoFileSender;
import io.netty.channel.ChannelHandlerContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerCommandReceiver extends CommandReceiver {


    @Override
    public void parseCommand(ChannelHandlerContext ctx, String cmd) throws Exception {
        if(cmd.startsWith("/request ")) {
            String fileToClientName = cmd.split("\\s")[1];
            ProtoFileSender.sendFile(Paths.get("server_repository", fileToClientName), ctx.channel(), null);
        }

        if(cmd.startsWith("/delete ")){
            String fileToDeleteName = cmd.split("\\s")[1];
            Path filePath = Paths.get("server_repository", fileToDeleteName);
            if(Files.exists(filePath)){
                System.out.println(fileToDeleteName + " Удален");
                System.out.println(Files.deleteIfExists(filePath));
            } else {
                System.out.println("Такого файла нет на сервере");
            }
        }
    }
}
