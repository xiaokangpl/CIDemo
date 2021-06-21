package com.example.cidemo

/**
 * @author wenxiaokang
 * @className NumUtils
 * @description TODO
 * @date 6/9/21 6:18 PM
 */
class NumUtils {
    fun getMinInt(integers: List<Int>): Int {
        var temp = integers[0]
        for (integer in integers) {
            if (integer < temp) {
                temp = integer
            }
        }
        return temp
    }
}