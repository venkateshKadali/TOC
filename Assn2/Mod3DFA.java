package toc;

import java.util.Scanner;

public class Mod3DFA {
	private static final int q0 = 0;
	private static final int q1 = 1;
	private static final int q2 = 2;
	private static final int q3 = 3;
	private static int state;
	public static boolean processString(String x) {
		state = 0;
		for(int i=0;i<x.length();i++) {
			char c = x.charAt(i);
			state = delta(state,c);
		}
		return state == q0;
	}
	
	private static int delta(int s, char c) {
		switch(s) {
		case q0: switch(c) {
		case '0': return q0;
		case '1': return q1;
		default: return q3;
		}
		case q1: switch(c) {
		case '0': return q2;
		case '1': return q0;
		default: return q3;
		} 
		case q2: switch(c) {
		case '0': return q1;
		case '1': return q2;
		default: return q3;
		}
		default: return q3;
		}		
	}

	public static void main(String[] args) {
		
		System.out.println("Enter string");
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		System.out.println(processString(input));
	}

}
