package ms.pb.rsi.rmi;

import ms.pb.rsi.rmi.DB.MyDataBase;
import ms.pb.rsi.rmi.DB.Player;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class MyServerImpl extends UnicastRemoteObject implements MyServerInt {
    MyDataBase myDataBase = new MyDataBase();

    protected MyServerImpl() throws RemoteException {
        super();
    }

    @Override
    public String calculate(int a, int b, char symbol) throws RemoteException {
        String result = " ";

        switch(symbol) {
            case '+': {
                result = String.valueOf(a + b);
                break;
            }
            case '-': {
                result = String.valueOf(a - b);
                break;
            }
            case '*': {
                result = String.valueOf(a * b);
                break;
            }
            case '/': {
                if (b != 0) {
                    result = String.valueOf(a / b);
                } else {
                    result = "Nie dzielimy przez zero!!!";
                }
                break;
            }
        }

        return "Wynik: " + result;
    }

    @Override
    public List<Player> getAllPlayers() throws RemoteException {
        System.out.println("Wywołano getAllPlayers()");
        return myDataBase.getAllPlayers();
    }

    @Override
    public Player getPlayerById(int id) throws RemoteException {
        System.out.println("Wywołano getPlayerById(" + id + ")");
        return myDataBase.getPlayerById(id);
    }
}
