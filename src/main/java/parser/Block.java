package parser;

import java.nio.ByteBuffer;
import java.util.List;

// https://en.bitcoin.it/wiki/Block
public class Block {
    public static final byte[] magicNumber = new byte[] { (byte)0xC0, (byte)0xC0, (byte)0xC0, (byte)0xC0 };
    public int blockSize;
    public BlockHeader blockHeader;
    public int transactionCounter;
    public List<Transaction> transactions;

    /**
     * Given an array of bytes, determines if it is the magic number
     * associated with Dogecoin.
     *
     * @param magicNumber
     * @return value indicates whether magic number is correct
     */
    public static boolean checkMagicNumber(byte[] magicNumber) {
        if (magicNumber.length != Block.magicNumber.length)
            return false;

        for (int i = 0; i < Block.magicNumber.length; i++)
            if (magicNumber[i] != Block.magicNumber[i])
                return false;

        return true;
    }

    // TODO: function should probably get a ByteBuffer
    // as a parameter, that would make things easier
    /**
     * parseBlock parses an array of bytes into a Block object.
     *
     * @param blockBytes
     * @return parsed Block object
     */
    public static Block parseBlock(byte[] blockBytes) {
        ByteBuffer blockBuffer = ByteBuffer.wrap(blockBytes);
        Block block = new Block();

        // parse magic number
        byte[] magicNumberBytes = new byte[Block.magicNumber.length];
        blockBuffer.get(magicNumberBytes, 0, Block.magicNumber.length);
        if (!Block.checkMagicNumber(magicNumberBytes))
            // TODO: figure out error handling
            return null;

        // parse block size
        block.blockSize = blockBuffer.getInt();
        if (block.blockSize != (blockBuffer.limit() - Block.magicNumber.length))
            // TODO: figure out error handling
            return null;
        int lengthUntilEndOfBlock = block.blockSize;

        // parse block header
        byte[] blockHeaderBytes = new byte[BlockHeader.length];
        blockBuffer.get(blockHeaderBytes);
        block.blockHeader = BlockHeader.parseBlockHeader(blockHeaderBytes);
        lengthUntilEndOfBlock -= BlockHeader.length;

        // parse transaction counter
        block.transactionCounter = blockBuffer.getInt();
        lengthUntilEndOfBlock -= 4;

        // parse transactions
        byte[] transactionListBytes = new byte[lengthUntilEndOfBlock];
        blockBuffer.get(transactionListBytes);
        block.transactions = Transaction.parseTransactionList(transactionListBytes);

        return block;
    }
}
