#include <iostream>
#include <ctime>
#include <string>
#include "PatternMatchAlgorithms.h"





void timePMA(std::string text, std::string pattern)
{
	std::string divider = "-----------------------------------------";

	clock_t begin = clock();
	int k = naive(text, pattern);
	clock_t end = clock();
	double time_spent = (double)(end-begin)*1000.0/ CLOCKS_PER_SEC;	
	std::cout << "* Naive algorithm matches found: " << k << " time: " << time_spent << "ms"<<std::endl;
	std::cout << divider << std::endl;
	

	begin = clock();
	k = generalRabinKarp(text, pattern);
	end = clock();
	time_spent = (double)(end-begin)*1000.0/ CLOCKS_PER_SEC;	
	std::cout << "* Rabin-Karp algorithm matches found: " << k << " time: " << time_spent << "ms"<<std::endl;
	std::cout << divider << std::endl;


	begin = clock();
	k = algKnuthMorrisPratt(text, pattern);
	end = clock();
	time_spent = (double)(end-begin)*1000.0/ CLOCKS_PER_SEC;	
	std::cout << "* Knuth-Morris-Pratt algorithm matches found: " << k << " time: " << time_spent << "ms"<<std::endl;
	std::cout << divider << std::endl;


	std::cout<<"============================================"<<std::endl;
}




int main(int argc, char* argv[])
{
	int testCases;
    std::string testCasesStr;
	getline(std::cin, testCasesStr);
	testCases = stoi(testCasesStr);

	for(int i= 0; i < testCases; i++)
	{
		std::string bigStr, smallStr;
		getline(std::cin, bigStr);
		getline(std::cin, smallStr);
		timePMA(bigStr, smallStr);
	}

	return 0;
}
