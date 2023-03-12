package ms.pb.rsi.rmi;
import ms.pb.rsi.rmi.DB.Message;

import java.rmi.Naming;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MyClientMain {
    public static void main(String[] args) {
        System.setProperty("java.security.policy", "security.policy");

        try {
//            MyServerInt myRemoteObject = (MyServerInt) Naming.lookup("//82.139.168.80/ABC");
            MyServerInt myRemoteObject = (MyServerInt) Naming.lookup("//0.0.0.0/ABC");

            String nickname;
            Scanner input = new Scanner(System.in);
            System.out.println("Podaj swoją nazwę użytkownika:");
            nickname = input.nextLine();

            Thread scannerThread = new Thread(() -> {
                System.out.println("Podaj wiadomość: ");
                while (true) {
                    if (input.hasNext()) {
                        String text = input.next();

                        if (text.equals("/exit")) {
                            input.close();
                            break;
                        }

                        try {
                            Message newMessage = new Message(nickname, text, new Date());
                            myRemoteObject.sendMessage(newMessage);
                        } catch (Exception e) {
                            break;
                        }
                    }
                }
            });

            Thread messageThread = new Thread(() -> {
                Date date = new Date();
                while (true) {
                    try {
                        if (!scannerThread.isAlive()) {
                            System.out.println("Opuszczono czat");
                            scannerThread.stop();
                            input.close();
                            break;
                        }

                        List<Message> messages = myRemoteObject.updateMessages();
                        for (Message message : messages) {
                            if (message.getDate().after(date)) {
                                System.out.println(message.getAllMessage());
                            }
                        }
                    } catch (Exception e) {}
                }
            });

            scannerThread.start();
            messageThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

