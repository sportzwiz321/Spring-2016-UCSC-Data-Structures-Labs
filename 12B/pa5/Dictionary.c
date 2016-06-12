// Dictionary.c
// Nikolai Chen
// nmchen
// pa5
// Dictionary.c is a hash table implementation of a Dictionary
// it handles collisions by creating a singly linked list

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "Dictionary.h"

// private functions and structures

const int tableSize = 101;

	// rotate_left()
	// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {

    int sizeInBits = 8 * sizeof(unsigned int);
    shift = shift & (sizeInBits - 1);
    if ( shift == 0 ) {
		return value;
	}
	return (value << shift) | (value >> (sizeInBits - shift));

}
	// pre_hash()
	// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
    unsigned int result = 0xBAE86554;
    while (*input) {
       result ^= *input++;
       result = rotate_left(result, 5);
    }
    return result;
}
	// hash()
	// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
	return pre_hash(key)%tableSize;
}

typedef struct NodeObj {
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
	if (pN != NULL && *pN != NULL){
		free(*pN);
		*pN = NULL;
	}
}

typedef struct DictionaryObj{
	Node* table;
	int numItems;
} DictionaryObj;

Node findKey(Dictionary D, char* key) {
	int k = hash(key);
	Node N = D->table[k];
	while(N != NULL) {
		if (strcmp(N->key, key) == 0){
			return N;
		} else {
			N = N->next;
		}
	}
	return NULL;
}




// public functions and structures

Dictionary newDictionary(void) {

	Dictionary D = malloc(sizeof(DictionaryObj));
	assert(D != NULL);
	D->table = calloc(tableSize, sizeof(NodeObj));
	assert(D->table != NULL);
	int x = 0;
	while(x < tableSize) {
		D->table[x] = NULL;
		x++;
	}
	D->numItems = 0;
	return(D);

}

void freeDictionary(Dictionary* pD) {

	if (pD != NULL && *pD != NULL){
		if (isEmpty(*pD) == 0){
			makeEmpty(*pD);
		}
		Dictionary D = *pD;
		free(D->table);
		free(*pD);
		*pD = NULL;
	}

}

int isEmpty(Dictionary D) {

	if (D == NULL){
		fprintf(stderr, "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}

	return (D->numItems == 0);

}

int size(Dictionary D) {

	if (D == NULL){
		fprintf(stderr, "Dictionary Error: calling size() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}

	return D->numItems;

}

char* lookup(Dictionary D, char* k) {

	if (D == NULL){
		fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}

	Node n = findKey(D, k);

	if (n == NULL){
		return NULL;
	} else {
		return n->value;
	}

}

void insert(Dictionary D, char* k, char* v) {

	if (D == NULL){
		fprintf(stderr, "Dictionary Error: calling insert() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}

	Node b = findKey(D, k);

	if (b != NULL){
		fprintf(stderr, "Dictionary Error: calling insert() on Dictionary with pre-existing key\n");
		exit(EXIT_FAILURE);
	} else {
		int n = hash(k);
		Node a = newNode(k, v);
		a->next = D->table[n];
		D->table[n] = a;
		D->numItems++;
	}

}

void delete(Dictionary D, char* k) {

	if (D == NULL){
		fprintf(stderr, "Dictionary Error: calling delete() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}

	Node b = findKey(D, k);

	if (b == NULL){
		printf("Dictionary Error: calling delete() on Dictionary without currently exisitng key\n");
		exit(EXIT_FAILURE);
	} else {
		int n = hash(k);
		if (b == D->table[n]){
			D->table[n] = D->table[n]->next;
		} else {
			Node a = D->table[n];
			while(a->next != b) {
				a = a->next;
			}
			a->next = b->next;
		}
		freeNode(&b);
		b = NULL;
		D->numItems--;
	}

}

void makeEmpty(Dictionary D) {

	if (D == NULL){
		fprintf(stderr, "Dictionary Error: calling makeEmpty() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}

	for (int i = 0; i < tableSize; i++){
		
		while(D->table[i] != NULL) {
			Node a = D->table[i];
			delete(D, a->key);
		}

	}

}

void printDictionary(FILE* out, Dictionary D) {

	if (D == NULL){
		fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}

	for (int i = 0; i < tableSize; i++){
		
		Node a = D->table[i];
		while(a != NULL) {
			fprintf(out, "%s %s\n", a->key, a->value);
			a = a->next;
		}

	}

}