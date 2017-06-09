package edu.ncsu.csc.csc216.fsm;

public class Abbreviations {
	
	public boolean containsLol(String s) {
		int i = 0;
		int state = 0;
		s = s.toLowerCase();
		while (i < s.length()) {
			char c = s.charAt(i);
			switch (state) {
			case 0:
				if (Character.isWhitespace(c)) {
					state = 1;
				} else if (i == 0 && c == 'l') {
					state = 2;
				}
				break;
			case 1:
				if (c == 'l') {
					state = 2;
				} else if (!Character.isWhitespace(c)) {
					state = 0;
				}
				break;
			case 2:
				if (c == 'o') {
					state = 3;
				} else if (Character.isWhitespace(c)) {
					state = 1;
				} else {
					state = 0;
				}
				break;
			case 3:
				if (c == 'l') {
					state = 4;
				} else if (Character.isWhitespace(c)) {
					state = 1;
				} else {
					state = 0;
				}
				break;
			case 4:
				if (Character.isWhitespace(c)) {
					state = 5;
				} else if (Character.isLetter(c) || Character.isDigit(c)) {
					state = 0;
				} else {
					state = 5;
				}
				break;
			case 5:
				//Do nothing here
				break;
			}
			
			i++;
		}
		return state == 4 || state == 5;
	}

}
