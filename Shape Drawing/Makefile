a.out:  fstack.o main.o 
	 g++ fstack.o main.o

fstack.o: fstack.cpp fstack.hpp
	 g++ -c fstack.cpp

main.o: main.cpp fstack.hpp
	 g++ -c main.cpp

clean:
	 rm *.o a.out
