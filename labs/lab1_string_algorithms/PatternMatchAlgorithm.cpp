#include <iostream>
#include <vector>
#include <string>

using namespace std;

// compile : g++ PatternMatchAlgorithm.cpp -Wall -Werror -pedantic -std=c++11  -o pm

/**
 * A naive pattern matching algorithm - we simply consider
 * each possible shift in turn.
 * returns an array of indexes of all matches. 
 * O(|pattern| * |text|)
 * @param pattern - the pattern to be matched.
 * @param text - the text the pattern is to be matched with.
 * @return rVec - an integer vector for start-indices of all matches. 
 */
vector<int> naive(string text, string pattern)
{
	string current;
	int numMatch = 0;
	vector<int> rVec;

	for(unsigned i = 0; i <= (text.length() - pattern.length()); i++)
	{
		//cout << "test " << text[i] <<endl;
		bool test = true;
		for(unsigned j = 0; j < pattern.length(); j++)
		{
			if(text[i+j] != pattern[j])
				test = false;
		}
		if(test == true)
		{
			//resultVec[indexCount] = i;
			//rVec.resize(numMatch+1);
			rVec.push_back(i);
			numMatch++;
			//*resultVec = (int*)realloc(*resultVec, sizeof(int)*numMatch);
			//(*resultVec)[numMatch-1] = i;
		}
	}

	return rVec;
}


//int* rabinKarp(string text, string pattern)
//{
//	;
//}


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
}




int main(int argc, char* argv[])
{
	string bigStr;
	string smallStr; 
	cout << "Enter big str (text): ";
	getline(cin, bigStr);
	cout << "Enter small str (pattern): ";
	getline(cin, smallStr);	
	cout << "text: " << bigStr << " pattern: " <<smallStr<<endl;
	vector<int> a = naive(bigStr, smallStr);
	printArr(a);
	exit(0);
}

