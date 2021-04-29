package inf101.sem2.game;

import inf101.GetStarted;
import inf101.grid.GridDirection;
import inf101.grid.Location;
import inf101.sem2.player.DumbPlayer;
import inf101.sem2.player.Player;
import inf101.sem2.terminal.TerminalGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import inf101.sem2.game.Othello;
import inf101.sem2.game.Game;
import inf101.sem2.game.GameBoard;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


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
        //System.out.println(board);
    }

    @Test
    void testCanPlaceOthello(){
        System.out.println("This is the test for: testCanPlaceOthello");
        Location loc = new Location(2, 3);
        assertTrue(board.canPlace(loc));
        board.set(loc, currentPlayer);
        assertFalse(board.canPlace(loc));
    }
    @Test
    void testIsOnGrid(){
        System.out.println("This is the test for: testIsOnGrid");
        Location loc = new Location(0,0);
        Location loc2 = new Location(-1,1);
        assertTrue(board.canPlace(loc));
        assertFalse(board.canPlace(loc2));
    }

    @Test
    void testIsWinner(){
        System.out.println("This is the test for: testIsWinner");
    }

    @Test
    void testGetPossibleMoves(){
        System.out.println("This is the test for: testGetPossibleMoves");
        int sizeMoves = 4;
        List<Location> PossibleMoves = othello.getPossibleMoves();
        int posMovesSize = PossibleMoves.size();
        assertEquals(sizeMoves,posMovesSize);
    }

    @Test
    void testGetAffectedDisks(){
        Player curPlayer = othello.getCurrentPlayer();
        Location loc = new Location(3,5);
        othello.makeMove(loc);
        othello.getGraphics().display(board);
        List<Location> locs = othello.getAffectedDisks(loc, GridDirection.EAST);
        for(Location l : locs){
            assertEquals(curPlayer, board.get(l));
        }
    }

    @Test
    void testFlipDisk(){
        Player curPlayer = othello.getCurrentPlayer();
        Location loc = new Location(3,5);
        Location flipLocation = new Location(3,4);
        board.set(loc,othello.getCurrentPlayer());
        Player player = board.get(flipLocation);
        othello.flipDisks(loc);
        assertEquals(board.get(loc),curPlayer);
    }

    @Test
    void testCheckForCurrentPlayers(){


    }

    @Test
    void testMakeMove(){
        Player curPlayer = othello.getCurrentPlayer();
        Location loc = new Location(3,5);
        othello.makeMove(loc);
        assertEquals(curPlayer,board.get(loc));

    }

}
