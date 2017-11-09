package parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionInputTest {

    @Test
    public void TestParseTransactionInput() {
        byte[] inputAsByteArray = Helpers.hexStringToByteArray("0000000000000000000000000000000000000000000000000000000000000000FFFFFFFF0E04BCEDA3520101062F503253482FFFFFFFFF");

        TransactionInput in = TransactionInput.parseTransactionInput(inputAsByteArray);

        // TODO: asserts
        assertArrayEquals(in.prevTransactionHash, Helpers.hexStringToByteArray("0000000000000000000000000000000000000000000000000000000000000000"));
        assertEquals(in.prevTransactionOutputIndex, -1);
        assertEquals(in.scriptLength.value, 14);
        assertArrayEquals(in.script, Helpers.hexStringToByteArray("04BCEDA3520101062F503253482F"));
        assertEquals(in.sequenceNumber, -1);
        assertEquals(in.inputSize, (long)inputAsByteArray.length);
    }

    @Test
    public void TestParseTransactionInputShouldOnlyParseOne() {
        byte[] inputAsByteArray = Helpers.hexStringToByteArray("0000000000000000000000000000000000000000000000000000000000000000FFFFFFFF0E04BCEDA3520101062F503253482FFFFFFFFF0000000000000000000000000000000000000000000000000000000000000000FFFFFFFF0E04BCEDA3520101062F503253482FFFFFFFFF");

        TransactionInput in = TransactionInput.parseTransactionInput(inputAsByteArray);

        // TODO: asserts
        assertArrayEquals(in.prevTransactionHash, Helpers.hexStringToByteArray("0000000000000000000000000000000000000000000000000000000000000000"));
        assertEquals(in.prevTransactionOutputIndex, -1);
        assertEquals(in.scriptLength.value, 14);
        assertArrayEquals(in.script, Helpers.hexStringToByteArray("04BCEDA3520101062F503253482F"));
        assertEquals(in.sequenceNumber, -1);
        assertEquals(in.inputSize, ((long)inputAsByteArray.length)/2);
    }
}
