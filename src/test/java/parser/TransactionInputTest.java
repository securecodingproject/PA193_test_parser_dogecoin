package parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionInputTest {

    @Test
    public void TestParseTransactionInput() {
        byte[] inputAsByteArray = Helpers.hexStringToByteArray("0000000000000000000000000000000000000000000000000000000000000000FFFFFFFF0E04BCEDA3520101062F503253482FFFFFFFFF");

        TransactionInput out = TransactionInput.parseTransactionInput(inputAsByteArray);

        // TODO: asserts
    }

    @Test
    public void TestParseTransactionInputShouldOnlyParseOne() {
        byte[] inputAsByteArray = Helpers.hexStringToByteArray("0000000000000000000000000000000000000000000000000000000000000000FFFFFFFF0E04BCEDA3520101062F503253482FFFFFFFFF0000000000000000000000000000000000000000000000000000000000000000FFFFFFFF0E04BCEDA3520101062F503253482FFFFFFFFF");

        TransactionInput out = TransactionInput.parseTransactionInput(inputAsByteArray);

        // TODO: asserts
    }
}
