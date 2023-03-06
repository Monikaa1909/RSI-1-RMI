package ms.pb.rsi.rmi;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyServerInt extends Remote{
    String calculate(int a, int b, char symbol) throws RemoteException;
}
