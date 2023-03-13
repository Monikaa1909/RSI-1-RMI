package ms.pb.rsi.rmi;

import ms.pb.rsi.rmi.DB.Player;

import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

public class MyClientMain {
    public static void main(String[] args) {
        System.setProperty("java.security.policy", "security.policy");
        try {
//            MyServerInt myRemoteObject = (MyServerInt) Naming.lookup("//82.139.168.80/ABC");
            MyServerInt myRemoteObject = (MyServerInt) Naming.lookup("//0.0.0.0/ABC");

            int operation;
            Scanner input = new Scanner(System.in);
            System.out.println("Wybierz operację, którą chcesz wykonać:");
            System.out.println("Pobierz i prześlij listę wszystkich rekordów z bazy danych - WCIŚNIJ 1");
            System.out.println("Wyszukaj rekord z bazy danych po id - WCIŚNIJ 2");

            operation = input.nextInt();

            if (operation == 1) {
                List<Player> result = myRemoteObject.getAllPlayers();
                for (int i = 0; i < result.size(); i++) {
                    System.out.println(result.get(i).getPlayer());
                }
            } else if (operation == 2) {
                int id;
                System.out.println("Podaj id zawodnika, którego chcesz wyszukać: ");
                id = input.nextInt();
                Player player = myRemoteObject.getPlayerById(id);
                System.out.println(player.getPlayer());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
