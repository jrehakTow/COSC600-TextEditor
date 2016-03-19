package cosc600prj4;

import static java.lang.Math.abs;
import java.util.Scanner;

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
 * This class contains the commands and linkedlist manipulation methods
*/
public class LinkedList {
    
    private node head;
    private int line_count; //keep track of lines
   
    public int current_line = 0;
    
    public LinkedList(){
        head = new node(null); //Starts empty list
        line_count = 0;
    }
    
    public int command_atomizer(String line, char cmd){ //this breaks down the input into commands
        int kill_switch = 0;
        if(cmd !='$'){
            System.out.println("Error! not a command!");
        }
        else if(cmd == '$'){ //checks for a command
            int m = 0;
            int n = 0;
            String find = " ";
            
            line = line.replace(",", " "); //replaces ","
            String[] words = line.split(" "); //break string apart
            
            String command = words[0];
            if(command.equals("$search") ){ //create search parameters
                find = line.replace("$search ", "");
            }
            else if(words.length == 3){
                String num_m = words[1]; //pull numbers out of string
                String num_n = words[2];
                    
                try{ 
                    m = Integer.parseInt(num_m); //convert string to integers
                    n = Integer.parseInt(num_n);
                }catch(NumberFormatException e){ //catch non number inputs
                    System.out.println("Error, parameter not a number!");
                    command = "error"; //cause error state in operations to restart loop
                }
            }
            else if(words.length == 2){
                String num_m = words[1];
                try{
                    m = Integer.parseInt(num_m);
                }catch(NumberFormatException e){
                    System.out.println("Error, parameter not a number!");
                    command = "error";
                }
            }
            //shows commands in action
                
            kill_switch = operations(cmd, command, m, n, find, line); //function to process cleaned commands
        }
        return kill_switch; //signal to kill program
    }
    
    public int operations(char cmd, String command, int m, int n, String find, String line){
        if(command.equals("$insert")){
            System.out.print("("+ showRightLineNo() +")"+"Enter line or command: ");
            do{
                
                Scanner read_line = new Scanner(System.in);
                line = read_line.nextLine(); //stores a line
                cmd = line.charAt(0); //takes first variable of line
                if(cmd != '$'){
                    
                    insert(line, current_line);
                    System.out.print("("+ showRightLineNo() +")"+"Enter line or command: ");
                }   
            }while(cmd != '$');
            command_atomizer(line, cmd); //catches commands entered during the insert
            return 0;
        }
        else if(command.equals("$delete")){
           
           if(m > n || m == 0 || n == 0){
               System.out.println("Delete Error! Selected lines out of bounds");
           }
           else if(outofbounds(m,n) == true);
           else {
               System.out.println("Deleting lines: "+m+" thru "+ n );
               delete(m, n);
           }
           
           return 0;
        }
        else if(command.equals("$print")){
            if(outofbounds(m,n));
            else if(m>n){
                System.out.println("Error! First parameter must be smaller than second parameter!");
            }
            else if(m > 0 || n >0){
                System.out.println("Print lines: "+m + " thru "+n);
                print(m,n);
            }
            else{
                printall();
            }
            return 0;
        }
        else if(command.equals("$line")){
            if(outofbounds(m,n)== true);
            else{
                current_line = m;
                System.out.println("You are at line: "+ current_line);
                int lower = m-3;
                int upper = m+3;
                print(lower,upper);//prints nodes around selected node
            }
            return 0;
        }
        else if(command.equals("$search")){
            search(find);
            return 0;
        }
        else if(command.equals("$help")){
        	help();
        }
        else if(command.equals("$done")){
            //kill program
            return 1;
        }
        else
            System.out.println("Command Error, try again!");
            return 0;
    }
    
    public void help(){
    	System.out.println("Commands: \n$insert\n$line\ndelete\n$print\n$search\n$help\n$done");
    }
     
    public void insert(String readData, int setLine){
        node p = new node(readData);
        node q = head;
        
        if(setLine == 0){ //if current line not set 
            while(q.next != null){ //run thru list to end
            q = q.next; //move to next node
        }
            q.next = p; //points to next node
            line_count++; //increment line count list
        }
        
        else{
            int x = 0;
            
            int j = 1; //counter to keep track of current line
            
            for(int i=1; i<setLine && q.next != null; i++){ //
                q = q.next;
                j++;
            }
            j = j + 1; //increment for current line holder

            p.next = q.next; //new node is set to next node's reference
            q.next = p; //points to next node
            line_count++; //increment line count list
            
            current_line = j; //update current line //problem is here

        }
        
    }
    
    public boolean outofbounds(int m, int n){
        if( m > list_size() || n > list_size()){
            System.out.println("Parameters out of bounds!");
            return true;
        }
        else return false;
    }
       
    public int list_size(){
        return line_count;
    }
    
    public int currentLine(){
        return current_line;
    }
    
    public int showRightLineNo(){
        if(current_line == 0){
            return list_size() + 1;
        }
        else return current_line;
    }
  
    public void print(int m, int n){ //show line at specified list
        
        if(m <= 0){
            m = 1;
        }
        node q = head.next;
        for(int i=1; i<m && q.next != null; i++){
            q=q.next; //move to m postion
        }
       
        for(int j = m; j<=n && q.next !=null; j++){ //print until n reached
            System.out.println("("+ j + "): " + q.data);
            q = q.next;
        }
        
    }
    
    public void printall(){
        int i = 1;
        node q = head.next;
        while(q != null){
            
            System.out.println("("+ i + "): " + q.data);
            i++;
            q = q.next; //move q to next node
        }
    }
    
    public void delete(int m, int n){ //remove line at specifided location
       
        node q = head.next;
        if(m == 1){ //for deleting first node
            q = head;
            q.next = q.next;
        }
        else{
            for(int i=1; i<(m-1); i++){ //move q to node
                q=q.next;   
            }
        }

        node p = head.next; 
        for(int i=1; i<n; i++){ //move p to node at n
            p=p.next;
        }
        q.next = p.next; //delinke nodes in middle
        
        line_count = abs(line_count- (n-m+1)); //deincrement listcount
    }
    
    public void search(String find){
        node q = head.next;
        int i = 0;
        String searchedFor ="";
        while(q != null && searchedFor.contains(find) != true){
            searchedFor = q.data;
            i++;
            q = q.next;
        }
        if(searchedFor.contains(find)== true){
            System.out.println("Found at line ("+ i + "): " + searchedFor);
        }
        else System.out.println("Could not find: " + find);
    }
}
