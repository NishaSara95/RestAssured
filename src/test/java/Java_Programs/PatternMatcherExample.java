package Java_Programs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcherExample {

	public static void patternEx() {
		String input = "She is Nisha; she is very friendly; she is professional; she is easy going; she takes things in good way; she helps the needy";
		Pattern pattern = Pattern.compile("is");
		Matcher matcher = pattern.matcher(input);

		while (matcher.find()) {
			System.out.println("Found the is word " + matcher.start());

		}

	}

	public static void main(String[] args) {
		patternEx();
	}

}
