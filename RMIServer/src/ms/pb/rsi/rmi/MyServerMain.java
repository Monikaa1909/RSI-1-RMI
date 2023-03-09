package ms.pb.rsi.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class MyServerMain {
    public static void main(String[] args) {
        try {
            System.setProperty("java.security.policy", "security.policy");

            LocateRegistry.createRegistry(1099);
//            System.setProperty("java.rmi.server.hostname","82.139.168.80");
            System.setProperty("java.rmi.server.hostname","0.0.0.0");

            MyServerImpl server = new MyServerImpl();
//            Naming.rebind("//82.139.168.80/ABC", server);
            Naming.rebind("//0.0.0.0/ABC", server);
            System.out.println("Serwer oczekuje ...");

        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
