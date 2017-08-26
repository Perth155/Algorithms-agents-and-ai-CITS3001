import java.util.Arrays;

/**
 * A class that implements 1-0 knapsack and fractional knapsack.
 * @author Abrar Amin (21518928@student.uwa.edu.au)
 */
public class KnapsackImp implements Knapsack
{

		/**
		 * A private class used to store the index and the value pair in an array to keep 
		 * track of indices after array has being sorted.
		 */
		private class Pair implements Comparable<Pair>
		{
			int index;
			double val;

			public Pair(int i, double v)
			{
				this.index = i;
				this.val = v;
			}

			@Override
			public int compareTo(Pair p)
			{
				if(this.val == p.val)
					return 0;
				else if(this.val > p.val)
					return 1;
				return -1;
			}

		}

		/**
		 * implementation of 1-0 Knapsack using dynamic programming.
		 * @param weights the array of weights of each type of product available.
                 * @param values the array of values of each type of product available.
                 * @param capacity the size of the knapsack
                 * @return the greatest int less than or equal to the maximum possible value of the knapsack.
		 */
		public int discreteKnapsack(int[] weights, int[] values, int capacity)
		{
			int table[][] = new int[weights.length+1][capacity+1]; //adding 0 at start.

			for(int w = 0; w <= weights.length; w++) //account for the added 0.
			{
				for(int c = 0; c <= capacity; c++)
				{
					if(w == 0 || c == 0)
						table[w][c] =  0;
					else if(weights[w-1] > c) //do not add, as exceeding capacity.
						table[w][c] = table[w-1][c]; 
					else
						table[w][c] = Math.max( (table[w-1][c]), (values[w-1] + table[w-1][c-weights[w-1]]));

				}
			}
			return table[weights.length][capacity]; //bottom-right cell.
		 }


		/**
		 * Implementation of fractional Knapsack using a greedy approach.
		 * Choose whichever item gives us the most price per weight unit as
		 * each point. 
 		 * @param weights the array of weights of each type of product available.
  		 * @param values the array of values of each type of product available.
  		 * @param capacity the size of the knapsack
                 * @return the greatest int less than or equal to the maximum possible value of the knapsack.
		 */
		public int fractionalKnapsack(int[] weights, int[] values, int capacity)
		{
			int arrSiz = weights.length;

			Pair pricePerWeight[] = new Pair[arrSiz];
			
			for(int i = 0; i < arrSiz; i++)
			{
				pricePerWeight[i] = new Pair(i, (double)(values[i])/(double)(weights[i]));	
			}
			Arrays.sort(pricePerWeight);  //uses quick sort to sort in ascending order.
			
			double currentWeight = 0d;
			double currentVal = 0d;
			double remainingCap = (double)capacity;
			
			for(int i = arrSiz-1; i >= 0; i--)
			{
				if((double)weights[pricePerWeight[i].index] > remainingCap) 
				{
					currentVal += pricePerWeight[i].val*remainingCap;
					break;
				}
				else
				{
					currentVal += (double)values[pricePerWeight[i].index];
					remainingCap-=weights[pricePerWeight[i].index];
				}
			}
				
			return (int)currentVal;
		}



}

