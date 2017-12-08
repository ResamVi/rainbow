package main;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import edu.kit.informatik.UserInterface;

public class TestAddChanges {

    private String path = "C:\\Users\\User\\Desktop\\Workspace\\Abschlussaufgabe1\\src\\testAddChanges\\";
    private String outputPath = "C:\\Users\\User\\Desktop\\Workspace\\Abschlussaufgabe1\\src\\output.txt";
    
    /**
     * My test of the environment
     * 
     * @throws IOException a
     */
    @Test
    public void testTestEnvironment() throws IOException {

        // Write input
        BufferedWriter bw = new BufferedWriter(new FileWriter(path + "testEnvironment.txt"));
        bw.write("Test case\n");
        bw.write("quit");
        bw.close();

        // Run program
        UserInterface.main(new String[] {path + "testEnvironment.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        assertTrue(br.readLine().equals("Error, unavailable command."));
        br.close();
    }
    
    /**
     * Given test case C0
     * 
     * @throws IOException a
     */
    @Test
    public void testQuit() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testQuit.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        assertTrue(br.readLine().equals("Ok"));
        br.close();
    }
    
    /**
     * Given test case C1
     * 
     * @throws IOException a
     */
    @Test
    public void testAddAuthor() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testAddAuthor.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        assertTrue(br.readLine().equals("Ok"));
        assertTrue(br.readLine().equals("Ok"));
        assertTrue(br.readLine().equals("Error, author with same name already added."));
        assertTrue(br.readLine().equals("Ok"));
        br.close();
    }
    
    /**
     * Given test case C2
     * 
     * @throws IOException a
     */
    @Test
    public void testAddJournal() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testAddJournal.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        assertTrue(br.readLine().equals("Ok"));
        br.close();
    }
    
    /**
     * Given test case C3
     * 
     * @throws IOException a
     */
    @Test
    public void testAddSeries() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testAddSeries.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        assertTrue(br.readLine().equals("Ok"));
        assertTrue(br.readLine().equals("Ok"));
        br.close();
    }
    
    /**
     * Given test case C4
     * 
     * @throws IOException a
     */
    @Test
    public void testAddConference() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testAddConference.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        assertTrue(br.readLine().equals("Ok"));
        assertTrue(br.readLine().equals("Ok"));
        assertTrue(br.readLine().equals("Error, conference series not found."));
        assertTrue(br.readLine().equals("Ok"));
        br.close();
    }
    
    /**
     * Given test case C5
     * 
     * @throws IOException a
     */
    @Test
    public void testAddPublication() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testAddPublication.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        assertTrue(br.readLine().equals("Ok"));
        assertTrue(br.readLine().equals("Ok"));
        assertTrue(br.readLine().equals("Ok"));
        assertTrue(br.readLine().equals("Ok"));
        assertTrue(br.readLine().equals("Ok"));
        assertTrue(br.readLine().equals("Ok"));
        assertTrue(br.readLine().equals("Ok"));
        br.close();
    }
    
    /**
     * Given test case C8
     * 
     * @throws IOException a
     */
    @Test
    public void testAddKeyword() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testAddKeyword.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        
        // 7 OK
        for (int i = 0; i < 7; i++)
            assertTrue(br.readLine().equals("Ok"));
        
        assertTrue(br.readLine().equals("Error, pub does not exist."));
        
        // 5 OK
        for (int i = 0; i < 5; i++)
            assertTrue(br.readLine().equals("Ok"));
        
        br.close();
    }
}
