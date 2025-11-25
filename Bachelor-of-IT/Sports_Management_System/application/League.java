package application;

import exception.*;
import utils.*;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class League {
    private final Teams teams;
    private final List<Manager> managers;
    private final Players players;

    public League(Teams seededTeams, Players seededPlayers, List<Manager> seededManagers) {
        this.teams = seededTeams;
        this.players = seededPlayers;
        this.managers = seededManagers;
    }

    
    public Teams getTeams(){
        return teams;
    }

    public Players getPlayers(){
        return players;
    }


    public void use() {
        System.out.println("Welcome to the Prog2 Manager Panel");
        
        while (true){
            int a;
        
            try{
                 System.out.print("Manager Id: ");
                 a = In.nextInt();


            }


            catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
                In.nextLine();
                continue;
            }

           


            Manager c;

            try{
                c = validateManager(a);
            }

            catch (UnauthorisedAccessException e) {
                System.out.print("Invalid credentials. Would you like to retry? (y/n): ");
                 String r = In.nextLine().trim().toLowerCase();
                
                 if (r.equals("y")){
                    continue;

                 }

                 else if (r.equals("n")){
                    return;
                 }

                 else{
                    return;
                 }

                
            }

            

            c.use(this);

           

            

        }
        


    }

    

    private Manager validateManager(int id) throws UnauthorisedAccessException {
        for (Manager manager : this.managers) {
            if (manager.hasId(id)) {
                return manager;
            }
        }
        throw new UnauthorisedAccessException();
    }

    //Do not alter the main method
    public static void main(String[] args) {
        Team.REQUIRED_TEAM_SIZE = Integer.parseInt(args[0]);
        SeedData seededData = new SeedData();
        seededData.seed();
        new League(seededData.teams(), seededData.players(), seededData.managers()).use();
    }
}