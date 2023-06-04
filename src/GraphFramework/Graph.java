
package GraphFramework;

import PhoneNetworkApp.Line;
import PhoneNetworkApp.Office;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Random;
import java.util.Set;

public class Graph {

    public ArrayList<Vertex> vertices;
    public int verticesNo;
    public int edgeNo;
    boolean isDigraph;
    

    public Graph() {
    }

    public Graph(int verticesNo, int edgeNo) throws Exception {
        this.verticesNo = verticesNo;
        this.edgeNo = edgeNo;
        makeGraph(verticesNo, edgeNo);
    }

    /*
     * makeGraph(): void
     * generates a random graph with a specified number of vertices and edges
     */
    public void makeGraph(int verticesNo, int edgeNo) throws Exception {
        File newGraph = new File("./newGraph.txt");
        PrintWriter write = new PrintWriter(newGraph);
        isDigraph = false;
        this.verticesNo = verticesNo;

        this.edgeNo = (edgeNo) * 2;

        vertices = new ArrayList<>();

        Random random = new Random();

        // Generate vertices -> up to 26^4 combination of vertices
        for (int i = 0; i < verticesNo; i++) {
            int firstCharIndex = i % 26;
            int secondCharIndex = (i / 26) % 26;
            int thirdCharIndex = (i / (26 * 26)) % 26;
            int fourthCharIndex = (i / (26 * 26 * 26)) % 26;

            char firstChar = (char) ('a' + firstCharIndex);
            char secondChar = (char) ('a' + secondCharIndex);
            char thirdChar = (char) ('a' + thirdCharIndex);
            char fourthChar = (char) ('a' + fourthCharIndex);

            String label = String.valueOf(firstChar) + String.valueOf(secondChar) + String.valueOf(thirdChar) + String.valueOf(fourthChar);
            //System.out.println(label);


            // Create a new Office object with the generated label and add it to the graph
            Office vertex = createVertex(label);
            vertices.add(vertex);

        }

        // Create a variable to access verticesArray
        int idx = vertices.size() - 1;

        // Initialize a flag to track if all vertices have been connected
        boolean connected = false;

        // Select the first source vertex staring from the last element in the array and decrement the idx
        Office sourceVertex = (Office) vertices.get(idx);
        idx--;
        // Generate edges and connect vertices
        for (int i = 0; i < edgeNo - 1; i++) {

            Office targetVertex = (Office) vertices.get(idx);

            //weight of each edge is a random integer between 1 and 20
            int weight = 1 + random.nextInt(20);

            // Add the edge to the graph connecting the target and source vertices with the generated weight
            addEdge(sourceVertex, targetVertex, weight);
            //System.out.println(sourceVertex.label + " -> " + targetVertex.label + " weight: " + weight);
            
             write.write("src  "+sourceVertex.label + "  trg  "+targetVertex.label+"  wht  "+weight +"\n");
             
            // Update the source vertex for the next iteration as the target vertex for the previous iterartion, to make sure graph is connected
            sourceVertex = targetVertex;
            idx--;

            //Check if all vertices have been connected or if the idx has reached its limit
            if (connected || idx < 0) {
                // Reset the idx  to select a new source vertex randomly that doesnt point to itself
                idx = random.nextInt(vertices.size());
                while(vertices.get(idx) == sourceVertex){
                    idx = random.nextInt(vertices.size());
                }
                connected = true;
            }

        }
        write.flush();
        // connect the first with the last vertex
        int weight = 1 + random.nextInt(20);
        addEdge(vertices.get(vertices.size() - 1), vertices.get(0), weight);

        
    }

    public void readGraphFromFile(String fileName) throws Exception {
        // Create a new file
        File graphFile = new File("./graphFile.txt");

        // Create a scanner to read the file
        Scanner graphFileReader = new Scanner(graphFile);

        // if "digraph 1" then isDigraph= true, directed
        // if "digraph 0" then isDigraph= false, undirected
        String throw_digraph = graphFileReader.next();
        isDigraph = graphFileReader.next().equalsIgnoreCase("1");

        // read #verticies & #edges respectively
        verticesNo = graphFileReader.nextInt();
        edgeNo = graphFileReader.nextInt();

        // initialize vertices arraylist with the given #verticies
        vertices = new ArrayList<>();
        if (!isDigraph) {
            edgeNo *= 2;
        }
        // start reading thegraph file
        System.out.println("Orignal Graph:");
        while (graphFileReader.hasNextLine()) {
            // From each line read: Src Vertex, Trgt Vertex, weight
            String sourceVerexLebel = graphFileReader.next();
            String targetVerexLebel = graphFileReader.next();
            int weight = graphFileReader.nextInt();

            Office sourceVertex = addVertex(sourceVerexLebel);
            Office targetVertex = addVertex(targetVerexLebel);

            addEdge(sourceVertex, targetVertex, weight);
         
 
        }

        System.out.println("---------------------------------------------------");
    }

    /*
     * createEdge(): Edge
     * Create an edge that connects the source v to the target u with an edge of
     * weight w
     */
    public Line createEdge(Vertex v, Vertex u, int w) {
        Line newEdge = new Line(v, u, w);
        return newEdge;
    }

    /*
     * createVertex(): Vertex
     * Create a vertex with a label
     */
    public Office createVertex(String label) {
        Office newVertex = new Office(label);
        return newVertex;
    }

    /*
     * addEdge(): Vertex
     * adds Edge e to Vertex v adjList
     */
    public void addEdge(Vertex v, Vertex u, int w) {
        Line sourceLine = createEdge(v, u, w);
        v.adjList.add(sourceLine);

        if (!isDigraph) {
            Line targetLine = createEdge(u, v, w);
            u.adjList.add(targetLine);
        }

    }

    public Office addVertex(String vlabel) {
        // Create Src Vertex
        Office V = createVertex(vlabel);
        // If vertex does not exist >> ad to verticies list

        // Create Trgt Vertex
        //Office targetVertex = createVertex(targetVerexLebel);
        // If vertex does not exist >> ad to verticies list
        //Vertex x = new Vertex("dlkfde");
        //vertices.add((Office) x);
        boolean VertexExists = true;
        boolean sourceVertexExists = true;

        if (vertices.size() == 0) {
            vertices.add(V);
        }

        for (int i = 0; i < vertices.size(); i++) {
            if ((V.label).equalsIgnoreCase(vertices.get(i).label)) {
                V = (Office) vertices.get(i);
                VertexExists = false;
                break;
            }
        }
        if (VertexExists) {
            vertices.add(V);
        }

        return V;
    }

 
 

}
