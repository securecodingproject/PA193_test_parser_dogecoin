package parser;

import java.nio.ByteBuffer;

public class TransactionOutput {
    // byte array for now, we need a Satoshi implementation
    public byte[] value;
    // TODO: figure out VarInt protocol
    public /*var*/int scriptLength;
    public byte[] script;

    public TransactionOutput() {
        value = new byte[8];
    }

    public TransactionOutput parseTransactionOutput(byte[] transactionOutputBytes) {
        TransactionOutput txOut = new TransactionOutput();
        ByteBuffer txOutBuffer = ByteBuffer.wrap(transactionOutputBytes);

        // TODO: after implementing Satoshi, parse it properly. byte array for now
        txOutBuffer.get(value);

        // TODO: parse varint (for now its just hard coded 8 bytes, not gonna work)
        txOut.scriptLength = 8;

        txOut.script = new byte[txOut.scriptLength];
        txOutBuffer.get(txOut.script);

        return txOut;
    }
}
