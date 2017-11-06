package parser;

public class Helpers {

    /**
     * isSame returns a boolean indicating whether two byte arrays have equal content
     *
     * @param a one of the byte arrays to compare
     * @param b other byte array to compare
     * @return true if a and b have same content, false if they have different content (or length)
     */
    public static boolean isSame(byte[] a, byte[] b) {
        if (a.length != b.length)
            return false;

        for (int i = 0; i < a.length; i++)
            if (a[i] != b[i])
                return false;

        return true;
    }

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
}
