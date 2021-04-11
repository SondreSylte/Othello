package game;

import inf101.grid.GridDirection;
import inf101.grid.Location;
import inf101.v20.sem2.terminal.ConsolePlayer;

public class ConnectFour extends Game {

	ConnectFour(GameBoard board, Graphics graphics) {
		super(board, graphics);
	}

	public ConnectFour(Graphics graphics, Player p1, Player p2) {
		this(graphics);
		players.add(p1);
		players.add(p2);
	}


	public ConnectFour(Graphics graphics) {
		this(new GameBoard(6,7), graphics);
	}

	@Override
	public boolean isWinner(Player p) {
		return board.countNumInRow(p)>=4;
	}

	@Override
	public boolean gameOver() {
		for(Player p : players) {
			if(isWinner(p)) {
				return true;
			}
		}
		
		if(board.isFull()) {
			return true;
		}
		
		return false;
	}

	public Location drop(int col) {
		Location loc = new Location(0,col);
		
		while(board.canPlace(loc) && !canPlace(loc)) {
			loc = loc.getNeighbor(GridDirection.SOUTH);
		}
		return loc;
	}

	@Override
	public boolean canPlace(Location loc, Player p) {
		return canPlace(board,loc,p);
	}
	public boolean canPlace(Location loc) {
		return canPlace(board,loc,getCurrentPlayer());
	}
	@Override
	public boolean canPlace(GameBoard board, Location loc, Player p) {
		return board.canPlace(loc) && !board.canPlace(loc.getNeighbor(GridDirection.SOUTH));
	}


	@Override
	protected Game copy() {
		ConnectFour game = new ConnectFour(graphics);
		copyTo(game);
		return game;
	}

	public static void main(String[] args) {
		Player player1 = new ConsolePlayer('X');
		Player player2 = new MiniMaxPlayer('O',4);
		Game game = new ConnectFour(new TerminalGraphics(), player1, player2);
		game.run();
	}

}