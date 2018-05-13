/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj4;

/************************************************************/
/* Alex Schoonover                                          */
/* Login ID: scho9419                                       */
/* CS-102, Winter 2018                                      */
/* Programming Assignment 4                                 */
/* TreeNode<T> Class: For use of binary trees               */
/************************************************************/
public class TreeNode {
    private Person datum; //holds the content within the node
    private TreeNode left; //left node
    private TreeNode right; //right node
    
    public TreeNode(Person datum, TreeNode left, TreeNode right){
        this.datum = datum; //initializes datum
        this.left = left;  //initializes left node
        this.right = right; //initializs right node
    }
    public TreeNode(){
        datum = null; 
    }
    
    //these are getters and setters - they return or set the variables
    public Person getDatum(){return datum;} 
    public TreeNode getLeft(){return left;}
    public TreeNode getRight(){return right;}
    public void setLeft(TreeNode left){this.left = left;}
    public void setRight(TreeNode right){this.right = right;}
    public void setDatum (Person datum){this.datum = datum;}
    //end of getters and setters
} //end of TreeNode<T> class


