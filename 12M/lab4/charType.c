#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <assert.h>

#define MAX_STRING_LENGTH 100

void extract_chars(char* s, char* a, char* d, char* p, char* w) {

	int i = 0, j = 0, k = 0, l = 0, m = 0;

	while(s[i] != '\0' && i < MAX_STRING_LENGTH) {
		if(isalpha((int)s[i])) {
			a[j++] = s[i];
		} else if (isdigit((int)s[i])) {
			d[k++] = s[i];
		} else if (ispunct((int)s[i])) {
			p[l++] = s[i];
		} else if (isspace((int)s[i])) {
			w[m++] = s[i];
		}

		i++;

	}

	a[j] = '\0';
	d[k] = '\0';
	p[l] = '\0';
	w[m] = '\0';

}

int main(int argc, char* argv[]) {

	FILE* in;
	FILE* out;
	char* line;
	char* alpha;
	char* digit;
	char* punct;
	char* white;
	int count;

	if( argc != 3) {
		printf("Usage: %s input-file output-file\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	if ((in = fopen(argv[1], "r")) == NULL){
		printf("Unable to read from file %s\n", argv[1]);
		exit(EXIT_FAILURE);
	}

	if ((out = fopen(argv[2], "w")) == NULL){
		printf("Unable to write to file %s\n", argv[2]);
		exit(EXIT_FAILURE);
	}

	line = malloc(MAX_STRING_LENGTH+1 * sizeof(char));
	alpha = malloc(MAX_STRING_LENGTH+1 * sizeof(char));
	digit = malloc(MAX_STRING_LENGTH+1 * sizeof(char));
	punct = malloc(MAX_STRING_LENGTH+1 * sizeof(char));
	white = malloc(MAX_STRING_LENGTH+1 * sizeof(char));

	assert( line != NULL && alpha != NULL && digit != NULL && punct != NULL && white != NULL);

	count = 1;

	while( fgets(line, MAX_STRING_LENGTH, in) != NULL) {
		extract_chars(line, alpha, digit, punct, white);
		fprintf(out, "line %d contains:\n", count);

		fprintf(out, "%d alphabteic character", (int)strlen(alpha));
		if (strlen(alpha) != 1){
			fprintf(out, "s");
		}
		fprintf(out, ": %s\n", alpha);

		fprintf(out, "%d numeric character", (int)strlen(digit));
		if (strlen(digit) != 1){
			fprintf(out, "s");
		}
		fprintf(out, ": %s\n", digit);

		fprintf(out, "%d punctuation character", (int)strlen(punct));
		if (strlen(punct) != 1){
			fprintf(out, "s");
		}
		fprintf(out, ": %s\n", punct);

		fprintf(out, "%d whitespace character", (int)strlen(white));
		if (strlen(white) != 1){
			fprintf(out, "s");
		}
		fprintf(out, ": %s\n", white);

		fprintf(out, "\n");

		count += 1;
	}

	free(line);
	free(alpha);
	free(digit);
	free(punct);
	free(white);

	fclose(in);
	fclose(out);

}