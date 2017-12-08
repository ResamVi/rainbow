package main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    // TODO: Exit when no input
    
    public static void main(String[] args) throws InterruptedException, IOException {
        
        // Clear screen
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        
        Scanner listen = new Scanner(System.in);
        Scanner read = new Scanner(new FileReader("C:\\Users\\User\\Desktop\\Workspace\\AI\\CurrentTest\\data.txt"));
        
        // Wait until ready
        //Thread.sleep((long) (Math.random() * 90 * 60 * 1000));
        
        String lastDate = ""; 
        
        // Get latest entry's date
        while(read.hasNext()) {
            
            Pattern pattern = Pattern.compile("\\(.*\\)");
            Matcher matcher = pattern.matcher(read.next());
            if (matcher.find())
                lastDate = matcher.group();
            
        }
        read.close();
        
        // Check if we already asked
        Calendar date = Calendar.getInstance();
        
        String today = String.format("(%s.%s.%d)",
                formatNumber(date.get(Calendar.DAY_OF_MONTH)), 
                formatNumber(date.get(Calendar.MONTH)+1),
                date.get(Calendar.YEAR));
        
        if(today.equals(lastDate))
            System.exit(0);
        
        // Ask user
        System.out.println("Hey Julien, wie geht es dir heute?");
        System.out.println("Gefühl + Freude/Angst/Trauer/Wut");
        String word = listen.next();
        String label = listen.next();
        
        FileWriter write = new FileWriter("C:\\Users\\User\\Desktop\\Workspace\\AI\\CurrentTest\\data.txt", true);
          
        // Write input
        String line = String.format("%s %s (%s.%s.%d)", word, label, 
                formatNumber(date.get(Calendar.DAY_OF_MONTH)), 
                formatNumber(date.get(Calendar.MONTH)+1),
                date.get(Calendar.YEAR));
        
        write.append(line + "\n");
        
        write.close();
        listen.close();
        System.out.println("Alles klar.");
    }
    
    public static String formatNumber(int number) {
        return ("" + number).length() == 1 ? "0" + number : "" + number;
    }
}
