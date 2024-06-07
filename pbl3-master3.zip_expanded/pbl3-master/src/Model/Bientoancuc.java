package Model;

public class Bientoancuc {
	public static Thuthu tt = new Thuthu();
	public static Admin ad = new Admin();
	
	public static boolean CheckSDT(String s) {
	    if (s.length() != 10) {
	        return false;
	    }
	    for (int i = 0; i < s.length(); i++) {
	        if (!Character.isDigit(s.charAt(i))) {
	            return false;
	        }
	    }
	    return true;
	}
}
