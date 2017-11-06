package parser;

public class TransactionOutput {
    public /*Satoshi*/int value;
    // TODO: figure out VarInt protocol
    public /*var*/int scriptLength;
    public byte[] script;

    public TransactionOutput parseTransactionOutput(byte[] txOutBytes) {

    }
}
