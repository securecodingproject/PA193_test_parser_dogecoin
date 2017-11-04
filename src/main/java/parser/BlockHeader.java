package parser;

import java.nio.ByteBuffer;
import java.sql.Timestamp;

// https://en.bitcoin.it/wiki/Block_hashing_algorithm
public class BlockHeader {
    public static int length = 4 + 20 + 20 + 4 + 4 + 4;

    public int version;
    // scrypt hash length
    public static final short hashLength = 20;
    public byte[] hashPrevBlock;
    public byte[] hashMerkleRoot;
    public int timeInSecondsSinceEpoch;
    public Timestamp time;
    public int target;
    public int nonce;

    public BlockHeader() {
        hashPrevBlock = new byte[hashLength];
        hashMerkleRoot = new byte[hashLength];
    }

    /**
     * parseBlockHeader parses an array of bytes containing a block's header into a BlockHeader object
     *
     * @param blockHeaderBytes array of bytes containing a block's header
     * @return parsed BlockHeader object
     */
    public static BlockHeader parseBlockHeader(byte[] blockHeaderBytes) {
        if (blockHeaderBytes.length != BlockHeader.length)
            // TODO: figure out error handling
            return null;

        ByteBuffer blockHeaderBuffer = ByteBuffer.wrap(blockHeaderBytes);
        BlockHeader blockHeader = new BlockHeader();

        blockHeader.version = blockHeaderBuffer.getInt();
        blockHeaderBuffer.get(blockHeader.hashPrevBlock);
        blockHeaderBuffer.get(blockHeader.hashMerkleRoot);
        blockHeader.timeInSecondsSinceEpoch = blockHeaderBuffer.getInt();
        // TODO: parse Timestamp time from timeInSecondsSinceEpoch
        blockHeader.target = blockHeaderBuffer.getInt();
        blockHeader.nonce = blockHeaderBuffer.getInt();

        return blockHeader;
    }
}
