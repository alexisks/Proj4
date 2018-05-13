/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj4;
/*******************************************/
/* Alex Schoonover                         */
/* Login ID: scho9419                      */
/* CS-102, Winter 2018                     */
/* Programming Assignment 4                */
/* Main method                             */
/*******************************************/

import java.util.*;
import java.io.*;
import java.lang.*;
import java.time.LocalDate;

public class Proj4
{// begin Proj3 class
   private static File donorList = null; 
   private static Scanner donorScan = null;
   private static File donationList = null; 
   private static Scanner donationScan = null;

   public static void main (String [] args) {//main
      int donationCount = 0; //counts number of donations in donation file. 
      LinkedList object = new LinkedList();
      Database data = new Database(); //creates the database for use of lists   
      LoadFile(args); //gets the text documents from the user
      makePerson(data); //Making a Person
      makeDonations(data); //Start making donation dates and adding them to Donors
      if(data.getTree().size() ==  0) {//checks for blank files
         System.out.println("Blank file found."); //Lets the user know the file is blank
         System.exit(8); //exits the program
      }
      data.getTree().saveBloodBank("cats.txt","dogs.txt"); 
      UserInteraction(data); //calls the interface for the user
   }//end main   

/**************************************************************
method: LoadFile
Purpose: to load the files/arguments from the user
Parameters: String - containing a file name
Returns: file

***************************************************************/
   private static void LoadFile(String[] args){
   
      if (args.length != 2) { //checks to make sure two files are inputed
         System.out.println("You need to pass two arguments."); //Lets the user know they need 2 files
         System.exit(1);//exits program
      }
         
      try { //try
         donorList = new File(args[0]); //makes donorList the first file listed
         donorScan = new Scanner(donorList);//creates scanner for the donor file
      }
      catch (ArrayIndexOutOfBoundsException exc){//checks for arguements
         System.out.println("No arguments given to program."); //Lets the user know
         System.exit(2); //quits program
      }
      catch (FileNotFoundException exc){ //checks for case that a file can't be opened
         System.out.println("File could not be opened.");//lets the user know
         System.exit(3);//exits program
      }
          
      try { //try
         donationList = new File (args[1]);//makes donationList the second file listed
         donationScan = new Scanner(donationList); //creates scanner for the donation file
      }
      catch (ArrayIndexOutOfBoundsException exc) { //checks for arguement
         System.out.println("No arguments given to program."); //Lets user know if there isn't one
         System.exit(4); //exits the program. 
      }
          
      catch (FileNotFoundException exc) { //Checks for an error with opening a file
         System.out.println("File could not be opened.");//lets the user know the error
         System.exit(5); //exits program
      }    
   }//end of LoadFile
   
/**************************************************************
method: makePerson
Purpose: add the Person objects to database from .txt files
Parameters: Database - containing the tree list
Returns: void - makes person objects

***************************************************************/
   public static void makePerson(Database data){
       while (donorScan.hasNext()) {//while2 start

         String line = donorScan.nextLine(); //line = current line of the txt file
         String[] pieces = line.split("/");  //pieces = array of each indivual Person paramenter
                                             //[0]IDnum, [1] Last Name, [2] First name, [3] bloodtype 
         for(int index=0; index < pieces.length; index++){ //for loop is to remove extra characters
            pieces[index] = pieces[index].replaceAll("/"," "); //removes any "/"
            pieces[index] = pieces[index].replaceAll("/n", " ");  //removes any new lines
         }
      
         Person person1 = new Person(pieces[0],pieces[1],pieces[2],pieces[3]); //the person object
        // data.insertDonor(person1);
         
        //TreeNode temp = new TreeNode(); //creates Node that will be added to peson list
         
        //temp.setDatum(person1); //setting the datum as the person just created
        data.getTree().add(person1); //adding the node to the list

      }//while2 end
        
   }
/**************************************************************
method: makeDonations
Purpose: add the Donation objects to database from .txt files
Parameters: Database - containing the tree list
Returns: void - makes donation objects

***************************************************************/
   public static void makeDonations(Database data){
       while (donationScan.hasNext()) {//while3 start
         String line = donationScan.nextLine(); //line = current line of the txt file
         String[] piecesDonation = line.split("/"); //piecesDonation = array of each indivual Donation paramenter
         for(int index=0; index < piecesDonation.length; index++){ //for loop is to remove extra characters
            piecesDonation[index] = piecesDonation[index].replaceAll("/"," "); //removes any "/"
            piecesDonation[index] = piecesDonation[index].replaceAll("/n", " ");  //removes any new lines
         }//creats donation object
         Donation donation1 = new Donation(piecesDonation[0], data.dateFormat(piecesDonation[1])); 
         //data.addDonation(donation1);
         Node temp = new Node(); // new Node to add to the linked list
         temp.setDatum(donation1); //sets the datum for the Node as the donation object
         data.getTree().addDonation(temp); //adds the donation Node
      }//while3 end 
   }
/*********************************************************
method: UserInteraction
Purpose: to run interaction with the user
Paramenters: Database - containing the linked lists
Returns: void

**********************************************************/   
   private static void UserInteraction(Database data){ //start UserInteraction
      final int PRINT_ALL = 1; //constant for menu - printing all donors
      final int SEARCH_DONOR = 2; //constant for menu - searching for a donor by ID
      final int SEARCH_BLOOD_TYPE =3; //constant for menu - searching for a blood type
      final int ADD_NEW_DONOR = 4; //constant for menu - adding a new donor
      final int ADD_NEW_DONATION = 5; //constant for menu - adding a new donation date
      final int ELIGIBLE_DONORS = 6; //constant for menu - checks donors for an eligible blood types
      final int WRITE_TO_TXT = 7; //constant for menu - writes to txt file
      final int RUN_PROGRAM = 8; //constant for menu - run program with new files
      final int EXIT = 9; //constant for menu - exit
      
      LocalDate today = LocalDate.now(); //Gets current date from eternal clock on machine
      String menu = "Current available commands:\n1 --> Print all donors\n2 --> Search for a donor"; //text for menu
      menu += "\n3 --> Search for a blood type \n4 --> Add new doner\n5 --> Add new Donation\n";
      menu += "6 --> Find an Eligible donor\n7 --> Write to .txt file ";
      menu += "\n8 --> Run with new .txt files \n9 --> Exit\nYour choice? ";
        
      System.out.print("Welcome to the CS-102 Blood Donor Database Program. \nToday's date is: "); //text for menu
      System.out.println(today);//prints today's date
      System.out.print(menu); //prints menu option
                          
      
      Scanner choiceScan = new Scanner(System.in);//choice option for user
      int choice = 0;//choice variable
      do{//checks to make sure input is a valid integer
         try{ 
            choice = choiceScan.nextInt(); //checking the integer input from user
         } 
         catch (InputMismatchException exc){
            System.out.println("Only use integers: 1,2,3,4,5,6,7,8 or 9.");//error message
         }
         choiceScan.nextLine(); //clears the scanner
      }
      while(choice < 0 && choice >= 9); //check that the option is between 1 and 9

      while(choice != EXIT){ //while interface //while the input is not 9
          switch (choice) {
          //end 1
              case PRINT_ALL: //if input is 1 - to print
                  data.getTree().print(); //prints the linked list
                  break;
              case SEARCH_DONOR:
                  //if input is 2, search donor
                  System.out.print("Donor ID number: "); //asking the user for donor ID to search
                  Scanner scan2 = new Scanner(System.in); //scanner for user input
                  String testID = scan2.next();//takes user input for ID
                  data.searchID(testID); //finds index of ID
                  //data.printDonations(matchID);//prints the donations using index
                  break;
              case SEARCH_BLOOD_TYPE:
                  //if input is 3, search for blood type
                  System.out.print("Bloodtype: " ); //asks user for bloodtype
                  Scanner scan3 = new Scanner(System.in); //scanner created for input
                  String testBlood = scan3.next(); //puts users input into scanner 
                  data.searchBloodType(testBlood);//prints the searched bloodtypes
                  break;
          //end 4
              case ADD_NEW_DONOR:
                  //if input is 4, add a new donor
                  Scanner scan4 = new Scanner(System.in);//creates new scanner for user input
                  System.out.println("Enter first name: "); //asks for donor's first name
                  String newFirst = scan4.next(); //creates a first name variable
                  System.out.println("Enter last name: "); //asks for donor's last name
                  String newLast = scan4.next(); //creates a last name variable
                  System.out.println("Enter bloodtype: "); //asks for bloodtype
                  String newBlood = scan4.next(); //creates a bloodtype variable
                  System.out.println("Enter ID number: "); //asks for ID number
                  String newID = scan4.next(); //creates an ID variable
                  Person person2 = new Person(newID, newLast, newFirst, newBlood); //uses variables to create person object
                  data.getTree().add(person2);
                  break;
          //end 5
              case ADD_NEW_DONATION:
                  //5
                  Scanner scan5 = new Scanner(System.in); //creates scanner for user input
                  System.out.println("Enter donor's ID: "); //asks for Donor's ID number
                  String newID2 = scan5.next(); //creates ID variable
                  System.out.println("Enter date in this format yyyymmdd: "); //Asks for date 
                  String newDate = scan5.next();//creates date object
                  LocalDate test5; //creates LocalDate oject
                  Donation donation2; //creates donation object
                  try {
                      test5 = data.dateFormat(newDate); //formats the local date object that the user gave
                      donation2 = new Donation(newID2, test5); //creates the donation object
                      Node temp = new Node();
                      temp.setDatum(donation2);
                      data.getTree().addDonation(temp); //adds the donation to the list
                  }
                  catch (InputMismatchException exc){
                      System.out.println("Not a proper date."); //catches if user didn't give date in the proper format
                  }     break;
          //end 6
              case ELIGIBLE_DONORS:
                  //6
                  Scanner scan6 = new Scanner(System.in); //creates scanner for user input
                  System.out.println("Enter bloodType: "); //asks for Donor's ID number
                  String compatible = scan6.next();//creates date object
                  data.compatibleDonors(compatible);//searches for eligible donors
                  break;
              case WRITE_TO_TXT: //7
                  Scanner scan7 = new Scanner(System.in); 
                  System.out.print("Name for text file 1(include the .txt after name: ");
                  String name1 = scan7.next(); 
                  System.out.print("Name for text file 2(include the .txt after name: ");
                  String name2 = scan7.next(); 
                  data.getTree().saveBloodBank(name1,name2); 
                  break; 
              case RUN_PROGRAM: //8
                  System.out.println("Option 8");
                  String[] files = new String[2];
                  Scanner scan8 = new Scanner(System.in); 
                  System.out.print("Name of file 1(include .txt: ");
                  files[0] = scan8.next();
                  System.out.print("Name of file 2(include .txt: ");
                  files[1] = scan8.next(); 
                  data.setTree(new Tree()); 
                  LoadFile(files); //gets the text documents from the user
                  makePerson(data); //Making a Person
                  makeDonations(data); //Start making donation dates and adding them to Donors
                  break; 
              default:
                  System.out.print("Please choose from the current available commands. \n\n");
                  break;
          }
         System.out.println("");//adds space for ecstatic 
         System.out.print(menu);
         do{
            choice = 0;
            try { 
               choice = choiceScan.nextInt();
            } 
            catch (InputMismatchException exc){
               System.out.println("Only use integers: 1,2,3,4,5,6 or 9.");
            }
            choiceScan.nextLine(); 
         }
            while(!(choice > 0) && !(choice <= 9));
      }// while interface end
   }
   
   
}//end Proj3s Class