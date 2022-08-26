package edu.itvo.property

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

data class Person(val fullName:String,
          val dateOfBirth:LocalDate,
          val genre:String,
          val isSingleMother: Boolean
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getAge():Int {
        return LocalDate.now().year - dateOfBirth.year
    }
}

