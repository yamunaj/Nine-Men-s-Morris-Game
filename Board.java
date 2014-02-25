import java.util.ArrayList;
import java.util.Arrays;


public class Board {
private ArrayList<String> boardPosition;
public Board(ArrayList input)
{
	//for(int i = 0; i < )
	this.boardPosition = input;
	//System.out.println("Input.size :"+input.size());
	//this.setBoardPosition(new ArrayList<String>(Arrays.asList("x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x")));
}
public ArrayList<String> getBoardPosition() {
	return boardPosition;
}
public void setBoardPosition(ArrayList<String> boardPosition) {
	this.boardPosition = boardPosition;
}
public void printBoardPosition()
{
	System.out.println(this.boardPosition);
}
}
