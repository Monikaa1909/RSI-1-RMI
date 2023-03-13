package ms.pb.rsi.rmi;

import ms.pb.rsi.rmi.DB.BoardState;
import ms.pb.rsi.rmi.DB.MoveResult;

import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

public class MyClientMain {
    final static Scanner scanner = new Scanner(System.in);
    static int numberOfPlayers = -1;

    public static void main(String[] args) {
        try {
//            MyServerInt myRemoteObject = (MyServerInt) Naming.lookup("//82.139.168.80/ABC");
            MyServerInt myRemoteObject = (MyServerInt) Naming.lookup("//0.0.0.0/ABC");

            numberOfPlayers = myRemoteObject.joinTicTacToeGame();
            if (numberOfPlayers == -1) {
                System.out.println("Musisz zaczekać. Gra aktualnie w toku.");
                return;
            }

            System.out.println("Oczekiwanie na przeciwnika...");
            while (!myRemoteObject.canGameStart());

            if (myRemoteObject.whoStart() == numberOfPlayers) {
                System.out.println("Rozpoczynasz grę!");
            } else System.out.println("Grę rozpoczyna Twój przeciwnik!");

            int won = -1;
            while ((won = myRemoteObject.whoWon()) == -1) {
                while (myRemoteObject.whoStart() != numberOfPlayers);
                printBoardState(myRemoteObject.getState());
                if ((won = myRemoteObject.whoWon()) != -1) break;

                while (myRemoteObject.move(getMove()) == MoveResult.INVALID_MOVE);
                printBoardState(myRemoteObject.getState());
            }

            System.out.println(won == numberOfPlayers ? "Wygrałeś!" : won == -2 ? "Remis..." : "Nie wygrałeś!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getMove () {
        while (true) {
            System.out.print("Podaj swój ruch (np. a1, c3 itp...) ->  ");
            String move = scanner.next();
            char a = move.toLowerCase().charAt(0);
            char b = move.charAt(1);

            if (move.length() == 2 && Character.isDigit(b) && Character.isAlphabetic(a)) {
                if (a == 'a' || a == 'b' || a == 'c') {
                    if (b == '1' || b == '2' || b == '3') {
                        return (a - 'a') + (b - '1') * 3;
                    }
                }
            }
        }
    }

    private static void printBoardState (List<BoardState> state) {
        System.out.println(String.format("  ╭┈┈┈┈┈┈┈┈┈┈┈╮"));
        System.out.println(String.format("1 ┊ %s ┊ %s ┊ %s ┊", sign(state.get(0)), sign(state.get(1)), sign(state.get(2))));
        System.out.println(String.format("  ┊┈┈┈┊┈┈┈┊┈┈┈┊"));
        System.out.println(String.format("2 ┊ %s ┊ %s ┊ %s ┊", sign(state.get(3)), sign(state.get(4)), sign(state.get(5))));
        System.out.println(String.format("  ┊┈┈┈┊┈┈┈┊┈┈┈┊"));
        System.out.println(String.format("3 ┊ %s ┊ %s ┊ %s ┊", sign(state.get(6)), sign(state.get(7)), sign(state.get(8))));
        System.out.println(String.format("  ╰┈┈┈┈┈┈┈┈┈┈┈╯"));
        System.out.println(String.format("    a   b   c  "));
    }

    private static String sign (BoardState state) {
        switch (state) {
            case X: return (numberOfPlayers == 1 ? "\u001B[32m" : "\u001B[31m") + "X\u001B[0m";
            case O: return (numberOfPlayers == 0 ? "\u001B[32m" : "\u001B[31m") + "O\u001B[0m";
            default: return " ";
        }
    }
}
