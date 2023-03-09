package ms.pb.rsi.rmi.DB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyDataBase implements Serializable {
    List<Player> players = new ArrayList<>();

    public MyDataBase() {
        players.add(new Player(1, "Robert", "Lewandowski", 32, "FC Barcelona"));
        players.add(new Player(2, "Wojciech", "Szczęsny", 34, "Juventus"));
        players.add(new Player(3, "Leo", "Messi", 33, "PSG"));
        players.add(new Player(4, "Marco", "Reus", 29, "BVB"));
        players.add(new Player(5, "Kylian", "Mbappé", 26, "PSG"));
        players.add(new Player(6, "Cristiano", "Ronaldo", 35, "Al-Nassr"));
        players.add(new Player(7, "Karim", "Benzema", 33, "ReaL Madryt"));
        players.add(new Player(8, "Luka", "Modric", 35, "ReaL Madryt"));
    }

    public List<Player> getAllPlayers() {
        return this.players;
    }

    public Player getPlayerById(int id) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() == id)
                return players.get(i);
        }
        return null;
    }
}
