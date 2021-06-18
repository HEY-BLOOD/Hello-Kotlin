package example.myapp.generic

// 创建类型层次结构
open class WaterSupply(var needsProcessing: Boolean)

class TapWater : WaterSupply(true) {
    fun addChemicalCleaners() {
        needsProcessing = false
    }
}

class FishStoreWater : WaterSupply(false)

class LakeWater : WaterSupply(true) {
    fun filter() {
        needsProcessing = false
    }
}


// 创建泛型类

/**
 * waterSupply can be Any, including null
 */
class Aquarium<T>(val waterSupply: T)

/**
 * waterSupply can be Any, but null
 */
class Aquarium2<T : Any>(val waterSupply: T)

/**
 * waterSupply can only be WaterSupply
 */
class Aquarium3<T : WaterSupply>(val waterSupply: T) {
    fun addWater() {
        check(!waterSupply.needsProcessing) { "water supply needs processing first" }
        println("adding water from $waterSupply")
    }
}

fun genericsExample() {
    val aquarium = Aquarium<TapWater>(TapWater())
    println("water needs processing: ${aquarium.waterSupply.needsProcessing}")
    aquarium.waterSupply.addChemicalCleaners()
    println("water needs processing: ${aquarium.waterSupply.needsProcessing}")

    val aquarium2 = Aquarium("string")
    println(aquarium2.waterSupply)

    val aquarium3 = Aquarium(null)
    if (aquarium3.waterSupply == null) {
        println("waterSupply is null")
    }

    val aquarium4 = Aquarium3(LakeWater())
    aquarium4.waterSupply.filter()
    aquarium4.addWater()
}

fun main() {
    genericsExample()
}