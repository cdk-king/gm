package com.cdk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BufferUtil {
    private static final Logger logger = LoggerFactory.getLogger(BufferUtil.class);

    private BufferUtil() {
    }

    // --- end of tiny utf ---

    public static final int writeShort(byte[] array, int index, int value) {
        array[index] = (byte) (value >>> 8);
        array[index + 1] = (byte) (value >>> 0);
        return index + 1;
    }

    public static final int writeMedium(byte[] array, int index, int value) {
        array[index] = (byte) (value >>> 16);
        array[index + 1] = (byte) (value >>> 8);
        array[index + 2] = (byte) (value >>> 0);
        return index + 2;
    }

    /**
     * Compute the number of bytes that would be needed to encode an
     * {@code int64} field, including tag.
     */
    public static int computeVarInt64Size(final long value) {
        if ((value & (0xffffffffffffffffL << 7)) == 0) {
            return 1;
        }
        if ((value & (0xffffffffffffffffL << 14)) == 0) {
            return 2;
        }
        if ((value & (0xffffffffffffffffL << 21)) == 0) {
            return 3;
        }
        if ((value & (0xffffffffffffffffL << 28)) == 0) {
            return 4;
        }
        if ((value & (0xffffffffffffffffL << 35)) == 0) {
            return 5;
        }
        if ((value & (0xffffffffffffffffL << 42)) == 0) {
            return 6;
        }
        if ((value & (0xffffffffffffffffL << 49)) == 0) {
            return 7;
        }
        if ((value & (0xffffffffffffffffL << 56)) == 0) {
            return 8;
        }
        if ((value & (0xffffffffffffffffL << 63)) == 0) {
            return 9;
        }
        return 10;
    }

    /**
     * Compute the number of bytes that would be needed to encode a VarInt.
     * {@code value} is treated as unsigned, so it won't be sign-extended if
     * negative.
     */
    ////计算一个整数在varint编码下所占的字节数，
    public static int computeVarInt32Size(final int value) {
        if ((value & (0xffffffff << 7)) == 0) {
            return 1;
        }
        if ((value & (0xffffffff << 14)) == 0) {
            return 2;
        }
        if ((value & (0xffffffff << 21)) == 0) {
            return 3;
        }
        if ((value & (0xffffffff << 28)) == 0) {
            return 4;
        }
        return 5;
    }

    /**
     * 将数值以VarInt32的形式写入数组
     *
     * @param array
     * @param index
     * @param value
     *            下一个可写入的数组index
     * @return
     */
    public static int writeVarInt32(byte[] array, int index, int value) {
        while (true) {
            if ((value & ~0x7F) == 0) {
                array[index++] = (byte) value;
                return index;
            } else {
                //0x7F 01111111
                //0x80 10000000
                array[index++] = (byte) ((value & 0x7F) | 0x80);
                value >>>= 7;
            }
        }
    }

    public static int writeVarInt64(byte[] array, int index, long value) {
        while (true) {
            if ((value & ~0x7FL) == 0) {
                array[index++] = (byte) value;
                return index;
            } else {
                array[index++] = (byte) ((value & 0x7F) | 0x80);
                value >>>= 7;
            }
        }
    }

    private static final RuntimeException MALFORMED_VarInt = new RuntimeException("Malformed VarInt");

    /**
     * 从ChannelBuffer中读取一个VarInt. 如果大于32bit, 更高位的bit将会被抛弃
     */
    public static final int readVarInt32(byte[] data, int index) {
        byte tmp = data[index++];
        if (tmp >= 0) {
            return tmp;
        }
        //0x7f 128  0111 1111
        //0x7f与常量做与运算实质是保留常量（转换为二进制形式）的后7位数，既取值区间为[0,128]
        //<< 7 相当于后边加7个0（右边补7个0）
        int result = tmp & 0x7f;
        if ((tmp = data[index++]) >= 0) {
            //result = result|tmp << 7;
            result |= tmp << 7;
        } else {
            result |= (tmp & 0x7f) << 7;
            if ((tmp = data[index++]) >= 0) {
                result |= tmp << 14;
            } else {
                result |= (tmp & 0x7f) << 14;
                if ((tmp = data[index++]) >= 0) {
                    result |= tmp << 21;
                } else {
                    result |= (tmp & 0x7f) << 21;
                    result |= (tmp = data[index++]) << 28;
                    if (tmp < 0) {
                        // Discard upper 32 bits.
                        for (int i = 0; i < 5; i++) {
                            if (data[index++] >= 0) {
                                return result;
                            }
                        }
                        throw MALFORMED_VarInt;
                    }
                }
            }
        }
        return result;
    }

    /** Read a raw VarInt from the stream. */
    public static long readVarInt64(byte[] data, int index) {
        int shift = 0;
        long result = 0;

        while (shift < 64) {
            final byte b = data[index++];
            result |= (long) (b & 0x7F) << shift;
            if ((b & 0x80) == 0) {
                return result;
            }
            shift += 7;
        }

        throw MALFORMED_VarInt;
    }

}
