/*
 * COSC 600                        Project 4
 * James Rehak                     11-11-14
 * Program Name: Line editor
 * Set up a linked list of strings; Each line is a node;
 * 
 * Check for '$' char to check for commands; Use switch of 
 * if-else to sort thru commands, call methods;
 * Set up each command as method; Error check input;
 * Use loops to run thru list in each method un end or
 * parameters reached;
 */

/*
 * This class contains the node stucture
*/
 public class node {
    String data;
    node next;
    
    public node(String lineValue){ //node constructor
        data = lineValue;
        next = null;
    }
    public node(String x, node n){ //specific point node constructor
        data = x;
        next = n;
    }

 }
