package com.mp5a5.opencv

/**
 * @author ：王文彬 on 2020/4/11 18：28
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
object NativeLibUtils{

    init {
        System.loadLibrary("native-lib")
    }

    external fun bitmap2Gray(pixels: IntArray, w: Int, h: Int): IntArray
}