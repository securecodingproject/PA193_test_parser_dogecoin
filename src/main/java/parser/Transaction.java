package parser;

import java.util.List;

// https://en.bitcoin.it/wiki/Transaction
public class Transaction {
    public int version;
    // TODO: figure out VarInt protocol https://en.bitcoin.it/wiki/Protocol_specification#Variable_length_integer
    public /*var*/int inCounter;
    public List<InOrOutput> inputs;
    // TODO: figure out VarInt protocol https://en.bitcoin.it/wiki/Protocol_specification#Variable_length_integer
    public /*var*/int outCounter;
    public List<InOrOutput> outputs;
    public static int lockTimeLength = 4;
    public byte[] lockTime;

    public Transaction() {
        lockTime = new byte[lockTimeLength];
    }

    /**
     * parseTransaction parses an array of bytes containing a transaction into a Transaction object.
     *
     * @param transactionBytes array of bytes containing a transaction
     * @return parsed Transaction object.
     */
    public static Transaction parseTransaction(byte[] transactionBytes) {
        return null;
    }

    /**
     * parseTransactionList parses an array of bytes array of bytes containing a list of transaction into a List<Transaction> object.
     *
     * @param transactionListBytes array of bytes containing several transactions
     * @return parsed List<Transaction> object.
     */
    public static List<Transaction> parseTransactionList(byte[] transactionListBytes) {
        return null;
    }
}
