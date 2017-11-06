package parser;

import java.nio.ByteBuffer;

public class TransactionOutput {
    public Satoshi value;
    // TODO: figure out VarInt protocol
    public /*var*/int scriptLength;
    public byte[] script;

    public TransactionOutput parseTransactionOutput(byte[] transactionOutputBytes) {
        if (transactionOutputBytes == null)
            return null;

        TransactionOutput txOut = new TransactionOutput();
        ByteBuffer txOutBuffer = ByteBuffer.wrap(transactionOutputBytes);

        byte[] tempArray = new byte[8];
        txOutBuffer.get(tempArray);
        value = new Satoshi(tempArray);

        // TODO: parse varint (for now its just hard coded 8 bytes, not gonna work)
        txOut.scriptLength = 8;

        txOut.script = new byte[txOut.scriptLength];
        txOutBuffer.get(txOut.script);

        return txOut;
    }
}
