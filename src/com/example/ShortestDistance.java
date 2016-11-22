package com.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class QItem {
    String word;
    int position;
    
    public QItem(String word, int position) {
        this.word = word;
        this.position = position;
    }
}

public class ShortestDistance {
    
    // To check if strings differ by exactly one character
    // calculate Levenshtein distance
    public boolean isAdjacent(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
         if (costs[b.length()] == 1)
             return true;
         else 
             return false;
    }
    
    // Returns length of shortest chain to reach 'target' from 'start'
    // using minimum number of adjacent moves.  D is dictionary
    public int shortestChainLen(String start, String target, Set<String> dict) {
        
        List<String> list = new ArrayList<String>();
        
        // Create a queue for BFS and insert 'start' as source vertex
        LinkedList<QItem> queue = new LinkedList<>();
        queue.add(new QItem(start, 1));
        list.add(start);
        
        // while queue is not empty
        while(!queue.isEmpty()) {
            QItem curr = queue.remove();
            
            dict.remove(start);
            Iterator<String> iter = dict.iterator();
            while (iter.hasNext()) {
                
                // Process a dictionary word if it is adjacent to current
                // word (or vertex) of BFS
                String temp = iter.next();
                
                if(isAdjacent(curr.word, temp)) {
                    
                    // Add the dictionary word to Q
                    queue.add(new QItem(temp, curr.position + 1));
                    list.add(temp);
                    
                    // Remove from dictionary so that this word is not
                    // processed again.  This is like marking visited
                    iter.remove();
                    
                    // If we reached target
                    if (temp == target) {
                        for (String s: list) 
                            System.out.print(s + "-->");
                            System.out.println();
                        return curr.position;
                    }      
                }
            }       
            
        }
        
        return 0;
    }

}
