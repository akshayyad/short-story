// --== CS400 File Header Information ==--
// Name: Ananya Heroor
// Email: heroor@wisc.edu
// Team: LE
// Role: Front End Developer 1
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: Based on Data Wrangler 1 and Back End Developer 2 classes

import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TimeMachineUser { 
//exit status of the program (true if the user wants to exit from the time machine)
 private static boolean exitStatus = false;

 //scan the variable to scan for user inputs
 private static Scanner scan = new Scanner(System.in);

 // Input variable for user-control
 private static String input;

 // Time Machine database
 private static TimeMachine timemachine = new TimeMachine();
 

 private static String[] commands 
   = new String[] {"Add", "Search", "shortestPath", "Delete", "Reset", "Save", "Help", "Exit"};

 /** 
  * This method will print out a welcome message for the user
  */
 private static void welcome() {
     System.out.println("\n***** Welcome to the Time Travel Tourist Agency *****\n");   
     System.out.println("Enter \"Help\" for a list of possible commands \n");
   }

   /**
    * This method prints out a warning if the user inputs an invalid command.
    */
   private static void invalid() {
     System.out.println("\nInvalid command, enter \"Help\" for a list of possible commands.\n");
   }

   /**
    * Main method to load and set the time machine
    * @param args
    * @throws FileNotFoundException 
    */
   public static void main(String[] args) throws FileNotFoundException {
     welcome(); //prints the welcome message
     InitialGraph ig = new InitialGraph(); //create a graph
     ig.readFile("input.txt"); //read the Data Wrangler's file
     TimeMachine tm = new TimeMachine(); //time machine
     tm.graph = ig.graph; //time machine in graph
     
     while(!exitStatus) { //while we are in the program
       System.out.print("[User]: "); //prompt the user to type in the command line
       input = scan.nextLine();
       commands(input);
     }

     // Closes the scanner
     scan.close();
   }
   
   /** 
    * This determines which command to use
    */
   private static void commands(String input) {
       //command line
       String command = input.trim().toLowerCase();
       int index = -1;
       //Determines which command is being input
       for (int i = 0; i < commands.length; i++) {
           if (command.equalsIgnoreCase(commands[i].trim())) {
             index = i;
           }
       }
       if (index == -1) 
         invalid();
       else if (index == 0)
           add();
       else if (index == 1)
           search();
       else if (index == 2)
           shortestPath();
       else if (index == 3)
           delete();
       else if (index == 4)
           reset();
       else if (index == 5)
           save();
       else if (index == 6)
           help();   
       else if (index == 7)
           exit();
     }

   /** 
    * When this add option is chosen, a new location to travel to will be added
    */
   private static void add() {
     // to enter a location
     String year = "";
     // to enter energy
     String energy = "";
     // to enter the description (event) of a time period
     String description= "";
     System.out.print("\nEnter year: ");
     input = scan.nextLine();
     year = input;
     System.out.print("\nEnter energy: ");
     input = scan.nextLine();
     energy = input;
     System.out.print("\nEnter description: ");
     input = scan.nextLine();
     description = input;
     try {
         if (timemachine.insert(new TimePeriod(Integer.parseInt(year), Integer.parseInt(energy), description))) {
           System.out.println("\nEvent successfully added to Time Travel Tourist Agency!\n");
         } else {
         System.out.println("\nInvalid! Try again: \n");

         }
       } catch (Exception e) {
         System.out.println("\nThis event already exists!\n");
       }
      
     }
   
   /** 
    * This method searches for a specific location (year)
    */
   private static void search() {
     String year = "";
     while (year.equals("")) {
       System.out.print("\nEnter year to be searched: ");
       input = scan.nextLine();
       year = input;
     }
     
     int i =0;
     for(i=0; i< timemachine.catalogue.size(); i++) {
       if(timemachine.catalogue.get(i).getYear() == Integer.parseInt(year)) {
         int energy = timemachine.catalogue.get(i).getEnergy();
         String description= timemachine.catalogue.get(i).getDescription();
         
         System.out.println("\nYear: " + year + "\nEnergy: " + energy + "\nEvent: " + description);
         return;
       }
     }
     //Didn't find it.
     System.out.println("\nThis year does not exist!\n");
   }
   /**
    * This is for when the user wants to find the shortest path from one year to another year
    * @param String startYear
    * @param endYear
    * throws NoSuchElementException
    */
   private static void shortestPath(){
     String startYear = "";
     String endYear = "";
     while (startYear.equals("") && endYear.contentEquals("")) {
       System.out.print("\nEnter startYear: ");
       input = scan.nextLine();
       startYear = input;
       System.out.print("\nEnter endYear: ");
       input = scan.nextLine();
       endYear = input;
     }
     try {
       timemachine.shortestPath(Integer.parseInt(startYear), Integer.parseInt(endYear)); 
       System.out.println("\nThe shortest path from " + startYear + " to " + endYear + " is " + timemachine.shortestPath(Integer.parseInt(startYear), Integer.parseInt(endYear)));
     } catch (NoSuchElementException e) {
       System.out.println("One or both of these years do not exist in the Time Travel Tourist Agency");
     }
   }
   
   private static void delete() {
     int year = 0;
     while (year == 0) {
       System.out.print("\nEnter timePeriod year: ");
       String input = scan.nextLine();
       year = Integer.parseInt(input);
     }
     try {
     TimePeriod timePeriod = timemachine.find(year);
     timemachine.delete(timePeriod);
       System.out.println("The Time Travel Tourist Agency successfully deleted the timePeriod: " + timePeriod);
     } catch (NoSuchElementException e) {
       System.out.println("Unable to delete an invalid year");
     }
   }
   
   /** 
    * This method clears the time machine
    */
   private static void reset() {
       timemachine.clear();
       System.out.println("\nSuccessfully cleared Time Travel Tourist Agency's Time Machine!\n");
     }
   
   private static void save() {
     try {

       System.out.println();

       timemachine.write("input.txt");

       System.out.println("\nThe Time Travel Tourist Agency Time Machine was successfully saved to file!\n");

      } catch (Exception e) {
       System.out.println("\nFailed to save!\n");
     }
   }
   
   /** 
    * To show the list of commands, use the help command
    */
   private static void help() {
     System.out.println("\nCommands:");
     System.out.println("****************");
     System.out.println("*  Add         *\n*  Search      *\n*  shortestPath*\n*  Delete      *\n"
                         + "*  Reset       *\n*  Save        *\n*  Help        *\n*  Exit        *\n");
     System.out.println("****************\n");
   }
   
   /** 
    * When the user inputs this command, they will exit from the program
    */
   private static void exit() {
     System.out.println("\nYou have exited the Time Travel Tourist Agency!\n");
     exitStatus = true;
   }
}