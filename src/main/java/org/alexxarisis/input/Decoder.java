package org.alexxarisis.input;

public class Decoder {

    public static String atbash(String contents) {
        return contents.chars()
                .map(cp -> Character.isUpperCase(cp) ? 'Z' - (cp - 'A')
                        : Character.isLowerCase(cp) ? 'z' - (cp - 'a') : cp)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String rot13(String contents) {
        StringBuilder decoded = new StringBuilder();
        int len = contents.length();

        for (int i = 0; i < len; i++) {
            char c = contents.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            decoded.append(c);
        }
        return decoded.toString();
    }
}
