package parser;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BlockFinder {

    /**
     * byMerkleRootInByteArray looks for a hash in a byte array and returns block corresponding to hash
     *
     * @param src byte array to search for hash in
     * @param merkleRootHash hash to search for
     * @return byte array containig block in case hash was found, null otherwise
     */
    public static byte[] byMerkleRootInByteArray(byte[] src, byte[] merkleRootHash) {
        ByteBuffer bytesBuffer = ByteBuffer.wrap(src);
        bytesBuffer.order(ByteOrder.LITTLE_ENDIAN);

        while (bytesBuffer.position() != bytesBuffer.limit()) {
            int initialPosition = bytesBuffer.position();
            // get block size
            bytesBuffer.position(initialPosition + 4);
            int blockSize = bytesBuffer.getInt();

            // jump to first hash
            bytesBuffer.position(initialPosition + 32);

            // get hash, compare
            byte[] hash = new byte[BlockHeader.hashLength];
            bytesBuffer.get(hash);
            if (Helpers.isSame(hash, merkleRootHash)) {
                bytesBuffer.position(initialPosition);
                byte[] block = new byte[8 + blockSize];
                bytesBuffer.get(block);
                return block;
            }

            // jump to beginning of next block
            bytesBuffer.position(initialPosition + 8 + blockSize);
        }

        return null;
    }

    /**
     * Looks for a merkle root hash in a file, returns block if found, returns null if not found
     *
     * @param filepath file to look for hash in
     * @param merkleRootHash hash to look for in file
     * @return null if hash wasn' found, the block as an array of bytes if it was
     * @throws IOException
     */
    public static byte[] byMerkleRootInFile(String filepath, byte[] merkleRootHash) throws IOException {
        if (merkleRootHash == null || merkleRootHash.length != BlockHeader.hashLength)
            // TODO: throw exception
            return null;

        Path path = Paths.get(filepath);
        byte[] bytes = Files.readAllBytes(path);

        return byMerkleRootInByteArray(bytes, merkleRootHash);
    }
}
