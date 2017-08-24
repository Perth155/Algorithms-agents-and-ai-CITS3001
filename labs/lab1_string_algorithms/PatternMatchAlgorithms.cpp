#include <iostream>
#include <vector>
#include <string>

// The number of char in input alphabet. 
#define ASCII_CHARS 256;

using namespace std;

/**
* Print out the array, and then free its memory.
*/
void printArr(vector<int> a)
{
	cout << "[ ";
	for(unsigned i = 0; i < a.size(); i++)
	{
		cout<<a[i]<<" ";
	}
	cout << " ]" <<endl;
	vector<int>().swap(a); 
}


/**
 * A naive pattern matching algorithm - we simply consider
 * each possible shift in turn.
 * returns an array of indexes of all matches. 
 * worst case time complexity: O(|pattern| * |text|)
 * @param pattern - the pattern to be matched.
 * @param text - the text the pattern is to be matched with. 
 */
int naive(string text, string pattern)
{
	string current;
	vector<int> rVec;
	for(unsigned i = 0; i <= (text.length() - pattern.length()); i++)
	{
		bool test = true;
		for(unsigned j = 0; j < pattern.length(); j++)
		{
			if(text[i+j] != pattern[j])
				test = false;
		}
		if(test == true)
		{
			rVec.push_back(i);
		}
	}

	printArr(rVec);
	return rVec.size();
}



/**
 * An implementation of the Rabin-Karp algorithm for pattern matching. 
 * try to replace innermost loop of the naive method with single comparison,
 * wherever possible.
 * Currently implemented a general version of Rabin Karp algorithm.
 * Only WORKS IF text and pattern are strings composing of only digits from 0 to 9.
 * Worst case time complexity => O(n)
 */
int integerRabinKarp(string text, string pattern)
{
	vector<int> rVec;
	int m = text.length();
	int n = pattern.length();	
	int p_bar = 0; 
	int t_bar = 0; 
	int d = 10;
	int d_bar = 1;
	for(int j = 0; j < n; j++)
	{
		p_bar = p_bar*d + (pattern[j]-'0');
		d_bar*=d;
		t_bar = t_bar*10 + (text[j]-'0');
	}
	d_bar/=d;
	
	for(int i = 0; i< (m-n+1); i++)
	{
		if(p_bar == t_bar)
		{
			rVec.push_back(i);
		}
		t_bar = (t_bar%d_bar)*d + (text[i+n]-'0');
	}
	printArr(rVec);
	return rVec.size();
}



/**
 * This is a general Rabin-Karp algorithm, which stores hashes the pattern to a 
 * similar sized window in the text. 
 * However, if a hash collision is found, we still need to verify, which means
 * the worst case time complexity : O(m*n) same as the naive algorithm.
 * However in most common cases we will have a few matches, so it is expected to perform faster. 
 */
int generalRabinKarp(string text, string pattern)
{
	vector<int> rVec;
	int p_bar = 0; //store hash val of pattern.
	int t_bar = 0; //store hash val of text.
	int prime = 151;
	int d = ASCII_CHARS;
	int h = 1;
	int m = pattern.length();
	int n = text.length();
	//h will store the value d^(m-1)%q
	for(int i = 0; i < m-1; i++)
		h = (h*d)%prime;

	//Calculating hash values of pattern and the first window of text.
	for(int j = 0; j < m; j++)
	{
		p_bar = (d*p_bar + pattern[j])%prime;
		t_bar = (d*t_bar + text[j])%prime;
	}

	for(int i = 0; i <= n-m; i++)
	{
		//check for hash collisions. Add to vector and increment count if not.
		if(p_bar == t_bar)
		{
			bool genuineMatch = true;
			for(int j = 0; j < m; j++)
			{
				if(text[i+j] != pattern[j])
				{
					genuineMatch = false;
					break;
				}
			}
			if(genuineMatch)
			{
				rVec.push_back(i);
			}
		}

		//Now calculate the hash for the next window in text= remove leading digit
		//add trailing digit. 
		if( i < n-m)
		{
			t_bar = (d*(t_bar-(text[i]*h)) + text[i+m])%prime;

			if(t_bar < 0)
				t_bar = t_bar+prime;
		}

	}
	printArr(rVec);
	return rVec.size();
}



/**
 * The prefix function to KMP algorithm.
 * Find and return an array of longest proper prefixes for the pattern to be used for 
 * note: longestProperPrefix[i] is the longest prefix of pat[0..i] which is also a suffix of pat[0..i]
 */
void findLongestProperPrefixArr(string pattern, int* longestCommonPrefix)
{	
	int i = 0; //keeps track of the pattern index.
	int val = 0; //Length of prev. longest prefix suffix.
	longestCommonPrefix[i] = 0;
	i++;
	int lenP = pattern.size();
	while(i < lenP)
	{
		if(pattern[i] == pattern[val])
		{
			val++;
			longestCommonPrefix[i] = val;
			i++;
		}
		else
		{
			if(val != 0)
				val = longestCommonPrefix[val-1];
			else
			{	
				longestCommonPrefix[i] = 0;
				i++;
			}
		}
	}
}


/**
* Implementation of Knuth-Morris-Pratt (KMP) algorithm to find the number
* of occurances of pattern within a string. When character x that doesn't match
* is found - go to a state that recognises what we have already.
* We use the Longest prefix array, to determine next character to be matched.
* Idea is not to match the character that we know will anyway match.
* Worst case time complexity: O(n+m).
*/
int algKnuthMorrisPratt(string text, string pattern)
{
	const int pLen = pattern.length();
	const int tLen = text.length();
	int* prefixArr = new int[pLen];

	findLongestProperPrefixArr(pattern, prefixArr);
	int q = 0; //stores num items matched.
	vector<int> rVec;

	for(int i = 0; i < tLen; i++)
	{
		while(q > 0 && pattern[q] != text[i])
			q = prefixArr[q-1];
		
		if(pattern[q] == text[i])
			q++;
		
		if(q == pLen)
		{
			rVec.push_back(i-pLen+1);
			q = prefixArr[q-1];
		}
	}
	delete[] prefixArr;	
	printArr(rVec);
	return rVec.size();
}




/**
 * An implementation of a basic Boyer-Moore algorithm using
 * the bad-character heuristic.
 */
//int algBoyerMoore(string text, string pattern)
//{
//	
//}
