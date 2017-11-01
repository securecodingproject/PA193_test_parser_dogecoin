package parser.objects;

import java.sql.Timestamp;

// https://en.bitcoin.it/wiki/Block_hashing_algorithm
public class BlockHeader {
    public int version;
    public static final short hashLength = 32;
    public byte[] hashPrevBlock;
    public byte[] hashMerkleRoot;
    public Timestamp time;
    public static final short targetLength = 32;
    public byte[] target;
    public long nonce;
}
