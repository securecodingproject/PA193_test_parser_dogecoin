package parser;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BlockFinderTest {
    private String dogecoinFirstBlockAsHexString = "C0C0C0C0E0000000010000000000000000000000000000000000000000000000000000000000000000000000696AD20E2DD4365C7459B4A4A5AF743D5E92C6DA3229E6532CD605F6533F2A5B24A6A152F0FF0F1E678601000101000000010000000000000000000000000000000000000000000000000000000000000000FFFFFFFF1004FFFF001D0104084E696E746F6E646FFFFFFFFF010058850C020000004341040184710FA689AD5023690C80F3A49C8F13F8D45B8C857FBCBC8BC4A8E4D3EB4B10F4D4604FA08DCE601AAF0F470216FE1B51850B4ACF21B179C45070AC7B03A9AC00000000";
    private String dogecoinSecondBlockAsHexString = "C0C0C0C0BE000000010000009156352C1818B32E90C9E792EFD6A11A82FE7956A630F03BBEE236CEDAE3911A1C525F1049E519256961F407E96E22AEF391581DE98686524EF500769F777E5FAFEDA352F0FF0F1E001083540101000000010000000000000000000000000000000000000000000000000000000000000000FFFFFFFF0E04AFEDA3520102062F503253482FFFFFFFFF01004023EF3806000023210338BF57D51A50184CF5EF0DC42ECD519FB19E24574C057620262CC1DF94DA2AE5AC00000000";
    private byte[] dogecoinFirstBlockAsByteArray = Helpers.hexStringToByteArray(dogecoinFirstBlockAsHexString);
    private byte[] dogecoinSecondBlockAsByteArray = Helpers.hexStringToByteArray(dogecoinSecondBlockAsHexString);
    private byte[] dogecoinFirstTwoBlocksByteArray = Helpers.hexStringToByteArray(dogecoinFirstBlockAsHexString + dogecoinSecondBlockAsHexString);

    @Test
    public void TestByMerkleRootInByteArrayFirstBlockHasHash() {
        // merkle root hash of first block
        String hashMerkleRootToLookForAsHexString = "696AD20E2DD4365C7459B4A4A5AF743D5E92C6DA3229E6532CD605F6533F2A5B";
        byte[] hashMerkleRootToLookForAsByteArray = Helpers.hexStringToByteArray(hashMerkleRootToLookForAsHexString);

        byte[] out = BlockFinder.byMerkleRootInByteArray(dogecoinFirstTwoBlocksByteArray, hashMerkleRootToLookForAsByteArray);

        assertArrayEquals(dogecoinFirstBlockAsByteArray, out);
    }

    public void TestByMerkleRootInByteArrayNotFirstBlockHasHash() {
        // merkle root hash of second block
        String hashMerkleRootToLookForAsHexString = "3b14b76d22a3f2859d73316002bc1b9bfc7f37e2c3393be9b722b62bbd786983";
        byte[] hashMerkleRootToLookForAsByteArray = Helpers.hexStringToByteArray(hashMerkleRootToLookForAsHexString);

        byte[] out = BlockFinder.byMerkleRootInByteArray(dogecoinFirstTwoBlocksByteArray, hashMerkleRootToLookForAsByteArray);

        assertArrayEquals(dogecoinSecondBlockAsByteArray, out);
    }
}
