package com.cmcpredict.helper;

import java.util.Scanner;

public class SystemHelper {

	public Object getInput(String Question) {
		System.out.println(Question);
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}
}
