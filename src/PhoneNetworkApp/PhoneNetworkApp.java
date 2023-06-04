/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PhoneNetworkApp;

import GraphFramework.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Hadeel ,Layla, Rima, Zainab
 */
public class PhoneNetworkApp {

    public static BluePrintGraph graph;

    public static void main(String[] args) throws Exception {
        //create scanner object
        Scanner input = new Scanner(System.in);
        // ceate gragh from BluePrintGraph class
        graph = new BluePrintGraph();

        System.out.println("---------------------------------------------------------------------\n"
                + " Test to Compute the Minimum Spanning Tree and Compare Running Time\n"
                + "    For Kruskal's Algorithm & Prim's Algorithm  (min-heap based) \n"
                + "---------------------------------------------------------------------\n");

        System.out.println("Option (1) for The First Requirement: Use readGraphFromFile Function \n"
                + "Option (2) for The Second Requirement: Use makeGraph  Function \n");
        System.out.println("------------------------------------------------------------------------\n");

        System.out.print("Choose your option: ");
        int userOption = input.nextInt();// requirment

        // make sure userOption doesn't go out of range
        while (userOption != 1 && userOption != 2) {
            System.out.println("Wrong Selection. ");
            System.out.print("Select Requirement Option: ");
            userOption = input.nextInt();
        }
        //--------------------------------------------------------------------------------------------------
        //Requirment 1

        if (userOption == 1) {
            System.out.println("Requirment 1");
            graph.readGraphFromFile("file");

            MHPrimAlg primAlg = new MHPrimAlg(graph);
            primAlg.findMST(graph);
            primAlg.displayResultingMST();

            //*************************************************
            System.out.println("\n----------------------------------");

            //*********************************************************************
            KruskalAlg j = new KruskalAlg(graph);
            j.findMST(graph);
            j.displayResultingMST();

        } // end of Requirment 1
        //=================================================================================================================

        if (userOption == 2) {
            System.out.println("Requirment 2");
            System.out.println("");
            System.out.print("Enter The Number of Vertices: ");
            int verticesNum = input.nextInt();
            System.out.print("Enter The Number of Edges: ");
            int edgesNum = input.nextInt();
            System.out.println("");

            graph.makeGraph(verticesNum, edgesNum);
            System.out.println("");

            //System.nanoTime() --> in nano second
            //System.currentTimeMillis() --> in milli seconds
            MHPrimAlg primAlg = new MHPrimAlg(graph);

            long primStartTime = System.currentTimeMillis();
            primAlg.findMST(graph);
            long primEndTime = System.currentTimeMillis();

            primAlg.displayResultingMST();

            System.out.println("Prim Alogrith Execution Time: " + (primEndTime - primStartTime) + " ms \n");

            KruskalAlg kruskalAlg = new KruskalAlg(graph);

            long kruskalStartTime = System.currentTimeMillis();
            kruskalAlg.findMST(graph);// only display method
            long kruskalEndTime = System.currentTimeMillis();

            kruskalAlg.displayResultingMST();

            System.out.println("Kruskal Alogrith Execution Time: " + (kruskalEndTime - kruskalStartTime) + " ms \n");

        }
    }

}
