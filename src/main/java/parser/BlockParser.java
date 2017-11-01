package parser;

import parser.objects.Block;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BlockParser implements Parser {
    private String filePath;
    private ByteBuffer blockBuffer;
    private int blockSize;
    private Block block;

    byte[] readBlockFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }

    public BlockParser(String filePath) {
        this.filePath = filePath;

        try {
            this.blockBuffer = ByteBuffer.wrap(readBlockFile(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkMagicNumber() {
        for (int i = 0; i < Block.magicNumberSize; i++)
            if (blockBuffer.get(i) != Block.magicNumber[i])
                return false;
        return true;
    }

    public void parse() {
        if (!checkMagicNumber())
            // TODO: figure out error handling
            return;

        // TODO: parse the whole block
    }

    /**
     * Returns parsed block, or null if parse() hasn't been called yet.
     * @return parsed block
     */
    public Block getBlock() {
        return block;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
