package application;

import enums.Position;
import utils.In;

import java.util.InputMismatchException;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class Team {
    private final String localName;
    private final String teamName;
    private Manager manager;
    private final Players allPlayers;
    private final Player[] currentTeam;
    public static int REQUIRED_TEAM_SIZE;

    public Team(String localName, String teamName, Manager manager, Players allPlayers) {
        this.localName = localName;
        this.teamName = teamName;
        this.manager = manager;
        this.allPlayers = allPlayers;
        this.currentTeam = new Player[REQUIRED_TEAM_SIZE];
    }

    public String getTeamName(){
        return teamName;
    }

    public String getLocalName() {
        return this.localName;
    }

    public Players getAllPlayers() {
        return this.allPlayers;
    }

    public Manager getManager() {
        return this.manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
    
    public void use(League league) {
        System.out.println("Welcome to the team management panel for " + getLocalName() + ", please select an option below.\n" + "D. View team details\n" + "V. View all players\n" + "S. Sign new player\n" + "U. Unsign player\n" + "F. Fill current team\n" + "X. Exit");
        
        
        while (true){
            System.out.print("Choice (D/V/S/U/F/X): ");
            String a = In.nextLine().trim();
            if (a.length() == 0){
                System.out.println("Invalid input. Try again.");
                continue;
            }

            if (a.equalsIgnoreCase("D")){
                System.out.println("-----------------------"); 
                System.out.println(getLocalName() + " " + getTeamName());
                System.out.println("-----------------------"); 
                System.out.println("Total signed players: " + getAllPlayers().getPlayers().size());
                
                if (getManager() == null){
                    System.out.println("Managed by None");
                }

                else{
                    System.out.println("Managed by " + getManager().getFirstName() + " " + getManager().getLastName());
                }


                int f = 0;

                for (int i = 0; i < currentTeam.length; i++){
                    if (currentTeam[i] != null){
                        f++;
                    }
                }
                


                if (currentTeam.length > 0 && f == currentTeam.length){
                    System.out.println("Status: Ready to field.");

                }

                else{
                    System.out.println("Status: Not ready to field");
                }

            }


            else if (a.equalsIgnoreCase("V")){
                 List<Player> p = getAllPlayers().getPlayers();
            
                 if (p.size() == 0){
                        System.out.println("There is no players that are currently signed");
                }

            else{
                System.out.println("All signed players in " + getLocalName() + " " + getTeamName() + ":");
                for (int i = 0; i < p.size(); i++){
                        System.out.println(p.get(i));
                
                }   

            }
    
            }

            else if (a.equalsIgnoreCase("S")){

                System.out.println("Signing a new player to " + getLocalName());
                System.out.print("Enter player's full name: ");
                String n = In.nextLine().trim();
                

                List<Player> p = league.getPlayers().getPlayers();

                int m = 0;
                Player c = null;

                for (int i = 0; i < p.size(); i++){
                    Player u = p.get(i);
                    if (u.getFullName().equals(n)){
                        m++;
                        c = u;

                    }
                }

                if (m == 0){
                     System.out.println("Player " + n + " not found.");
                }

                else if (c.getTeam() != null){
                    System.out.println(c.getFullName() + " is already signed to " + c.getTeam().getLocalName() + " " + c.getTeam().getTeamName());
                    
                }

                else{
                    c.setTeam(this);
                    List<Player> ps = getAllPlayers().getPlayers();
                    ps.add(c);
                    System.out.println(c.getFullName() + " " + "has been signed.");
                    
                }

               

        } 

        else if (a.equalsIgnoreCase("U")){
            List<Player> p = getAllPlayers().getPlayers();

            System.out.println("Unsigning a player from " + getLocalName());
            System.out.print("Enter player's full name: ");
            String n = In.nextLine();

            Player m = null;

            for (int i = 0; i < p.size(); i++){
                if (p.get(i).getFullName().equals(n)){
                    m = p.get(i);
                    break;

                }
            }



            if(m == null){
                System.out.println("Player " + n + " not found in the team.");
                continue;
            }

            
                        
            int k = 0;
                
            for (int j = 0; j < currentTeam.length; j++){
                if (currentTeam[j] == m){
                    k++;
                    break;
                }

                        
            }

            if (k > 0){
                 System.out.println("Player " + m.getFullName() + " is currently in the active playing team.");
                 continue;
            }


            else{
                 p.remove(m);
                 m.setTeam(null);
                 System.out.println(m.getFullName() + " has been unsigned.");

            }        
           
            
                

                
        }


           



        

        else if (a.equalsIgnoreCase("F")){
            

            List<Player> r = getAllPlayers().getPlayers();
             System.out.println("Filling " + getLocalName() + "s active team..");
             System.out.println("-----------------------"); 

        while (true){         
            
            System.out.println("Current active team:");
            System.out.println("-----------------------"); 


            for (int i = 0; i < currentTeam.length; i++){
                if (currentTeam[i] != null){
                    System.out.println((i + 1) + ". " + currentTeam[i].getFullName());
                }

                else if (currentTeam[i] == null){
                    System.out.println((i + 1) + ". ");
                        
                }

        }

            System.out.println("-----------------------"); 
            
            
       

        
            int n;
            while (true){
                 System.out.print("Position to alter: ");
                 String c = In.nextLine();


                 try{
                    n = Integer.parseInt(c);
                 }
                 
                 catch(NumberFormatException e){
                    System.out.println("Invalid input. Try again.");
                    System.out.println("-----------------------"); 

                    System.out.println("Current active team:");
                    System.out.println("-----------------------"); 
                    

                    for (int i = 0; i < currentTeam.length; i++){
                        if (currentTeam[i] != null){
                            System.out.println((i + 1) + ". " + currentTeam[i].getFullName());
                        }

                        else if (currentTeam[i] == null){
                            System.out.println((i + 1) + ". ");
                            
                        }

                    
                }

                    System.out.println("-----------------------"); 
                    continue;
                    
                 }


                 if (n < 1 || n > currentTeam.length){
                    System.out.println("No such position in this team.");
                    System.out.println("-----------------------");

                    System.out.println("Current active team:");
                    System.out.println("-----------------------"); 


                    for (int i = 0; i < currentTeam.length; i++){
                        if (currentTeam[i] != null){
                            System.out.println((i + 1) + ". " + currentTeam[i].getFullName());
                        }

                        else if (currentTeam[i] == null){
                            System.out.println((i + 1) + ". ");
                            
                        }

                        
                    }

                    System.out.println("-----------------------"); 
                    continue;
                }

                break;
            

                

            }

           

            if (currentTeam[n - 1] != null){
                System.out.println("Removed " + currentTeam[n - 1] + " from active team.");
                currentTeam[n - 1] = null;


            }

            else{
                System.out.print("Enter the name of player to add: ");
                String k = In.nextLine();

                Player p = null;

                for (int i = 0; i < r.size(); i++){
                    Player g = r.get(i);
                    if (g.getFullName().equals(k)){
                        p = g;
                        break;
                    }

                }

               

                if (p == null){
                    System.out.println("Player " +  k + " not found.");
                    System.out.println("-----------------------"); 
                  

                }

                else{
                    int m = 0;

                    for (int i = 0; i < currentTeam.length; i++){
                        if (currentTeam[i] == p){
                            m++;
                            break;
                        }
                    }

                    if (m > 0){
                        System.out.println(k + " is already active in the team");
                        System.out.println("-----------------------"); 
                    }

                    else {
                        currentTeam[n - 1] = p;
                        System.out.println(k + " has been added to the active team.");
                        

                    }
                }

                

            }

            System.out.println("-----------------------"); 
            System.out.println("Current active team:");
            System.out.println("-----------------------"); 
            

            for (int i = 0; i < currentTeam.length; i++){
                if (currentTeam[i] != null){
                    System.out.println((i + 1) + ". " + currentTeam[i].getFullName());
                }

                else if (currentTeam[i] == null){
                    System.out.println((i + 1) + ". ");
                }
            }

            System.out.println("-----------------------"); 

            System.out.print("Confirm team? (y/n): ");

            String con = In.nextLine();
            

            if(con.equals("y")){

                int e = 0;

                for (int i = 0; i < currentTeam.length; i++){
                    if (currentTeam[i] == null){
                        e = 1;
                        break;
                    }
                }

                if (e > 0){
                    System.out.println("Not all positions are filled. Team cannot field!");
                }

                break;
            
            
        
            }

             else if (con.equals("n")){
                System.out.println("-----------------------"); 
                continue;
            }

            else{
                System.out.println("Invalid input. Try again.");
                System.out.println("-----------------------"); 
                continue;
            }



           

            

            }

           

            
            
        }

        else if (a.equalsIgnoreCase("X")){
            break;

            
        }
       
    }
    }
}