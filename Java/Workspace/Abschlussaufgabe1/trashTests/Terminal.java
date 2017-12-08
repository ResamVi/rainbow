package edu.kit.informatik;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Terminal {

    private static BufferedReader testIn;
    private static FileWriter testOut;

    /**
     * @return a
     */
    public static String readLine() {
        try {
            return testIn.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "String not found";
    }

    /**
     * @param input
     *            a
     */
    public static void printLine(final String input) {
        try {
            testOut.write(input + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param input
     *            a
     */
    public static void printError(final String input) {
        try {
            testOut.write("Error, " + input + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param path
     *            a
     */
    public static void initializeStream(String path) {
        try {
            testIn = new BufferedReader(new FileReader(path));
            testOut = new FileWriter("C:\\Users\\User\\Desktop\\Workspace\\Abschlussaufgabe1\\src\\output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    public static void endStream() {
        try {
            testOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Private constructor to avoid object generation.
     * 
     * @deprecated Utility-class constructor.
     */
    @Deprecated
    private Terminal() {
        throw new AssertionError("Utility class constructor.");
    }
}