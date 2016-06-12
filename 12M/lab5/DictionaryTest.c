// DictionaryTest.c
// Nikolai Chen
// nmchen
// lab5
// Test C file to test each operation as created

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

int main(int argc, char* argv[]) {

	// tests newDictionary(), isEmpty(), and size()

	Dictionary dict = newDictionary();

	printf("isEmpty: %s\n", (isEmpty(dict)?"true":"false"));
	printf("size: %d\n", size(dict));

	// tests insert() and lookup()

	char* a = "halo";
	char* b = "reach";

	insert(dict, a, b);

	printf("isEmpty: %s\n", (isEmpty(dict)?"true":"false"));
	printf("size: %d\n", size(dict));

	printf("%s\n", lookup(dict, a));

	// test insert() precondition

	// insert(dict, a, a);

	// tests delete()

	delete(dict, a);

	// tests delete() precondition

	// delete(dict, b);

	printf("isEmpty: %s\n", (isEmpty(dict)?"true":"false"));
	printf("size: %d\n", size(dict));

	insert(dict, "one", "the phantom menace");
	insert(dict, "two", "attack of the clones");
	insert(dict, "three", "revenge of the sith");
	insert(dict, "four", "A new hope");
	insert(dict, "five", "The empire strikes back");
	insert(dict, "size", "Return of the jedi");

	// tests printDictionary()

	printDictionary(stdout, dict);

	printf("isEmpty: %s\n", (isEmpty(dict)?"true":"false"));
	printf("size: %d\n", size(dict));

	delete(dict, "four");

	printDictionary(stdout, dict);

	printf("size: %d\n", size(dict));

	// tests makeEmpty()

	makeEmpty(dict);

	printDictionary(stdout, dict);

	printf("isEmpty: %s\n", (isEmpty(dict)?"true":"false"));
	printf("size: %d\n", size(dict));

	freeDictionary(dict);



}