package edu.itvo.property

import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDate

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun ageIsThan18(){
        val person = Person(fullName = "Rosa Maria",
            dateOfBirth = LocalDate.parse("2010-08-20"),
            genre = "M", isSingleMother =false)
        assertEquals(true,person.getAge()>18)
    }

}