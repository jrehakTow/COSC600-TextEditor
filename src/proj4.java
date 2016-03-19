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
 * This class contains the main method
 */
import java.util.Scanner;


public class proj4 {
    
    public static void main(String[] args) {
        
        LinkedList a = new LinkedList();
        String line;
        char cmd;
        int kill =0;
        
        Scanner input = new Scanner(System.in);
        
        System.out.print("Please enter a command: ");
        
        while(kill !=1 ){ //"$done returns 1, breaks loop to end reach end
            line = input.nextLine(); //stores a line
            cmd = line.charAt(0); //takes first variable of line
        
            kill = a.command_atomizer(line, cmd); //function to break down linput
            if(kill != 1){
                System.out.print("("+ a.showRightLineNo() +")"+"Please enter a command: ");
            }
            
        }
        input.close();
        System.out.println("Program ended!");
    }
} //end program
