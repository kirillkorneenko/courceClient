package by.bsuir.stock.conection;

import by.bsuir.stock.bean.Batch;
import by.bsuir.stock.bean.UsersEntity;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

public class Connection {

    private static Batch result= new Batch();
    private static UsersEntity user = new UsersEntity();
    private static int port;

   static  {
        try {
        Properties properties = new Properties();
        InputStream input = new FileInputStream("classes/connection.properties");
        properties.load(input);
        Integer port =Integer.parseInt(properties.getProperty("port"));
        setPort(port);
        } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Batch connection(Batch batch){

        try{


        Socket socket = new Socket("localhost",9000);
            ObjectOutputStream out =new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(batch);

            result = (Batch) in.readObject();


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
      return result;
    }

    public static UsersEntity getUser() {
        return user;
    }

    public static void setUser(UsersEntity user) {
        Connection.user = user;
    }

    public static Batch getB() {
        return result;
    }

    private static void setPort(int port) {
        Connection.port = port;
    }
}
