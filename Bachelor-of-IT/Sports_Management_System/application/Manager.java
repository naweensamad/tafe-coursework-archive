package application;

import utils.In;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class Manager {
    private final int id;
    private final String firstName;
    private final String lastName;
    private Team team;

    public Manager(int id, String firstName, String lastName, Team team) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
    }

    public boolean hasId(int id) {
        return this.id == id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void use(League league) {
        System.out.println("Welcome to the manager panel " + firstName + ", please select an option below.\n" + "D. View your details\n" + "S. Swap team\n" + "W. Withdraw from team\n" + "M. Manage team\n" + "X. Logout");

        while (true){
            System.out.print("Choice (D/S/W/M/X): ");
            String a = In.nextLine().trim();

            
            
           

            if (a.length() == 0){
                System.out.println("Invalid input. Try again.");
                continue;
            }
            


           
            if (a.equalsIgnoreCase("D")){
                System.out.println("-----------------------"); 
                System.out.println(firstName + " " + lastName);
                System.out.println("-----------------------");
                
                
                if (team != null){
                    System.out.println("Manager for " + team.getLocalName() + " " + team.getTeamName());
                    System.out.println("-----------------------");
                    
                }

                
                
            }

            

            

            

            else if (a.equalsIgnoreCase("S")){
                if (team != null){
                    System.out.println(team.getLocalName() + " will miss you!");
                }
                System.out.println("Please choose one of the teams in need of a manager:");
                
                Teams g = league.getTeams();

                List<Team> c = g.getTeams();

                List<Team> un = new ArrayList<>();

                for (int i = 0; i < c.size(); i++){
                    Team d = c.get(i);
                    if (d.getManager() == null){
                        un.add(d);
                    }


                }

                if (un.size() == 0){
                    System.out.println("There is no unmanaged teams");
                    continue;
                }

                else{

                    for (int i = 0; i < un.size(); i++){
                        Team j = un.get(i);
                        System.out.println((i + 1) + ": " + j.getLocalName());
                       

                    }

                   
                    String se = In.nextLine().trim();
                    int h;

                    
                    
                        try{
                            
                            h = Integer.parseInt(se);
                            if(h < 1 || h > un.size()){
                                System.out.println("No such team.");
                                continue;
                            }

                            

                        }

                        catch (NumberFormatException e) {
                            System.out.println("Invalid input.");
                            continue;
                            
                        }

                       
   
                

                Team k = un.get(h - 1);


                
                
               
                 
                 Team od = team;

                 

                 if(od != null){
                    od.setManager(null);
                   
                  
                    
                 }

                

                k.setManager(this);
                assignTeam(k);

                System.out.println("Congratulations! You are now the manager of " + k.getLocalName() + " " + k.getTeamName());

                continue;

              





                
            }

                
                


        }

        else if (a.equalsIgnoreCase("W")){

            Team ct = team;
            if (team == null){
                System.out.println("You are not currently managing any teams.");
               
            }

            else{


                System.out.println("Are you sure you want to withdraw as the manager of " + ct.getLocalName() + " " + ct.getTeamName() + "?");
                

               while (true){
                System.out.print("Confirm (y/n): ");
                String c = In.nextLine().trim().toLowerCase();

                
                if (c.equals("y")){
                    ct.setManager(null);
                    team = null;
                    System.out.println("Successfully withdrawn.");
                    break;
                    

                }

                else if(c.equals("n")){
                    System.out.println("Action cancelled.");
                    break;
                }

                else{
                    System.out.println("Invalid input. Try again.");
                    
                }

               
               }


            }
        }

        else if (a.equalsIgnoreCase("M")){

           
            if (team == null){
                System.out.println("You are not currently managing any teams.");
                continue;
            }

            else{
                team.use(league);
            }

           
        }

       else if (a.equalsIgnoreCase("X")){
            System.out.println("Thankyou for using the manager panel.");
            break;
       }


       else {
            System.out.println("Invalid input. Try again.");
       }

    }

    }
    

    

    public void assignTeam(Team team) {
        this.team = team;
    }

    
}