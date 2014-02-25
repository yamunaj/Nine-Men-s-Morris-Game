import java.util.ArrayList;


public class MorrisGame {

	Board board;
	int depth;
	public static int count;
	ArrayList bestMove;
	//public static ArrayList bestMove;
	public MorrisGame(ArrayList<String> input, int depth, String Player)
	{
		if(Player.equals("W"))
		{
			board = new Board(input);
		}
		else if(Player.equals("B"))
		{
			ArrayList<String> newInput =  new ArrayList<String>();
			newInput = this.switchWhiteBlack(input);
			board = new Board(newInput);
		}
		this.depth = depth;
		count = 0;
		//board.printBoardPosition();
	}
	public ArrayList gameMoveOpening(ArrayList input, String moveOf)
	{
		ArrayList output = new ArrayList();

		MoveGenerator movegenerator = new MoveGenerator();
		output = movegenerator.generateMovesOpening(input, moveOf);
		//System.out.println("Inside game move: "+output);
		return output;
	}
	public ArrayList gameMove(ArrayList input,  String moveOf)
	{
		ArrayList output = new ArrayList();
		MoveGenerator movegenerator = new MoveGenerator();
		output = movegenerator.generateMovesMidgameEndgame(input, moveOf);
		//System.out.println("Inside game move: "+output);
		return output;
	}
	@SuppressWarnings("rawtypes")
	public int MaxMin(int maxDepth, int currentDepth,ArrayList input)
	{
		int value = 0;
		//ArrayList bestMove = new ArrayList();
		if(maxDepth == currentDepth)
		{
			value = staticEstimationOpening(input);
			return value;
		}
		else
		{
			//System.out.println("Current Depth : "+currentDepth);
			value = Integer.MIN_VALUE;
			ArrayList output = new ArrayList();
			output = gameMoveOpening(input, "W");
			//System.out.println("inside Maxmin Output :" +output.size());
			for(int i = 0; i < output.size(); i++)
			{
				//System.out.println("inside Maxmin "+i+"th Output :" +output.get(i));
				int temp = MinMax(maxDepth, currentDepth+1, (ArrayList) output.get(i));
				if(value < temp)
				{
					value = temp;
					//bestMove.clear();
					if(currentDepth == 0)
						bestMove = (ArrayList) output.get(i);
				}
			}
			return value;
		}
	}
	public int MaxMinGame(int maxDepth, int currentDepth,ArrayList input)
	{
		int value;
		//ArrayList bestMove = new ArrayList();
		if(maxDepth == currentDepth)
		{
			value = staticEstimationGame(input, maxDepth);
			return value;
		}
		else
		{
			//System.out.println("Current Depth : "+currentDepth);
			value = Integer.MIN_VALUE;
			ArrayList output = new ArrayList();
			output = gameMove(input, "W");
			//System.out.println("inside Maxmin Output :" +output.size());
			for(int i = 0; i < output.size(); i++)
			{
				//System.out.println("inside Maxmin "+i+"th Output :" +output.get(i));
				int temp = MinMaxGame(maxDepth, currentDepth+1, (ArrayList) output.get(i));
				if(value < temp)
				{
					value = temp;
					//bestMove.clear();
					if(currentDepth == 0)
						bestMove = (ArrayList) output.get(i);
				}
			}
			return value;
		}
	}
	public int MinMax(int maxDepth, int currentDepth,ArrayList input)
	{
		int value;
		//ArrayList bestMove = new ArrayList();
		if(maxDepth == currentDepth)
		{
			value = staticEstimationOpening(input);
			return value;
		}
		else
		{
			//System.out.println("Current Depth : "+currentDepth);
			value = Integer.MAX_VALUE;
			ArrayList output = new ArrayList();
			/*ArrayList tempb = new ArrayList();
			ArrayList tempw = new ArrayList();
			tempb = switchWhiteBlack(input); 
			tempw = gameMoveOpening(tempb, moveOf);
			output = switchBlackWhite(tempw);*/
			//System.out.println("inside minmax Output :" +output);
			output = gameMoveOpening(input, "B");
			for(int i = 0; i < output.size(); i++)
			{
				int temp = MaxMin(maxDepth, currentDepth+1, (ArrayList) output.get(i));
				if(value > temp)
				{
					value = temp;
					//bestMove.clear();
					//bestMove = (ArrayList) output.get(i);
				}
			}
			return value;
		}
	}
	public int MinMaxGame(int maxDepth, int currentDepth,ArrayList input)
	{
		int value;
		//ArrayList bestMove = new ArrayList();
		if(maxDepth == currentDepth)
		{
			value = staticEstimationGame(input, maxDepth);
			return value;
		}
		else
		{
			//System.out.println("Current Depth : "+currentDepth);
			value = Integer.MAX_VALUE;
			ArrayList output = new ArrayList();
			/*ArrayList tempb = new ArrayList();
			ArrayList tempw = new ArrayList();
			tempb = switchWhiteBlack(input); 
			tempw = gameMove(tempb, moveOf);
			for(int j =0 ; j < tempw.size(); j++)
				output.add(switchWhiteBlack((ArrayList) tempw.get(j)));*/
			//output = switchBlackWhite(tempw);
			//System.out.println("inside minmax Output :" +output);
			output = gameMove(input, "B");
			for(int i = 0; i < output.size(); i++)
			{
				int temp = MaxMinGame(maxDepth, currentDepth+1, (ArrayList) output.get(i));
				if(value > temp)
				{
					value = temp;
					//bestMove.clear();
					//bestMove = (ArrayList) output.get(i);
				}
			}
			return value;
		}
	}
	//Switch white to black
	public ArrayList switchWhiteBlack(ArrayList input)
	{
		for(int i = 0; i< input.size(); i++)
		{
			if(input.get(i).equals("W") == true)
				input.set(i, "B");
			else if(input.get(i).equals("B") == true)
				input.set(i, "W");
		}
		return input;
	}
	//Switch black to white
	public ArrayList switchBlackWhite(ArrayList positions)
	{
		for(int i = 0; i < positions.size(); i++)
		{
			ArrayList<String> temp = new ArrayList<String>();
			temp = (ArrayList<String>) positions.get(i);
			for(int j = 0; j < temp.size(); j++)
			{
				if(temp.get(j).equals("W") == true)
					temp.set(j, "B");
				else if(temp.get(j).equals("B") == true)
					temp.set(j, "W");
			}
			positions.set(i, temp);
		}
		return positions;
	}
	private int staticEstimationOpening(ArrayList<String> input)
	{
		int nWhite = 0, nBlack = 0;
		for(int i = 0; i < input.size(); i++)
		{
			//System.out.println("size :"+input.size());
			if(input.get(i).equals("W") == true)
			{
				nWhite++;
			}
			else if(input.get(i).equals("B") == true)
			{
				nBlack++;
			}
		}
		count++;
		return (nWhite - nBlack);
	}
	private int staticEstimationGame(ArrayList<String> input, int depth)
	{
		int nWhite = 0, nBlack = 0, nBlackMoves, nWhiteMoves;
		count++;
		for(int i = 0; i < input.size(); i++)
		{
			//System.out.println("size :"+input.size());
			if(input.get(i).equals("W") == true)
			{
				nWhite++;
			}
			else if(input.get(i).equals("B") == true)
			{
				nBlack++;
			}
		}
		ArrayList output = new ArrayList();
		/*ArrayList tempb = new ArrayList();
		ArrayList tempw = new ArrayList();
		tempb = switchWhiteBlack(input);*/
		//Board b1 = new Board(input);

		MoveGenerator gen = new MoveGenerator();
		output = gen.generateMovesMidgameEndgame(input,"B");
		/*output = switchBlackWhite(tempw);*/
		nBlackMoves = output.size();
		if(nBlack <= 2)
			return 10000;
		else if(nWhite <= 2)
			return -10000;
		else if(nBlackMoves == 0)
			return 10000;
		else
			return (1000 * (nWhite - nBlack) - nBlackMoves);
	}
	public int alphaBetaOpening(int maxDepth, int currentDepth, ArrayList<String> input, int a, int b) 
	{
		int value;
		//ArrayList bestMove = new ArrayList();
		if(maxDepth == currentDepth)
		{
			value = staticEstimationOpening(input);
			return value;
		}
		else
		{
			//System.out.println("Current Depth : "+currentDepth);
			value = Integer.MIN_VALUE;
			ArrayList output = new ArrayList();
			output = gameMoveOpening(input, "W");
			//System.out.println("inside Maxmin Output :" +output.size());
			for(int i = 0; i < output.size(); i++)
			{
				//System.out.println("inside Maxmin "+i+"th Output :" +output.get(i));
				int temp = betaAlphaOpening(maxDepth, currentDepth+1, (ArrayList) output.get(i),a,b);
				if(value < temp)
				{
					value = temp;
					//bestMove.clear();
					if(currentDepth == 0)
						bestMove = (ArrayList) output.get(i);
				}
				if(value >= b)
					return value;
				else
				{
					if(value > a)
						a = value;
				}
			}
			return value;
		}
	}
	public int betaAlphaOpening(int maxDepth, int currentDepth, ArrayList<String> input, int a, int b) 
	{
		int value;
		//ArrayList bestMove = new ArrayList();
		if(maxDepth == currentDepth)
		{
			value = staticEstimationOpening(input);
			return value;
		}
		else
		{
			//System.out.println("Current Depth : "+currentDepth);
			value = Integer.MAX_VALUE;
			ArrayList output = new ArrayList();
			/*ArrayList tempb = new ArrayList();
			ArrayList tempw = new ArrayList();
			tempb = switchWhiteBlack(input); 
			tempw = gameMove(tempb);
			output = switchBlackWhite(tempw);*/
			//System.out.println("inside Maxmin Output :" +output.size());
			output = gameMoveOpening(input, "B");
			for(int i = 0; i < output.size(); i++)
			{
				//System.out.println("inside Maxmin "+i+"th Output :" +output.get(i));
				int temp = alphaBetaOpening(maxDepth, currentDepth+1, (ArrayList) output.get(i),a,b);
				if(value > temp)
				{
					value = temp;
					//bestMove.clear();
					//bestMove = (ArrayList) output.get(i);
				}
				if(value <= a)
					return value;
				else
				{
					if(value < b)
						b = value;
				}
			}
			return value;
		}
	}
	public int alphaBetaGame(int maxDepth, int currentDepth, ArrayList<String> input, int a, int b) 
	{
		int value;
		//ArrayList bestMove = new ArrayList();
		if(maxDepth == currentDepth)
		{
			value = staticEstimationGame(input, maxDepth);
			return value;
		}
		else
		{
			//System.out.println("Current Depth : "+currentDepth);
			value = Integer.MIN_VALUE;
			ArrayList output = new ArrayList();
			output = gameMove(input, "W");
			//System.out.println("inside Maxmin Output :" +output.size());
			for(int i = 0; i < output.size(); i++)
			{
				//System.out.println("inside Maxmin "+i+"th Output :" +output.get(i));
				int temp = betaAlphaGame(maxDepth, currentDepth+1, (ArrayList) output.get(i),a,b);
				if(value < temp)
				{
					value = temp;
					//bestMove.clear();
					if(currentDepth == 0)
						bestMove = (ArrayList) output.get(i);
				}
				if(value >= b)
					return value;
				else
				{
					if(value > a)
						a = value;
				}
			}
			return value;
		}
	}
	public int betaAlphaGame(int maxDepth, int currentDepth, ArrayList<String> input, int a, int b) 
	{
		int value;
		//ArrayList bestMove = new ArrayList();
		if(maxDepth == currentDepth)
		{
			value = staticEstimationGame(input, maxDepth);
			return value;
		}
		else
		{
			//System.out.println("Current Depth : "+currentDepth);
			value = Integer.MAX_VALUE;
			ArrayList output = new ArrayList();
			/*ArrayList tempb = new ArrayList();
			ArrayList tempw = new ArrayList();
			tempb = switchWhiteBlack(input); 
			tempw = gameMove(tempb);
			output = switchBlackWhite(tempw);*/
			//System.out.println("inside Maxmin Output :" +output.size());
			output = gameMove(input, "B");
			for(int i = 0; i < output.size(); i++)
			{
				//System.out.println("inside Maxmin "+i+"th Output :" +output.get(i));
				int temp = alphaBetaGame(maxDepth, currentDepth+1, (ArrayList) output.get(i),a,b);
				if(value > temp)
				{
					value = temp;
					//bestMove.clear();
					//bestMove = (ArrayList) output.get(i);
				}
				if(value <= a)
					return value;
				else
				{
					if(value < b)
						b = value;
				}
			}
			return value;
		}
	}
	private int staticEstimationImproveOpening(ArrayList<String> input)
	{
		int value = 1000;
		int nWhite = 0, nBlack = 0, nBlackMoves = 0, blackMillCount = 0;
		count++;
		for(int i = 0; i < input.size(); i++)
		{
			//System.out.println("size :"+input.size());
			if(input.get(i).equals("W") == true)
			{
				nWhite++;
			}
			else if(input.get(i).equals("B") == true)
			{
				nBlack++;
			}
		}
		ArrayList output = new ArrayList();
		/*ArrayList tempb = new ArrayList();
		ArrayList tempw = new ArrayList();
		tempb = switchWhiteBlack(input);*/
		//Board b1 = new Board(input);

		MoveGenerator gen = new MoveGenerator();
		output = gen.generateMovesMidgameEndgame(input,"B");
		/*output = switchBlackWhite(tempw);*/
		nBlackMoves = output.size();
		for(int j = 0; j < nBlackMoves; j++)
		{
			boolean flag = false;
			ArrayList a = new ArrayList();
			a = (ArrayList) output.get(j);
			for( int k=0; k<a.size(); k++)
			{
				flag = gen.closeMillBlack(a, k);
				if(flag == true)
				{
					blackMillCount++;
				}
				flag = false;
			}
		}
		if(nBlackMoves == 0)
			return 10000;
		else
			return (1000 * (nWhite - nBlack) - nBlackMoves - (100*blackMillCount));

	}
	private int staticEstimationImproveGame(ArrayList<String> input, int depth)
	{
		int nWhite = 0, nBlack = 0, nBlackMoves = 0, blackMillCount = 0;
		count++;
		for(int i = 0; i < input.size(); i++)
		{
			//System.out.println("size :"+input.size());
			if(input.get(i).equals("W") == true)
			{
				nWhite++;
			}
			else if(input.get(i).equals("B") == true)
			{
				nBlack++;
			}
		}
		ArrayList output = new ArrayList();
		/*ArrayList tempb = new ArrayList();
		ArrayList tempw = new ArrayList();
		tempb = switchWhiteBlack(input);*/
		//Board b1 = new Board(input);

		MoveGenerator gen = new MoveGenerator();
		output = gen.generateMovesMidgameEndgame(input,"B");
		/*output = switchBlackWhite(tempw);*/
		nBlackMoves = output.size();
		for(int j = 0; j < nBlackMoves; j++)
		{
			boolean flag = false;
			ArrayList a = new ArrayList();
			a = (ArrayList) output.get(j);
			for( int k=0; k<a.size(); k++)
			{
				flag = gen.closeMillBlack(a, k);
				if(flag == true)
				{
					blackMillCount++;
				}
				flag = false;
			}
		}
		if(nBlack <= 2)
			return 10000;
		else if(nWhite <= 2)
			return -10000;
		else if(nBlackMoves == 0)
			return 10000;
		else
			return (1000 * (nWhite - nBlack) - nBlackMoves - (100*blackMillCount));
	}
	public int MaxMinImproved(int maxDepth, int currentDepth,ArrayList input)
	{
		int value = 0;
		//ArrayList bestMove = new ArrayList();
		if(maxDepth == currentDepth)
		{
			value = staticEstimationImproveOpening(input);
			return value;
		}
		else
		{
			//System.out.println("Current Depth : "+currentDepth);
			value = Integer.MIN_VALUE;
			ArrayList output = new ArrayList();
			output = gameMoveOpening(input, "W");
			//System.out.println("inside Maxmin Output :" +output.size());
			for(int i = 0; i < output.size(); i++)
			{
				//System.out.println("inside Maxmin "+i+"th Output :" +output.get(i));
				int temp = MinMaxImproved(maxDepth, currentDepth+1, (ArrayList) output.get(i));
				if(value < temp)
				{
					value = temp;
					//bestMove.clear();
					if(currentDepth == 0)
						bestMove = (ArrayList) output.get(i);
				}
			}
			return value;
		}
	}
	public int MinMaxImproved(int maxDepth, int currentDepth,ArrayList input)
	{
		int value;
		//ArrayList bestMove = new ArrayList();
		if(maxDepth == currentDepth)
		{
			value = staticEstimationImproveOpening(input);
			return value;
		}
		else
		{
			//System.out.println("Current Depth : "+currentDepth);
			value = Integer.MAX_VALUE;
			ArrayList output = new ArrayList();
			/*ArrayList tempb = new ArrayList();
			ArrayList tempw = new ArrayList();
			tempb = switchWhiteBlack(input); 
			tempw = gameMoveOpening(tempb, moveOf);
			output = switchBlackWhite(tempw);*/
			//System.out.println("inside minmax Output :" +output);
			output = gameMoveOpening(input, "B");
			for(int i = 0; i < output.size(); i++)
			{
				int temp = MaxMinImproved(maxDepth, currentDepth+1, (ArrayList) output.get(i));
				if(value > temp)
				{
					value = temp;
					//bestMove.clear();
					//bestMove = (ArrayList) output.get(i);
				}
			}
			return value;
		}
	}
	public int MaxMinGameImproved(int maxDepth, int currentDepth,ArrayList input)
	{
		int value;
		//ArrayList bestMove = new ArrayList();
		if(maxDepth == currentDepth)
		{
			value = staticEstimationImproveGame(input, maxDepth);
			return value;
		}
		else
		{
			//System.out.println("Current Depth : "+currentDepth);
			value = Integer.MIN_VALUE;
			ArrayList output = new ArrayList();
			output = gameMove(input, "W");
			//System.out.println("inside Maxmin Output :" +output.size());
			for(int i = 0; i < output.size(); i++)
			{
				//System.out.println("inside Maxmin "+i+"th Output :" +output.get(i));
				int temp = MinMaxGameImproved(maxDepth, currentDepth+1, (ArrayList) output.get(i));
				if(value < temp)
				{
					value = temp;
					//bestMove.clear();
					if(currentDepth == 0)
						bestMove = (ArrayList) output.get(i);
				}
			}
			return value;
		}
	}
	public int MinMaxGameImproved(int maxDepth, int currentDepth,ArrayList input)
	{
		int value;
		//ArrayList bestMove = new ArrayList();
		if(maxDepth == currentDepth)
		{
			value = staticEstimationImproveGame(input, maxDepth);
			return value;
		}
		else
		{
			//System.out.println("Current Depth : "+currentDepth);
			value = Integer.MAX_VALUE;
			ArrayList output = new ArrayList();
			/*ArrayList tempb = new ArrayList();
			ArrayList tempw = new ArrayList();
			tempb = switchWhiteBlack(input); 
			tempw = gameMove(tempb, moveOf);
			for(int j =0 ; j < tempw.size(); j++)
				output.add(switchWhiteBlack((ArrayList) tempw.get(j)));*/
			//output = switchBlackWhite(tempw);
			//System.out.println("inside minmax Output :" +output);
			output = gameMove(input, "B");
			for(int i = 0; i < output.size(); i++)
			{
				int temp = MaxMinGameImproved(maxDepth, currentDepth+1, (ArrayList) output.get(i));
				if(value > temp)
				{
					value = temp;
					//bestMove.clear();
					//bestMove = (ArrayList) output.get(i);
				}
			}
			return value;
		}
	}
}
