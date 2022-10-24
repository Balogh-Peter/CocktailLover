package com.mobiletest.cocktaillover.presentation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import javax.inject.Inject

class BitmapFactoryHelper @Inject constructor() {

    fun getByteArray(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

}