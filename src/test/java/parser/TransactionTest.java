package parser;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

public class TransactionTest {

    @Test
    public void TestParseTransaction() {
        byte[] input = Helpers.hexStringToByteArray("01000000010000000000000000000000000000000000000000000000000000000000000000FFFFFFFF0E04BCEDA3520101062F503253482FFFFFFFFF0100CFDF5F0401000023210382D15F92B26AC556B5FE13DF19629BE1F68440547FCB236516757CF64F6BFF6CAC00000000");

        Transaction out = Transaction.parseFirstTransactionFromBytes(input);

        // TODO: asserts
        assertEquals(out.version, 1);
        assertEquals(out.inCounter.value, 1);
        //List<TransactionInput> inputs
        assertEquals(out.outCounter.value, 1);
        //List<TransactionOutput> ouputs
        //lockTimeLength
        assertArrayEquals(out.lockTime, Helpers.hexStringToByteArray("00000000"));
        assertEquals(out.transactionSize, (int)input.length);
    }
}
