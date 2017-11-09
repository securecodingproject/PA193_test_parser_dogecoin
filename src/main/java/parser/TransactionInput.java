package parser;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TransactionInput {
    public byte[] prevTransactionHash;
    public int prevTransactionOutputIndex;
    public VarInt scriptLength;
    public byte[] script;
    public int sequenceNumber;
    public long inputSize;

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
    	if (transactionInputBytes == null)
            return null;
    	
    	TransactionInput txIn = new TransactionInput();
    	ByteBuffer txInBuffer = ByteBuffer.wrap(transactionInputBytes);
    	txInBuffer.order(ByteOrder.LITTLE_ENDIAN);
        
        txInBuffer.get(txIn.prevTransactionHash);
        txIn.prevTransactionOutputIndex = txInBuffer.getInt();

        byte[] vi = new byte[9];
        txInBuffer.get(vi);
        txIn.scriptLength = new VarInt(vi, 0);
        txInBuffer.position(txInBuffer.position() - (9 - txIn.scriptLength.size));

        txIn.script = new byte[(int)txIn.scriptLength.value];
        txInBuffer.get(txIn.script);

        txIn.sequenceNumber = txInBuffer.getInt();
        
        txIn.inputSize = 32 + 4 + txIn.scriptLength.size + txIn.scriptLength.value + 4;
    	
        return txIn;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- begin transaction input ---\r\n");
        sb.append("Previous transaction hash: ");
        sb.append(Helpers.reversedByteArrayAsString(this.prevTransactionHash));
        sb.append("\r\nPrevious transaction out index: ");
        sb.append(Integer.toUnsignedString(this.prevTransactionOutputIndex));
        sb.append(" (hex: ");
        sb.append(Integer.toHexString(this.prevTransactionOutputIndex));
        sb.append(")\r\nScript Length: ");
        sb.append(Long.toUnsignedString(this.scriptLength.value));
        sb.append("\r\nScript: ");
        sb.append(Helpers.reversedByteArrayAsString(this.script));
        sb.append("\r\nSequence number: ");
        sb.append(Integer.toUnsignedString(this.sequenceNumber));
        sb.append(" (hex: ");
        sb.append(Integer.toHexString(this.sequenceNumber));
        sb.append(")\r\n--- end transaction input ---");
        return sb.toString();
    }
}