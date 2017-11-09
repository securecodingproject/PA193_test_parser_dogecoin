package parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionOutputTest {

    @Test
    public void TestParseTransactionOutput() {
        String inputAsHexString = "004023EF3806000023210338BF57D51A50184CF5EF0DC42ECD519FB19E24574C057620262CC1DF94DA2AE5AC";
        byte[] inputAsByteArray = Helpers.hexStringToByteArray(inputAsHexString);

        TransactionOutput out = TransactionOutput.parseTransactionOutput(inputAsByteArray);

        assertNotEquals(null, out);
        assertEquals(35, out.scriptLength.value);
        assertArrayEquals(Helpers.hexStringToByteArray("210338BF57D51A50184CF5EF0DC42ECD519FB19E24574C057620262CC1DF94DA2AE5AC"), out.script);
        assertEquals((long)inputAsByteArray.length, out.outputSize);
    }

    @Test
    public void TestParseTransactionOutputShouldOnlyParseOne() {
        String inputAsHexString = "004023EF3806000023210338BF57D51A50184CF5EF0DC42ECD519FB19E24574C057620262CC1DF94DA2AE5ACFFFF";
        byte[] inputAsByteArray = Helpers.hexStringToByteArray(inputAsHexString);

        TransactionOutput out = TransactionOutput.parseTransactionOutput(inputAsByteArray);

        assertNotEquals(null, out);
        assertEquals(35, out.scriptLength.value);
        assertArrayEquals(Helpers.hexStringToByteArray("210338BF57D51A50184CF5EF0DC42ECD519FB19E24574C057620262CC1DF94DA2AE5AC"), out.script);
        assertEquals((long)(inputAsByteArray.length - 2), out.outputSize);
    }

    @Test
    public void TestParseTransactionOutputGivenNullShouldReturnNull() {
        TransactionOutput out = TransactionOutput.parseTransactionOutput(null);

        assertEquals(null, out);
    }
}
