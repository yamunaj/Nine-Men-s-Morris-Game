import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MinMaxImprovedOpening {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		try {
			String letter = "";
			ArrayList<String> input = new ArrayList<String>();
			ArrayList output = new ArrayList();
			String inputfilename = "";
			String outputfilename = "";
			int depth;
			BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
	        System.out.print("Enter Input file: \n");
	        inputfilename = br1.readLine();
	        System.out.print("Enter Output file: \n");
	        outputfilename = br1.readLine();
	        System.out.print("Enter depth: \n");
	        depth = Integer.parseInt(br1.readLine());
			FileInputStream fstream = new FileInputStream(inputfilename);
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  int charc;
		      while ((charc = br.read()) != -1) {
		    	  char c = (char) charc;
		        input.add(Character.toString(c));
		      }
		      br.close();

		      MorrisGame game = new MorrisGame(input, depth, "W");
		      int minmaxEstimate;
		      MorrisGame.count = 0;
		      minmaxEstimate = game.MaxMinImproved(depth, 0, input);
		      //output = game.gameMove(input);
		      /*FileOutputStream fstream1 = new FileOutputStream(outputfilename);
			  DataOutputStream out = new DataOutputStream(fstream1);
			  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));*/
		      /*FileOutputStream fileOut = new FileOutputStream(outputfilename);  
	             ObjectOutputStream oos = new ObjectOutputStream (fileOut);  */
		      //ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputfilename));
		      FileWriter out = new FileWriter(outputfilename);
              
	             
		      //PrintWriter out = new PrintWriter(new FileWriter(outputfilename));
		      ArrayList bestMove = new ArrayList();
		     /* if(depth%2 != 0)
		      {
		    	  bestMove = game.switchWhiteBlack(game.bestMove);
		    	  for (int i = 0; i < bestMove.size(); i++) {
	                   
	                  out.append((CharSequence) bestMove.get(i));

		    	  }
                  out.close();*/
			      /*oos.writeObject(bestMove.toString());
			      oos.close();*/
		    	  /*for( int j =0 ;j < bestMove.size(); j++)
		    	  bw.write(bestMove.get(j).toString());*/
		    	  //oos.writeObject (bestMove); 
		          //out.write(bestMove.toString());
		      /*  System.out.println("Output :"+bestMove);
		      }
		      else
		      {*/
		    	  System.out.println("Output :"+game.bestMove);
		    	  for (int i = 0; i < game.bestMove.size(); i++) {
	                   
	                  out.append((CharSequence) game.bestMove.get(i));

		    	  }
                  out.close();
/*			      oos.writeObject(game.bestMove.toString());
			      oos.close();*/
/*		    	  for( int j =0 ;j < game.bestMove.size(); j++)
		    	  bw.write(game.bestMove.get(j).toString());*/
		    	  //oos.writeObject (game.bestMove); 
		          //out.write(game.bestMove.toString());
		      //}

		      System.out.println("MinMax Estimate :"+minmaxEstimate);
		      System.out.println("Positions evaluated by static estimation : "+MorrisGame.count);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
