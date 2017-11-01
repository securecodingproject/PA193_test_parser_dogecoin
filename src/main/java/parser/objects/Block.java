package parser.objects;

import java.util.List;

// https://en.bitcoin.it/wiki/Block
public class Block {
    public static final byte[] magicNumber = new byte[] { (byte)0xC0, (byte)0xC0, (byte)0xC0, (byte)0xC0};
    public static final int magicNumberSize = 4;
    public int blockSize;
    public BlockHeader blockHeader;
    public int transactionCounter;
    public List<Transaction> transactions;
}
