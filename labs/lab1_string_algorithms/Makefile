PROJECTNAME = pattern_matcher
PMALGORITHMS = PatternMatchAlgorithms
CPP11 = g++ -std=c++11
CPPFLAGS = -Wall -Werror -pedantic
OBJ = pattern_matching_algorithms.o
RM = /bin/rm

main : $(OBJ)
	$(CPP11) $(CPPFLAGS) -o $(PROJECTNAME) $(PMALGORITHMS).o main.cpp

$(OBJ) : $(PMALGORITHMS).cpp $(PMALGORITHMS).h
	$(CPP11) $(CPPFLAGS) -c $(PMALGORITHMS).cpp

clean:
	$(RM) -f *.o
rmexe:
	$(RM) -f $(PROJECTNAME)

clobber: clean rmexe
