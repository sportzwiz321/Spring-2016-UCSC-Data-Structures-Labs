// DictionaryTest.c
// Nikolai Chen
// nmchen
// pa5
// DictionaryTest.c is a test file to test all of the operations in Dictionary.c

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Dictionary.h"

int main(int argc, char* arv[]) {

	// tests Dictionary constructor
	Dictionary dict = newDictionary();


	// tests isEmpty() and size()
	printf("isEmpty: %s\n", (isEmpty(dict)?"true":"false"));
	printf("size: %d\n", size(dict));

	// tests insert() and lookup()

	insert(dict, "reach", "for the stars");
	insert(dict, "I'm", "not done yet!");
	insert(dict, "star", "wars");
	insert(dict, "YOLO", "CMPE16");
	insert(dict, "UCSC", "Sammy the Slug");

	printf("isEmpty: %s\n", (isEmpty(dict)?"true":"false"));
	printf("size: %d\n", size(dict));

	printf("key = star value = %s\n", lookup(dict, "star"));

	// tests printDictionary()

	printDictionary(stdout, dict);

	// tests delete()

	delete(dict, "I'm");
	delete(dict, "YOLO");

	printf("isEmpty: %s\n", (isEmpty(dict)?"true":"false"));
	printf("size: %d\n", size(dict));

	printf("key = YOLO value = %s\n", lookup(dict, "YOLO"));

	printDictionary(stdout, dict);

	// tests makeEmpty()

	makeEmpty(dict);

	printf("isEmpty: %s\n", (isEmpty(dict)?"true":"false"));
	printf("size: %d\n", size(dict));

	printDictionary(stdout, dict);

	exit(EXIT_SUCCESS);
}