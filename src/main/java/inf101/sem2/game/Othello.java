package inf101.sem2.game;


import inf101.grid.Location;
import inf101.sem2.player.Player;
import inf101.grid.GridDirection;
import inf101.sem2.terminal.TerminalGraphics;

public class Othello extends Game {

    public Othello(Graphics graphics, Player p1, Player p2) {
        this(graphics);
        players.add(p1);
        players.add(p2);

        board.set(new Location(3,4), p1);
        board.set(new Location(4,3),p1);
        board.set(new Location(3,3), p2);
        board.set(new Location(4,4),p2);


    }

    public Othello(Graphics graphics) {
        super(new GameBoard(8,8),graphics);
    }

    public Othello(Graphics graphics, Iterable<Player> players) {
        super(new GameBoard(8, 8), graphics, players);
    }



    @Override
    public Game copy() {
        return null;
    }

    @Override
    public boolean isWinner(Player player) {
        return false;
    }

    @Override
    public boolean gameOver() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }
}
