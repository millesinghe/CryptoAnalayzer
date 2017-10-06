package com.cmcpredict.helper;

import java.util.Scanner;

public class SystemHelper {

	public Object getInput(String[] opsList) {
		System.out.println(opsList[1]);
		Scanner sc = new Scanner(System.in);

		if (opsList[0].equals("s")) {
			return sc.next();
		} else if (opsList[0].equals("i")) {
			return String.valueOf(sc.nextInt());
		} else if (opsList[0].equals("d")) {
			return String.valueOf(sc.nextDouble());
		} else {
			return null;
		}

	}
}
