# Makefile for FileReverse.c
FLAGS = -std=c99 -Wall
CSOURCES = FileReverse.c
OBJECTS = FileReverse.o
EXEBIN = FileReverse
SOURCES = README Makefile $(CSOURCES)
SUBMIT = submit cmps012b-pt.s16 lab3

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(CSOURCES)
clean:
	rm -f $(EXEBIN) $(OBJECTS)
submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
check:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.s16/lab3/nmchen