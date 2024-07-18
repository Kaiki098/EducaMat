package br.com.kbat.educamat.presentation.utils

import android.graphics.Color
import kotlin.random.Random

object ColorUtil {
    fun getRandomColor(): Int {
        val random = Random
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }

    fun getContrastColor(backgroundColor: Int): Int {
        val luminance = (0.299 * Color.red(backgroundColor) +
                0.587 * Color.green(backgroundColor) +
                0.114 * Color.blue(backgroundColor)) / 255
        return if (luminance > 0.5) Color.BLACK else Color.WHITE
    }
}
