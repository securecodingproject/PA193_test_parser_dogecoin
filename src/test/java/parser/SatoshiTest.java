package parser;

import org.junit.Test;
import static org.junit.Assert.*;

public class SatoshiTest {
    private class SatoshiTestCase {
        public String testName;
        public byte[] input;
        public long expected;
    }

    @Test
    public void TestSatoshi() {
        SatoshiTestCase[] tcs = new SatoshiTestCase[2];

        tcs[0] = new SatoshiTestCase();
        tcs[0].testName = "1";
        tcs[0].input = new byte[] {(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01 };
        tcs[0].expected = 1;

        tcs[1] = new SatoshiTestCase();
        tcs[1].testName = "0xFF";
        tcs[1].input = new byte[] {(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xFF };
        tcs[1].expected = 0x00000000000000FF;

        for (int i = 0; i < tcs.length; i++) {
            long out = new Satoshi(tcs[i].input).asLong();
            assertTrue(tcs[i].expected == out);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestSatoshiNullInput() {
        Satoshi s = new Satoshi(null);
    }
}
