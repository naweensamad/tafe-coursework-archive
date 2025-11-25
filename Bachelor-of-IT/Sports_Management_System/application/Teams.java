package application;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class Teams {
    private final List<Team> teams;

    public Teams(List<Team> teams) {
        this.teams = new ArrayList<Team>(teams);
    }

    public List<Team> getTeams() {
        return teams;
    }

}