/*
 * KruskalAlg class
 * called by  phoneNetworkApp class
 * CPCS324 Project - Part#1 - Team#37
 */

package GraphFramework;

import PhoneNetworkApp.*;
import java.util.HashSet;
//import javax.xml.transform.sourceVertex;
import java.util.*;

// to upper case Xxxxxxxxxxxx
public class KruskalAlg extends MSTAlgorithm {

    // create cost int variable set to zero
    private int cost = 0;
    // initial MST
    Edge MSTresultList[];
    // Indexes to track the positions of src and trgt
    int srcI;
    int tarI;
    // List of Sets for vericies
    public List<Set<String>> quickFindDS;

    /**
     * KruskalAlg Constructor
     *
     * @param graph
     * @param takes BluePrintGraph object creates an empty result list with size
     *              of graph`s number of edges
     */
    public KruskalAlg(BluePrintGraph graph) {
        this.MSTresultList = new Edge[graph.verticesNo];
    }

    /**
     * findMST(BluePrintGraph graph) takes BluePrintGraph object creates minimum
     * spanning tree and stores it in the MSTresultList
     *
     * @param graph
     * @return void
     */
    public void findMST(BluePrintGraph graph) {
        Vertex sourceVertex; // Vertex sourceVertex
        Vertex targVertex; // Vertex target
        Edge edge; // Vertex edge
        Edge[] edges = new Edge[graph.edgeNo]; // PriorityQueue to store edges weights
        int edgesCounter = 0; // edgesCounter for edges array

        // ========================================================
        // STEP#1: Store All Edges of Graph in Increasing Order
        // ========================================================
        // Iterate the vertices of the graph
        for (int i = 0; i < graph.verticesNo; i++) {
            sourceVertex = graph.vertices.get(i);
            // For each vertex:
            // Loop through adjacent list of this vertex
            for (int j = 0; j < sourceVertex.adjList.size(); j++) {
                // Check if maxcimum num,ber of edges exceeded
                if (edgesCounter == edges.length) {
                    break;
                }
                // For every Edge:
                // add every adjlist of every vertex to the PriorityQueue
                edges[edgesCounter] = (sourceVertex.adjList.get(j));
                // inceremebnt the edgesCounter of edges
                edgesCounter++;
            } // end Edges j loop
        } // end of vertices i loop
        Arrays.sort(edges);

        // ========================================================
        // STEP#2: Create Indevidual Set for Each Vertex
        // ========================================================
        // Inirialaize List of sets
        quickFindDS = new ArrayList<Set<String>>();
        // make Endivudal sets and store them in the quickFindDS List using the
        // makeSet() method
        makeSet(graph);
        int MSTcounter = 0; // initialize MSTcounter integer edgesCounter with with zero to iterate
                            // MSTresultList
        edgesCounter = 0; // reset edges edgesCounter to 0

        // ========================================================
        // STEP#3: Walk through edges of the graph increasingly
        // ========================================================
        while (MSTcounter < MSTresultList.length - 1) {
            if (MSTcounter == graph.edgeNo || edgesCounter == graph.edgeNo) {
                break;
            }
            // STEP#3.1 Get Minimum-weight Edge & its sourceVertex & target
            edge = edges[edgesCounter]; // get edge from edges array
            edgesCounter++; // increment edgesCounter
            sourceVertex = edge.source;
            targVertex = edge.target;

            // STEP#3.2 CheckquickFindDSquickFindDS weather the target - sourceverticies are
            // aleady in a set (cyclic)
            if (!findSet(sourceVertex.label, targVertex.label)) {
                // STEP#3.3 If not Union the two sets of the two verticies
                quickFindDS.get(tarI).addAll(quickFindDS.get(srcI)); // add all labels of src set to trgt set
                quickFindDS.get(srcI).clear(); // clear src set
                MSTresultList[MSTcounter] = edge; // Add the target edge to the MST list
                cost += MSTresultList[MSTcounter].weight; // Get cost of minimum-weight edges (MST)
                MSTcounter++; // increment number of edges MSTcountered

            } // End of if-statement
        } // MST is ready

    } // End of findMST()

    // ========================================================
    // Functions Section
    // ========================================================
    /**
     * makeSet()
     *
     * @param BluePrintGraph graph
     * @return void
     */
    public void makeSet(BluePrintGraph graph) {
        for (int i = 0; i < graph.verticesNo; i++) { // list of sets
            Set<String> set = new HashSet<String>();
            set.add(graph.vertices.get(i).label);
            quickFindDS.add(set);
            // .add(graph.vertices.get(i).label);
        }
    } // End of makeSet Method

    /**
     * findSet()
     *
     * @param sourceVertex
     * @param targetVertex
     * @return boolean (true if in the same set, false if in different sets)
     */
    public boolean findSet(String sourceVertex, String targetVertex) {
        for (int i = 0; i < quickFindDS.size(); i++) {
            Set<String> set = new HashSet<String>();
            set = (Set) quickFindDS.get(i);
            if (set.contains(sourceVertex)) {
                srcI = i;
            }
            if (set.contains(targetVertex)) {
                tarI = i;
            }
            if (set.contains(sourceVertex) && set.contains(targetVertex)) {
                return true;
            }
        }

        return false;
    } // End of FindSet Method

    /**
     * displayResultingMST()
     * 
     * @param none
     * @return void
     */
    public void displayResultingMST() {

        System.out.println("\"The phone network (minimum spanning tree) Kruskal algorithm is as follows:\"");
        for (int i = 0; i < MSTresultList.length - 1; i++) {
            if (MSTresultList[i] != null) {
                Office sourceVertexOffice = (Office) MSTresultList[i].source;
                Office targetOffice = (Office) MSTresultList[i].target;

                Line e = (Line) MSTresultList[i];
                System.out.print("Office No. ");
                System.out.print(sourceVertexOffice.label);
                System.out.print(" - ");
                System.out.print("Office No. ");
                System.out.print(targetOffice.label);
                System.out.print(": line length:");
                System.out.println(e.weight);
            }

        }
        System.out.println("...");
        System.out.println("The cost of designed phone network: " + this.cost);
    }

    /**
     * displayMSTcost() display the calculated cost
     */
} // End of Class
