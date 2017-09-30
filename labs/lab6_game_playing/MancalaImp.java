/**
 * An AI for a game of Mancala implemented using a minimax search algorithm.
 * @author Abrar Amin (21518928@student.uwa.edu.au)
 */
public class MancalaImp implements MancalaAgent
{

	private int depth;
	private String agentName;


	public MancalaImp(int inDepth, String inAgentName)
	{
		if(inDepth > 0)
			depth = inDepth;
		else
		{
			System.err.println("invalid depth! set to defaults [6]");
			depth = 6;
		}

		agentName = inAgentName;
	}


	public MancalaImp()
	{
		depth = 6;
		agentName = "MANC_HAL by abraram";
	}


	/**
	 * Evaluate a score for player incase maximum depth has been reached.
	 */
	private int evalScore(int[] b)
	{
		int score = 0;
		
		score+=2*(b[6] - b[13]); //give some weight to having max. number of seeds in store.

		int seedsPlayerOne = 0;
		int seedsPlayerTwo = 0;
		
		//eval func accounts for whether there exists more capturable positions.
		for(int i = 0; i < 6; i++)
		{
			if(b[i] == 0){
				score+=3*b[12-i]; //number of seeds that could potentially be captured.
			}
			seedsPlayerOne+=b[i];
		}

		for(int j = 7; j < 13; j++)
		{
			if(b[j] == 0){
				score-=3*b[12-j];
			}
			seedsPlayerTwo+=b[j];
		}	
		
		//which player has more seeds on their board..
		score+=2*(seedsPlayerOne - seedsPlayerTwo);
	}

	private int getGameWinner(int[] b)
	{
		if(b[6] > b[13]) {return 1;}
		else if(b[13] > b[6]) {return 2;}
		else {return 0;}
	}

	/**
	 * A method that looks to see if the game is currently over, if so return an integer that
	 * represents the winner.
	 * @param gameBoard showing the current state of the game.
	 * @return -1 if game is Active, 1 if player1 (this agent) wins, 2 if player2 (opponent) wins
	 * and 0 if match is tied. 
	 */
	private int checkForWin(int[] gameBoard)
	{
		int gameResult = -1;
		boolean gameOver = true;
		//Check if player1 ended the game by exhausting his/her houses.
		for(int i = 0; i < 6; i++)
		{
			gameOver = (gameOver && (gameBoard[i] == 0));
		}
		if(gameOver)
		{
			for(int j = 7; j < 13; j++)
			{
				gameBoard[13]+=gameBoard[j];
				gameBoard[j] = 0;
			}
			gameResult = getGameWinner(gameBoard);	
			return gameResult;
		}
		//check if player2 ended the game by exhausting his/her houses.
		gameOver = true;
		for(int j = 7; j < 13; j++)
		{
			gameOver = (gameOver && (gameBoard[j] == 0));
		}
		if(gameOver)
		{
			for(int i = 0; i < 6; i++)
			{
				gameBoard[6]+=gameBoard[i];
				gameBoard[i] = 0;
			}
			gameResult = getGameWinner(gameBoard);
			return gameResult;
		}
		return gameResult;
	}

	 /**
 	* Allows the agent to nominate the house the agent would like to move seeds from. 
 	* The agent will allways have control of houses 0-5 with store at 6. 
 	* Any move other than 0-5 will result in a forfeit. 
 	* An move from an empty house will result in a forfeit.
	 * A legal move will always be available.
 	* Assume your agent has 0.5 seconds to make a move. 
 	* @param board the current state of the game. 
 	* The board is an int array of length 14, indicating the 12 houses and 2 stores. 
 	* The agent's house are 0-5 and their store is 6. 
	* The opponents houses are 7-12 and their store is 13. Board[i] is the number of seeds in house (store) i.
 	* board[(i+1}%14] is the next house (store) anticlockwise from board[i].  
 	* This will be consistent between moves of a normal game so the agent can maintain a strategy space.
 	* @return the house the agent would like to move the seeds from this turn.
 	*/
 	public int move(int[] board)
 	{
		int winner = checkForWin(int[] board); //-1 if no one, 1 if player, 2 if opp. 3 if tie.
		//if game state is inactive.
		if(winner != -1){
			if(winner == 1) 
				return Integer.MAX_VALUE;
			else if(winner == 2)
				return Integer.MIN_VALUE;
			else
				return 0;
		}
		//max depth reached, but game is still active.. Return the best move now.
		if(depth = 0)
		{
			return generateScore(int[] board);
		}
		
		
		
		
		return 0;
	}


	/**
	* The agents name.
	* @return a hardcoded string, the name of the agent.
	*/
	public String name()
	{
		  return agentName;
	}

	/**
	* A method to reset the agent for a new game.
	*/
	public void reset()
	{

	}


}

