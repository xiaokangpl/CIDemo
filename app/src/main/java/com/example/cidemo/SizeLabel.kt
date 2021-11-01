package com.example.cidemo

import android.content.res.Resources
import android.text.Editable
import android.text.Html.TagHandler
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import org.xml.sax.XMLReader

/**
 * @author wenxiaokang
 * @className SizeLabel
 * @description TODO
 * @date 8/3/21 11:22 AM
 */
class SizeLabel(private val size: Int) : TagHandler {
    private var startIndex = 0
    private var stopIndex = 0
    override fun handleTag(opening: Boolean, tag: String, output: Editable, xmlReader: XMLReader) {
        if (tag.toLowerCase() == "size") {
            if (opening) {
                startIndex = output.length
            } else {
                stopIndex = output.length
                output.setSpan(AbsoluteSizeSpan(dip2px(size.toFloat())), startIndex, stopIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
    }

    companion object {
        fun dip2px(dpValue: Float): Int {
            return (dpValue * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
        }
    }
}