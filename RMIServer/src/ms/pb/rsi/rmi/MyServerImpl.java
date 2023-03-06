package ms.pb.rsi.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyServerImpl extends UnicastRemoteObject implements MyServerInt {
    int i = 0;

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
}
