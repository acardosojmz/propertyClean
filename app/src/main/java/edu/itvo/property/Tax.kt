package edu.itvo.property

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.Month


class Tax private  constructor(
                               private val dateOfPayment: LocalDate,
                               private val properties:List<Property>,
                               private val owner:Person){

    private val SEVENTY_YEARS_OLD = 70
    data class Builder(val folio:Int,
                       val dateOfPayment: LocalDate, val owner: Person ) {

        private val properties= ArrayList<Property>()
        fun addProperty(property: Property): Builder {
            properties.add(property)
            return this
        }
        fun addAllProperties(elements: ArrayList<Property>): Builder {
            properties.addAll(elements)
            return this
        }

        fun build()= Tax (dateOfPayment, properties, owner)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTotal(): Double {
        val total= properties.sumOf {it.tax()} * (1-getDiscount())
        return (total)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDiscount():Double {
        var discount=Discount.WITHOUT
        if (owner.getAge()>=SEVENTY_YEARS_OLD || owner.isSingleMother) {
            discount = when(dateOfPayment.month) {
                Month.JANUARY, Month.FEBRUARY -> Discount.SEVENTY_PERCENT
                else -> Discount.FIFTY_PERCENT
            }
        }
        else if (dateOfPayment.month<=Month.FEBRUARY ) {
            discount= Discount.FORTY_PERCENT
        }
        return discount.value
    }
}
