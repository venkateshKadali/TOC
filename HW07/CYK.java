package CNF;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CYK {
	HashMap<String, String> hashmap;
	Set<String> set[][];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CYK cyk = new CYK();
		cyk.CNF();
		boolean b1 = cyk.unit_test_1("abc");
		if (b1)
			System.out.println("\n Input String: \"abc\" \n Result = " + b1);
		else
			System.out.println("\n Input String: \"abc\" \n Result = " + b1);

		boolean b2 = cyk.unit_test_2("abbbabb");

		if (b2)
			System.out.println("\n Input String: \"abbbabb\" \n Result = " + b2);
		else
			System.out.println("\n Input String: \"abbbabb\" \n Result = " + b2);

		boolean b3 = cyk.unit_test_3("abbc");
		if (b3)
			System.out.println("\n Input String: \"abbc\" \n Result = " + b3);
		else
			System.out.println("\n Input String: \"abbc\" \n Result = " + b3);

		boolean b4 = cyk.unit_test_4("bbc");
		if (b4)
			System.out.println("\n Input String: \"bbc\" \n Result = " + b4);
		else
			System.out.println("\n Input String: \"bbc\" \n Result = " + b4);

		boolean b5 = cyk.unit_test_5("aaabb");
		if (b5)
			System.out.println("\n Input String: \"aaabb\" \n Result = " + b5);
		else
			System.out.println("\n Input String: \"abbc\" \n Result = " + b5);

	}

	private boolean unit_test_1(String string) {
		// TODO Auto-generated method stub
		boolean value = isInCFL(string);
		return value;
	}

	public boolean unit_test_2(String string) {
		boolean b = isInCFL(string);
		return b;

	}

	public boolean unit_test_3(String string) {
		boolean b = isInCFL(string);
		return b;

	}

	public boolean unit_test_4(String string) {
		boolean b = isInCFL(string);
		return b;

	}

	public boolean unit_test_5(String string) {
		boolean b = isInCFL(string);
		return b;

	}

	@SuppressWarnings("unchecked")
	private boolean isInCFL(String string) {
		set = new HashSet[string.length()][string.length()];
		for (int i = 0; i < string.length(); i++) {
			for (int j = 0; j < string.length(); j++) {
				set[i][j] = new HashSet<String>();
			}
		}
		try {
			if (hashmap.isEmpty()) {
				System.out.println("empty");
				return false;
			}
			for (int i = 0; i < string.length(); i++) {
				if (hashmap.containsKey(String.valueOf(string.charAt(i)))) {
					set[i][0].add(hashmap.get(String.valueOf(string.charAt(i))));
				}
			}
			for (int i = 1; i < string.length(); i++) {
				for (int s = 0; s < string.length() - i; s++) {
					for (int k = 0; k < i; k++) {
						for (String p : set[s][k]) {
							for (String q : set[s + k + 1][i - k - 1]) {
								String r = p.concat(q);
								if (hashmap.containsKey(r)) {
									set[s][i].add(hashmap.get(r));
								}
							}

						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (set[0][string.length() - 1].isEmpty())
			return false;
		else {
			if (set[0][string.length() - 1].contains("S"))
				return true;
			else
				return false;
		}
	}

	void CNF() {
		// TODO Auto-generated method stub
		hashmap = new HashMap<String, String>();
		hashmap.put("XC", "S");
		hashmap.put("YB", "C");
		hashmap.put("BB", "B");
		hashmap.put("AB", "X");
		hashmap.put("BA", "Y");
		hashmap.put("a", "A");
		hashmap.put("b", "B");
		hashmap.put("c", "C");

	}

}
