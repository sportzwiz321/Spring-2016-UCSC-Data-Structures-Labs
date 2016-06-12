class Recursion {

	public static void reverseArray1 (int[] X, int n, int[] Y) {

		if (n > 0) {
			
			Y[Y.length - n] = X[n - 1];
			reverseArray1(X ,n - 1, Y);

		}

	}

	public static void reverseArray2 (int[] X, int n, int[] Y) {

		if (n > 0) {
			
			Y[n - 1] = X[X.length - n];
			reverseArray2(X ,n - 1, Y);

		}

	}

	public static void reverseArray3 (int[] X, int i, int j) {


		if (j > i) {
			int middle = X[j];
			X[j] = X[i];
			X[i] = middle;
			reverseArray3(X, i + 1, j - 1);
		}


	}

	public static int maxArrayIndex (int[] X, int p, int r) {



		if (p == r) {
			return p;
		} else {
			int q = (p + r)/2;
			int a = maxArrayIndex(X,p,q);
			int b = maxArrayIndex(X,q+1,r);
			if(X[a] > X[b]) {
				System.out.println("Position " + a + " is larger than position " + b);
				return a;
			} else {
				System.out.println("Position " + b + " is greater than or equal to position " + a);
				return b;
			}
		}

	}

	public static int minArrayIndex (int[] X, int p, int r) {

		if (p == r) {
			return p;
		} else {
			int q = (p + r)/2;
			int a = minArrayIndex(X,p,q);
			int b = minArrayIndex(X,q+1,r);
			if(X[a] < X[b]) {
				return a;
			} else {
				return b;
			}
		}

	}

	public static void main(String[] args) {

		int[] alpha = {1,2,3,4,5,6,7,8,9,10};
		int[] beta = {17,18,19,20,21,22,23,24};

		for (int a : beta) System.out.println(a);

		reverseArray1(alpha, 3, beta);
		reverseArray2(alpha, 4, beta);
		reverseArray3(alpha, 1, 8);

		System.out.println("\nAdded the first three numbers to the end of beta\nAdded the last four numbers to the beginning of beta\n");

		for (int a : beta) System.out.println(a);

		System.out.println("\nReversed the middle eight numbers of alpha\n");

		for (int a : alpha) System.out.println(a);

		System.out.println("\n" + beta[maxArrayIndex(beta, 0, 7)]);
		System.out.println("\n" + beta[minArrayIndex(beta, 0, 7)]);

	}

}