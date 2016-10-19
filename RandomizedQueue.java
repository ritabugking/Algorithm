/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomizedqueue;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author yijuwang
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
   
    private Node first, last;
    private int size;
    
    public RandomizedQueue() {
   
        first = null;
        last = null;
        size = 0;
   
   }                // construct an empty randomized queue
    private class Node {
        
        private Item item;
        private Node next;

    }
   public boolean isEmpty() {
       
       return first == null;
   }                // is the queue empty?
   public int size() {
   
       return size;
   }                 // return the number of items on the queue
   public void enqueue(Item item) {
       if (item == null) throw new NullPointerException("item is null");
       Node oldlast = last;
       last = new Node();
       last.item = item;
       if (isEmpty())
           first = last;
       else
           oldlast.next = last;
       
       size++;
       
       
   }          // add the item
   public Item dequeue() {
   
       if (size == 0) throw new NoSuchElementException("out of bounds");
       Item item;
       if (size == 1) {
           item = first.item;
           first = null;
           last = null;
           size--;
           return item;
       }
       Node current = first;
       int random = (int) StdRandom.uniform() * size + 1;
       if (random == 1) {
       
           item = first.item;
           first = first.next;
           size--;
           return item;
       }
       
       for (int i = 0; i < random-2; i++) {
       
           current = current.next;
           
       }
       item = current.next.item;
       current.next = current.next.next;
       
       size--;
       return item;
       
   }                   // remove and return a random item
   public Item sample() {
   
       if (size == 0) throw new NoSuchElementException("no item in queue");
       Item item;
       Node current = first;
       int random = (int) StdRandom.uniform() * size + 1;
       if (random == 1) {
           item = first.item;
           return item;
       }
       for (int i = 0; i < random-2; i++) {
           current = current.next;
       }
       item = current.next.item;
       return item;
   }                    // return (but do not remove) a random item
   public Iterator<Item> iterator() {
   
       return new RandomizedQueueIterator();
       
   }        // return an iterator over items in order from front to end
   private class RandomizedQueueIterator implements Iterator<Item> {
   
       private Node current = first;
       
       @Override
       public boolean hasNext() {
           return current != null;
       }
       
       @Override
       public Item next() {
       
           if (current == null) {
               throw new NoSuchElementException("No item can be poped out");
           }
           Item item = current.item;
           current = current.next;
           
           return item;
       }
       @Override
       public void remove() {
           throw new UnsupportedOperationException("UnsupportedOperationException");
       }
       
   }        // return an independent iterator over items in random order
   public static void main(String[] args) { }  // unit testing
}
