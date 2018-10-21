package ui;

public abstract class WinDecorator implements CheckWinCon {

	protected CheckWinCon checkWin;
	public WinDecorator(CheckWinCon checkWin) {
		this.checkWin = checkWin;
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean checkWinCondition(Game game) {
		if(checkWin.checkWinCondition(game) == false){
			return false;
		}
		return true;
	}

}
