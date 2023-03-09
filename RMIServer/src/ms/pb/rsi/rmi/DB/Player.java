package ms.pb.rsi.rmi.DB;

import java.io.Serializable;

public class Player implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String club;

    public Player (int id, String firstName, String lastName, int age, String club) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.club = club;
    }

    public String getPlayer () {
        return this.firstName + ' ' + this.lastName + ' ' + this.age + ' ' + this.club;
    }

    public int getId() {
        return this.id;
    }
}
