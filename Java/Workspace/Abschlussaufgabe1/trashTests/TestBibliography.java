package main;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import edu.kit.informatik.UserInterface;

public class TestBibliography {
    
    private String path = "C:\\Users\\User\\Desktop\\Workspace\\Abschlussaufgabe1\\src\\testBibliography\\";
    private String outputPath = "C:\\Users\\User\\Desktop\\Workspace\\Abschlussaufgabe1\\src\\output.txt";
    
    /**
     * Test case C20
     * @throws IOException a
     */
    @Test
    public void testDirectPrintConference() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testDirectPrintConference.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        
        assertTrue(br.readLine().equals("Ok"));
        assertTrue(br.readLine().equals("Ok"));
        
        assertTrue(br.readLine().equals("[1] S. Brin and L. Page, "
                + "\"The Anatomy of a Large-Scale Hypertextual Web Search Engine,\" in Proceedings of WWW, "
                + "Brisbane Australia, "
                + "1998."));
        
        assertTrue(br.readLine().equals("(Brin, 1998) Page, Lawrence."
                + " \"The Anatomy of a Large-Scale Hypertextual Web Search Engine.\" "
                + "Paper presented at WWW, 1998, Brisbane Australia."));
        
        // Quit
        assertTrue(br.readLine().equals("Ok"));
        
        br.close();
    }
    
    /**
     * Test case C21
     * @throws IOException a
     */
    @Test
    public void testDirectPrintJournal() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testDirectPrintJournal.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        
        assertTrue(br.readLine().equals("Ok"));
        
        assertTrue(br.readLine().equals("[1] E. Dijkstra, \"Go To Statement Considered Harmful,\" "
                + "Comm. of the ACM, 1968."));
        
        assertTrue(br.readLine().equals("(Dijkstra, 1968) Dijkstra, Edsger."
                + " \"Go To Statement Considered Harmful.\" "
                + "Comm. of the ACM (1968)."));
        
        // Quit
        assertTrue(br.readLine().equals("Ok"));
        
        br.close();
    }
    
    /**
     * Test case C22
     * @throws IOException a
     */
    @Test
    public void testPrintBibliography() throws IOException {

        // Run program
        UserInterface.main(new String[] {path + "testPrintBibliography.txt" });

        // Read output
        BufferedReader br = new BufferedReader(new FileReader(outputPath));
        
        for (int i = 0; i < 12; i++)
            assertTrue(br.readLine().equals("Ok"));

        assertTrue(br.readLine().equals("[1] S. Afolabi and E. Lowry, \"Better Model Consistency,\" TSE, 2016."));
        assertTrue(br.readLine().equals("[2] S. Afolabi and R. Rhinelander, \"Model Consistency,\" TSE, 2015."));
        assertTrue(br.readLine().equals("[3] R. Rhinelander, \"Components still have no interfaces,\" in Proceedings of ICSA, Gothenburg, 2017."));
        
        assertTrue(br.readLine().equals("(Afolabi, 2016) Afolabi, Shashi, and Lowry, Emiola. \"Better Model Consistency.\" TSE (2016)."));
        assertTrue(br.readLine().equals("(Afolabi, 2015) Afloabi, Shashi, and Rhinelander, Richard. \"Model Consistency.\" TSE (2015)."));
        assertTrue(br.readLine().equals("(Rhinelander, 2017) Rhinelander, Richard. \"Components still have no interfaces.\" Paper presented at ICSA, 2017, Gothenburg."));
        
        // Quit
        assertTrue(br.readLine().equals("Ok"));
        
        br.close();
    }
    
}
