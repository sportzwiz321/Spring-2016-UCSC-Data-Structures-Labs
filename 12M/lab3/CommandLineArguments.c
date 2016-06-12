/*
*CommandLineArguments.c
*/
#include <stdio.h>
#include <stdlib.h>

// int main(int argc, char* argv[]) {
// 	int i;
// 	printf("argc = %d\n", argc);
// 	for(i=0; i<argc; i++) printf("%s\n", argv[i]);
// 	return EXIT_SUCCESS;
// }

// int main() {
// 	int x, y, z;
// 	printf("Enter three integers separated by commas, then press return:");
// 	scanf(" %d, %d, %d", &x, &y, &z);
// 	printf("The integers entered were %d, %d, %d\n", x, y, z);
// 	return EXIT_SUCCESS;
// }

int main() {
	int n;
	int i;
	int x[3];
	printf("Enter three integers separated by spaces, then press return: ");
	n = scanf(" %d %d %d", &x[0], &x[1], &x[2]);
	printf("%d numbers were successfully read: ", n);
	for (i = 0; i < n; i++)printf("%d ", x[i]);
	printf("\n");
	return EXIT_SUCCESS;
}