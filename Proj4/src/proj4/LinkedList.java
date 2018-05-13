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
/* LinkedList Class                        */
/*******************************************/
import java.time.LocalDate;
public class LinkedList 
{// Begin LinkedList class
   public Node head;
  
   public LinkedList() 
   {
      head = null; 
   }
   
   public Node getFirst(){
       return head; 
   }
   
   public boolean isEmpty() 
   {    
       if(head == null)
           return true;
       else
           return false;  
   }
   
   public int size() 
   {// begin size()
      Node current = head; 
      int count = 0; 
      while(current != null) 
      {
         count++;
         current = current.getNext();
      }
      return count; 
   }// end size()
      
   public void add(Node item)
   {// begin add()
      Node current = head; 
      Node previous = null; 
      if(current == null){//if the list is empty
         head = item; //make head   
         return; 
      }
      while(current != null)
       {
           if(item.getDatum().getDate().isAfter(current.getDatum().getDate())){
               if(previous == null){
                   item.setNext(current);
                   head = item; 
                   return; 
               }
               else {
                   item.setNext(current);
                   previous.setNext(item);
                   return; 
                   
               }
  
           }
           else{
               previous = current; 
               current = current.getNext(); 
           }   
       }//end while
      if(previous != null)
        previous.setNext(item);
      
   }//end add()
public void printDonations(){
    Node current = head; 
    while(current != null){
        System.out.print(current.getDatum().getDate() + "\n");
        current = current.getNext(); 
    }
}
   
   
}//end LinkedList class
