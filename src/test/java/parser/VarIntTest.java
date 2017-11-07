package parser;

import org.junit.Test;
import static org.junit.Assert.*;

public class VarIntTest {
    @Test
    public void TestVarInt() {
        byte[] ba = Helpers.hexStringToByteArray("010100000001000000");
        VarInt vi = new VarInt(ba, 0);

        assertTrue(vi.size == 1);
        assertTrue(vi.value == 1);
    }
}
