import java.io.*;
import java.util.Scanner;

class FileReverse {

	public static void main(String[] args) throws IOException {

		Scanner in = null;
		PrintWriter out = null;
		String line = null;
		String[] token = null;
		int i,n,lineNumber = 0;

		if(args.length < 2) {
			System.out.println("Usage: FileCopy <input file> <output file>");
			System.exit(1);
		}

		in = new Scanner(new File(args[0]));
		out = new PrintWriter(new FileWriter(args[1]));

		while(in.hasNextLine()) {
			lineNumber++;

			line = in.nextLine().trim() + " ";

			token = line.split("\\s+");

			n = token.length;
			out.println("Line " + lineNumber + " contains " + n + " tokens:");
			for(i = 0; i < n; i++) out.println(" " + stringReverse(token[i],token[i].length()));
		}

		in.close();
		out.close();

	}

	public static String stringReverse(String s, int n) {

		if (n > 0) {
			return stringReverse(s.substring(1,s.length()), n - 1) + s.substring(0,1);
		}

		return "";

	}

}