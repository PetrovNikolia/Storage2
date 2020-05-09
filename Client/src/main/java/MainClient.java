import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8189);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner in = new Scanner(socket.getInputStream());
            //out.write(new byte[]{22, 22, 22});
            try(FileInputStream fin=new FileInputStream("D:\\БД\\Storage2\\Input\\Dz"))
            {
                System.out.printf("File size: %d bytes \n", fin.available());
                int i=-1;
                while((i=fin.read())!=-1){
                    out.write((char)i);
                }
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
            String x = in.nextLine();
            System.out.println("A: " + x);
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
