package ms.pb.rsi.rmi;
import ms.pb.rsi.rmi.DB.BoardState;
import ms.pb.rsi.rmi.DB.Message;
import ms.pb.rsi.rmi.DB.MoveResult;
import ms.pb.rsi.rmi.DB.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MyServerInt extends Remote{
    String calculate(int a, int b, char symbol) throws RemoteException;
    List<Player> getAllPlayers() throws RemoteException;
    Player getPlayerById(int id) throws RemoteException;
    List<Message> updateMessages() throws RemoteException;
    String sendMessage(Message message) throws RemoteException;

    public int joinTicTacToeGame() throws RemoteException;
    public MoveResult move (int index) throws RemoteException;
    public boolean canGameStart() throws RemoteException;
    public List<BoardState> getState () throws RemoteException;
    public int whoWon () throws RemoteException;
    public int whoStart() throws RemoteException;
}
