package parser;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.lang.*;
// https://en.bitcoin.it/wiki/Block_hashing_algorithm
public class BlockHeader {
    public static final int length = 4 + 32 + 32 + 4 + 4 + 4;
    public int version;
    // scrypt hash length
    public static final short hashLength = 32;
    public byte[] hashPrevBlock;
    public byte[] hashMerkleRoot;
    public int timeInSecondsSinceEpoch;
    public Date time;
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
        if (blockHeaderBytes == null)
            return null;

        if (blockHeaderBytes.length != BlockHeader.length)
            return null;

        ByteBuffer blockHeaderBuffer = ByteBuffer.wrap(blockHeaderBytes);
        blockHeaderBuffer.order(ByteOrder.LITTLE_ENDIAN);
        BlockHeader blockHeader = new BlockHeader();

        blockHeader.version = blockHeaderBuffer.getInt();
        blockHeaderBuffer.get(blockHeader.hashPrevBlock);
        blockHeaderBuffer.get(blockHeader.hashMerkleRoot);
        blockHeader.timeInSecondsSinceEpoch = blockHeaderBuffer.getInt();
	    blockHeader.time = new Date((long)blockHeader.timeInSecondsSinceEpoch * 1000);
        blockHeader.target = blockHeaderBuffer.getInt();
        blockHeader.nonce = blockHeaderBuffer.getInt();

        return blockHeader;
    }

	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n--- begin block header --- \r\n");
        sb.append("Version: ");
        sb.append(Integer.toUnsignedString(this.version));
        sb.append("\r\nPrevious block hash: ");
        sb.append(Helpers.reversedByteArrayAsString(this.hashPrevBlock));
        sb.append("\r\nMerkle root hash of transactions: ");
        sb.append(Helpers.reversedByteArrayAsString(this.hashMerkleRoot));
        sb.append("\r\nTime: ");
        sb.append(this.time.toString());
        sb.append("\r\nTarget: ");
        sb.append(Integer.toUnsignedString(this.target));
        sb.append("\r\nNonce: ");
        sb.append(Integer.toUnsignedString(this.nonce));
        sb.append("\r\n--- end block header --- \r\n");

        return sb.toString();
    }
}
