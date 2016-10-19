/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author yijuwang
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {
    
   private Node first, last;
   private int n;
   
   public Deque() {
       first = null;
       last = null;
       n = 0;
   }                           // construct an empty deque
   
   private class Node {
       private Item item;
       private Node next;
       private Node pre;
   }
           
   public boolean isEmpty() {
       return first == null;
   }                // is the deque empty?
   public int size() {
       return n;
   }                       // return the number of items on the deque
   public void addFirst(Item item) {
       
       if (item == null) throw new NullPointerException("item is null");
       n++;
       Node oldfirst = first;
       first = new Node();
       first.item = item;
       if (oldfirst == null) {
           last = first;
       }
       else {
            first.next = oldfirst;
            oldfirst.pre = first;
       }
   }         // add the item to the front
   public void addLast(Item item) {
   
       if (item == null) throw new NullPointerException("item is null");
       n++;
       Node oldlast = last;
       last = new Node();
       last.item = item;
       
       if (isEmpty())
           first = last;
       else {
           oldlast.next = last;
           last.pre = oldlast;
       }
   
   }          // add the item to the end
   public Item removeFirst() {
   
       if (isEmpty()) throw new NoSuchElementException("No item can be poped out");
       n--;
       Item item = first.item;
       first = first.next;
       if (isEmpty()) {
           last = null;
       }
       else 
           first.pre = null;
       return item;
   }               // remove and return the item from the front
   public Item removeLast() {
   
       if (isEmpty()) throw new NoSuchElementException("No item can be poped out");
       n--;
       Item item = last.item;
       if (first == last) {
       
           first = null;
           last = null;
       }
       else {
           last = last.pre;
           last.next = null;
           
       }
       
       return item;
       
   }                // remove and return the item from the end
 
   
   @Override
   public Iterator<Item> iterator() {
   
       return new DequeIterator();
       
   }        // return an iterator over items in order from front to end
   private class DequeIterator implements Iterator<Item> {
   
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
       
   }
   
   
   
   public static void main(String[] args) {
   
   }   // unit testing
}

