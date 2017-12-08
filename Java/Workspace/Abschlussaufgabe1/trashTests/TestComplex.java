package main;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import edu.kit.informatik.UserInterface;
import edu.kit.informatik.structures.Util;

public class TestComplex {
    
    private String path = "C:\\Users\\User\\Desktop\\Workspace\\Abschlussaufgabe1\\src\\testComplex\\";
    private String outputPath = "C:\\Users\\User\\Desktop\\Workspace\\Abschlussaufgabe1\\src\\output.txt";
    
    /**
     * Given test case C13
     * @throws IOException a
     */
    @Test
    public void testFindKeywords() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testFindKeywords.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        
        for (int i = 0; i < 13; i++)
            assertTrue(br.readLine().equals("Ok"));
        
        assertTrue(br.readLine().equals("sommerville2015"));
        // empty
        assertTrue(br.readLine().equals("rr2016"));
        assertTrue(br.readLine().equals("mvp2016"));
        // Quit
        assertTrue(br.readLine().equals("Ok"));
        
        br.close();
    }
    
    /**
     * Given test case C14
     * @throws IOException a
     */
    @Test
    public void testJaccard() {
        assertEquals("0.000", Util.calculateJaccard("a;b;c", "d;e"));
        assertEquals("0.666", Util.calculateJaccard("a;b;c;d;e", "b;c;d;e;f"));
    }
    
    /**
     * Given test case C15
     * @throws IOException a
     */
    @Test
    public void testSimilarity() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testSimilarity.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        
        for (int i = 0; i < 13; i++)
            assertTrue(br.readLine().equals("Ok"));
        
        assertTrue(br.readLine().equals("0.500"));
        
        // Quit
        assertTrue(br.readLine().equals("Ok"));
        
        br.close();
    }
    
    /**
     * Given test case C16, wikipedia
     * @throws IOException a
     */
    @Test
    public void testDirectHIndex() throws IOException {
        
        assertEquals(3, Util.calculateHIndex("17;3;1;5"));
        assertEquals(5, Util.calculateHIndex("8;6;8;4;8;6"));
        assertEquals(5, Util.calculateHIndex("10;9;8;7;6;5;4;3;2;1"));
        assertEquals(2, Util.calculateHIndex("100;100;2;2;2;2;2;2;2;2"));
        assertEquals(4, Util.calculateHIndex("100;100;9;8;3;2;2;1;1;0"));
        assertEquals(6, Util.calculateHIndex("6;6;6;6;6")); // My test
        
    }
    
    /**
     * Test case C17
     * @throws IOException a
     */
    @Test
    public void testHIndex() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testHIndex.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        
        for (int i = 0; i < 14; i++)
            assertTrue(br.readLine().equals("Ok"));
        
        assertTrue(br.readLine().equals("1"));
        assertTrue(br.readLine().equals("1"));
        
        // Quit
        assertTrue(br.readLine().equals("Ok"));
        
        br.close();
    }
    
    /**
     * Test case C18
     * @throws IOException a
     */
    @Test
    public void testCoauthorOf() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testCoauthorOf.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        
        for (int i = 0; i < 13; i++)
            assertTrue(br.readLine().equals("Ok"));
        
        assertTrue(br.readLine().equals("Eniola Lowry"));
        assertTrue(br.readLine().equals("Richard Rhinelander"));
        assertTrue(br.readLine().equals("Shashi Afolabi"));
        
        // Quit
        assertTrue(br.readLine().equals("Ok"));
        
        br.close();
    }
    
    /**
     * Test case C19 (NOT UP TO CURRENT STANDARDS)
     * @throws IOException a
     */
    @Deprecated
    public void testForeignCitationOf() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testForeignCitationOf.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        
        for (int i = 0; i < 18; i++)
            assertTrue(br.readLine().equals("Ok"));
        
        assertTrue(br.readLine().equals("p3"));
        
        // Quit
        assertTrue(br.readLine().equals("Ok"));
        
        br.close();
    }
}
