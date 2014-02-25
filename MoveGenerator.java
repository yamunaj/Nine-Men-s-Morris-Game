import java.util.ArrayList;


public class MoveGenerator {
	public ArrayList generateMovesOpening(ArrayList input, String moveOf)
	{
		ArrayList<String> l = new ArrayList<String>(21);
		l = generateAdd(input, moveOf);
		//System.out.println("inside generateMovesOpening : " +l);
		return l;
	}
	@SuppressWarnings("unchecked")
	public ArrayList generateMovesMidgameEndgame(ArrayList input, String moveOf)
	{
		ArrayList l = new ArrayList(21);
		/*ArrayList b = new ArrayList(21);
		b = board.getBoardPosition();*/
		int wCount = 0;
		for(int i = 0; i < input.size(); i++)
		{
			if(input.get(i).equals(moveOf) == true)
				wCount++;
		}
		if(wCount <= 2)
		{
			l.add(input);
		}
		else if(wCount == 3)
		{
			l = generateHopping(input, moveOf);
		}
		else
		{
			l = generateMove(input, moveOf);
		}

		//System.out.println("inside generateMovesOpening : " +l);
		return l;
	}
	public ArrayList generateMove(ArrayList input, String moveOf)
	{
		ArrayList L = new ArrayList(21);
		/*ArrayList<String> boardPosition = new ArrayList<String>(21);
		boardPosition = board.getBoardPosition();*/
		int r = 0;

		for(int j = 0 ; j < input.size(); j++)
		{
			if(input.get(j).equals(moveOf) == true)
			{
				ArrayList n = new ArrayList();
				n = neighbors(j);
				int nb;
				for(int k = 0;k < n.size(); k++)
				{
					nb = (int) n.get(k);
					if(input.get(nb).equals("x") == true)
					{
						int p = 0;
						ArrayList<String> b = new ArrayList<String>(21);
						for(p = 0 ; p < input.size() ; p++)
						{
							String t = new String();
							t = (String) input.get(p);
							//b.remove(p);
							b.add(p,t);
						}
						b.remove(j);
						b.add(j, "x");
						b.remove(nb);
						b.add(nb, moveOf);
						if(closeMill(b, nb) == true)
							L = generateRemove(b, L, moveOf);
						else
						{
							//int size = b.size();
							L.add(b);	
							//r++;
						}
					}
				}
			}
		}
		return L;
	}
	public ArrayList neighbors(int j)
	{
		@SuppressWarnings("rawtypes")
		ArrayList listN = new ArrayList();
		switch(j)
		{
		case 0:
			listN.add(1);
			listN.add(2);
			listN.add(6);
			return listN;
		case 1:
			listN.add(0);
			listN.add(11);
			return listN;
		case 2:
			listN.add(0);
			listN.add(3);
			listN.add(4);
			listN.add(7);
			return listN;
		case 3:
			listN.add(2);
			listN.add(10);
			return listN;
		case 4:
			listN.add(2);
			listN.add(5);
			listN.add(8);
			return listN;
		case 5:
			listN.add(4);
			listN.add(9);
			return listN;
		case 6:
			listN.add(0);
			listN.add(7);
			listN.add(18);
			return listN;
		case 7:
			listN.add(2);
			listN.add(6);
			listN.add(8);
			listN.add(15);
			return listN;
		case 8:
			listN.add(4);
			listN.add(7);
			listN.add(12);
			return listN;
		case 9:
			listN.add(5);
			listN.add(10);
			listN.add(14);
			return listN;
		case 10:
			listN.add(3);
			listN.add(9);
			listN.add(11);
			listN.add(17);
			return listN;
		case 11:
			listN.add(1);
			listN.add(10);
			listN.add(20);
			return listN;
		case 12:
			listN.add(8);
			listN.add(13);
			return listN;
		case 13:
			listN.add(12);
			listN.add(14);
			listN.add(16);
			return listN;
		case 14:
			listN.add(9);
			listN.add(13);
			return listN;
		case 15:
			listN.add(7);
			listN.add(16);
			return listN;
		case 16:
			listN.add(13);
			listN.add(15);
			listN.add(17);
			listN.add(19);
			return listN;
		case 17:
			listN.add(10);
			listN.add(16);
			return listN;
		case 18:
			listN.add(6);
			listN.add(19);
			return listN;
		case 19:
			listN.add(16);
			listN.add(18);
			listN.add(20);
			return listN;
		case 20:
			listN.add(11);
			listN.add(19);
			return listN;
		}
		return listN;
	}
	public ArrayList generateHopping(ArrayList input, String moveOf)
	{
		ArrayList L = new ArrayList(21);
		/*ArrayList<String> boardPosition = new ArrayList<String>(21);
		boardPosition = board.getBoardPosition();*/
		int r = 0;
		for(int j = 0 ; j < input.size(); j++)
		{			
			if(input.get(j).equals(moveOf) == true)
			{
				for(int k = 0; k < input.size(); k++)
				{
					if(input.get(k).equals("x") == true)
					{
						int p = 0;
						ArrayList<String> b = new ArrayList<String>(21);
						for(p = 0 ; p < input.size() ; p++)
						{
							String t = new String();
							t = (String) input.get(p);
							//b.remove(p);
							b.add(p,t);
						}
						b.remove(j);
						b.add(j, "x");
						b.remove(k);
						b.add(k, moveOf);
						if(closeMill(b, k) == true)
							L = generateRemove(b, L, moveOf);
						else
						{
							//int size = b.size();
							L.add(b);	
							//r++;
						}
					}
				}
			}
		}
		return L;
	}
	@SuppressWarnings("unchecked")
	public ArrayList generateAdd(ArrayList input, String moveOf)
	{
		//System.out.println("inside generate add");
		//board.printBoardPosition();
		ArrayList L = new ArrayList(21);
		//ArrayList<String> boardPosition = new ArrayList<String>(21);
		//boardPosition = input;
		//System.out.println("Inside generate Add "+boardPosition.size());
		//System.out.println("Board Position : "+boardPosition);
		int i = input.size();
		int j =0, r= 0;
		//System.out.println("outside generate add loop : "+board.getBoardPosition());
		for(j = 0 ; j < input.size(); j++)
		{			
			//System.out.println("inside generate add loop : "+boardPosition.get(j));
			if(input.get(j).equals("x") == true)
			{
				//System.out.println("Inside if : "+boardPosition.get(j));
				//copy board position
				int p = 0;
				ArrayList<String> b = new ArrayList<String>(21);
				for(p = 0 ; p < i ; p++)
				{
					String t = new String();
					t = (String) input.get(p);
					//b.remove(p);
					b.add(p,t);
				}
				//b = (ArrayList<String>) boardPosition.clone();
				//System.out.println("b-size, i : "+b.size()+ "," +i);
				//b = boardPosition;
				b.remove(j);
				b.add(j, moveOf);
				
				//b.set(j, "W");
				//boardPosition = board.getBoardPosition();
				//System.out.println("b,j : "+b+", "+j);
				//System.out.println("boardPosition : "+boardPosition);
				//System.out.println("closemill : "+closeMill(b, j));
				if(closeMill(b, j) == true)
					L = generateRemove(b, L, moveOf);
				else
				{
					int size = b.size();
					L.add(r,b);	
					r++;
				}
			}
			/*int q = 0, count = 0;
			while(count >= 21)
			{
				b.remove(q);
				count++;
			}*/
			//b.clear();
		}
		//System.out.println("L :"+L);
		return L;
	}
	public boolean closeMill(ArrayList<?> b, int k)
	{
		String C = (String) b.get(k);
		if ( C.equals("x") == false)
		{
			switch(k)
			{
			case 0 :
				if((b.get(6).equals(C) && b.get(18).equals(C))|| (b.get(2).equals(C) && b.get(4).equals(C)))
					return true;
				else
					return false;
			case 1 :
				if((b.get(11).equals(C) && b.get(20).equals(C)))
					return true;
				else
					return false;
			case 2 :
				if((b.get(0).equals(C) && b.get(4).equals(C))|| (b.get(7).equals(C) && b.get(15).equals(C)))
					return true;
				else
					return false;	
			case 3 :
				if((b.get(10).equals(C) && b.get(17).equals(C)))
					return true;
				else
					return false;
			case 4 :
				if((b.get(0).equals(C) && b.get(2).equals(C))|| (b.get(8).equals(C) && b.get(12).equals(C)))
					return true;
				else
					return false;
			case 5 :
				if((b.get(9).equals(C) && b.get(14).equals(C)))
					return true;
				else
					return false;
			case 6 :
				if((b.get(0).equals(C) && b.get(18).equals(C))|| (b.get(8).equals(C) && b.get(7).equals(C)))
					return true;
				else
					return false;
			case 7 :
				if((b.get(2).equals(C) && b.get(15).equals(C))|| (b.get(8).equals(C) && b.get(6).equals(C)))
					return true;
				else
					return false;
			case 8 :
				if((b.get(4).equals(C) && b.get(12).equals(C))|| (b.get(7).equals(C) && b.get(6).equals(C)))
					return true;
				else
					return false;
			case 9 :
				if((b.get(10).equals(C) && b.get(11).equals(C))|| (b.get(5).equals(C) && b.get(14).equals(C)))
					return true;
				else
					return false;
			case 10 :
				if((b.get(9).equals(C) && b.get(11).equals(C))|| (b.get(17).equals(C) && b.get(3).equals(C)))
					return true;
				else
					return false;
			case 11 :
				if((b.get(1).equals(C) && b.get(20).equals(C))|| (b.get(9).equals(C) && b.get(10).equals(C)))
					return true;
				else
					return false;
			case 12 :
				if((b.get(4).equals(C) && b.get(8).equals(C))|| (b.get(13).equals(C) && b.get(14).equals(C)))
					return true;
				else
					return false;
			case 13 :
				if((b.get(14).equals(C) && b.get(12).equals(C))|| (b.get(16).equals(C) && b.get(19).equals(C)))
					return true;
				else
					return false;
			case 14 :
				if((b.get(13).equals(C) && b.get(12).equals(C))|| (b.get(5).equals(C) && b.get(9).equals(C)))
					return true;
				else
					return false;
			case 15 :
				if((b.get(16).equals(C) && b.get(17).equals(C))|| (b.get(7).equals(C) && b.get(2).equals(C)))
					return true;
				else
					return false;
			case 16 :
				if((b.get(19).equals(C) && b.get(13).equals(C))|| (b.get(17).equals(C) && b.get(15).equals(C)))
					return true;
				else
					return false;
			case 17 :
				if((b.get(3).equals(C) && b.get(10).equals(C))|| (b.get(15).equals(C) && b.get(16).equals(C)))
					return true;
				else
					return false;
			case 18 :
				if((b.get(19).equals(C) && b.get(20).equals(C))|| (b.get(0).equals(C) && b.get(6).equals(C)))
					return true;
				else
					return false;
			case 19 :
				if((b.get(18).equals(C) && b.get(20).equals(C))|| (b.get(13).equals(C) && b.get(16).equals(C)))
					return true;
				else
					return false;
			case 20 :
				if((b.get(1).equals(C) && b.get(11).equals(C))|| (b.get(18).equals(C) && b.get(19).equals(C)))
					return true;
				else
					return false;
			}			
		}
		
			return false;		
	}
	public boolean closeMillBlack(ArrayList<?> b, int k)
	{
		String C = (String) b.get(k);
		if ( C.equals("B") == true)
		{
			switch(k)
			{
			case 0 :
				if((b.get(6).equals(C) && b.get(18).equals(C))|| (b.get(2).equals(C) && b.get(4).equals(C)))
					return true;
				else
					return false;
			case 1 :
				if((b.get(11).equals(C) && b.get(20).equals(C)))
					return true;
				else
					return false;
			case 2 :
				if((b.get(0).equals(C) && b.get(4).equals(C))|| (b.get(7).equals(C) && b.get(15).equals(C)))
					return true;
				else
					return false;	
			case 3 :
				if((b.get(10).equals(C) && b.get(17).equals(C)))
					return true;
				else
					return false;
			case 4 :
				if((b.get(0).equals(C) && b.get(2).equals(C))|| (b.get(8).equals(C) && b.get(12).equals(C)))
					return true;
				else
					return false;
			case 5 :
				if((b.get(9).equals(C) && b.get(14).equals(C)))
					return true;
				else
					return false;
			case 6 :
				if((b.get(0).equals(C) && b.get(18).equals(C))|| (b.get(8).equals(C) && b.get(7).equals(C)))
					return true;
				else
					return false;
			case 7 :
				if((b.get(2).equals(C) && b.get(15).equals(C))|| (b.get(8).equals(C) && b.get(6).equals(C)))
					return true;
				else
					return false;
			case 8 :
				if((b.get(4).equals(C) && b.get(12).equals(C))|| (b.get(7).equals(C) && b.get(6).equals(C)))
					return true;
				else
					return false;
			case 9 :
				if((b.get(10).equals(C) && b.get(11).equals(C))|| (b.get(5).equals(C) && b.get(14).equals(C)))
					return true;
				else
					return false;
			case 10 :
				if((b.get(9).equals(C) && b.get(11).equals(C))|| (b.get(17).equals(C) && b.get(3).equals(C)))
					return true;
				else
					return false;
			case 11 :
				if((b.get(1).equals(C) && b.get(20).equals(C))|| (b.get(9).equals(C) && b.get(10).equals(C)))
					return true;
				else
					return false;
			case 12 :
				if((b.get(4).equals(C) && b.get(8).equals(C))|| (b.get(13).equals(C) && b.get(14).equals(C)))
					return true;
				else
					return false;
			case 13 :
				if((b.get(14).equals(C) && b.get(12).equals(C))|| (b.get(16).equals(C) && b.get(19).equals(C)))
					return true;
				else
					return false;
			case 14 :
				if((b.get(13).equals(C) && b.get(12).equals(C))|| (b.get(5).equals(C) && b.get(9).equals(C)))
					return true;
				else
					return false;
			case 15 :
				if((b.get(16).equals(C) && b.get(17).equals(C))|| (b.get(7).equals(C) && b.get(2).equals(C)))
					return true;
				else
					return false;
			case 16 :
				if((b.get(19).equals(C) && b.get(13).equals(C))|| (b.get(17).equals(C) && b.get(15).equals(C)))
					return true;
				else
					return false;
			case 17 :
				if((b.get(3).equals(C) && b.get(10).equals(C))|| (b.get(15).equals(C) && b.get(16).equals(C)))
					return true;
				else
					return false;
			case 18 :
				if((b.get(19).equals(C) && b.get(20).equals(C))|| (b.get(0).equals(C) && b.get(6).equals(C)))
					return true;
				else
					return false;
			case 19 :
				if((b.get(18).equals(C) && b.get(20).equals(C))|| (b.get(13).equals(C) && b.get(16).equals(C)))
					return true;
				else
					return false;
			case 20 :
				if((b.get(1).equals(C) && b.get(11).equals(C))|| (b.get(18).equals(C) && b.get(19).equals(C)))
					return true;
				else
					return false;
			}			
		}
		
			return false;		
	}
	public ArrayList generateRemove(ArrayList<String> b, ArrayList L, String moveOf)
	{
		
		int bMillCount = 0, bCount = 0;
		boolean added = false;
		String removeOf = new String();
		if(moveOf.equals("W") == true)
			removeOf = "B";
		else
			removeOf = "W";
		//System.out.println("generateRemove size" +b.size());
		for(int p = 0; p < b.size(); p++)
		{
			if(b.get(p).equals(removeOf) == true)
			{
				bCount++;
				if(closeMill(b,p) == false)
				{
					ArrayList<String> c = new ArrayList<String>();
					for(int q = 0; q < b.size() ; q++)
					{
						String t = new String();
						t = b.get(q);
						//b.remove(p);
						c.add(q,t);
					}
					//c = (ArrayList<String>) b.clone();
					c.remove(p);
					c.add(p, "x");
					//c.set(p, "x");
					L.add(c);
					added = true;
				}
				/*else
				{
					bMillCount++;
				}*/
			}
			//c.clear();
		}
		/*if(bCount == bMillCount)
		{
			L.add(b);
		}*/
		if(added == false)
		{
			L.add(b);
		}
		return L;
	}
}
