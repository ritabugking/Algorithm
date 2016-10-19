/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package percolationstats;
 
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 *
 * @author yijuwang
 */
public class PercolationStats {
    
   private double [] allThreshold;

   public PercolationStats(int n, int trials) {
       
       if (n <= 0 || trials <= 0) throw new IllegalArgumentException("out of bounds");
       
       Percolation pc = new Percolation(n*n);
       allThreshold = new double [trials];
       
       for (int i = 0; i < trials; i++) {
       
            int totalOpenSite = 0; 
            double threshold = 0;
            
            while (!pc.percolate) {

                 int randomI = (int) StdRandom.uniform() * n + 1;
                 int randomJ = (int) StdRandom.uniform() * n + 1;

                 if (!pc.isOpen(randomI, randomJ)) {

                     pc.open(randomI, randomJ);     
                     totalOpenSite++;
                 }
            }
            
            threshold = (double) totalOpenSite/(n*n);
            allThreshold[i] = threshold;
            
            for (int a = 0; a < n; a++)
                for (int b = 0; b < n; b++)
                    pc.grid[a][b] = false;
            
            pc.percolate = false;
            pc.uf = new WeightedQuickUnionUF(n*n+2);
            pc.uf2 = new WeightedQuickUnionUF(n*n+1);
       }
   }    // perform trials independent experiments on an n-by-n grid
   
   public double mean()      {
       return StdStats.mean(allThreshold);
   }                    // sample mean of percolation threshold
   
   public double stddev()  {
       return StdStats.stddev(allThreshold);
   }                      // sample standard deviation of percolation threshold
   
   public double confidenceLo()   {
       return mean()-1.96*stddev()/Math.sqrt(allThreshold.length);
   }               // low  endpoint of 95% confidence interval
   
   public double confidenceHi()   {
       return mean()+1.96*stddev()/Math.sqrt(allThreshold.length);
   }               // high endpoint of 95% confidence interval
    
   
   public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(200, 100);
        System.out.println(ps.mean());
        System.out.println(ps.stddev());
        System.out.println(ps.confidenceLo());
        System.out.println(ps.confidenceHi());
       
       
    }
   
        
    

}