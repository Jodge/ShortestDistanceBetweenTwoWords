package com.test.example;

import java.util.HashSet;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.ShortestDistance;

public class ShortestDistanceTest {
    
    
    @Test
    public void testShortestDistanceCalculator() {
        ShortestDistance shortestDistance = new ShortestDistance();
        
        Set<String> dict = new HashSet<>();
        dict.add("poon");
        dict.add("plee");
        dict.add("same");
        dict.add("poie");
        dict.add("plea");
        dict.add("plie");
        dict.add("poin");
        
        // assert distance from toon to plea is 6
        Assert.assertEquals(6, shortestDistance.shortestChainLen("toon", "plea", dict));
    }

}
