package com.github.poscat.rss.viewer.Utility

import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.zomato.photofilters.imageprocessors.Filter
import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubFilter
import java.nio.charset.Charset
import java.security.MessageDigest

class ImageFilter : BitmapTransformation() {
    private val ID = "com.github.poscat.rss.viewer.Utility.ImageFilter"
    private val ID_BYTES = ID.toByteArray(Charset.forName("UTF-8"))

    public override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        System.loadLibrary("NativeImageProcessor")

        val myFilter = Filter()
        /**
         * If you want your photo filter added to it. I just lowered the brightness.
         * Please refer to the library below.
         *
         * https://github.com/Zomato/AndroidPhotoFilters
         */
        myFilter.addSubFilter(BrightnessSubFilter(-25))

        return myFilter.processFilter(toTransform)
    }

    override fun equals(o: Any?): Boolean {
        return o is ImageFilter
    }

    override fun hashCode(): Int {
        return ID.hashCode()
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_BYTES)
    }
}