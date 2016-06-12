// Dictionary.c
// Nikolai Chen
// nmchen
// lab5
// Class file for dictionary, implementation of the header file Dictionary.c
// implements a LinkedList Data Structure to hold the key and value pairings

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "Dictionary.h"

// private functions and structures

typedef struct NodeObj{
	char key[100];
	char value[100];
	struct NodeObj* next;
} NodeObj;

typedef NodeObj* Node;

Node newNode(char* k, char* v) {
	Node N = malloc(sizeof(NodeObj));
	assert(N != NULL);
	strcpy(N->key, k);
	strcpy(N->value, v);
	N->next = NULL;
	return(N);
}

void freeNode(Node* pN) {
	if(pN != NULL && *pN != NULL) {
		free(*pN);
		*pN = NULL;
	}
}

typedef struct DictionaryObj {
	Node head;
	Node tail;
	int numItems;
} DictionaryObj;

// returns the Node in the dictionary given it's key as a parameter, if it doesn't exist, it returns a NULL Node

Node findKey(Dictionary D, char* key) {
	Node n = D->head;

	while(n != NULL) {
		if (strcmp(n->key,key) == 0){
			// printf("I have found the key\n");
			return n;
		} else {
			// printf("I have yet to find the key\n");
			n = n->next;
		}
	}

	// printf("I will not find the key\n");

	return NULL;
}

// public functions and structures

Dictionary newDictionary(void) {
	Dictionary D = malloc(sizeof(DictionaryObj));
	assert(D != NULL);
	D->head = NULL;
	D->tail = NULL;
	D->numItems = 0;
	return(D);
}

// empties Dictionary and frees Dictionary from heap memory

void freeDictionary(Dictionary* pD) {

	if (pD != NULL && *pD != NULL){
		if (isEmpty(*pD) == 0){
			makeEmpty(*pD);
		}
		free(*pD);
		*pD = NULL;
	}

}

// returns a 0 if the dictionary isn't empty and a 1 if it is

int isEmpty(Dictionary D) {

	if (D == NULL){
		fprintf(stderr, "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}

	return (D->numItems == 0);

}

// returns the number of entries in the dictionary

int size(Dictionary D) {

	if (D == NULL){
		fprintf(stderr, "Dictionary Error: calling size() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}

	return D->numItems;

}

// looks up an entry in the dictionary with a given key, if it exists it returns it's value if not it returns NULL

char* lookup(Dictionary D, char* k) {
	
	if (D == NULL){
		fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}

	Node N = findKey(D, k);

	if (N != NULL){
		return N->value;
	} else {
		return NULL;
	}

}

// inserts a new entry into the dictionary, if the key doesn't already exist, otherwise it exits the program and prints an error message

void insert(Dictionary D, char* k, char* v) {

	if (D == NULL){
		fprintf(stderr, "Dictionary Error: calling insert() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}

	if (lookup(D , k) != NULL){
		fprintf(stderr, "Dictionary Error: calling insert() on Dictionary with pre-existing key\n");
		exit(EXIT_FAILURE);
	} else if (D->head == NULL) {
		// printf("A new Node A is created\n");
		Node A = newNode(k, v);
		// printf("%s %s\n", A->key, A->value);
		D->head = A;
		D->tail = D->head;
		// printf("The number of items in this dictionary is %d\n", D->numItems);
		D->numItems++;
		// printf("The number of items in this dictionary is %d\n", D->numItems);
	} else {
		Node A = newNode(k, v);
		D->tail->next = A;
		D->tail = D->tail->next;
		D->numItems++;
	}

}

// removes an entry from the dictionary given its key if it exists, if it doesn't exist it exits and prints an error message

void delete(Dictionary D, char* k) {

	if (D == NULL){
		fprintf(stderr, "Dictionary Error: calling delete() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}

	Node N = findKey(D, k);

	if (N == NULL){
		printf("Dictionary Error: calling delete() on Dictionary without currently exisitng key\n");
		exit(EXIT_FAILURE);
	} else {
		Node P = D->head;
		if (N == P){
			D->head = D->head->next;
		} else if (N == D->tail) {
			while(P->next != D->tail) {
				P = P->next;
			}
			D->tail = P;
			P->next = NULL;
		} else {
			while(P->next != N) {
				P = P->next;
			}
			P->next = N->next;
			N->next = NULL;
		}
		N = NULL;
		freeNode(&N);
		D->numItems--;
		
	}

}

// resets the dictionary to it's original state, empty

void makeEmpty(Dictionary D) {

	if (D == NULL){
		fprintf(stderr, "Dictionary Error: calling makeEmpty() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}

	Node N = D->head;

	while(N != NULL) {
		delete(D, N->key);
		N = N->next;
	}

}

// prints out every entry in the dictionary in the order that it was inserted

void printDictionary(FILE* out, Dictionary D) {

	if (D == NULL){
		fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}

	Node N = D->head;

	while(N != NULL) {
		fprintf(out, "%s %s\n", N->key, N->value);
		N = N->next;
	}
	
}