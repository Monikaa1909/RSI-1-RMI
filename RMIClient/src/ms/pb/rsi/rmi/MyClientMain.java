package ms.pb.rsi.rmi;
import java.rmi.Naming;
import java.util.Scanner;

public class MyClientMain {
    public static void main(String[] args) {
        System.setProperty("java.security.policy", "security.policy");

        try {
            MyServerInt myRemoteObject = (MyServerInt) Naming.lookup("//localhost/ABC");

            int a, b;
            char symbol;
            Scanner input = new Scanner(System.in);
            System.out.println("Podaj pierwszą liczbę:");
            a = input.nextInt();
            System.out.println("Podaj drugą liczbę:");
            b = input.nextInt();

            System.out.println("Dodawanie dwóch liczb - wciśnij +");
            System.out.println("Odejmowanie dwóch liczb - wciśnij -");
            System.out.println("Mnożenie dwóch liczb - wciśnij *");
            System.out.println("Dzielenie dwóch liczb - wciśnij /");

            symbol = input.next().charAt(0);

            String result = myRemoteObject.calculate(a, b, symbol);
            System.out.println("Wysłano do servera: " + a + " " + symbol + " " + b);
            System.out.println("Otrzymana z serwera odpowiedź: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

