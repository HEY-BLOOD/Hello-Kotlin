package example.myapp.decor

// 扩展函数
fun String.hasSpaces(): Boolean {
    val found = this.find { it == ' ' }
    return found != null
}

fun String.hasSpaces2() = find { it == ' ' } != null


// 扩展的局限性，只能访问类中的 public 成员
open class AquariumPlant(val color: String, private val size: Int) {

}

fun AquariumPlant.isRed() = color == "red"    // OK
// error: cannot access 'size': it is private in 'AquariumPlant'
//fun AquariumPlant.isBig() = size > 50         // gives error

class GreenLeafyPlant(size: Int) : AquariumPlant("green", size)

fun AquariumPlant.print() = println("AquariumPlant")
fun GreenLeafyPlant.print() = println("GreenLeafyPlant")


// 添加扩展属性
val AquariumPlant.isGreen: Boolean
    get() = color == "green"


// 了解可空接收器
fun AquariumPlant?.pull() {
    this?.apply {
        println("removing $this")
    }
}

fun main() {
    println("Does it have spaces?".hasSpaces())
    println("Does it have spaces?".hasSpaces2())

    val plant = GreenLeafyPlant(size = 10)
    plant.print()
    println("\n")
    val aquariumPlant: AquariumPlant = plant
    aquariumPlant.print()  // what will it print?

    println(aquariumPlant.isGreen)

    val aquariumPlant2: AquariumPlant? = null
    aquariumPlant2.pull()
}