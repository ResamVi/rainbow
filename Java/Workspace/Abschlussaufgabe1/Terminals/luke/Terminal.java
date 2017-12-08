package edu.kit.informatik;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * This class can be used to tests your final 1 implementation.<br>
 * Replace the original Terminal.java with this file.<br><br>
 *
 * 1. Do not change the package declaration!<br>
 * 2. Do not upload this file to the Praktomat!<br>
 *
 * @author  Luke 'luk3b' Bro
 * @version 1.0.0
 * @license WTFPL
 * @link    https://github.com/luk3b/prog-assignment-tests
 */
public class Terminal {
    // ############ CONFIGURATION START ############
    /**
     * Hide statistics at the end? This includes
     *   the total number of tests as well as
     *   the pass rate.
     */
    private static boolean CONF_HIDE_STATS = false;

    /**
     * Disable colors? Colors only work in Terminals
     *   with ANSI color support, the windows command
     *   prompt e.g. does _not_ support this.
     */
    private static boolean CONF_DISABLE_COLORS = true;

    // ############ CONFIGURATION END ############
    // Do not change anything below this line!

    /**
     * ANSI color codes
     */
    private static final String COLOR_RESET = "\u001B[0m";
    private static final String COLOR_RED   = "\u001B[31m";
    private static final String COLOR_GREEN = "\u001B[32m";
    private static final String COLOR_BLUE  = "\u001B[34m";
    
    /**
     * Time measurement
     */
    private static long start;
    private static long end;

    /**
     * Index counters
     */
    private static int i = -1;
    private static boolean pass;

    /**
     * Init status
     */
    private static boolean init = false;

    /**
     * Tests storage
     */
    private static Map<String, String[]> tests = new LinkedHashMap<>();
    private static Object[] cmds;
    private static Object[] outp;
    
    /**
     * Temp storage for multi-line output
     */
    private static List<String> o = new ArrayList<>();

    /**
     * Stats
     */
    private static int TESTS_TOTAL  = 0;
    private static int TESTS_PASSED = 0;

    /**
     * Prints a line without modification
     *
     * @param str Line to print
     */
    private static void sysPrintLine(String str) {
        System.out.println(str);
    }

    /**
     * Prints a command
     *
     * @param cmd Command to print
     */
    private static void sysPrintCommand(String cmd) {
        System.out.println(CONF_DISABLE_COLORS ? "\n> " + cmd : COLOR_GREEN + "\n> " + cmd + COLOR_RESET);
    }

    /**
     * Prints an output
     *
     * @param str Output to print
     */
    private static void sysPrintOutput(String str) {
        System.out.println(CONF_DISABLE_COLORS ? str : COLOR_BLUE + str + COLOR_RESET);
    }

    /**
     * Prints an error
     *
     * @param error Error to print
     */
    private static void sysPrintError(String error) {
        System.out.println(CONF_DISABLE_COLORS ? "! " + error : COLOR_RED + "! " + error + COLOR_RESET);
    }

    /**
     * Inits test
     */
    private static void init() {
        start = System.currentTimeMillis();
        
        tests.put("add", new String[] {
                "Error,"
              });
        tests.put("add ", new String[] {
                "Error,"
              });
        tests.put("add stuff", new String[] {
                "Error,"
              });
        tests.put("add-author", new String[] {
                "Error,"
              });

        // add author
        tests.put("add author Eniola,Lowry", new String[] {
                "Ok"
              });
        tests.put("add author Richard,Rhinelander", new String[] {
                "Ok"
              });
        tests.put("add author Shashi,Afolabi", new String[] {
                "Ok"
              });
        tests.put("add author eniola,Lowry", new String[] {
                "Ok"
              });
        tests.put("add author Eniola,Rhinelander", new String[] {
                "Ok"
              });
        tests.put("add author shashi,afolabi", new String[] {
                "Ok"
              });
        tests.put("add author a,a", new String[] {
                "Ok"
              });
        tests.put("add author b,b", new String[] {
                "Ok"
              });
        tests.put("add author c,c", new String[] {
                "Ok"
              });
        tests.put("add author d,d", new String[] {
                "Ok"
              });
        tests.put("add author shashi afolabi", new String[] {
                "Error,"
              });
        tests.put(" add author another,name", new String[] {
                "Error,"
              });
        tests.put("add author first,middle,last", new String[] {
                "Error,"
              });
        tests.put("add author F1rst,last", new String[] {
                "Error,"
              });
        tests.put("add author first,l4st", new String[] {
                "Error,"
              });
        tests.put("add author fir_st,l.st", new String[] {
                "Error,"
              });
        
        // add journal
        tests.put("add journal TSE,IEEE", new String[] {
                "Ok"
              });
        tests.put("add journal ABC,test publisher", new String[] {
                "Ok" // all characters other than ; and , allowed for publisher
              });
        tests.put("add journal DEF,test-publisher", new String[] {
                "Ok" // all characters other than ; and , allowed for publisher
              });
        tests.put("add journal TSE,NOTIEEE", new String[] {
                "Error,"
              });
        tests.put("add journal EFG,IEEE,", new String[] {
                "Error,"
              });
        
        
        // add conference series
        tests.put("add conference series ICSA", new String[] {
                "Ok"
              });
        tests.put("add conference series QoSA", new String[] {
                "Ok"
              });
        tests.put("add conference series NOTICSA,", new String[] {
                "Error,"
              });
        
        // add conference
        tests.put("add conference ICSA,2016,Karlsruhe", new String[] {
                "Ok"
              });
        tests.put("add conference ICSA,2017,Gothenburg", new String[] {
                "Ok"
              });
        tests.put("add conference QoSA,2016,Venice", new String[] {
                "Ok"
              });
        tests.put("add conference QoSA,2017,Somewhere", new String[] {
                "Ok"
              });
        tests.put("add conference ICSA,2016,Somewhere", new String[] {
                "Error,"
              });
        tests.put("add conference QoSA,2017,Karlsruhe", new String[] {
                "Error,"
              });
        tests.put("add conference TEST,2012,Berlin", new String[] {
                "Error,"
              });
        tests.put("add conference ICSA,999,Berlin", new String[] {
                "Error,"
              });
        tests.put("add conference ICSA,0123,Berlin", new String[] {
                "Error,"
              });
        tests.put("add conference ICSA,10000,Berlin", new String[] {
                "Error,"
              });
        tests.put("add conference ICSA,2018,_Muenchen", new String[] {
                "Error,"
              });
        tests.put("add conference ICSA,2019,Muenchen,", new String[] {
                "Error,"
              });
        
        // add article to
        tests.put("add article to series ICSA:rr2016,2016,Components have no interfaces", new String[] {
                "Ok"
              });
        tests.put("add article to series ICSA:rr2017,2017,Components still have no interfaces", new String[] {
                "Ok"
              });
        tests.put("add article to series ICSA:rr2017,2018,These components still have no interfaces", new String[] {
                "Error," // same identifier
              });
        tests.put("add article to series ICSA:rr2015,2015,Title test", new String[] {
                "Error," // no conference in that year
              });
        tests.put("add article to series ICSA:rv2016,2016,Title-test", new String[] {
                "Ok"
              });
        tests.put("add article to series ICSA:rr2015,999,Title test", new String[] {
                "Error," // invalid year / no conference in that year
              });
        tests.put("add article to series TEST:rr2000,2000,Title test", new String[] {
                "Error," // series does not exist
              });
        tests.put("add article to journal TSE:mvp2015,2015,Model Consistency", new String[] {
                "Ok"
              });
        tests.put("add article to journal TSE:mvp2016,2016,Better Model Consistency", new String[] {
                "Ok"
              });
        tests.put("add article to journal ABC:abc2016,2016,Some title", new String[] {
                "Ok"
              });
        tests.put("add article to other TSE:xyz2013,2013,Some-title", new String[] {
                "Error,"
              });
        tests.put("add article to journal ABC:abc2016,2017,Some title", new String[] {
                "Error,"
              });
        tests.put("add article to journal TSE:p0,2012,Title", new String[] {
                "Ok"
              });
        tests.put("add article to journal TSE:p1,2013,Title", new String[] {
                "Ok"
              });
        tests.put("add article to journal TSE:p2,2014,Title", new String[] {
                "Ok"
              });
        tests.put("add article to journal TSE:p3,2015,Title", new String[] {
                "Ok"
              });
        
        // written-by
        tests.put("written-by rr2017,Richard Rhinelander", new String[] {
                "Ok"
              });
        tests.put("written-by rr2017,Richard Rhinelander;Eniola Lowry", new String[] {
                "Error,"
              });
        tests.put("written-by rr2017,Eniola Lowry", new String[] {
                "Ok"
              });
        tests.put("written-by rr2018,Richard Rhinelander", new String[] {
                "Error,"
              });
        tests.put("written-by rr2017,eniola Lowry", new String[] {
                "Ok"
              });
        tests.put("written-by mvp2015,Shashi Afolabi", new String[] {
                "Ok"
              });
        tests.put("written-by mvp2016,Richard Rhinelander;Shashi Afolabi", new String[] {
                "Ok"
              });
        tests.put("written-by rr2017,Test Author", new String[] {
                "Error,"
              });
        tests.put("written-by rr2017,Test Author", new String[] {
                "Error,"
              });
        tests.put("written-by rr2017,Eniola Rhinelander,", new String[] {
                "Error,"
              });
        tests.put("written-by rr2017,Eniola Rhinelander,shashi afolabi", new String[] {
                "Error,"
              });
        tests.put("written-by p0,a a", new String[] {
                "Ok"
              });
        tests.put("written-by p1,a a;b b", new String[] {
                "Ok"
              });
        tests.put("written-by p2,b b;c c", new String[] {
                "Ok"
              });
        tests.put("written-by p3,c c;d d", new String[] {
                "Ok"
              });
        
        // cites
        tests.put("cites rr2017,rr2016", new String[] {
                "Ok"
              });
        tests.put("cites rr2017,mvp2015", new String[] {
                "Ok"
              });
        tests.put("cites rr2016,rr2017", new String[] {
                "Error,"
              });
        tests.put("cites rr2016,rr2016", new String[] {
                "Error,"
              });
        tests.put("cites mvp2016,mvp2015", new String[] {
                "Ok"
              });
        tests.put("cites abc2016,mvp2015", new String[] {
                "Ok"
              });
        tests.put("cites mvp2015,mvp2015", new String[] {
                "Error,"
              });
        tests.put("cites p1,p0", new String[] {
                "Ok"
              });
        tests.put("cites p2,p0", new String[] {
                "Ok"
              });
        tests.put("cites p3,p0", new String[] {
                "Ok"
              });
        
        // add keywords to
        tests.put("add keywords to pub mvp2015:swe;java", new String[] {
                "Ok"
              });
        tests.put("add keywords to pub mvp2016:swe;reference;java", new String[] {
                "Ok"
              });
        tests.put("add keywords to pub mvp2016:oop;java", new String[] {
                "Ok"
              });
        tests.put("add keywords to journal TSE:java", new String[] {
                "Ok"
              });
        tests.put("add keywords to series ICSA:swe;performance", new String[] {
                "Ok"
              });
        tests.put("add keywords to series BLA:test", new String[] {
                "Error,"
              });
        tests.put("add keywords to series:test", new String[] {
                "Error,"
              });
        tests.put("add keywords to conference ICSA,2016:java;oop", new String[] {
                "Ok"
              });
        tests.put("add keywords to conference ICSA,2020:java;oop", new String[] {
                "Error,"
              });
        tests.put("add keywords to conference KEK,2016:swe", new String[] {
                "Error,"
              });
        tests.put("add keywords to conference KEK,2016:swe;dsadsa;", new String[] {
                "Error,"
              });
        tests.put("add keywords to journal:doesnotexist", new String[] {
                "Error,"
              });
        tests.put("add keywords to journal TSE:iNvAlIdKeYwOrD", new String[] {
                "Error,"
              });
        tests.put("add keywords to journal TSE:12345", new String[] {
                "Error,"
              });
        
        tests.put("add article to journal TSE:mvp2017,2017,Lit AF Model Consistency", new String[] {
                "Ok" // article added after keywords, keywords should still be inherited
              });
        
        // all publications
        tests.put("all publications", new String[] {
                "rr2016",
                "rr2017",
                "rv2016",
                "mvp2015",
                "mvp2016",
                "mvp2017",
                "abc2016",
                "p0",
                "p1",
                "p2",
                "p3"
              });
        
        // list invalid publications
        tests.put("list invalid publications", new String[] {
                "rr2016",
                "rv2016",
                "abc2016",
                "mvp2017"
              });
        
        // publications by
        tests.put("publications by Eniola Lowry", new String[] {
                "rr2017"
              });
        tests.put("publications by Eniola Lowry;Richard Rhinelander", new String[] {
                "rr2017",
                "mvp2016"
              });
        tests.put("publications by Shashi Afolabi;eniola Lowry", new String[] {
                "rr2017",
                "mvp2015",
                "mvp2016"
              });
        tests.put("publications by Eniola Lowry;and me", new String[] {
                "Error,"
              });
        tests.put("publications by test author", new String[] {
                "Error,"
              });
        
        // in proceedings
        tests.put("in proceedings ICSA,2016", new String[] {
                "rr2016",
                "rv2016"
              });
        tests.put("in proceedings ICSA,2017", new String[] {
                "rr2017"
              });
        tests.put("in proceedings QoSA,2016", new String[] {
              });
        tests.put("in proceedings uksa,2016", new String[] {
                "Error,"
        });
        tests.put("in proceedings ICSA,2018", new String[] {
                "Error,"
        });
        
        // find keywords
        tests.put("find keywords java", new String[] {
                "mvp2015",
                "mvp2016",
                "mvp2017",
                "rr2016",
                "rv2016",
                "p0",
                "p1",
                "p2",
                "p3"
        });
        tests.put("find keywords notfound", new String[] {
        });
        tests.put("find keywords java;", new String[] {
                "Error,"
        });
        tests.put("find keywords java;performance", new String[] {
                "rr2016",
                "rv2016"
        });
        
        // jaccard
        tests.put("jaccard a;b;c d;e", new String[] {
                "0.000"
        });
        tests.put("jaccard a;b;c;d;e b;c;d;e;f", new String[] {
                "0.666"
        });
        tests.put("jaccard a;b;c;d;e;f;g b;c;d;e;f", new String[] {
                "0.714"
        });
        
        // similarity        
        tests.put("similarity mvp2015,mvp2016", new String[] {
                "0.500"
        });
        tests.put("similarity mvp2016,mvp2016", new String[] {
                "1.000"
        });
        tests.put("similarity rv2016,mvp2017", new String[] {
                "0.250"
        });
        
        // direct h-index
        tests.put("direct h-index 17;3;1;5", new String[] {
                "3"
        });
        tests.put("direct h-index 8;6;8;4;8;6", new String[] {
                "5"
        });
        tests.put("direct h-index 10;9;8;7;6;5;4;3;2;1", new String[] {
                "5"
        });
        tests.put("direct h-index 100;100;2;2;2;2;2;2;2;2", new String[] {
                "2"
        });
        tests.put("direct h-index 100;100;9;8;3;2;2;1;1", new String[] {
                "4"
        });
        
        // h-index
        tests.put("h-index some dude", new String[] {
                "Error,"
        });
        tests.put("h-index Richard Rhinelander", new String[] {
                "0"
        });
        tests.put("h-index Shashi Afolabi", new String[] {
                "1"
        });
        
        // coauthors of
        tests.put("coauthors of Shashi Afolabi", new String[] {
                "Richard Rhinelander"
        });
        tests.put("coauthors of Richard Rhinelander", new String[] {
                "Shashi Afolabi",
                "eniola Lowry",
                "Eniola Lowry"
        });
        tests.put("coauthors of eniola Lowry", new String[] {
                "Richard Rhinelander",
                "Eniola Lowry"
        });
        tests.put("coauthors of test dude", new String[] {
                "Error,"
        });
        
        // foreign citations of
        tests.put("foreign citations of Richard Rhinelander", new String[] {
        });
        tests.put("foreign citations of a a", new String[] {
                "p3"
        });
        
        // direct print conference
        tests.put("direct print conference ieee:Sergey Brin,Lawrence Page,,The Anatomy of a Large-Scale Hypertextual"
                + " Web Search Engine,WWW,Brisbane Australia,1998", new String[] {
                "[1] S. Brin and L. Page, \"The Anatomy of a Large-Scale Hypertextual Web Search Engine,\" in "
                + "Proceedings of WWW, Brisbane Australia, 1998."
        });
        tests.put("direct print conference chicago:Sergey Brin,Lawrence Page,,The Anatomy of a Large-Scale Hypertextual"
                + " Web Search Engine,WWW,Brisbane Australia,1998", new String[] {
                "(Brin, 1998) Brin, Sergey, and Page, Lawrence. \"The Anatomy of a Large-Scale Hypertextual Web Search"
                + " Engine.\" Paper presented at WWW, 1998, Brisbane Australia."
        });
        
        // direct print journal
        tests.put("direct print journal ieee:Edsger Dijkstra,,,Go To Statement Considered Harmful,"
                + "Comm. of the ACM,1968", new String[] {
                "[1] E. Dijkstra, \"Go To Statement Considered Harmful,\" Comm. of the ACM, 1968."
        });
        tests.put("direct print journal chicago:Edsger Dijkstra,,,Go To Statement Considered Harmful,"
                + "Comm. of the ACM,1968", new String[] {
                "(Dijkstra, 1968) Dijkstra, Edsger. \"Go To Statement Considered Harmful.\" Comm. of the ACM (1968)."
        });
        
        // print bibliography
        tests.put("print bibliography ieee:mvp2017", new String[] {
                "Error,"
        });
        tests.put("print bibliography ieee:rr2017", new String[] {
                "[1] S. Afolabi, \"Model Consistency,\" TSE, 2015."
        });
        tests.put("print bibliography ieee:p1;p2;p3;rr2017", new String[] {
                "[1] S. Afolabi, \"Model Consistency,\" TSE, 2015.",
                "[2] A. a, \"Title,\" TSE, 2012."
        }); // note: 'A' is ordered before 'a'
        tests.put("print bibliography chicago:p1;p2;p3;rr2017", new String[] {
                "(Afolabi, 2015) Afolabi, Shashi. \"Model Consistency.\" TSE (2015).",
                "(a, 2012) a, a. \"Title.\" TSE (2012)."
        });

        cmds = tests.keySet().toArray();
        outp = tests.values().toArray();
        init = !init;

        sysPrintLine("All tests come with ABSOLUTELY NO WARRANTY. Do NOT upload this file to the Praktomat!");
        sysPrintLine("See source of file to configure statistics and color support.");
    }

    /**
     * Returns next command, logs it to Terminal and checks for
     *   missing lines.
     *
     * @return Next command, "quit" if no next command available
     */
    public static String readLine() {
        if (!init)
            init();

        // check for missing lines
        if (i >= 0) {            
            String[] out = (String[]) outp[i];
            for (int i = o.size(); i < out.length; i++) {
                sysPrintError("Missing line");
                pass = false;
            }
        }

        if (pass)
            TESTS_PASSED++;

        o.clear();
        i++;
        TESTS_TOTAL++;
        pass = true;

        // end of test, do calculation
        if (i >= tests.size()) {
            sysPrintCommand("quit");
            TESTS_PASSED++;
            
            end = System.currentTimeMillis();

            if (!CONF_HIDE_STATS) {
                sysPrintLine("-------------------------");
                sysPrintLine("Total tests finished: " + TESTS_TOTAL + " (~" + (end - start) + "ms)");
                sysPrintLine("PASSED: " + TESTS_PASSED + " ("
                           + String.format(Locale.UK, "%.1f", ((float) TESTS_PASSED / TESTS_TOTAL) * 100.0f)
                           + "%), FAILED: " + (TESTS_TOTAL - TESTS_PASSED));
            }

            return "quit";
        }

        String cmd = (String) cmds[i];
        sysPrintCommand(cmd);

        return cmd;
    }

    /**
     * Prints line to Terminal and checks for correctness
     *
     * @param str Line to print
     */
    public static void printLine(String str) {
        if (!init)
            return;

        String[] out = (String[]) outp[i];
        sysPrintOutput(str);
        
        if (o.size() > out.length) {
            sysPrintError("Output too long");
            pass = false;
            return;
        }
        
        if (out.length == 1) {
            o.add(str);
            
            if (out[0].startsWith("Error,")) {
                if (!str.startsWith("Error,")) {
                    sysPrintError("Expected error message");
                    pass = false;
                }
            } else {
                if (!str.equals(out[0])) {
                    sysPrintError("Incorrect output! (" + str + ")");
                    pass = false;
                }
            }
        } else {
            if (contains(out, str)) {
                if (o.contains(str)) {
                    sysPrintError("Duplicate output! (" + str + ")");
                    pass = false;
                } else {
                    o.add(str);
                }
            } else {
                sysPrintError("Unexpected output '" + str + "'");
                pass = false;
            }
        }
    }
    
    /**
     * Prints line to Terminal and checks for correctness
     *
     * @param i Line to print
     */
    public static void printLine(int i) {
        printLine(String.valueOf(i));
    }

    /**
     * Prints error to Terminal
     *
     * @param str The error to print
     */
    public static void printError(String str) {
        printLine("Error, " + str);
    }
    
    /**
     * Prints error to Terminal
     *
     * @param i The error to print
     */
    public static void printError(int i) {
        printError(i);
    }
    
    /**
     * Checks whether a string is part of an array
     * 
     * @param arr The array to check
     * @param str String to search for
     * @return true if found, false otherwise
     */
    private static boolean contains(String[] arr, String str) {
        for (String s : arr) {
            if (s.equals(str))
                return true;
        }
        return false;
    }
}