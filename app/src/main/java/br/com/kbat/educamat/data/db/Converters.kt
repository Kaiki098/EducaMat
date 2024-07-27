package br.com.kbat.educamat.data.db

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString(separator = ",")
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return value.split(",")
    }
}