package edu.itvo.property

data class Zone( val key:String,
            val zone:String,
            val costForSquareMeter:Double
) {
    override fun toString(): String {
        return zone
    }
}
