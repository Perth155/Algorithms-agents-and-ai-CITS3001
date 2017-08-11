/**
 * Java implementation of several common sorting algorithms-
 * Insertion sort, Merge sort, Quick sort, Heap sort
 * and Radix sort, class field count holds the number of array
 * assignments performed per algorithm.
 * @author Abrar Amin [Perth155] (abrar.a.amin@gmail.com)
 */

public class Sorter
{

	private int count; //count the number of array assignments per sorting algorithm.

	public Sorter()
	{
		setCount();
	}

	public void setCount()
	{
		count = 0;
	}



	/**
	 * An implementation of insertion sort in Java, a simple sorting
	 * algorithm, Worst case time complexity - O(n^2).
	 * @param a the array of ints to be sorted.
	 */
	public void insertionSort(int[] unsortedArr)
	{
		long startTime = System.nanoTime();
		for(int y = 1; y<unsortedArr.length; y++)
		{
			int key = unsortedArr[y];
			int i = y-1;

			while(i >= 0 && unsortedArr[i] > key)
			{
			       unsortedArr[i+1] = unsortedArr[i];
			       this.count++;
			       i--;
			}
			i++;
			unsortedArr[i] = key;
		}
		long endTime = System.nanoTime();
		printExecTime("Insertion Sort", startTime, endTime);
		setCount();
	}



	/**
	 * print out the number of array assignments and time in nano seconds for each
	 * sorting algorithms to console.
	 * @param sortingAlgo name of the sorting algorithm.
	 * @param startTime start time in nano sec obtain by using System.nanoTime method
	 * @param endTime end time in nano sec.
	 */
	private void printExecTime(String sortingAlgo, long startTime, long endTime)
	{
		System.out.println("===="+sortingAlgo+"====\nNumber of Array assignments: "+this.count+".");
		System.out.println("Time taken for execution : " + (endTime - startTime) + " nano seconds.");
	}


	/**
	 * This function calls merge sort algorithm.
	 * @param a the array of integers to be sorted.
	 */
	public void callMergeSort(int[] a)
	{
		long startTime = System.nanoTime();
		mergeSort(0, a.length, a);
		long endTime = System.nanoTime();
		printExecTime("Merge Sort", startTime, endTime);
		setCount();
	}

	private void mergeSort(int startIndex, int len, int[] unsortedArr)
	{
		if(len == 1)
			return; //base case, array of one element is already sorted.

		int midPoint = len/2;
		//int rightLength = endIndex - leftLength;
		int[] l = new int[midPoint];
		int[] r = new int[len - midPoint];

		for(int x = 0; x < midPoint; x++)
		{
			l[x] = unsortedArr[x];
		}
		for(int y = midPoint; y < len; y++)
		{
			r[y-midPoint] = unsortedArr[y];
		}

		mergeSort(0, l.length, l);
		mergeSort(0, r.length, r);
		merge(unsortedArr, r, l);
	}


	private void merge(int[] a, int[] leftArr, int[] rightArr)
	{
		int lenLeft = leftArr.length;
		int lenRight = rightArr.length;
		int lIndex = 0;
		int rIndex = 0;
		int arrIndex = 0;

		while(lIndex < lenLeft && rIndex < lenRight)
		{
			if(leftArr[lIndex] <= rightArr[rIndex])
			{
				a[arrIndex] = leftArr[lIndex];
				lIndex++;
			}
			else
			{
				a[arrIndex] = rightArr[rIndex];
				rIndex++;
			}
			arrIndex++;
			count++; //Keeping track of number of array assignments.
		}

		while(lIndex < lenLeft)
		{
			a[arrIndex++] = leftArr[lIndex++];
			count++;
		}
		while(rIndex < lenRight)
		{
			a[arrIndex++] = rightArr[rIndex++];
			count++;
		}

	}


	public void callQuickSort(int[] a)
	{
		long startTime = System.nanoTime();
		quickSort(a, 0, a.length-1);
		long endTime = System.nanoTime();
		printExecTime("Quick Sort", startTime, endTime);
		setCount();
	}


	private void quickSort(int[] unsortedArr, int start, int end)
	{
		if(start<end)
		{
			int pIndex = partition(unsortedArr, start, end);
			quickSort(unsortedArr, start, pIndex-1);
			quickSort(unsortedArr, pIndex+1, end);
		}
	}


	private int partition(int[] a, int start, int end)
	{
		int pivot = a[end];
		int partIndex = start-1;

		for(int i = start; i < end; i++)
		{
			if(a[i] <= pivot)
			{
				partIndex++;
				swap(a, partIndex, i);
			}
		}
		swap(a, partIndex+1, end);
		return partIndex+1;
	}

	/**
	 * Swap element in 2 given index in the array.
	 * @param arr the array in which the swap occurs.
	 * @param a the index of the first element to be swapped with element in index 2.
	 * @param b the index of the second element to be swapped with element in index 1.
	 */
	private void swap(int[] arr, int a, int b)
	{
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
		count++;
	}



	private int iParentNode(int arraySiz)
	{
		return ((arraySiz-1)/2);
	}


	private int getLeftChild(int parentNode)
	{
		return 2*parentNode + 1;
	}

	private int getRightChild(int parentNode)
	{
		return 2*parentNode + 2;
	}



	/**
	 * Use heap sort to sort an unsorted array
	 * step 1 - Call heapify() on the list, builds a heap from list in O(n) operations
	 * step 2 - Swap first element on list with final element, decrease the considered range of the list by 1
	 * step 3 - Call siftDown() on the list to sift the new first element to its appropriate index in the heap
	 * repeat step 2 unless considered range of the list is 1 element
	 * heapify run once- O(n) performance; sift down function if O(log n) called n times
	 * So performance of the algorithm - O(nlogn)
	 * @param arr the array to be sorted.
	 */
	public void  heapSort(int[] arr)
	{
		long startTime = System.nanoTime();

		heapify(arr, arr.length); //build heap in array so largest value is in root.

		// the following loop maintains the intervals that a[0:end] is a heap and every element beyond
		// end is greater than everything before it (so a[end:arr.length] is in sorted order.
		int end = arr.length-1;

		while(end>0)
		{
			//a[0] is the root and largest value. The swap moves it in front of the sorted elements.
			swap(arr, end, 0);
			//reduce heap-size by 1, as the last item in the heap is now sorted.
			end--;
			//the swap ruined the max heap, so need to restore it.
			siftDown(arr, 0, end);
		}

		long endTime = System.nanoTime();
		printExecTime("Heap Sort", startTime, endTime);
	       	setCount();
	}




	/**
	 * Put elements of array in heap order in place.
	 * @param arr the unsorted array.
	 * @param arrSiz the size of arr.
	 */
	private void heapify(int[] arr, int arrSiz)
	{
		//start is assigned the index in arr of the last parent node.
		int start = iParentNode(arrSiz-1);
		//the last element in a 0-based array is at index arrSiz-1, rtf parent of that element.
		while (start >=  0)
		{
			//sift down the node at index 'start' to the proper place such that all nodes below
			//the start index are in heap order.
        		siftDown(arr, start, arrSiz - 1);
       	 		//(go to the next parent node)
        		start = start - 1;
    			//(after sifting down the root all nodes/elements are in heap order)
		}
	}



	/**
	 * Repair the heap whose root element is at index 'start', assuming the heaps rooted at its children are valid.
	 */
	private void siftDown(int[] a, int start, int end)
	{
		int root = start;
		while(getLeftChild(root) <= end) //while root has at least one child.
		{
			int child = getLeftChild(root); //left child of root
			int swap = root; //keeps track of child to swap with.

			if(a[swap] < a[child])
				swap = child;

			int rightChild = getRightChild(root);

			// Check if there is a right child and that child is greater.
			if( ( rightChild <= end) && (a[swap] < a[rightChild]) )
				swap = rightChild;

			if(swap == root) //the root holds the largest element.
				return; //Heaps rooted at children are valid, we are done.
			else
			{
				swap(a, root, swap);
				root = swap;
			}

		}
	}


	private int findMaxNum(int[] a)
	{
		int max = a[0];

		for(int y = 0; y < a.length; y++)
		{
			if(a[y] > max)
				max = a[y];
		}

		return max;
	}



	private void countSort(int[] a, int n, int exp)
	{
		int output[] = new int[n];
		int count[] = new int[10];

		for(int x = 0; x < count.length; x++)
			count[x] = 0;

		for(int x = 0; x < a.length; x++)
			count[(a[x]/exp)%10]++;

		for(int x = 1; x < 10; x++)
			count[x] += count[x-1];

		for(int x = n - 1; x >= 0; x--)
		{
			output[count[(a[x]/exp)%10] -1] = a[x];
			count[(a[x]/exp)%10]--;
			this.count++;
		}

		for(int x = 0; x<n; x++)
			a[x] =output[x];
	}


	public void radixSort(int[] arr)
	{
		long startTime = System.nanoTime();
		int maxNum = findMaxNum(arr);
		//int maxNum = arr[maxNumIndex];
		int n = arr.length;
		System.out.println(maxNum);

		for(int exp = 1; maxNum/exp > 0; exp*=10)
		{
			countSort(arr, n, exp);
		}
		long endTime = System.nanoTime();
		printExecTime("Radix Sort", startTime, endTime);
		setCount();
	}



}
