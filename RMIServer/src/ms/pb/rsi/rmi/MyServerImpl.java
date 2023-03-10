package ms.pb.rsi.rmi;

import ms.pb.rsi.rmi.DB.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyServerImpl extends UnicastRemoteObject implements MyServerInt {
    MyDataBase myDataBase = new MyDataBase();
    List<Message> messages = new ArrayList<>();

    List<BoardState> state = new ArrayList<>();
    int players = 0;
    int whoStart = 0;

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

    @Override
    public List<Message> updateMessages() throws RemoteException {
        return messages;
    }

    @Override
    public String sendMessage(Message message) throws RemoteException {
        messages.add(message);
        return null;
    }

    @Override
    public int joinTicTacToeGame() throws RemoteException {
        if (players >= 2) return -1;

        players += 1;

        if (players == 2) {
            resetBoard();
        }

        return players - 1;
    }

    @Override
    public MoveResult move(int index) throws RemoteException {
        BoardState s = state.get(index);

        if (s == BoardState.EMPTY) {
            state.set(index, whoStart == 0 ? BoardState.O : BoardState.X);
            whoStart = Math.abs(whoStart - 1);

            if (whoWon() != -1) {
                players = 0;
                return MoveResult.WIN;
            }

            return MoveResult.NOOP;
        }

        return MoveResult.INVALID_MOVE;
    }

    @Override
    public boolean canGameStart() throws RemoteException {
        if (players == 2)
            return true;
        return false;
    }

    @Override
    public List<BoardState> getState() throws RemoteException {
        return state;
    }

    @Override
    public int whoWon() throws RemoteException {
        BoardState winningState = BoardState.EMPTY;
        for (int i = 0; i < 3; i++) {
            if (state.get(i * 3 + 0) == state.get(i * 3 + 1) && state.get(i * 3 + 0) == state.get(i * 3 + 2)) {
                winningState = state.get(i * 3 + 0);
                break;
            }

            if (state.get(i) == state.get(3 + i) && state.get(0 + i) == state.get(6 + i)) {
                winningState = state.get(i);
                break;
            }
        }

        if (winningState == BoardState.EMPTY) {
            if (state.get(0) == state.get(4) && state.get(4) == state.get(8) || state.get(2) == state.get(4) && state.get(4) == state.get(6)) {
                winningState = state.get(4);
            }
        }

        if (!state.contains(BoardState.EMPTY)) return -2;
        if (winningState == BoardState.EMPTY) return -1;
        return winningState == BoardState.O ? 0 : 1;
    }

    @Override
    public int whoStart() throws RemoteException {
        return whoStart;
    }

    private void resetBoard() {
        state.clear();

        for (int i = 0; i < 9; i++) {
            state.add(BoardState.EMPTY);
        }

        Random random = new Random();
        whoStart = Math.abs(random.nextInt()) % 2;
    }
}
