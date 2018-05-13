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
/* Programming Assignment 2                */
/* LinkedList Class                        */
/*******************************************/
import java.time.LocalDate;
import java.io.BufferedWriter; 
import java.io.FileWriter; 
import java.io.*; 

public class Tree 
{// Begin LinkedList class
   public TreeNode root;
   public Tree() {root = null;} 
   
   public boolean search(Person target){
       TreeNode current = root; 
       while(current!=null){
           if(current.getDatum().getPersonID().equals(target.getPersonID()))
           {return true;}
           if(current.getDatum().getLastName().compareTo(target.getLastName()) < 0)
               current = current.getRight(); 
           else
               current = current.getLeft(); 
           
       }
       return false; 
   }//end search 
   
   public boolean isEmpty() 
   { 
      return root == null; 
   }
   public int size(){
       return size(root);
   }
   private int size(TreeNode current) 
   {// begin size()
      int count = 0;
      if(current==null){
          return count;
      }
      count = 1; 
      count += size(current.getLeft());
      count += size(current.getRight()); 
      
      return count; 
   }// end size()
   public void searchBloodType(String bloodType){
       searchBloodType(bloodType, root); 
   }
   private void searchBloodType(String bloodType, TreeNode current){
       if(current == null) return;
       searchBloodType(bloodType, current.getLeft());
       if (current.getDatum().getBloodType().equals(bloodType))//if bloodtypes match 
         System.out.println(current.getDatum()); //prints matches
       searchBloodType(bloodType, current.getRight()); 
   }
   
   public Person searchID(String ID){
       return searchID(ID, root); 
   }
   
   private Person searchID(String ID, TreeNode current){
       if(current == null) return null; 
       if(current.getDatum().getPersonID().equals(ID)){ //if the IDs match
            System.out.print(current.getDatum()); //prints the person
            return current.getDatum(); 
       }
       
       Person ret = searchID(ID, current.getLeft());
       return ret == null ? searchID(ID, current.getRight()): ret;
   }
   
   public void print(){
       print(root);
   }
   private void print(TreeNode current){
       if(current == null) return; //if list is empty, leave
       print(current.getLeft()); //gets the left treenode
       System.out.println(current.getDatum());//prints the person's information
       if(current.getDatum().getDonationList().isEmpty() == false){ //if donation list is not empty
            Donation test = current.getDatum().getDonationList().getFirst().getDatum(); //get first in list
            System.out.println("Last donation date: " + test.getDate()); //prints last donation date
            if(test.getDate() != LocalDate.MIN) //checks for valid date
               {//start if
                  if(current.getDatum().eligible(test)) //if eligible to donate
                  System.out.println("Eligible: yes."); //prints yes
                  else //if not eligible to donate
                  System.out.println("Eligible: no.");//prints no
                  System.out.println(""); //prints a space
               }
            else //error check
                  System.out.println("Never donated before.\n"); //prints never donated
       }// end if
            else//if never donated
            {
               System.out.println("Last donation date: never"); //last donation date
               System.out.println("Eligible: yes."); //prints eligiblity
               System.out.println("");//prints space
            }
       print(current.getRight()); //gets right treenode
   }
   public void add(Person target)
   {// begin add()
       TreeNode current = root; //current is the root
       TreeNode prev = null; //previous is null
       while(current != null){ //while not at the end of the tree
           prev = current; //previous equals the current
           if(current.getDatum().getLastName().compareTo(target.getLastName()) == 0){ //if last names are the same
               if(current.getDatum().getFirstName().compareTo(target.getFirstName()) < 0){ //checks first names
                   current = current.getRight(); //goes right if comparison is less than 0
               }
               else{ 
                   current = current.getLeft();}     //goes left if comparison returns greater than 0
           }  
           if(current.getDatum().getLastName().compareTo(target.getLastName()) < 0) //checks last names
               current = current.getRight(); //if less than 0 goes to the right of the list
           else 
               current = current.getLeft();//if greater than 0, goes to the left of the list
       }
       TreeNode leaf = new TreeNode();  //makes new treenode
       leaf.setLeft(null);//makes left null
       leaf.setRight(null); //makes right null
       if(prev==null){ //if previous is null
           root = leaf; //root is the leaf
           leaf.setDatum(target); //sets leaf's datum
           return; //leaves method
       }
       else if(prev.getDatum().getLastName().compareTo(target.getLastName())==0){ //if last names are same, check first
           if(prev.getDatum().getFirstName().compareTo(target.getFirstName())<0) //checks if first name comes first
                prev.setRight(leaf); //sets it right
           else
                prev.setLeft(leaf); //sets it left
       }  
       if(prev.getDatum().getLastName().compareTo(target.getLastName())<0){ //if last name is before
           prev.setRight(leaf); //sets previous right as leaf
           leaf.setDatum(target); //sets datum
           return; //leaves method
        }
       else{
           prev.setLeft(leaf); //sets previous left as leaf
           leaf.setDatum(target); //sets datum
           return; //leaves method
       }
       
   }//end add()
   // getID() - Donation class //getPersonID() Person class
   public void addDonation(Node item){
      if(!addDonation(item, root)){ //adds donations
          System.out.println("Could not find donor with that ID.");//if it wasn't able to set donation
      }
          
      
   }
   private boolean addDonation(Node item, TreeNode current) 
   {//begin addDonation
      boolean found = false; //sets boolean as false
      TreeNode previous = null; //previous is null
      LinkedList list = null;  //linked list is null
      Donation spliceItem = (Donation) item.getDatum(); //spliceItem is donation item to be added
      if(current == null){ //if list is empty
          return false; //returns false
      }//end if
      if(current.getDatum().getPersonID().compareTo(spliceItem.getID()) == 0){ //if IDs match
          list = current.getDatum().getDonationList(); //gets donation list
          list.add(item); //adds item
          return true; //returns true
       }
      else {
          if(!found) //if donor not found
                found = addDonation(item, current.getLeft()); //check left
          if(!found) //if donor not found
                found = addDonation(item, current.getRight()); //check right
      }
      
      return found; //returns true or false

   } //end addDonation
   
   public void saveBloodBank(String fileName1, String fileName2){
       FileWriter donOut = null; 
       FileWriter donaOut = null; 
       BufferedWriter donorsOut = null; 
       BufferedWriter donationsOut = null; 
       
       try{
           //initializing FileWriter objects
           donOut = new FileWriter(fileName1); 
           donaOut = new FileWriter(fileName2); 
           
           //initializes BufferedWriter onjects
           donorsOut = new BufferedWriter(donOut); 
           donationsOut = new BufferedWriter(donaOut); 
           
           saveBloodBank(donorsOut, donationsOut, root); 
           
           //closes the file out streams 
           donorsOut.close(); 
           donationsOut.close(); 
       }
       catch(IOException exp) {
           System.out.println("Error trying to save database");
           return; 
       }  
   }
   
   private void saveBloodBank(BufferedWriter donorsOut, BufferedWriter donationsOut, TreeNode current) throws IOException{
       if (current == null) return; 
       
       donorsOut.write(current.getDatum().toSave());
       donorsOut.newLine(); 
       donorsOut.flush();
       
       Node curDonation = current.getDatum().getDonationList().getFirst(); 
       
       while(curDonation != null){
           donationsOut.write(curDonation.getDatum().toSave());
           donationsOut.newLine();
           donationsOut.flush(); 
           
           curDonation = curDonation.getNext(); 
       }
       
       saveBloodBank(donorsOut, donationsOut, current.getLeft()); 
       saveBloodBank(donorsOut, donationsOut, current.getRight()); 
   }
   
   public void removeAll() 
   {
      root = null; 
   }
 
}//end LinkedList class
