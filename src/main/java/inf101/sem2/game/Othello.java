package inf101.sem2.game;


import inf101.grid.Location;
import inf101.sem2.player.Player;
import inf101.grid.GridDirection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Othello extends Game {

    public Othello(Graphics graphics, Player p1, Player p2) {
        super(new GameBoard(8,8), graphics);
        addPlayer(p1);
        addPlayer(p2);


        board.set(new Location(3,4), players.getCurrentPlayer());
        board.set(new Location(4,3), players.nextPlayer());
        board.set(new Location(3,3), players.getCurrentPlayer());
        board.set(new Location(4,4), players.nextPlayer());


    }

    public Othello(Graphics graphics) {
        super(new GameBoard(8,8),graphics);
    }

    public Othello(Graphics graphics, Iterable<Player> players) {
        super(new GameBoard(8, 8), graphics, players);

        board.set(new Location(3,4), getCurrentPlayer());
        board.set(new Location(4,3), nextPlayer());
        board.set(new Location(3,3), getCurrentPlayer());
        board.set(new Location(4,4), nextPlayer());

    }

    private Player nextPlayer() {
        return null;
    }

    public List<GridDirection> oppositeColor(Location location) {
        Collection<Location> allNeighbors = location.allNeighbors();
        ArrayList<GridDirection> oppositeColor = new ArrayList<>();
        for (Location l: allNeighbors) {
            if (l.equals(players.nextPlayer())){
                oppositeColor.add(location.directionTo(l));
            }
        }
        return oppositeColor;
    }

    public List<Location> validMoves (Location loc){
        List<GridDirection> validMoves = new ArrayList<>();

        return null;
    }

    public Location validMoveRow(Location loc){
        int row = loc.row;
        return null;
    }

    public Location validMoveColoumn(Location loc){return null;}

    public Location validMoveDiagonal(Location loc){return null;}

    @Override
    public boolean canPlace(Location loc){
        return false;
    }

    @Override
    public void makeMove(Location loc){}




    @Override
    public Game copy() {
        Othello game = new Othello(graphics);
        copyTo(game);
        return game;
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
