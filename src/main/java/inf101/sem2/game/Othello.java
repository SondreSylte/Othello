package inf101.sem2.game;


import inf101.grid.Location;
import inf101.sem2.player.Player;
import inf101.grid.GridDirection;
import inf101.sem2.player.PlayerList;
import inf101.sem2.game.GameBoard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Othello extends Game {

    /**
     *
     * @param graphics The graphics used to display the game for the human players
     * @param p1 - First player
     * @param p2 - Second player
     */

    public Othello(Graphics graphics, Player p1, Player p2) {
        super(new GameBoard(8,8), graphics);
        addPlayer(p1);
        addPlayer(p2);


        board.set(new Location(3,3), players.getCurrentPlayer());
        board.set(new Location(3,4), players.nextPlayer());
        board.set(new Location(4,3), players.getCurrentPlayer());
        board.set(new Location(4,4), players.nextPlayer());


    }

    public Othello(Graphics graphics) {
        super(new GameBoard(8,8),graphics);
    }

    public Othello(Graphics graphics, Iterable<Player> players) {
        super(new GameBoard(8, 8), graphics, players);

        board.set(new Location(3,3), getCurrentPlayer());
        board.set(new Location(3,4), nextPlayer());
        board.set(new Location(4,3), getCurrentPlayer());
        board.set(new Location(4,4), nextPlayer());

    }



    @Override
    public boolean canPlace(Location loc){
            for (GridDirection d : GridDirection.EIGHT_DIRECTIONS) {
                Location currentLoc = loc.getNeighbor(d);
                if (!board.isOnGrid(currentLoc)){
                    continue; }
                if (board.get(currentLoc) != getCurrentPlayer()) {
                    if (board.get(currentLoc) != null) {
                        while(board.isOnGrid(currentLoc.getNeighbor(d)) && !(board.get(currentLoc.getNeighbor(d))==null)){
                            currentLoc = currentLoc.getNeighbor(d);
                            if (board.get(currentLoc) == getCurrentPlayer()){
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
        /*
    @Override
    public boolean isWinner(Player player) {
        return false;
    }*/

    /*@Override
    public boolean isWinner(Player player) {
        int pieces = 0;
        int player1 = board.countNumInRow(getCurrentPlayer());
        int player2 = board.countNumInRow(nextPlayer());


            if (getPosMovesOthello().isEmpty())
                if (player1 > player2)
                    return true;

            if (board.isFull())
                for (Location l : board.locations())
                    pieces++;
            if (board.countNumOnBoard(player) > pieces / 2) {
                return true;
            }


        return false;
    }*/

    @Override
    public boolean isWinner(Player player) {
        int mainPlayer = 0;
        int opponent = 0;
        if(!board.isFull()){
            return false;
        }

        if (getPossibleMoves().isEmpty())
            return true;

        for(Location loc : board.locations()){
            if(board.get(loc) == player){
                mainPlayer++;
            }
            else if(board.get(loc) != null && board.get(loc) != player){
                opponent++;
            }
        }
        return mainPlayer > opponent;
    }

    public List<Location> getPossibleMoves() {
        ArrayList<Location> moves = new ArrayList<>();
        for(Location loc : board.locations()) {
            if(canPlace(loc)) {
                moves.add(loc);
            }
        }
        return moves;
    }


    public List<Location> getOpponentDisks(Location loc, GridDirection dir){
        Player player = getCurrentPlayer();
        List<Location> disks = new ArrayList<>();
        Location start = loc.getNeighbor(dir);
        while(board.isOnGrid(start) && (board.get(start) != player) && (board.get(start) != null )){
            disks.add(start);
            start = start.getNeighbor(dir);
            if (board.isOnGrid(start) && board.get(start) == player){
                disks.add(start);
                break;
            }
        }
        System.out.println(disks);
        return disks;
    }



    public void flipDisks(Location currentLoc){
        for(GridDirection dir : GridDirection.EIGHT_DIRECTIONS){
            List<Location> dirList = getOpponentDisks(currentLoc, dir);
            if (!dirList.isEmpty()){
                if(checkForCurrentPlayer(dirList)){
                    for(Location l : dirList){
                        flipDisk(l);
                    }
                }
            }
        }
    }

    public boolean checkForCurrentPlayer(List<Location> ls) {
        for (Location l : ls){
            if(board.get(l) == getCurrentPlayer()){
                return true;
            }
        }
        return false;
    }

    public void flipDisk(Location loc){
        board.flip(loc, getCurrentPlayer());
    }


    @Override
    public void makeMove(Location loc){
        if(!canPlace(loc)){
            throw new IllegalArgumentException("Can not make that move");
        }
        System.out.println("Possiblemoves: " + getPossibleMoves());
        board.set(loc,getCurrentPlayer());
        flipDisks(loc);
        nextPlayer();
    }


    @Override
    public Game copy() {
        Othello game = new Othello(graphics);
        copyTo(game);
        return game;
    }


    @Override
    public boolean gameOver() {
        for(Player p : players) {
            if(isWinner(p)) {
                return true;
            }
        }

        return board.isFull();
    }


    @Override
    public String getName() {
        return "Othello";
    }


}
