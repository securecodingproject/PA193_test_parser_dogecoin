package parser;

import java.nio.ByteBuffer;

public class Satoshi {
    private byte[] satoshi;

    public Satoshi() {}

    public Satoshi(byte[] satoshi) {
        setSatoshi(satoshi);
    }

    public long asLong() {
        ByteBuffer bb = ByteBuffer.wrap(satoshi);
        return bb.getLong();
    }

    public void setSatoshi(byte[] satoshi) {
        if (satoshi == null)
            throw new IllegalArgumentException("satoshi byte array can not be null");
        if (satoshi.length != 8)
            throw new IllegalArgumentException("satoshi byte array must be 8 bytes long");

        this.satoshi = satoshi;
    }
}
