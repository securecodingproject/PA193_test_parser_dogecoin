package parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionInputTest {

    @Test
    public void TestParseTransactionInput() {
        byte[] inputAsByteArray = Helpers.hexStringToByteArray("0000000000000000000000000000000000000000000000000000000000000000FFFFFFFF0E04BCEDA3520101062F503253482FFFFFFFFF");

        TransactionInput out = TransactionInput.parseTransactionInput(inputAsByteArray);

        assertArrayEquals(Helpers.hexStringToByteArray("0000000000000000000000000000000000000000000000000000000000000000"), out.prevTransactionHash);
        assertEquals(-1, out.prevTransactionOutputIndex);
        assertEquals(14, out.scriptLength.value);
        assertArrayEquals(Helpers.hexStringToByteArray("04BCEDA3520101062F503253482F"), out.script);
        assertEquals(-1, out.sequenceNumber);
        assertEquals((long)inputAsByteArray.length, out.inputSize);
    }

    @Test
    public void TestParseTransactionInputShouldOnlyParseOne() {
        byte[] inputAsByteArray = Helpers.hexStringToByteArray("0000000000000000000000000000000000000000000000000000000000000000FFFFFFFF0E04BCEDA3520101062F503253482FFFFFFFFF0000000000000000000000000000000000000000000000000000000000000000FFFFFFFF0E04BCEDA3520101062F503253482FFFFFFFFF");

        TransactionInput out = TransactionInput.parseTransactionInput(inputAsByteArray);

        assertArrayEquals(Helpers.hexStringToByteArray("0000000000000000000000000000000000000000000000000000000000000000"), out.prevTransactionHash);
        assertEquals(-1, out.prevTransactionOutputIndex);
        assertEquals(14, out.scriptLength.value);
        assertArrayEquals(Helpers.hexStringToByteArray("04BCEDA3520101062F503253482F"), out.script);
        assertEquals(-1, out.sequenceNumber);
        assertEquals(((long)inputAsByteArray.length)/2, out.inputSize);
    }

    @Test
    public void TestParseTransactionInputGivenNullShouldReturnNull() {
        TransactionInput out = TransactionInput.parseTransactionInput(null);

        assertEquals(null, out);
    }
}
