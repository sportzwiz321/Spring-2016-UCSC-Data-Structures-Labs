class Search {

	public static void main(String[] args) {

		String[] word = {"carry", "main", "mid", "bot", "top", "adc", "jg", "assassin", "tank", "baron", "dragon", "mejais"};
		int[] lineNumber = new int[word.length];
		for (int x = 0; x < word.length; x++ ) {

			lineNumber[x] = x + 1;
			System.out.println(word[x] + " is found on line number " + lineNumber[x]);
			
		}
		System.out.println();
		mergeSort(word, lineNumber, 0, word.length - 1);
		for (int x = 0; x < word.length; x++ ) System.out.println(word[x] + " is found on line number " + lineNumber[x]);
		int index = binarySearch(word, 0, word.length - 1, "main");
		System.out.println("main is found in row " + lineNumber[index]);

	}

	public static void mergeSort(String[] word, int[] lineNumber, int p, int r) {
		int q = (p+r)/2;

		if (r > p) {
			mergeSort(word, lineNumber, p, q);
			mergeSort(word, lineNumber, q + 1, r);
			merge(word, lineNumber, p, q, r);
		}

	}

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