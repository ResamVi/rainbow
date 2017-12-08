package edu.kit.informatik;

import static edu.kit.informatik.matchthree.tests.TestUtils.assertSetOfSetsEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.MatchThreeGame;
import edu.kit.informatik.matchthree.MaximumDeltaMatcher;
import edu.kit.informatik.matchthree.MoveFactoryImplementation;
import edu.kit.informatik.matchthree.framework.Delta;
import edu.kit.informatik.matchthree.framework.DeterministicStrategy;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Game;
import edu.kit.informatik.matchthree.framework.interfaces.Matcher;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public class PrettyPrintDONOTUPLOAD {

    public static void main(String[] args) {
        

        Board b = new MatchThreeBoard(Token.set("AXO*"), "O*O;***;O*O;O*O");
        System.out.println(prettyPrint(b));
        
        b.setFillingStrategy(new DeterministicStrategy(
                Token.iterator("AOA**"), Token.iterator("AXAXA"), Token.iterator("A**A*")));
        Matcher m1 = new MaximumDeltaMatcher(new HashSet<Delta>(Arrays.asList(Delta.dxy(1, 0))));
        Matcher m2 = new MaximumDeltaMatcher(new HashSet<Delta>(Arrays.asList(Delta.dxy(0, 1))));
        
        Game g1 = new MatchThreeGame(b, m1);
        g1.initializeBoardAndStart();

        System.out.println(prettyPrint(b));
        
        g1.setMatcher(m2);
        g1.initializeBoardAndStart();
        
        System.out.println(prettyPrint(b));
        
        System.out.println(b.toTokenString());
        System.out.println(g1.getScore());
        
    }
    
    /**
     * Returns a pretty "unicode-image" of the given board using box-drawing characters,
     *   e.g. the board 'ABC;DEF;GHI' will print:
     *   
     *   ┌───┬───┬───┐
     *   │ A │ B │ C │
     *   ├───┼───┼───┤
     *   │ D │ E │ F │
     *   ├───┼───┼───┤
     *   │ G │ H │ I │
     *   └───┴───┴───┘
     * 
     * Do not upload this to the Praktomat! Don't judge me for code style, it's ugly I know.
     *   
     * @param board Well, the board to print obviously
     * @return A pretty board :)
     */
    public static String prettyPrint(Board board) {
        final String TL   = "\u250c";             // top left corner
        final String TR   = "\u2510";             // top right corner
        final String BR   = "\u2518";             // bottom right corner
        final String BL   = "\u2514";             // bottom left corner
        final String T0   = "\u252c";             // "T" character
        final String T90  = "\u2524";             // 90° flipped "T"
        final String T180 = "\u2534";             // 180° flipped "T"
        final String T270 = "\u251c";             // 270° flipped "T"
        final String VERT = "\u2500\u2500\u2500"; // vertical bar
        final String HORI = "\u2502";             // horizontal bar
        final String CROS = "\u253c";             // cross

        int width  = board.getColumnCount();
        int height = board.getRowCount();
        StringBuilder b = new StringBuilder();

        b.append(TL + VERT); for (int i = 1; i < width; i++) b.append(T0 + VERT); b.append(TR + "\n");

        for (int y = 0; y < height; y++) {
            b.append(HORI);
            for (int x = 0; x < width; x++) {
                Token t = board.getTokenAt(Position.at(x, y));
                b.append(" " + (t != null ? t : " ") + " ");

                if (x < width - 1)
                    b.append(HORI);
            }
            b.append(HORI + "\n");

            if (y < height - 1) {
                b.append(T270 + VERT); for (int h = 1; h < width; h++) b.append(CROS + VERT); b.append(T90 + "\n");
            }
        }

        b.append(BL + VERT); for (int i = 1; i < width; i++) b.append(T180 + VERT); b.append(BR);
        return b.toString();
    }
}
