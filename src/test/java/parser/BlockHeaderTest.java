package parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class BlockHeaderTest {
    public class BlockHeaderTestCase {
        String testName;
        byte[] blockHeaderByteArray;
        BlockHeader expected;
    }

    @Test
    public void TestParseBlockHeader() {
        BlockHeaderTestCase tcs[] = new BlockHeaderTestCase[3];
        tcs[0] = new BlockHeaderTestCase();
        tcs[0].testName = "valid block header";
        tcs[0].blockHeaderByteArray = Helpers.hexStringToByteArray("010000000000000000000000000000000000000000000000000000000000000000000000696AD20E2DD4365C7459B4A4A5AF743D5E92C6DA3229E6532CD605F6533F2A5B24A6A152F0FF0F1E67860100");
        tcs[0].expected = new BlockHeader();
        tcs[0].expected.hashPrevBlock = new byte[32];
        tcs[0].expected.hashPrevBlock = Helpers.hexStringToByteArray("0000000000000000000000000000000000000000000000000000000000000000");
        tcs[0].expected.hashMerkleRoot = new byte[32];
        tcs[0].expected.hashMerkleRoot = Helpers.hexStringToByteArray("696AD20E2DD4365C7459B4A4A5AF743D5E92C6DA3229E6532CD605F6533F2A5B");
        tcs[0].expected.timeInSecondsSinceEpoch = 1386325540;
        tcs[0].expected.target = 504365040;
        tcs[0].expected.nonce = 99943;

        tcs[1] = new BlockHeaderTestCase();
        tcs[1].testName = "inappropriate length";
        tcs[1].blockHeaderByteArray = Helpers.hexStringToByteArray("01898984732EBFC3");
        tcs[1].expected = null;

        tcs[2] = new BlockHeaderTestCase();
        tcs[2].testName = "null passed";
        tcs[2].blockHeaderByteArray = null;
        tcs[2].expected = null;

        for (int i = 0; i < tcs.length; i++) {
            BlockHeader bh = BlockHeader.parseBlockHeader(tcs[i].blockHeaderByteArray);

            if (tcs[i].expected == null || bh == null) {
                assertTrue(tcs[i].expected == bh);
                continue;
            }

            assertTrue(bh.version == 1);
            assertTrue(Helpers.isSame(bh.hashPrevBlock, tcs[i].expected.hashPrevBlock));
            assertTrue(Helpers.isSame(bh.hashMerkleRoot, tcs[i].expected.hashMerkleRoot));
            assertTrue(bh.timeInSecondsSinceEpoch == tcs[i].expected.timeInSecondsSinceEpoch);
            assertTrue(bh.target == tcs[i].expected.target);
            assertTrue(bh.nonce == tcs[i].expected.nonce);
        }
    }
}