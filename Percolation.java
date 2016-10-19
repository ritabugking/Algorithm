/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

  package Percolation;



 
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/**
 *
 * @author yijuwang
 */
public class Percolation {

    private boolean [][] grid;
    private int row, col;
    private WeightedQuickUnionUF uf, uf2;
    boolean percolate;
    
   public Percolation(int n) {
   
       if (n <= 0) throw new IllegalArgumentException("out of bounds");
       row = n;
       col = n;
       grid = new boolean [row][col];
       percolate = false;
       uf = new WeightedQuickUnionUF(row*col+2); // check if percolation or not
       uf2 = new WeightedQuickUnionUF(row*col+1);  // check if full or not
   }               // create n-by-n grid, with all sites blocked
   
   public void open(int i, int j) {  // after opening a site, union all the sites aside it if it is also an open site
   
       // open a site
       if (i <= 0 || i > row) throw new IndexOutOfBoundsException("row index i out of bounds");
       if (j <= 0 || j > col) throw new IndexOutOfBoundsException("column index j out of bounds");
       
       grid [i-1][j-1] = true;
       
       if (percolate) {
           // if cell is at the first row 
           if (i == 1)
               uf2.union(j, 0);
           // union all the site aside the new open site if they are both open sites
           if (i != 1)   // connect to upper cell
               if (isOpen(i-1, j)) 
                   uf2.union((i-1)*col+j, (i-2)*col+j);
           if (i != row) // connect to lower cell
                 if (isOpen(i+1, j)) 
                   uf2.union((i-1)*col+j, i*col+j);
           if (j != 1)   // connect to left cell
                 if (isOpen(i, j-1)) 
                   uf2.union((i-1)*col+j, (i-1)*col+j-1);
           if (j != col) // connect to right cell
               if (isOpen(i, j+1)) {
                   uf2.union((i-1)*col+j, (i-1)*col+j+1);  
           }
       }
       else {
            // if cell is at the first row 
            if (i == 1)
                uf.union(j, 0);
                uf2.union(j, 0);
            // if cell is at the last row

            if (i == row) {
                uf.union((i-1)*col+j, row*col+1);
            }

            // union all the site aside the new open site if they are both open sites
            if (i != 1)   // connect to upper cell
                if (isOpen(i-1, j)) {
                        uf.union((i-1)*col+j, (i-2)*col+j); // convert 2D array into 1D array and then union two cell together
                        uf2.union((i-1)*col+j, (i-2)*col+j);
                }
            if (i != row) // connect to lower cell
                if (isOpen(i+1, j)) {
                        uf.union((i-1)*col+j, i*col+j);
                        uf2.union((i-1)*col+j, i*col+j);
                }
            if (j != 1)   // connect to left cell
                if (isOpen(i, j-1)) {
                        uf.union((i-1)*col+j, (i-1)*col+j-1);
                        uf2.union((i-1)*col+j, (i-1)*col+j-1);
                }
            if (j != col) // connect to right cell
                if (isOpen(i, j+1)) {
                        uf.union((i-1)*col+j, (i-1)*col+j+1);       
                        uf2.union((i-1)*col+j, (i-1)*col+j+1);  
                }
       }
   }         // open site (row i, column j) if it is not open already
   
   public boolean isOpen(int i, int j)  {
       
       if (i <= 0 || i > row) throw new IndexOutOfBoundsException("row index i out of bounds");
       if (j <= 0 || j > col) throw new IndexOutOfBoundsException("column index j out of bounds");
       
       return (grid [i-1][j-1]);       
   }   // is site (row i, column j) open?
   
   public boolean isFull(int i, int j) {
   
       if (i <= 0 || i > row) throw new IndexOutOfBoundsException("row index i out of bounds");
       if (j <= 0 || j > col) throw new IndexOutOfBoundsException("column index j out of bounds");
       
       if (!isOpen(i, j)) // if this site is not an open site
           return false;
        // if this site connect to any sites in the first row  
       return uf2.connected((i-1)*col+j, 0);  
           
   }    // is site (row i, column j) full?
   
   public boolean percolates() {  // if any sites in the first row connects to any sites in the last row, then it's percolation
   
       if (percolate)
           return percolate;
       
       if (row == 1 && isOpen(1, 1))
           percolate = true;
                           
       if (uf.connected(0, row*col+1))
           percolate = true;
       
       return percolate;  
   
   }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Percolation pc = new Percolation(7);
        System.out.println(pc.uf.find(0));
        pc.open(6, 1);
        System.out.println(pc.isOpen(6, 1));
        System.out.println(!pc.isFull(6, 1));
        System.out.println(!pc.percolates());
        pc.open(7, 1);
        System.out.println(pc.isOpen(7, 1));
        System.out.println(!pc.isFull(7, 1));
        System.out.println(!pc.percolates());
        pc.open(7, 2);
        System.out.println(pc.isOpen(7, 2));
        System.out.println(!pc.isFull(7, 2));
        System.out.println(!pc.percolates());
        pc.open(7, 4);
        System.out.println(pc.isOpen(7, 4));
        System.out.println(!pc.isFull(7, 4));
        System.out.println(!pc.percolates());
        System.out.println(pc.uf.find(0));
        pc.open(1, 1);
        System.out.println(pc.uf.find(0));
        System.out.println(pc.isOpen(1, 1));
        System.out.println(pc.isFull(1, 1));
        System.out.println(!pc.percolates());
        pc.open(1, 5);
        System.out.println(pc.isOpen(1, 5));
        System.out.println(pc.isFull(1, 5));
        System.out.println(!pc.percolates());
        pc.open(2, 5);
        System.out.println(pc.isOpen(2, 5));
        System.out.println(pc.isFull(2, 5));
        System.out.println(!pc.percolates());
        pc.open(3, 5);
        System.out.println(pc.isOpen(3, 5));
        System.out.println(pc.isFull(3, 5));
        System.out.println(!pc.percolates());
        pc.open(4, 5);
        System.out.println(pc.isOpen(4, 5));
        System.out.println(pc.isFull(4, 5));
        System.out.println(!pc.percolates());
        pc.open(5, 5);
        System.out.println(pc.isOpen(5, 5));
        System.out.println(pc.isFull(5, 5));
        System.out.println(!pc.percolates());
        pc.open(6, 5);
        System.out.println(pc.isOpen(6, 5));
        System.out.println(pc.isFull(6, 5));
        System.out.println(!pc.percolates());
        pc.open(7, 5);
        System.out.println(pc.isOpen(7, 5));
        System.out.println(pc.isFull(7, 5));
        System.out.println(pc.percolates());
        
        pc.open(2, 1);
        System.out.println(pc.isOpen(2, 1));
        System.out.println(pc.isFull(2, 1));
        System.out.println(pc.percolates());
        pc.open(4, 1);
        System.out.println(pc.isOpen(4, 1));
        System.out.println(!pc.isFull(4, 1));
        System.out.println(pc.percolates());
        
        System.out.println(pc.uf.find(29));
        System.out.println(pc.uf.find(0));
        
        pc.open(5, 1);
        System.out.println(pc.isOpen(5, 1));
        System.out.println(pc.isFull(5, 1));
        
        System.out.println(pc.uf.find(15));
        System.out.println(pc.uf.find(22));
        System.out.println(pc.uf.find(29));
        System.out.println(pc.uf.find(0));
        
        System.out.println(pc.percolates());
        pc.open(3, 1);
        System.out.println(pc.isOpen(3, 1));
        System.out.println(pc.isFull(3, 1));
        System.out.println(pc.percolates());
        
        
        pc.open(3, 1);
        System.out.println(pc.isOpen(3, 1));
        System.out.println(pc.percolates());
        pc.open(4, 1);
        System.out.println(pc.isOpen(4, 1));
        System.out.println(pc.percolates());
        pc.open(5, 1);
        System.out.println(pc.isOpen(5, 1));
        System.out.println(pc.percolates());
        pc.open(5, 2);
        System.out.println(pc.isOpen(5, 2));
        System.out.println(pc.percolates());
        pc.open(6, 2);
        System.out.println(pc.isOpen(6, 2));
        System.out.println(pc.percolates());
        pc.open(5, 4);
        System.out.println(pc.isOpen(5, 4));
        System.out.println(pc.percolates());

    }
    
}
    