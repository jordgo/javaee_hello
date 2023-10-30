package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.el.StaticFieldELResolver;

public class Validation {
	
	public Validation() {};
	
	public static boolean isNameValid(String name) {
		int minLength = 4;
		int maxLength = 20;
		
		if (name != null && minLength < name.length() && name.length() < maxLength) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public static boolean isPasswordValid(String password) {
		// digit + lowercase char + uppercase char + punctuation + symbol
	    String PASSWORD_PATTERN =
	            "^[a-zA-Z0-9_]{6,}$";
	    
	    Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
	    
	    Matcher matcher = pattern.matcher(password);		
		
		return password != null;// && matcher.matches();
		
	}

}
