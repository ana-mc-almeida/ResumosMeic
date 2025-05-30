package stringhelper;

public class StringHelper {

    public String reverseLast2Chars(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }
        
        return str.substring(0, str.length() - 2) + str.charAt(str.length() - 1) + str.charAt(str.length() - 2);
    }
    
}
