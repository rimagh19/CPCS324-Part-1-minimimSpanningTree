/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PhoneNetworkApp;

import GraphFramework.Vertex;

/**
 *
 * @author hadee
 */
public class Office extends Vertex{
    
     public Office(String label) {
         super(label);
       
    }
     public Office( ) {
       
       
    }
     public void displayInfo() {
        //System.out.println("label=" + label);
        for (int i = 0; i < adjList.size(); i++) {
            System.out.println("Source Vertex= "+adjList.get(i).source.label+" Target Verex= " 
                    + adjList.get(i).target.label+" Weight= "+adjList.get(i).weight);
        }
    }
}
