import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class FileSystem {
    public static void main(String[] args) throws IOException  {
        
        Scanner sc = new Scanner(System.in);
        // Choose a file to read from
        
        System.out.print("Input file path: ");
        String filePath = sc.nextLine();
        
        try {
                File file = new File(filePath);
                Scanner scanner = new Scanner(file);

                int nUsers;
                int nRelationships;

    
                // READING FILE START
                BufferedReader readFile;
        
                readFile = new BufferedReader(new FileReader(filePath));
                String line;
                
                // Reads first line
                line = readFile.readLine();

                // Splits the line into an array of Strings (nUsers, nRelationships)
                String[] fileAttributes = line.split(" ");

                nUsers = Integer.parseInt(fileAttributes[0]);
                nRelationships = Integer.parseInt(fileAttributes[1]);
                
                // System.out.println("This file has " + nUsers + " nodes and " + nRelationships + " relationships.");

                // Initialize Graph
                Graph graph = new Graph(nUsers);

                String[] userRelationship;
                int user1;
                int user2;

                // Reads remaining lines
                while((line = readFile.readLine()) != null) {

                    // Splits the line into an array of Strings
                    userRelationship = line.split(" ");

                    user1 = Integer.parseInt(userRelationship[0]);
                    user2 = Integer.parseInt(userRelationship[1]);

                    graph.addConnection(user1, user2);
                }
                System.out.println("Graph loaded!");
                readFile.close();
                // READING FILE END
                
                // [0] MAIN MENU
                boolean stopProgram = false;
                int choice;

                while(stopProgram == false) {

                    // Display Main Menu
                    System.out.println("MAIN MENU");
                    System.out.println("[1] Get friend list");
                    System.out.println("[2] Get connection");
                    System.out.println("[3] Exit\n");

                    choice = 0;
                    System.out.print("Enter your choice: ");
                    while(choice < 1 || choice > 3) {
                        choice = sc.nextInt();
                        if(choice < 1 || choice > 3)
                        System.out.print("ERROR! Enter valid choice: ");
                    }
                    
                    switch(choice) {
                        case 1 : // [1] GET FRIEND LIST
                            System.out.print("Enter ID of person: ");
                            int user = -1;
                            while(user < 0 || user > graph.getGraph().size() -1) {
                                user = sc.nextInt();
                            }
                            System.out.println();
                            graph.displayFriendList(user);
                            break;

                        case 2 : // [2] GET CONNECTION
                            System.out.print("\nEnter ID of first person: ");
                            int userSrc = -1;
                            while(userSrc < 0 || userSrc > graph.getGraph().size() -1) {
                                userSrc = sc.nextInt();
                            }

                            System.out.print("Enter ID of second person: ");
                            int userDst = -1;
                            while(userDst < 0 || userDst > graph.getGraph().size() -1) {
                                userDst = sc.nextInt();
                            }
            
                            graph.displayConnections(graph.getGraph(), userSrc, userDst, nUsers);

                            break;

                        case 3 : // [3] EXIT
                            stopProgram = true;

                            break;
                    }
                }

                scanner.close();
            } catch (FileNotFoundException e) {
                System.err.println("ERROR! " + filePath + " does not exist.");
            }

    }
}
