import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {

    private ArrayList<ArrayList<Integer>> graph;
    private int users;

    public Graph(int users) {
        this.users = users;
        int i;

        graph = new ArrayList<ArrayList<Integer>>();

        for(i = 0; i < users; i++) {
            graph.add(new ArrayList<Integer>());
        }
    }

    public ArrayList<ArrayList<Integer>> getGraph() {
        return graph;
    }

    public void addConnection(int user1, int user2) {
        graph.get(user1).add(user2);
        graph.get(user2).add(user1);
    }

    public void displayFriendList(int user) {
        int i;
        int n = (graph.get(user)).size();
        int tempFriend;
        boolean userFound = true;

        // Displays error if user is not found
        if(user < 0 || user > users-1) {
            System.out.println("ERROR: User does not exist.");
            userFound = false;
        }

        // Displays user's list of friends if user exists
        if(n > 0 && userFound) {
            System.out.println("Person " + user + " has " + n + " friends!");
            System.out.print("List of friends: ");
            for(i = 0; i < n; i++) {
                tempFriend = (graph.get(user)).get(i);
                System.out.print(tempFriend + " ");
            }
            System.out.println("\n");
        }
        
    }

    public void displayConnections(ArrayList<ArrayList<Integer>> graph, int userSrc, int userDst, int nodes) {
        int prevConnections[] = new int[nodes];
        int distConnections[] = new int[nodes];

        if(shortestPath(graph, userSrc, userDst, nodes, prevConnections, distConnections) == false) {
            System.out.println("Cannot find connection between " + userSrc + " and " + userDst);
            return;
        }

        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = userDst;
        path.add(crawl);

        while(prevConnections[crawl] != -1) {
            path.add(prevConnections[crawl]);
            crawl = prevConnections[crawl];
        }
        
        System.out.println("There is a connection from " + userSrc + " to " + userDst + "!");
        for(int i = path.size() - 1; i >= 0; i--) {
            if(i > 0)
                System.out.println(path.get(i) + " is friends with " + path.get(i-1));
        }
        System.out.println();

    }

    public boolean shortestPath(ArrayList<ArrayList<Integer>> graph, int userSrc, int userDst, int nodes, int prevConnections[], int distConnections[]) {
        LinkedList<Integer> queue = new LinkedList<Integer>();

        boolean visited[] = new boolean[nodes];

        for(int i = 0; i < nodes; i++) {
            visited[i] = false;
            distConnections[i] = Integer.MAX_VALUE;
            prevConnections[i] = -1;
        }

        visited[userSrc] = true;
        distConnections[userSrc] = 0;
        queue.add(userSrc);

        boolean returnFlag = false;

        while(!queue.isEmpty()) {
            int u = queue.remove();
            for(int i = 0; i < graph.get(u).size() && returnFlag == false; i++) {
                if(visited[graph.get(u).get(i)] == false) {
                    visited[graph.get(u).get(i)] = true;
                    distConnections[graph.get(u).get(i)] = distConnections[u] + 1;
                    prevConnections[graph.get(u).get(i)] = u;
                    queue.add(graph.get(u).get(i));

                    if(graph.get(u).get(i) == userDst)
                        returnFlag = true;
                }
            }
        }
        if(returnFlag)
            return true;
        else
            return false;
    }
}

