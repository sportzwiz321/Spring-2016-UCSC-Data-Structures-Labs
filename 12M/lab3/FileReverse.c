/*
*FileReverse.c
*Reads input file and prints each word
*on a separate line in reverse
*each word on a separate line of the output file.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


//reverses the string by switching the first and last character
//followed by the second and second to last character until
//there are no characters left or there is only one remaining
void stringReverse(char* s) {

	int i = 0;
	int j = strlen(s)-1;
	char middle;

	while(j > i) {

		middle = s[j];
		s[j] = s[i];
		s[i] = middle;

		i++;
		j--;

	}

}

//goes through every line of the read file separating words separated by spaces
//uses the stringReverse() method to reverse all the strings before printing
//the reversed words to separate lines on a new output file
//if there aren't three arguments, or either of the last two arguments are null, then
//the program is exitted immediately
int main(int argc, char* argv[]) {
	FILE* in;
	FILE* out;
	char word[256];

	if(argc != 3) {
		printf("Usage: %s <input file> <output file>\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	in = fopen(argv[1], "r");
	if (in == NULL){
		printf("Unable to read from file %s\n", argv[1]);
		exit(EXIT_FAILURE);
	}

	out = fopen(argv[2], "w");
	if(out == NULL) {
		printf("Unable to write to file %s\n", argv[2]);
		exit(EXIT_FAILURE);
	}

	while(fscanf(in, " %s", word) != EOF) {
		stringReverse(word);
		fprintf(out, "%s\n", word);
	}

	fclose(in);
	fclose(out);

	return(EXIT_SUCCESS);
}