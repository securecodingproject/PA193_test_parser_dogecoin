package parser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Helpers {

    // https://stackoverflow.com/questions/140131/convert-a-string-representation-of-a-hex-dump-to-a-byte-array-using-java
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    // Byte array to hex string
    // https://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to-a-hex-string-in-java
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String byteArrayToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    // https://stackoverflow.com/questions/2137755/how-do-i-reverse-an-int-array-in-java
    public static byte[] reverseByteArray(byte[] array) {
        int left = 0;
        int right = array.length - 1;
        byte[] tmpByteArray = Arrays.copyOf(array, array.length);

        while( left < right ) {
            // swap the values at the left and right indices
            byte temp = tmpByteArray[left];
            tmpByteArray[left] = tmpByteArray[right];
            tmpByteArray[right] = temp;

            // move the left and right index pointers in toward the center
            left++;
            right--;
        }

        return tmpByteArray;
    }

    public static String reversedByteArrayAsString(byte[] ba) {
        return byteArrayToHexString(Helpers.reverseByteArray(ba));
    }
}
