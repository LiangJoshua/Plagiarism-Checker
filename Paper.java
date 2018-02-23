package Homework4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.*;

public class Paper {

	private static String value;

	public Paper(String value) {
		this.value = value;
	}

	public static void parse(int x) {
		Scanner in = null;
		try {
			in = new Scanner(new File("C:\\Users\\Josh\\Desktop\\corpus.txt"));
		} catch (FileNotFoundException e) { // gets the corpus file
			e.printStackTrace();
		}
		String[] words = null;
		int corpusNumber;
		// hashtable key is the queue, the integer is the value
		Hashtable<Queue<String>, Integer> corpusTable = new Hashtable<>();
		String temp;
		// parsing the corpus file and putting it into a hashtable
		// that will be used to compare with the input/target string
		// to check plagiarizing
		while (in.hasNextLine()) {
			temp = in.nextLine().toLowerCase();
			temp = temp.replaceAll("\\.", ""); // gets rid of periods
			temp = temp.replaceAll("\"", ""); // get rid of quotations
			// splits by the colon after document number, then by white spaces
			words = temp.split(":|\\s");
			corpusNumber = Integer.parseInt(words[0]);// the doc_num of corpus
														// file
			Queue<String> corpusQueue = new LinkedList<String>();
			for (int i = 1; i < words.length; i++) {// i isn't 0 to skip doc_num
				corpusQueue.add(words[i]);
				if (i >= x) {
					Queue<String> resultQueue = new LinkedList<String>(corpusQueue);
					corpusTable.put(resultQueue, corpusNumber);
					corpusQueue.remove();
				}

			}
		}

		// parsing the target/input
		String[] input = value.toLowerCase().split("\\s");
		boolean plagiarized = false;
		Queue<String> inputQueue = new LinkedList<String>();
		for (int i = 0; i < input.length; i++) {
			inputQueue.add(input[i]);
			if (i + 1 >= x) {
				if (corpusTable.containsKey(inputQueue)) {
					System.out.println("Plagiarized from " + corpusTable.get(inputQueue));
					plagiarized = true;
				}
				inputQueue.remove();
			}
			if (i == input.length - 1) {
				if (plagiarized == false) {
					System.out.println("Not Plagiarized");
				}
			}
		}
	}

	// getting the corpusfile for command line argument that's used in the main
	// method
	public static void getCorpusFile() {
		try {
			Scanner scan = new Scanner(new File("C:\\Users\\Josh\\Desktop\\corpus.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// getting the targetfile for command line argument that's used in the main
	// method
	public static void getTargetFile() {
		Scanner scanner = null;
		String target = "";
		try {
			scanner = new Scanner(new File("C:\\Users\\Josh\\Desktop\\target.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scanner.hasNextLine()) {
			target = target + scanner.nextLine() + " ";
		}
		value = target;
	}

	public static void main(String[] args) {

		String corpusFile = args[0];
		if (corpusFile.equalsIgnoreCase("corpus.txt")) {
			getCorpusFile();

		} else {
			System.out.println("incorrect corpus file name");
		}

		String targetFile = args[1];
		if (targetFile.equalsIgnoreCase("target.txt")) {
			getTargetFile();
		} else {
			System.out.println("incorrect target file name");
		}

		// get match sequence
		int parseSequence = Integer.parseInt(args[2]);

		Paper p = new Paper(value);

		p.parse(parseSequence);

	}
}
