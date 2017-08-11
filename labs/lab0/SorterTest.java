import java.util.HashMap;
import java.util.Map;


/**
 * A test class for the implemented sorting algorithm for comparison.
 * @author Abrar Amin
 */
public class SorterTest
{

	public static void main(String[] args)
	{
		int[] testArr;

      	if (args.length ==0)
      	{
      		testArr = new int[0];
         	printUsage();
      	}
		else if (args.length == 1)
			testArr = createTestArray(Integer.parseInt(args[0]));
		else{
	        	 /* convert arguments to longs */
	         testArr = new int[args.length];
	         for (int i = 0; i < args.length; i++)
	            	testArr[i] = Integer.parseInt(args[i]);
		}

      	callAllSortAlgorithms(testArr);
      	System.exit(0);
	}



	private static void callAllSortAlgorithms(int[] testArr)
	{
		Sorter s = new Sorter();
		System.out.println("Array to be tested: ");
		printArr(testArr);

		checkIfArraySorted(testArr);
		//insertion sort.
		int[] insArr = testArr.clone();
		s.insertionSort(insArr);
		printStats(testArr, insArr);

		//merge sort.
		int[] mergeArr = testArr.clone();
		s.callMergeSort(mergeArr);
		printStats(testArr, mergeArr);

		//quick-sort
		int[] quickArr = testArr.clone();
		s.callQuickSort(quickArr);
		printStats(testArr, quickArr);

		//heap sort
		int[] heapSArr = testArr.clone();
		s.heapSort(heapSArr);
		printStats(testArr, heapSArr);

		//radix sort
		int[] radSArr = testArr.clone();
		s.radixSort(radSArr);
		printStats(testArr, radSArr);
	}


	/**
	 * Print the usage if invalid input provided by user. and exit
	 * after printing this message.
	 */
	private static void printUsage()
	{
		System.out.println("usage: java SorterTest arg1 [arg2 arg3 arg4 ...]");
		System.out.println("(e.g.) For creating a random array of size 1000");
		System.out.println("java SorterTest 1000");
		System.out.println("To pass array elements as arguments");
		System.out.println("java SorterTest 1 0 23 8 17 2 3 18 10000 14");
		System.exit(1);
	}

	private static void printStats(int[] originalArr, int[] sortedArr)
	{
		printArr(sortedArr);
		checkIfArraySorted(sortedArr);
		checkIfSameElements(originalArr, sortedArr);
	}


	/**
	 * Create the test array of random integers ranging from 0 to 99 of user
	 * specified size
	 * @param sizeOfTestArr, the size of the array to be created.
	 * @return outputArray the array of random numbers created.
	 */
	private static int[] createTestArray(int sizeOfTestArr)
	{
		int[] outputArray = new int[sizeOfTestArr];
		if(sizeOfTestArr < 0)
		{
			System.err.println("Invalid array size. Min = 2");
			System.exit(1);
		}else if(sizeOfTestArr < 2)
		{
			System.err.println("An array of size 1 is already sorted. Needs to be 2 or greater.");
			System.exit(1);
		}

		for(int i = 0; i < sizeOfTestArr; i++)
		{
			outputArray[i] = (int)(Math.random() * 100);
		}

		return outputArray;
	}

	/**
	 * This method takes an array of integers and prints it out to console.
	 * @param a the array to be sorted.
	 */
	private static void printArr(int[] a)
	{
		if(a.length < 1000)
		{
			System.out.print("[");

			for(int y = 0; y < a.length; y++)
			{
				if(y != a.length-1)
					System.out.print(a[y] + " ");
				else
					System.out.print(a[y]);
			}

			System.out.print("]\n");
		}
		else {System.out.println("Array not printed, because its too big.");}
	}



	/**
	 * Check is the array is sorted in an ascending order.
	 * @param a the array to be tested.
	 */
	private static void checkIfArraySorted(int[] a)
	{
		boolean sorted = true;
		for(int y = 0; y < a.length-1; y++)
		{
			if(a[y] > a[y+1])
			{
				sorted = false;
				break;
			}
		}

		if(sorted)
			System.out.println("[X] The array is sorted in an ascending order.");
		else
			System.out.println("[ ] The array is NOT sorted in an ascending order [!!!!]");
	}


	/**
	 * Check if the set	of elements	is the same	as the original	list
	 * @param a original array
	 * @param b modified array
	 */
	private static void checkIfSameElements(int[] a, int[] b)
	{
		Map<Integer, Integer> elementFrequency = new HashMap<Integer, Integer>();

		boolean similar = true;

		if(a.length != b.length)
		{
			System.out.println("Array length mismatch! Can't compare elements. [!!!!]");
			return;
		}

		for(int i = 0; i<a.length; i++)
		{
			if(elementFrequency.get(a[i]) == null)
				elementFrequency.put((a[i]),0);
			if(elementFrequency.get(b[i]) == null)
				elementFrequency.put((b[i]),0);

			elementFrequency.put(a[i], elementFrequency.get(a[i])+1);
			elementFrequency.put(b[i], (elementFrequency.get(b[i])-1));
		}

		for(Integer value : elementFrequency.values())
		{
			if(value < 0)
			{
				similar = false;
				break;
			}
		}

		if(similar)
			System.out.println("[X] The set of elements is the same as the original list.");
		else
			System.out.println("[ ]The set of elements is NOT the same as the original list [!!!!]");
	}


}
