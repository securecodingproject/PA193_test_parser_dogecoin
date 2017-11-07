package parser;

public class VarInt {
    public final long value;
    public final int size;

    //  Just follow table https://en.bitcoin.it/wiki/Protocol_documentation#Variable_length_integer
    public static int sizeOf(long v){
        if( v < 0 ) return 9; // Negative value means a lenght of 9. 1 marker + 8 data
        if( v <= 0xFDL ) return 1; // 1 data
        if( v <= 0xFFFFL ) return 3; // 1 marker + 2 data
        if( v <=  0xFFFFFFFFL ) return 5; // 1 marker + 4 data
        return 9;
    }

    public VarInt(long v){
        value = v;
        size = sizeOf(v);
    }
    // Creates a new VarInt with value parsed from the specified offset of the given buffer.
    // 'buf' is the buffer containing the value
    // 'offset' is the offset of the value
    public VarInt(byte[] buf, int offset){
        int first = 0xFF & buf[offset];
        if( first < 253 ){
            value = first;
            size = 1; // 1 data
        } else if( first == 253 ){
            value = (0xFFL & buf[offset + 1]) |
                    ((0xFFL & buf[offset + 2]) << 8);
            size = 3; // 1 marker + 2 data
        } else if( first == 254 ){
            value = (0xFFL & buf[offset + 1]) |
                    ((0xFFL & buf[offset + 2]) << 8) |
                    ((0xFFL & buf[offset + 3]) << 16) |
                    ((0xFFL & buf[offset + 4]) << 24);
            size = 5; // 1 marker + 4 data
        } else{
            value = (0xFFL & buf[offset + 1]) |
                    ((0xFFL & buf[offset + 2]) << 8) |
                    ((0xFFL & buf[offset + 3]) << 16) |
                    ((0xFFL & buf[offset + 4]) << 24) |
                    ((0xFFL & buf[offset + 5]) << 32) |
                    ((0xFFL & buf[offset + 6]) << 40) |
                    ((0xFFL & buf[offset + 7]) << 48) |
                    ((0xFFL & buf[offset + 8]) << 56);
            size = 9; // 1 marker + 8 data
        }
    }
}
