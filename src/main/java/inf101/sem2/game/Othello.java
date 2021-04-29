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
     * @param graphics Grafikken brukt for å displaye spillet for spillere
     * @param p1 - Første spiller
     * @param p2 - Motspiller
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

    /**
     * Sjekker først om det er en brikke på lokasjonen, i tilfellet returneres false.
     * Deretter sjekker den lokasjoner i alle retninger. Dersom en nabo er motsatt farge,
     * sjekkes de resterende naboene i samme retning og det returneres true dersom den møter på
     * en brikke i samme farge.
     * Posisjonen er da et lovlig trekk
     *
     * @param loc    - where to place
     * @return boolean
     */



    @Override
    public boolean canPlace(Location loc){
            if(!(board.get(loc) == null))
                return false;
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

    /**
     * Metoden sjekker først om brettet ikke er fullt. Om det er fullt, returneres false, da er det ingen vinner
     * foreløpig. Videre sjekker metoden for alle lokasjoner som player1 har og så hvor mange lokasjoner player 2 har.
     * Så sjekkes det hvem som har mest brikker på brettet.
     *
     * @param player
     * @return boolean
     */

    @Override
    public boolean isWinner(Player player) {
        int player1 = 0;
        int player2 = 0;
        if(!board.isFull()){
            return false;
        }

        if (getPossibleMoves().isEmpty())
            return true;

        for(Location loc : board.locations()){
            if(board.get(loc) == player){
                player1++;
            }
            else if(board.get(loc) != null && board.get(loc) != player){
                player2++;
            }
        }
        return player1 > player2;
    }

    /**
     * Bruker canPlace til å finne mulige lokasjoner der brikker kan plasseres. Lokasjonene klagres i en liste som
     * heter moves.
     *
     * @return moves
     */

    public List<Location> getPossibleMoves() {
        ArrayList<Location> moves = new ArrayList<>();
        for(Location loc : board.locations()) {
            if(canPlace(loc)) {
                moves.add(loc);
            }
        }
        return moves;
    }

    /**
     * Fra en lokasjon sjekkes alle lokasjoner på brettet i retning dir.
     * Alle brikker som skal flippes legges da til i en liste som returneres
     *
     * @param loc
     * @param dir
     * @return
     */

    public List<Location> getAffectedDisks(Location loc, GridDirection dir){
        Player player = getCurrentPlayer();
        List<Location> affectedDisks = new ArrayList<>();
        Location startLoc = loc.getNeighbor(dir);
        while(board.isOnGrid(startLoc) && (board.get(startLoc) != player) && (board.get(startLoc) != null )){
            affectedDisks.add(startLoc);
            startLoc = startLoc.getNeighbor(dir);
            if (board.isOnGrid(startLoc) && board.get(startLoc) == player){
                affectedDisks.add(startLoc);
                break;
            }
        }
        return affectedDisks;
    }

    /**
     * Metoden sjekker alle retninger og bruker getAffectedDisk til å lage en liste for lokasjoner som skal flippes
     * i den gitte retninger. Om Listen ikke er tom, sjekkes det om listen inneholder en lokasjon som har
     * currentPlayer i, da flippes lokasjonene mellom.
     * @param currentLoc
     */

    public void flipDisks(Location currentLoc){
        for(GridDirection dir : GridDirection.EIGHT_DIRECTIONS){
            List<Location> dirList = getAffectedDisks(currentLoc, dir);
            System.out.println(dirList);
            if (!dirList.isEmpty()){
                if(checkForCurrentPlayer(dirList)){
                    for(Location l : dirList){
                        board.flip(l, getCurrentPlayer());
                    }
                }
            }
        }
    }

    /**
     * Tar inn en liste og sjekker om den inneholder en lokasjon med typen samme spiller, altså currentPlayer.
     * @param ls
     * @return
     */
    public boolean checkForCurrentPlayer(List<Location> ls) {
        for (Location l : ls){
            if(board.get(l) == getCurrentPlayer()){
                return true;
            }
        }
        return false;
    }

    /**
     * Tar inn en lokasjon og sjekker først om en kan plassere en brikke i en lokasjon ved å bruke canPlace.
     * Om det er mulig å plassere, settes lokasjonen på brettet, så flippes dei påvirkede brikkene, før
     * nextPlayer() kalles, og det går videre til neste spiller.
     * @param loc
     */
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

    /**
     * gameOver metoden brukes når spillet er over, altså når isWinner kalles, eller når det ikke er flere mulige
     * moves å gjøre. Da kalles det på nextPlayer, og om den heller ikke har flere possibleMoves så er det game over.
     * Brukes også om brettet er fullt.
     * @return boolean
     */
    @Override
    public boolean gameOver() {
        for(Player p : players) {
            if(isWinner(p)) {
                return true;
            }
            if (getPossibleMoves().isEmpty()){
                players.nextPlayer();
                if (getPossibleMoves().isEmpty()){
                    return true;
                }
            }
        }

        return board.isFull();
    }


    @Override
    public String getName() {
        return "Othello";
    }


}
