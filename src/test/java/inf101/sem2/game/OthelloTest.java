package inf101.sem2.game;

import inf101.GetStarted;
import inf101.grid.GridDirection;
import inf101.grid.Location;
import inf101.sem2.player.DumbPlayer;
import inf101.sem2.player.Player;
import inf101.sem2.terminal.TerminalGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class OthelloTest {


    GameBoard board;
    Othello othello;
    DumbPlayer currentPlayer;
    DumbPlayer nextPlayer;

    /*
    Her blir det lagd et nytt Othello-brett brukt til testene. @BeforeEach kjøres før hver test.
     */
    @BeforeEach
    void setup() {
        assertTrue(GetStarted.hasRead);
        this.currentPlayer = new DumbPlayer('X');
        this.nextPlayer = new DumbPlayer('O');
        this.othello = new Othello(new TerminalGraphics(), currentPlayer, nextPlayer);
        this.board = othello.board;
    }


    /*
    Tester om en lokasjon kan bruke canPlace. assertTrue om den kan, og assertFalse om den ikke kan.
     */
    @Test
    void testCanPlaceOthello(){
        System.out.println("This is the test for: testCanPlaceOthello");
        Location loc = new Location(2, 3);
        assertTrue(board.canPlace(loc));
        board.set(loc, currentPlayer);
        assertFalse(board.canPlace(loc));
    }

    /*
    Tester enkelt og grei om en lokasjon er i griden.
     */

    @Test
    void testIsOnGrid(){
        System.out.println("This is the test for: testIsOnGrid");
        Location loc = new Location(0,0);
        Location loc2 = new Location(-1,1);
        assertTrue(board.canPlace(loc));
        assertFalse(board.canPlace(loc2));
    }

    /**
     * Testen vil sammenligne hvor mange brikker hver spiller har, og deretter returnere en vinner.
     */
    @Test
    void testIsWinner(){
        System.out.println("This is the test for: testIsWinner");

    }

    /**
     *Grunnen til at det e forventet fire lokasjoner er fordi den bare sjekker for den ene spilleren.
     * Videre lages en liste med possible moves og så sammenlignes de i assertEquals(expected, actual).
     */
    @Test
    void testGetPossibleMoves(){
        System.out.println("This is the test for: testGetPossibleMoves");
        int expectedMoves = 4;
        List<Location> PossibleMoves = othello.getPossibleMoves();
        int posMovesSize = PossibleMoves.size();
        assertEquals(expectedMoves,posMovesSize);
    }


    @Test
    void testGetAffectedDisks(){
        Player currentPlayer = othello.getCurrentPlayer();
        Location loc = new Location(3,5);
        othello.makeMove(loc);
        othello.getGraphics().display(board);
        List<Location> locations = othello.getAffectedDisks(loc, GridDirection.EAST);
        for(Location l : locations){
            assertEquals(currentPlayer, board.get(l));
        }
    }

    /**
     * Testen plasserer en currentPlayer på en lokasjon der det ligger en motspillers brikke mellom.
     * Brikken som ligger mellom er flipLocation, som er locationen som vi vil flippe og teste om flipper.
     * Bruker så flipDisk til å flippe lokasjonene mellom currentPlayer og loc. Da vil flipLocation flippes.
     * Bruker assertEquals for å sjekke likheten mellom to objekter. assertEquals(expected, actual).
     */
    @Test
    void testFlipDisks(){
        Player currentPlayer = othello.getCurrentPlayer();
        Location loc = new Location(5,3);
        Location flipLocation = new Location(4,3);
        board.set(loc, othello.getCurrentPlayer());
        board.get(flipLocation);
        othello.flipDisks(loc);
        assertEquals(board.get(loc), currentPlayer);
    }

    @Test
    void testCheckForCurrentPlayers(){
    }


    /**
     * Sjekker enkelt og greit om loc har samme spillbrikke som currentPlayer etter en move.
     */
    @Test
    void testMakeMove(){
        Player currentPlayer = othello.getCurrentPlayer();
        Location loc = new Location(3,5);
        othello.makeMove(loc);
        assertEquals(currentPlayer,board.get(loc));

    }

}
