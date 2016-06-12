# Makefile for charType.c
FLAGS = -std=c99 -Wall
CSOURCES = charType.c
OBJECTS = charType.o
EXEBIN = charType
SOURCES = Makefile $(CSOURCES) README
SUBMIT = submit cmps012b-pt.s16 lab4

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(CSOURCES)
	gcc -c $(FLAGS) $(CSOURCES)
clean:
	rm -f $(EXEBIN) $(OBJECTS)
submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
checkSubmit:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.s16/lab4/nmchen	
check:
	valgrind --leak-check=full $(EXEBIN) $(CSOURCES) outfile