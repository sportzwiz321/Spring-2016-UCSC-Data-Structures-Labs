/*
*Search.java scans a file consisting of one word lines.
*It stores all of the words into an array and checks to see
*if any of the "target" words inputted on the command line
*are in that array of words. To do this the array is sorted
*using a Merge Sort, and then searched through using a Binary Search.
*Then a message is printed on the stdout, displaying whether or not
*the word was found and if so, what line in the origninal file it was found in.
*/

import java.io.*;
import java.util.Scanner;

class Search {

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(new File(args[0]));
		String[] word = null;
		int[] rowNumber = null;
		int lineNumber = 0;

		while(in.hasNextLine()) {
			lineNumber++;
			in.nextLine();
		}

		word = new String[lineNumber];
		rowNumber = new int[lineNumber];
		in = new Scanner(new File(args[0]));
		lineNumber = 0;

		while(in.hasNextLine()) {
			word[lineNumber] = in.nextLine().trim();
			rowNumber[lineNumber] = lineNumber + 1;
			lineNumber++;
		}

		mergeSort(word, rowNumber, 0, lineNumber - 1);

		for(int i = 1; i < args.length; i ++) {
			int index = binarySearch(word, 0, word.length - 1, args[i]);
			if (index == -1) {
				System.out.println(args[i] + " not found");
			} else {
				System.out.println(args[i] + " found on line " + rowNumber[index]);
			}
		}

		in.close();

	}

	// this method recursively sorts an array of strings
	// by splitting the array in half,
	// sorting the halves separately,
	// and then merging the arrays together
	// when they are done being sorted.
	// also keeps track of what row the strings appeared in
	// on the original file
	public static void mergeSort(String[] word, int[] lineNumber, int p, int r) {
		int q = (p+r)/2;

		if (r > p) {
			mergeSort(word, lineNumber, p, q);
			mergeSort(word, lineNumber, q + 1, r);
			merge(word, lineNumber, p, q, r);
		}

	}

	// this method merges two sorted arrays of strings given the range of indices amd the "middle index"
	// it also merges the row number of the strings as they appeared in the original file
	public static void merge(String[] word, int[] lineNumber, int p, int q, int r) {

		String[] alpha = new String[q - p + 1];
		int[] alphaCharlie = new int[q - p + 1];

		String[] beta = new String[r - q];
		int[] betaDelta = new int[r - q];

		for (int i = 0; i < alpha.length; i++) {

			alpha[i] = word[p + i];
			alphaCharlie[i] = lineNumber[p + i];

		}

		for (int j = 0; j < beta.length; j++) {

			beta[j] = word[q + 1 + j];
			betaDelta[j] = lineNumber[q + 1 + j];

		}

		int a = 0,b = 0;
		for (int k = 0; k < r - p + 1; k++) {
			if (a < alpha.length && b < beta.length) {
				
				if (alpha[a].compareTo(beta[b])>0) {

					word[p + k] = beta[b];
					lineNumber[p + k] = betaDelta[b];
					b++;

				} else {

					word[p + k] = alpha[a];
					lineNumber[p + k] = alphaCharlie[a];
					a++;

				}

			} else if (a >= alpha.length) {

				word[p + k] = beta[b];
				lineNumber[p + k] = betaDelta[b];
				b++;

			} else {

				word[p + k] = alpha[a];
				lineNumber[p + k] = alphaCharlie[a];
				a++;

			}
		}

	}

	// this recursively searches through an array of strings given a range of indices and a target word to search for.
	// The array must be sorted. If those preconditions are met then I check the middle index of the array.
	// I compare the string in the middle of the array to the target word using compareTo.
	// If the result is zero then I return that middle index.
	// If the result is positive, I BinarySearch the first half index p through index q-1
	// and if it's negative I search the second half index q+1 through index r.
	// binarySearch continues to be called until either: the target word is matched or the array becomes out of range
	// if the target is found, then the index of the word is returned, otherwise -1 is returned.
	public static int binarySearch(String[] word, int p, int r, String target) {

		int q = (p+r)/2;

		if (p > r) {
			return -1;
		}

		if (word[q].compareTo(target) == 0) {
			return q;
		} else if (word[q].compareTo(target) > 0) {
			return binarySearch(word, p, q - 1, target);
		} else {
			return binarySearch(word, q + 1, r, target);
		}

	}

}