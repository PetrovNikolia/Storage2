import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;
import java.nio.file.Path;
import java.util.concurrent.CountDownLatch;

public class MainClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        CountDownLatch networkStarter = new CountDownLatch(1);
        new Thread(() -> Network.getInstance().start(networkStarter)).start();
        networkStarter.await();

        ProtoFileSender.sendFile(Paths.get("Input/Dz"), Network.getInstance().getCurrentChannel(), future -> {
            if (!future.isSuccess()) {
                future.cause().printStackTrace();
//                Network.getInstance().stop();
            }
            if (future.isSuccess()) {
                System.out.println("Файл успешно передан");
//                Network.getInstance().stop();
            }
        });

    }
}
