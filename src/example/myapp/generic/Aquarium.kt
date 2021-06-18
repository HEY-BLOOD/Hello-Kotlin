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
class Aquarium3<out T : WaterSupply>(val waterSupply: T) {
    fun addWater() {
        check(!waterSupply.needsProcessing) { "water supply needs processing first" }
        println("adding water from $waterSupply")
    }
}


// 定义输出类型
fun addItemTo(aquarium: AquariumOut<WaterSupply>) = println("item added")

/**
 * waterSupply can only be WaterSupply
 */
class AquariumOut<out T : WaterSupply>(val waterSupply: T) {
    fun addWater() {
        check(!waterSupply.needsProcessing) { "water supply needs processing first" }
        println("adding water from $waterSupply")
    }
}

// 定义输入类型
interface Cleaner<in T : WaterSupply> {
    fun clean(waterSupply: T)
}

class TapWaterCleaner : Cleaner<TapWater> {
    override fun clean(waterSupply: TapWater) = waterSupply.addChemicalCleaners()
}

class AquariumIn<out T : WaterSupply>(val waterSupply: T) {
    fun addWater(cleaner: Cleaner<T>) {
        if (waterSupply.needsProcessing) {
            cleaner.clean(waterSupply)
        }
        println("water added")
    }
}


// 创建泛型函数
class AquariumReified<T>(val waterSupply: T) {
    inline fun <reified R : WaterSupply> hasWaterSupplyOfType() = waterSupply is R
}

fun <T : WaterSupply> isWaterClean(aquarium: Aquarium<T>) {
    println("aquarium water is clean: ${!aquarium.waterSupply.needsProcessing}")
}


// 创建泛型扩展函数
inline fun <reified T : WaterSupply> WaterSupply.isOfType() = this is T

inline fun <reified R : WaterSupply> Aquarium<*>.hasWaterSupplyOfType() = waterSupply is R

// 泛型示例
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

    val aquariumOut = AquariumOut(TapWater())
    addItemTo(aquariumOut)

    val cleaner = TapWaterCleaner()
    val aquariumIn = AquariumIn(TapWater())
    aquariumIn.addWater(cleaner)

    val aquarium5 = Aquarium(TapWater())
    isWaterClean(aquarium5)

    val aquariumReified = AquariumReified(TapWater())
    println(aquariumReified.hasWaterSupplyOfType<TapWater>())   // true
    println(aquariumReified.hasWaterSupplyOfType<LakeWater>())   // false

    val aquarium6 = Aquarium(TapWater())
    println(aquarium6.waterSupply.isOfType<TapWater>())   // true
    println(aquarium6.waterSupply.isOfType<LakeWater>())   // false

    val aquarium7 = Aquarium(TapWater())
    println(aquarium7.hasWaterSupplyOfType<TapWater>())   // true
    println(aquarium7.hasWaterSupplyOfType<LakeWater>())   // false
}

fun main() {
    genericsExample()
}