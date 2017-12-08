package edu.kit.informatik.matchthree;

import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.matchthree.framework.FillingStrategy;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.exceptions.IllegalTokenException;
import edu.kit.informatik.matchthree.framework.exceptions.NoFillingStrategyException;
import edu.kit.informatik.matchthree.framework.exceptions.TokenStringParseException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;

/**
 * A board is the container of tokens. *
 */
public class MatchThreeBoard implements Board {

    private static final char DELIMITER = ';';
    private static final char EMPTY = ' ';

    private Set<Token> tokens;
    private final int columnCount;
    private final int rowCount;

    private Token[][] board;
    private FillingStrategy strategy;

    /**
     * Creates a new board for playing.
     * 
     * @param tokens
     *            contains symbols used for this board
     * @param columnCount
     *            amount of columns
     * @param rowCount
     *            amount of rows
     */
    public MatchThreeBoard(Set<Token> tokens, int columnCount, int rowCount) {

        // Check for null
        if (tokens == null)
            throw new NullPointerException("Constructor cannot have null arguments");

        // A board has atleast 2 rows and columns
        if (columnCount < 2 || rowCount < 2)
            throw new BoardDimensionException("Board should have atleast 2 rows and 2 columns.");

        // A board needs Two different tokens
        if (tokens.size() < 2)
            throw new IllegalArgumentException("Board should have atleast 2 different tokens.");

        this.tokens = tokens;
        this.columnCount = columnCount;
        this.rowCount = rowCount;
        this.board = new Token[columnCount][rowCount];
        
        // Lay tokens on board
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                board[column][row] = null;
            }
        }
    }

    /**
     * Creates a new board with pre-initialized tokens for playing. <br />
     * Token format: [token][token]...[token];...;[token][token]...[token] Empty
     * space for unavailable token
     * 
     * @param tokens
     *            contains symbols used for this board
     * @param tokenString
     *            formatted string of token layout on board
     */
    public MatchThreeBoard(Set<Token> tokens, String tokenString) {

        // Check for null
        if (tokenString == null || tokens == null)
            throw new NullPointerException("Constructor cannot have null arguments");

        // Check board for invalid tokens
        for (int i = 0; i < tokenString.length(); i++) {

            // Skip special cases
            if (tokenString.charAt(i) == EMPTY || tokenString.charAt(i) == DELIMITER)
                continue;

            Token token = new Token(tokenString.charAt(i));
            if (tokenString.charAt(i) != DELIMITER && !tokens.contains(token))
                throw new TokenStringParseException("Used tokenString has invalid tokens.");
        }

        // Check syntax
        if (tokenString.charAt(tokenString.length() - 1) == ';')
            throw new TokenStringParseException("Wrong syntax. String should not end with semicolon.");
        
        if (tokenString.contains(";;"))
            throw new TokenStringParseException("Wrong syntax. Empty row.");
        
        // Check board dimension
        String[] rows = tokenString.split(Character.toString(DELIMITER));

        // Atleast 2 rows
        if (rows.length < 2)
            throw new BoardDimensionException("Board should have atleast 2 rows.");
        int rowCount = rows.length;

        // Atleast 2 columns
        if (rows[0].length() < 2)
            throw new BoardDimensionException("Board should have atleast 2 columns.");
        int columnCount = rows[0].length();

        // Check for equal size in columns
        for (String row : rows) {
            if (row.length() != columnCount)
                throw new TokenStringParseException("Columns don't match in size.");
        }

        // Check token size
        if (tokens.size() < 2)
            throw new IllegalArgumentException("Board should have atleast 2 different tokens.");

        this.tokens = tokens;
        this.columnCount = columnCount;
        this.rowCount = rowCount;
        this.board = new Token[columnCount][rowCount];

        // Remove semicolon
        String tokenSequence = String.join("", tokenString.split(";"));

        // Lay tokens on board TODO: Extra method?
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {

                char symbol = tokenSequence.charAt(column + row * columnCount);

                // Skip special cases
                if (symbol == DELIMITER || symbol == EMPTY)
                    continue;

                Token token = new Token(symbol);
                board[column][row] = token;
            }
        }
    }

    @Override
    public Set<Token> getAllValidTokens() {
        return tokens;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public Token getTokenAt(Position position) throws BoardDimensionException {

        if (!containsPosition(position))
            throw new BoardDimensionException("Position not within board dimension.");

        return board[position.x][position.y];
    }

    @Override
    public void setTokenAt(Position position, Token newToken) throws BoardDimensionException, IllegalTokenException {

        if (!containsPosition(position))
            throw new BoardDimensionException("Position not within board dimension.");

        if (newToken != null && !tokens.contains(newToken))
            throw new IllegalTokenException("Invalid token.");

        board[position.x][position.y] = newToken;
    }

    @Override
    public boolean containsPosition(Position position) {
        return (position.x < columnCount && position.y < rowCount) && (position.x >= 0 && position.y >= 0);
    }

    @Override
    public Set<Position> moveTokensToBottom() {
        
        Set<Position> changes = new HashSet<>();
        
        // Iterate from bottom
        for (int row = rowCount - 1; row >= 0; row--) {
            for (int column = columnCount - 1; column >= 0; column--) {
                
                // If empty token found
                if (isEmpty(column, row)) {
                    
                    // Look for non-empty tokens above
                    for (int above = 1; row - above >= 0; above++) {
                        
                        // When found swap
                        if (!isEmpty(column, row - above)) {
                            
                            // Add both positions to changes log
                            changes.add(Position.at(column, row));
                            changes.add(Position.at(column, row - above));
                            
                            swapTokens(Position.at(column, row), Position.at(column, row - above));
                            break;
                        }
                    }
                }
            }
        }
        
        return changes;
    }

    @Override
    public void swapTokens(Position positionA, Position positionB) throws BoardDimensionException {
        if (!containsPosition(positionA) || !containsPosition(positionB))
            throw new BoardDimensionException("Position not within board dimension.");

        Token cache = getTokenAt(positionA);
        setTokenAt(positionA, getTokenAt(positionB));
        setTokenAt(positionB, cache);
    }
    

    @Override
    public void removeTokensAt(Set<Position> positions) throws BoardDimensionException {

        for (Position position : positions) {
            if (!containsPosition(position))
                throw new BoardDimensionException("Position not within board dimension.");
        }

        for (Position position : positions)
            setTokenAt(position, null);

    }
    

    @Override
    public void setFillingStrategy(FillingStrategy strategy) {

        if (strategy == null)
            throw new NullPointerException();

        this.strategy = strategy;
    }
    

    @Override
    public void fillWithTokens() throws NoFillingStrategyException {

        if (strategy == null)
            throw new NoFillingStrategyException();

        strategy.fill(this);

    }
    

    private boolean isEmpty(int x, int y) {
        return getTokenAt(Position.at(x, y)) == null;
    }
    
    @Override
    public String toString() {
        return toTokenString();
    }

    @Override
    public String toTokenString() {
        String string = "";

        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {

                // Use empty spaces for empty cells
                if (board[column][row] == null)
                    string += EMPTY;
                else
                    string += board[column][row];
            }
            string += DELIMITER;
        }

        // Remove last semicolon
        return string.substring(0, string.length() - 1);
    }
}
