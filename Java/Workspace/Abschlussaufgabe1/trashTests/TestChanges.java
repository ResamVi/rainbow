package main;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import edu.kit.informatik.UserInterface;

public class TestChanges {

    private String path = "C:\\Users\\User\\Desktop\\Workspace\\Abschlussaufgabe1\\src\\testChanges\\";
    private String outputPath = "C:\\Users\\User\\Desktop\\Workspace\\Abschlussaufgabe1\\src\\output.txt";
    
    /**
     * Given test case C6
     * 
     * @throws IOException a
     */
    @Test
    public void testWrittenBy() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testWrittenBy.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        
        // 13 OK's
        for (int i = 0; i < 12; i++)
            assertTrue(br.readLine().equals("Ok"));
        
        
        br.close();
    }
    
    /**
     * Given test case C7
     * 
     * @throws IOException a
     */
    @Test
    public void testCites() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testCites.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        
        // 7 OK's
        for (int i = 0; i < 7; i++)
            assertTrue(br.readLine().equals("Ok"));
        
        assertTrue(br.readLine().equals("Error, latter publication does not exist"));
        assertTrue(br.readLine().equals("Ok"));
        assertTrue(br.readLine().equals("Error, publications cannot cite themselves"));
        assertTrue(br.readLine().equals("Ok"));
        
        br.close();
    }
}
