package parser;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

// https://en.bitcoin.it/wiki/Transaction
public class Transaction {
    public int version;
    public VarInt inCounter;
    public List<TransactionInput> inputs;
    public VarInt outCounter;
    public List<TransactionOutput> outputs;
    public static int lockTimeLength = 4;
    public byte[] lockTime;

    public Transaction() {
        lockTime = new byte[lockTimeLength];
    }

    /**
     * parseTransaction parses an array of bytes containing a transaction into a Transaction object.
     *
     * @param transactionBytes array of bytes containing a transaction
     * @return parsed Transaction object, or null if failed.
     */
    public static Transaction parseTransaction(byte[] transactionBytes) {
        if (transactionBytes == null)
            return null;

        ByteBuffer txBuffer = ByteBuffer.wrap(transactionBytes);
        txBuffer.order(ByteOrder.LITTLE_ENDIAN);

        Transaction tx = new Transaction();
        tx.version = txBuffer.getInt();

        byte[] vi = new byte[9];
        txBuffer.get(vi);
        tx.inCounter = new VarInt(vi, 0);
        // jump to after varint
        // casting to int is very wrong, don't know what to do instead
        txBuffer.position(txBuffer.position() - (9 - (int)tx.inCounter.value));

        List<TransactionInput> txInList = new ArrayList<TransactionInput>();

        for (int i = 0; i < tx.inCounter.value; i++) {
            // parse txIn then add to txInList
        }


        txBuffer.get(vi);
        tx.outCounter = new VarInt(vi, 0);
        // jump to after varint
        // casting to int is very wrong, don't know what to do instead
        txBuffer.position(txBuffer.position() - (9 - (int)tx.inCounter.value));

        for (int i = 0; i < tx.outCounter.value; i++) {
            // parse txIn then add to txInList
        }

        byte[] lockTimeBytes = new byte[lockTimeLength];
        txBuffer.get(lockTimeBytes);

        return tx;
    }

    /**
     * parseTransactionList parses an array of bytes array of bytes containing a list of transactions into a List<Transaction> object.
     *
     * @param transactionListBytes array of bytes containing several transactions
     * @return parsed List<Transaction> object.
     */
    public static List<Transaction> parseTransactionList(byte[] transactionListBytes, VarInt transactionCounter) {
        ByteBuffer txListBuffer = ByteBuffer.wrap(transactionListBytes);
        txListBuffer.order(ByteOrder.LITTLE_ENDIAN);

        List<Transaction> txList = new ArrayList<Transaction>();

        // parse transactions

        return null;
    }
}
