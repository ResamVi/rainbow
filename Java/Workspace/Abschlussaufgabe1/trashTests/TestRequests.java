package main;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import edu.kit.informatik.UserInterface;

public class TestRequests {

    private String path = "C:\\Users\\User\\Desktop\\Workspace\\Abschlussaufgabe1\\src\\testRequests\\";
    private String outputPath = "C:\\Users\\User\\Desktop\\Workspace\\Abschlussaufgabe1\\src\\output.txt";
    
    
    /**
     * Given test case C9
     * 
     * @throws IOException a
     */
    @Test
    public void testAllPublication() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testAllPublication.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        
        // 6 OK's
        for (int i = 0; i < 6; i++)
            assertTrue(br.readLine().equals("Ok"));
        
        assertTrue(br.readLine().equals("mk2017"));
        assertTrue(br.readLine().equals("ky2017a"));
        assertTrue(br.readLine().equals("ky2017b"));
        
        assertTrue(br.readLine().equals("Ok"));
        
        br.close();
    }
    
    /**
     * Given test case C10
     * 
     * @throws IOException a
     */
    @Test
    public void testInvalidPublication() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testInvalidPublication.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        
        // 10 OK's
        for (int i = 0; i < 10; i++)
            assertTrue(br.readLine().equals("Ok"));
        
        assertTrue(br.readLine().equals("mk2017"));
        
        assertTrue(br.readLine().equals("Ok"));
        
        br.close();
    }

    /**
     * Given test case C11
     * 
     * @throws IOException a
     */
    @Test
    public void testPublicationsBy() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testPublicationBy.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        
        // 10 OK's
        for (int i = 0; i < 13; i++)
            assertTrue(br.readLine().equals("Ok"));
        
        assertTrue(br.readLine().equals("nngrade"));
        assertTrue(br.readLine().equals("mvp2015"));
        assertTrue(br.readLine().equals("mvp2016"));
        assertTrue(br.readLine().equals("Error, author \"unnamed author\" not found."));
        assertTrue(br.readLine().equals("Error, author \"unnamed author\" not found."));
        
        assertTrue(br.readLine().equals("Ok"));
        
        br.close();
    }
    
    /**
     * Given test case C12
     * For some reason this test only works when run alone.?????????????????????
     * @throws IOException a
     */
    @Test
    public void testInProceedings() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testInProceedings.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        
        for (int i = 0; i < 7; i++)
            assertTrue(br.readLine().equals("Ok"));
        
        assertTrue(br.readLine().equals("rr2017"));
        assertTrue(br.readLine().equals("Error, series \"uksa\" not found."));
        assertTrue(br.readLine().equals("bspblication2015a"));
        assertTrue(br.readLine().equals("bspblication2015b"));
        
        // Quit
        assertTrue(br.readLine().equals("Ok"));
        
        br.close();
    }
    
}
