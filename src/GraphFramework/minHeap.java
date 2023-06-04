/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphFramework;

import java.security.spec.KeySpec;
import java.util.HashMap;

/**
 *
 * @author hadee
 */
public class minHeap {
   
    public heapNode[] Heap;
    public int size;
    public int maxsize;

    public minHeap(int maxsize) {
        // This keyword refers to current object itself
        this.maxsize = maxsize;
        this.size = 1;
        Heap = new heapNode[this.maxsize + 1];
        
        
    }
//==========================================================================================================
    
    public void insert(Vertex v, int k, Vertex parent) {
        //update the key and the heap 
        //insert the Vertex in the heap and update the heap
        
        Heap[size]= new heapNode(v, k, parent);
        int i = size;
        if (size != maxsize) {
            size++;
        }
        
        heapify_up(i);
    }
//==========================================================================================================
    
    public void updateKey(int k, int i) {
        //update the key value and update the heap 
        Heap[i].key=k;
        heapify_up(i);

    }
//==========================================================================================================
    
    public int VertexIndex(Vertex v) {
        //find the index of the vertex in the min heap
        for (int i = 1; i <= size ; i++) {
            if (Heap[i].vertex == v) {
                return i;
            }
        }
        return -1;
    }
//==========================================================================================================
   
    public void heapify_up(int i) {
       // Modify the min heap from down to the top 
        int j = 1;
        heapNode temp = null;
        while (i > 1) {
            j = (int) Math.floor(i / 2);
            if (Heap[i].key < Heap[j].key) {
                temp = Heap[i];
                Heap[i] = Heap[j];
                Heap[j] = temp;
                i = j;
            } else {
                break;
            }
        }
    }
//==========================================================================================================
    
    public heapNode extract_min() {
        // return the first node(minmumm key) 
        heapNode ret = Heap[1];
        Heap[1] = Heap[size];
        size--;
        if (size >= 1) {
            heapify_down(1);
        }
        return ret;
    }
//==========================================================================================================
    
    public void heapify_down(int i) {
        //Modify the min heap from top to down
        int j;
        heapNode temp;
        while (2 * i <= size) {
           if (2 * i == size || Heap[2 * i].key <= Heap[2 * i + 1].key) {
                j = 2 * i;
            } else {
                j = 2 * i + 1;
            }
            if (Heap[j].key < Heap[i].key) {
                temp = Heap[i];
                Heap[i] = Heap[j];
                Heap[j] = temp;
                i = j;
            } else {
                break;
            }
        }
    }
//===========================================================================================================
    
    public boolean isisEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }


}
