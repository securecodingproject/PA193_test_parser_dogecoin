package parser;

public class TransactionInput {
    public byte[] prevTransactionHash;
    public int prevTransactionOutputIndex;
    public /*var*/int scriptLength;
    public byte[] scriptSig;
    public int sequenceNo;

    public TransactionInput() {
        prevTransactionHash = new byte[32];
    }

    /**
     * parseTransactionInput parses a byte array into a TransactionInput object
     *
     * @param transactionInputBytes byte array containing transaction input
     * @return parsed TransactionInput object
     */
    public static TransactionInput parseTransactionInput(byte[] transactionInputBytes) {
        return null;
    }
}
