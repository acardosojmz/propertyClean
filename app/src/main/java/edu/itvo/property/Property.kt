package edu.itvo.property

data class Property(val zone: Zone,
                    val areaInSquareMeter:Double
) {
    fun tax(): Double{
        return zone.costForSquareMeter * areaInSquareMeter
    }
}
