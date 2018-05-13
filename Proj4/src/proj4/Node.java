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
/* Node Class                              */
/*******************************************/
import java.time.LocalDate;
public class Node 
{
   Donation datum;
   Node next;
   
   public Node() 
   {
      datum = null;
      next = null; 
   }
   
   public Donation getDatum() 
   {
      return datum;
   }

   public Node getNext() 
   { 
      return next; 
   }
   
   public void setDatum(Donation datum) 
   {
      this.datum = datum;
   }
   
   
   public void setNext(Node next) 
   {
      this.next = next; 
   }
   
}
