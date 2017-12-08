package edu.kit.informatik;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {
	
    static ArrayList<String> strings;
    static ArrayList<Tile> list;
    
    public static void main(String[] args) throws FileNotFoundException {

		PrintWriter out = new PrintWriter("testfitsTo.txt");

		strings = new ArrayList<>();
		list = new ArrayList<>();
		
		permutation("RRGGYY");
		permutation("RRGG--");
		permutation("RRYY--");
		permutation("GGYY--");
		permutation("RR----");
		permutation("GG----");
		permutation("YY----");
		permutation("------");
		
		// list is some List of Strings
		Set<String> s = new LinkedHashSet<>(strings);
		strings.clear();
		strings.addAll(s);
		
		for(int i = 0; i < strings.size(); i++) {
		    generateTile(strings.get(i));
		}
		
		// add elements to al, including duplicates
		
		// ====================== TESTING
		
		for(int i = 0; i < list.size(); i++) {
		    for(int k = 0; k < list.size(); k++) {
		    
    		    Tile tile = list.get(i);
    		    Tile tile2 = list.get(k);

    		    for(int h = 0; h < 6; h++) {
    		        if(tile.fitsTo(tile2, h))
    		            out.println((tile + " fitsTo " + tile2 + " at " + h));
    		    }
    		    
    		    //if(tile.isRotationEqualTo(tile2))
    		      //  out.println((tile + " rotation " + tile2 + " | " + tile.isRotationEqualTo(tile2)));
    		    
    		    // Test empty
    		    //if(tile.isEmpty())
                    //out.println((tile + " is Empty | " + tile));
		    }
		}
		
		out.close();
		System.out.println("finished");
	}
	
	public static void permutation(String str) { 
	    permutation("", str); 
	}

	private static void permutation(String prefix, String str) {
	    int n = str.length();
	    if (n == 0) strings.add(prefix);
	    else {
	        for (int i = 0; i < n; i++)
	            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
	    }
	}
	
	public static void generateTile(String str) {
	    
	    LineType[] lines = new LineType[6];
	    
	    for(int i = 0; i < 6; i++) {
	        
	        if(str.charAt(i) == LineType.RED.getAbbreviation())
	            lines[i] = LineType.RED;
	            
            if(str.charAt(i) == LineType.GREEN.getAbbreviation())
                lines[i] = LineType.GREEN;
                
            if(str.charAt(i) == LineType.YELLOW.getAbbreviation())
                lines[i] = LineType.YELLOW;
            
            if(str.charAt(i) == LineType.NONE.getAbbreviation())
                lines[i] = LineType.NONE;
	        
	    }
	    
	    list.add(new Tile(lines));
	}
}
