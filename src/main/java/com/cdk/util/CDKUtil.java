package com.cdk.util;

public class CDKUtil {


    public static long int2Long(int high, int low) {
        //位运算
        return (((long) high) << 32) | (low & 0xFFFFFFFFL);
    }

    public static int getLowInt(long l) {
        return (int) l;
    }

    public static int getHighInt(long l) {
        return (int) (l >>> 32);
    }
}
