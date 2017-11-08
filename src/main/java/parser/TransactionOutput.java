package parser;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TransactionOutput {
    public long value;
    // TODO: figure out VarInt protocol
    public VarInt scriptLength;
    public byte[] script;
    public long outputSize;

    public static TransactionOutput parseTransactionOutput(byte[] transactionOutputBytes) {
        if (transactionOutputBytes == null)
            return null;

        TransactionOutput txOut = new TransactionOutput();
        ByteBuffer txOutBuffer = ByteBuffer.wrap(transactionOutputBytes);
        txOutBuffer.order(ByteOrder.LITTLE_ENDIAN);

        txOut.value = txOutBuffer.getLong();

        byte[] vi = new byte[9];
        txOutBuffer.get(vi);
        txOut.scriptLength = new VarInt(vi, 0);
        txOutBuffer.position(txOutBuffer.position() - (9 - txOut.scriptLength.size));

        txOut.script = new byte[(int)txOut.scriptLength.value];
        txOutBuffer.get(txOut.script);

        txOut.outputSize = 8 + txOut.scriptLength.size + txOut.scriptLength.value;

        return txOut;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Value: ");
        sb.append(Long.toUnsignedString(this.value));
        sb.append(" Satoshis");
        sb.append("\r\nScript Length: ");
        sb.append(Long.toUnsignedString(this.scriptLength.value));
        sb.append("\r\nScript: ");
        sb.append(Helpers.byteArrayToHexString(this.script));
        return sb.toString();
    }
}
