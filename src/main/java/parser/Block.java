package parser;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://en.bitcoin.it/wiki/Block
public class Block {
    public static final byte[] magicNumber = new byte[] { (byte)0xC0, (byte)0xC0, (byte)0xC0, (byte)0xC0 };
    public int blockSize;
    public BlockHeader blockHeader;
    public VarInt transactionCounter;
    public List<Transaction> transactions;

    /**
     * Given an array of bytes, determines if it is the magic number
     * of Dogecoin (0xC0C0C0C0).
     *
     * @param magicNumber array of bytes containing bytes to check against magic number
     * @return value indicates whether magic number is correct
     */
    public static boolean checkMagicNumber(byte[] magicNumber) {
        return Arrays.equals(magicNumber, Block.magicNumber);
    }

    /**
     * Parses an array of bytes containing a block into a Block object
     *
     * @param blockBytes array of bytes containing block
     * @return parsed Block object
     */
    public static Block parseBlock(byte[] blockBytes) {
        ByteBuffer blockBuffer = ByteBuffer.wrap(blockBytes);
        blockBuffer.order(ByteOrder.LITTLE_ENDIAN);

        Block block = new Block();

        // check magic number
        byte[] magicNumberBytes = new byte[Block.magicNumber.length];
        blockBuffer.get(magicNumberBytes);
        if (!Block.checkMagicNumber(magicNumberBytes))
            return null;

        // parse block size
        block.blockSize = blockBuffer.getInt();
        if (block.blockSize != (blockBuffer.limit() - Block.magicNumber.length - 4))
            return null;
        int lengthUntilEndOfBlock = block.blockSize;

        // parse block header
        byte[] blockHeaderBytes = new byte[BlockHeader.length];
        blockBuffer.get(blockHeaderBytes);
        block.blockHeader = BlockHeader.parseBlockHeader(blockHeaderBytes);
        lengthUntilEndOfBlock -= BlockHeader.length;

        // parse transaction counter
        byte[] vi = new byte[9];
        blockBuffer.get(vi);
        block.transactionCounter = new VarInt(vi, 0);
        blockBuffer.position(blockBuffer.position() - (9 -  block.transactionCounter.size));
        lengthUntilEndOfBlock -= block.transactionCounter.size;


        block.transactions = new ArrayList<Transaction>();
        long remainingTransactionsToParse = block.transactionCounter.value;
        for (long i = remainingTransactionsToParse; i > 0; i--) {
            int initialPos = blockBuffer.position();

            // get remaining bytes
            byte[] remainingTransactionsBytes = new byte[lengthUntilEndOfBlock];
            blockBuffer.get(remainingTransactionsBytes);

            Transaction tx = Transaction.parseFirstTransactionFromBytes(remainingTransactionsBytes);
            if (tx == null)
                return null;

            block.transactions.add(tx);

            lengthUntilEndOfBlock -= tx.transactionSize;

            // move buffer mark to beginning of next transaction
            blockBuffer.position(initialPos + tx.transactionSize);
        }

        return block;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- begin block ---\r\nBlock size: ");
        sb.append(Integer.toUnsignedString(this.blockSize));
        sb.append(this.blockHeader.toString());

        sb.append("\r\nNumber of transactions: ");
        sb.append(Long.toUnsignedString(this.transactionCounter.value));
        sb.append("\r\n\r\nTransactions:\r\n");
        for (Transaction tx : this.transactions) {
            sb.append(tx.toString());
        }

        sb.append("--- end block ---\r\n");
        return sb.toString();
    }
}
