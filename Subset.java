/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subset;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author yijuwang
 */
public class Subset {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int n;    
        String s;
        n = StdIn.readInt();
        
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        
        while (!StdIn.isEmpty())
            rq.enqueue(StdIn.readString());
        
        for (int i = 0; i < n; i++)
            StdOut.println(rq.dequeue());
        
    }
    
}

