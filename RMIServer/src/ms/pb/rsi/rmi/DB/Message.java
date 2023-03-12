package ms.pb.rsi.rmi.DB;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    String nickname;
    String message;
    private Date date;

    public Message (String nickname, String message, Date date) {
        this.nickname = nickname;
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public String getNickname() {
        return nickname;
    }

    public Date getDate() {
        return date;
    }

    public String getAllMessage() {
        return "[" + nickname + "] " + message;
    }
}
