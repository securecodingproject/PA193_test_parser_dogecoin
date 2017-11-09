package parser;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Expecting first parameter to be merkle root hash to find block by, second argument to be file to look for it in");
        }

        String merkleRootHash = args[0];
        if (merkleRootHash.length() != 64) {
            System.out.println("Wrong merkle root hash length");
            return;
        }
        byte[] merkleRootHashToFind = Helpers.reverseByteArray(Helpers.hexStringToByteArray(merkleRootHash));

        byte[] blockBytes = null;
        try {
            blockBytes = BlockFinder.byMerkleRootInFile(args[1], merkleRootHashToFind);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (blockBytes == null) {
            System.out.println("Block corresponding to given merkle root hash could not be found. Check file path and the hash. Exiting");
            return;
        }

        Block parsedBlock = Block.parseBlock(blockBytes);

        System.out.println(parsedBlock);
    }
}
