grading3: main.o Record.o
	g++ main.o Record.o -o grading3

main.o: main.cpp Record.h
	g++ main.cpp -c

Record.o: Record.cpp Record.h
	g++ Record.cpp -c

clean:
	rm grading3 main.o Record.o