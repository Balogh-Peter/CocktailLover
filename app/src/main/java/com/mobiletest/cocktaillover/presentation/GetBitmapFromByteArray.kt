package com.mobiletest.cocktaillover.presentation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBitmapFromByteArray @Inject constructor() {

    operator fun invoke(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

}