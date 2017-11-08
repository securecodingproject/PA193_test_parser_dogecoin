package parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionOutputTest {

    @Test
    public void TestParseTransactionOutput() {
        String inputAsHexString = "004023EF3806000023210338BF57D51A50184CF5EF0DC42ECD519FB19E24574C057620262CC1DF94DA2AE5AC";
        byte[] inputAsByteArray = Helpers.hexStringToByteArray(inputAsHexString);

        TransactionOutput out = TransactionOutput.parseTransactionOutput(inputAsByteArray);

        assertEquals(out.scriptLength.value, 35);
        assertArrayEquals(out.script, Helpers.hexStringToByteArray("210338BF57D51A50184CF5EF0DC42ECD519FB19E24574C057620262CC1DF94DA2AE5AC"));
        assertEquals(out.outputSize, (long)inputAsByteArray.length);
    }
}
