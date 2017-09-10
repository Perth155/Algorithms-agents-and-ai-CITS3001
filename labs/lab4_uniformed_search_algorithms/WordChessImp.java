import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class WordChessImp implements WordChess
{

	 public WordChessImp(){}


	/**
	 * Perform binary search for a word in the dictionary.
	 * @param needle the word to be searched for.
	 * @param hayStack the dictionary
	 * @return true if word is found, false otherwise.
	 */
	private boolean binSearch(String needle, String hayStack[])
	{
		int start = 0;
		int endPt = hayStack.length-1;


		while(start <= endPt)
		{
			int midPt = (start+endPt)/2;
			if(needle.compareTo(hayStack[midPt]) > 0)
				start = midPt+1;
			else if(needle.compareTo(hayStack[midPt]) < 0)
				endPt = midPt-1;
			else
				return true;
		}

		return false;
	}

	/**
	 * Start with a tree of just the root node (original word), then we perform a BFS where
	 * a character change to the original word is a child of the parent word if it is a valid word.
	 * Worst Case Time Complexity : O(26*n*log(D)*X)
	 * @param dictionary The set of words that can be used in the sequence; all words in the dictionary are capitalised.
	 * @param startWord the first word on the sequence.
	 * @param endWord the last word in the sequence.
	 * @return an array containing a shortest sequence from startWord to endWord, in order,
	 * using only words from the dictionary that differ by a single character.
	 */
	public String[] findPath(String[] dictionary, String startWord, String endWord)
	{
		Queue<String> tree = new LinkedList<String>();
		List<String> wordSeqs = new ArrayList<String>();
		Set<String> visitedWords = new HashSet<String>();
		Map<String, String> parentChildPair = new HashMap<String, String>();
		boolean wordFound = false;

		tree.add(startWord);
		visitedWords.add(startWord);

		while(!tree.isEmpty() && !wordFound)
		{
			String start = tree.poll();

			for(int i  = 0; i < start.length(); i++)
			{
				for(char c = 'A'; c <= 'Z'; c++)
				{
					StringBuilder tmp = new StringBuilder(start);
					tmp.setCharAt(i, c);
					String tmpStr = tmp.toString();

					if(visitedWords.contains(tmpStr))
						continue;

					if(tmpStr.equals(endWord))
					{
						String ans[] = {start,tmpStr};
						wordFound = true;
					}

					if(binSearch(tmpStr, dictionary))
					{
						parentChildPair.put(tmpStr, start);
						tree.add(tmpStr);
						visitedWords.add(tmpStr);
					}

				}
			}
		}

		if(!wordFound)
			return null;

		String currentWord = endWord;

		while(!currentWord.equals(startWord))
		{
			wordSeqs.add(currentWord);
			currentWord = parentChildPair.get(currentWord);
		}
		wordSeqs.add(currentWord);
		String outputArr[] = new String[wordSeqs.size()];
		Collections.reverse(wordSeqs);
		wordSeqs.toArray(outputArr);
		return outputArr;
	}
}
