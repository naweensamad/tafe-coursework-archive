package application;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class Players {
    private final List<Player> players;
    
    public Players(List<Player> players) { this.players = players; }

    public List<Player> getPlayers() {
        return this.players;
    }

    //Lookup pattern
    public Player player(String name) {
        for (Player player : players) {
            if (player.getFullName().equals(name)) {
                return player;
            }
        }
        return null;
    }

}