package GraphFramework;

import java.util.ArrayList;


/**
 *
 * @author hadee
 */
public class Vertex {

    public String label;
    public boolean isVisited;
    public ArrayList<Edge> adjList = new ArrayList<Edge>();

    public Vertex(String label) {
        this.label = label;
    }

    public Vertex() {

    }

    public void displayInfo() {
        //System.out.println("label=" + label);
        for (int i = 0; i < adjList.size(); i++) {
            System.out.println("Source Vertex= " + adjList.get(i).source.label + " Target Verex= "
                    + adjList.get(i).target.label + " Weight= " + adjList.get(i).weight);
        }
    }

}