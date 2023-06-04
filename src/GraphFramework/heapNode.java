/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphFramework;

/**
 *
 * @author hadee
 */
public class heapNode {
    public Vertex vertex;
    public int key;
    public Vertex parent;

    public heapNode(Vertex vertex, int key, Vertex parent) {
        this.vertex = vertex;
        this.key = key;
        this.parent = parent;
    }
    
}
