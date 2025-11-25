package application;

import enums.Position;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class Player {
    private final String firstName;
    private final String lastName;
    private Team team;
    private final Position position;

    public Player(String firstName, String lastName, Team team, Position position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
        this.position = position;
    }

    public Team getTeam() {
        return this.team;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + position + ")";
    }
}