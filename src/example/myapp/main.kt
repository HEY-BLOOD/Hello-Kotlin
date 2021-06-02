package example.myapp

fun buildAquarium() {
    val aquarium1 = Aquarium()
    aquarium1.printSize()
    // default height and length
    val aquarium2 = Aquarium(width = 25)
    aquarium2.printSize()
    // default width
    val aquarium3 = Aquarium(height = 35, length = 110)
    aquarium3.printSize()
    // everything custom
    val aquarium4 = Aquarium(width = 25, height = 35, length = 110)
    aquarium4.printSize()

    // use secondary constructors
    println("========== aquarium6 ==========")
    val aquarium6 = Aquarium(numberOfFish = 29)
    aquarium6.printSize()
    println("Volume: ${aquarium6.width * aquarium6.length * aquarium6.height / 1000} l")

    println("========== aquarium7 ==========")
    val aquarium7 = Aquarium(numberOfFish = 29)
    aquarium7.printSize()
    println("Volume: ${aquarium7.width * aquarium7.length * aquarium7.height / 1000} l")
    aquarium7.volume = 70
    aquarium7.printSize()
    println("Volume: ${aquarium7.width * aquarium7.length * aquarium7.height / 1000} l")
}

fun main() {
    buildAquarium()
}