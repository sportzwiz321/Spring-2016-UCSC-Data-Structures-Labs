#------------------------------------------------------------------------------
# makefile for DictionaryClient.c with macros
#------------------------------------------------------------------------------

FLAGS   = -std=c99 -Wall
SOURCES = Dictionary.c DictionaryClient.c
HWSOURCES = Dictionary.c DictionaryClient.c Dictionary.h DictionaryTest.c README Makefile
OBJECTS = Dictionary.o DictionaryClient.o
HEADERS = Dictionary.h
EXEBIN  = DictionaryClient
SUBMIT = submit cmps012b-pt.s16 lab5

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)
submit: $(HWSOURCES)
	$(SUBMIT) $(HWSOURCES)
checkSubmit:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.s16/lab5/nmchen	
check:
	valgrind --leak-check=full $(EXEBIN) 