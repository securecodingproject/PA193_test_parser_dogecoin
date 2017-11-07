package parser;

import java.nio.ByteBuffer;

public class TransactionOutput {
    public Satoshi value;
    // TODO: figure out VarInt protocol
    public VarInt scriptLength;
    public byte[] script;

    public TransactionOutput parseTransactionOutput(byte[] transactionOutputBytes) {
        if (transactionOutputBytes == null)
            return null;

        TransactionOutput txOut = new TransactionOutput();
        ByteBuffer txOutBuffer = ByteBuffer.wrap(transactionOutputBytes);

        byte[] tempArray = new byte[8];
        txOutBuffer.get(tempArray);
        value = new Satoshi(tempArray);

        byte[] vi = new byte[9];
        txOutBuffer.get(vi);
        txOut.scriptLength = new VarInt(vi, 0);
        // casting to int is very wrong, don't know what to do instead
        txOutBuffer.position(txOutBuffer.position() - (9 - (int)txOut.scriptLength.value));

        txOut.script = new byte[(int)txOut.scriptLength.value];
        txOutBuffer.get(txOut.script);

        return txOut;
    }
}
