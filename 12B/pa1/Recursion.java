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
				return a;
			} else {
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

		int[] A = {-1,2,6,12,9,2,-5,-2,8,5,7};
		int[] B = new int[A.length];
		int[] C = new int[A.length];

		int minIndex = minArrayIndex(A, 0, A.length - 1);
		int maxIndex = maxArrayIndex(A, 0, A.length - 1);

		for (int x: A) System.out.print(x + " ");
		System.out.println();

		System.out.println("minIndex = " + minIndex);
		System.out.println("maxIndex = " + maxIndex);

		reverseArray1(A, A.length, B);
		for(int x: B) System.out.print(x + " ");
		System.out.println();

		reverseArray2(A, A.length, C);
		for(int x: C) System.out.print(x + " ");
		System.out.println();

		reverseArray3(A, 0, A.length - 1);
		for (int x: A) System.out.print(x + " ");
		System.out.println();

	}

}