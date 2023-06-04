/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphFramework;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class MSTAlgorithm {
    public Edge[] MSTresultList;
    Graph graph;
    public MSTAlgorithm() {
    
    }
    
    public void clearArray(){
        for (int i = 0; i < MSTresultList.length; i++) {
            MSTresultList[i]=null;
        }
    }
    
    
    public abstract void displayResultingMST();
    
}
