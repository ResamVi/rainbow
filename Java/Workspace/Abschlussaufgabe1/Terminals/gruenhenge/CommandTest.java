package Terminals.gruenhenge;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.library.management.Main;
import org.junit.*;

class CommandTest {
    private static final String[] NO_ARGS = {};

    @BeforeAll
    static void setupTestingMode() {
        Terminal.enableTestingMode();
    }

    @BeforeEach
    void resetTerminal() {
        Terminal.reset();
    }

    @AfterEach
    void addQuit() {
        ok("quit");

        execute(NO_ARGS);

        Terminal.flush();
        Terminal.reset();
    }

    @Test
    void quit() {
        //Nothing to do here, see addQuit above.
    }

    void ok(String command) {
        Terminal.addSingleLineOutputThatIsExactly(command, "Ok");
    }

    void err(String command) {
        Terminal.addSingleLineOutputThatMatches(command, Matchers.startsWith("Error, "));
    }

    @Test
    void addAuthor() {
        ok("add author Eniola,Lowry");
        ok("add author Richard,Rhinelander");
        err("add author Eniola,Lowry");
        ok("add author Shashi,Afolabi");
    }



    @Test
    void addJournal() {
        ok("add journal TSE,IEEE");
    }

    @Test
    void addConferenceSeries() {
        ok("add conference series ICSA");
    }

    @Test
    void addConference() {
        addConferenceSeries();

        ok("add conference ICSA,2017,Gothenburg");

        err("add conference QoSA,2016,Venice");
    }

    @Test
    void addArtricle() {
        addJournal();
        addConference();
        ok("add article to series ICSA:rr2017,2017,Components still have no interfaces");
        ok("add article to journal TSE:mvp2015,2015,Model Consistency");
        ok("add article to journal TSE:mvp2016,2016,Better Model Consistency");
    }

    @Test
    void writtenBy() {
        addArtricle();

        ok("written-by rr2017,Richard Rhinelander");
        
        ok("written-by nngrade,Eniola Lowry");
        
        ok("written-by mvp2016,Shashi Afolabi;Eniola Lowry");
        
        ok("written-by mvp2015,Shashi Afolabi;Richard Rhinelander");
        
    }

    @Test
    void cites() {
        writtenBy();

        ok("cites rr2017,mvp2016");
        
        ok("cites rr2017,sommerville2015");
        
        ok("cites rr2017,mvp2015");

        err("cites rr2017,rr2017");
    }

    @Test
    void addKeywords() {
        cites();

        ok("add keywords to pub sommerville2015:swe;reference;java;oop");
        
        ok("add keywords to pub rr2017:mdsd;components;java");
        
        ok("add keywords to pub mvp2016:mdsd;java;oop");
        
        ok("add keywords to journal TSE:swe");
        
        ok("add keywords to series ICPE:swe;performance");
        
    }

    @Test
    void allPublications() {
        ok("all publications");
    }

    @Test
    void listInvalidPublications() {
        ok("list invalid publications");
    }


    @Test
    void jaccard() {
        Terminal.addSingleLineOutputThatIsExactly("jaccard a;b;c d;e", "0.000");
        Terminal.addSingleLineOutputThatIsExactly("jaccard a;b;c;d;e b;c;d;e;f", "0.666");
    }

    @Test
    void directHIndex() {
        Terminal.addSingleLineOutputThatIsExactly("direct h-index 17;3;1;5", "3");
        Terminal.addSingleLineOutputThatIsExactly("direct h-index 8;6;8;4;8;6", "5");
    }

    private void execute(String[] args) {
        Main.main(args);
    }
}