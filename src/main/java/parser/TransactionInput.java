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

    public TransactionInput parseTransactionInput(byte[] transactionInputBytes) {

    }
}
