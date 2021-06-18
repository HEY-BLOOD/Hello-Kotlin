package example.functions

data class Fish(var name: String)

fun fishExamples() {
    val fish = Fish("splashy")  // all lowercase
    with(fish.name) {
        println(capitalize())
    }
}

fun myWith(name: String, block: String.() -> Unit) {}

fun fishExamples2() {
    val fish = Fish("splashy")  // all lowercase
    myWith(fish.name) {
        println(capitalize())
    }
}

fun moreBuiltInExtensions() {
    val fish = Fish("splashy")  // all lowercase
    val ranFish = fish.run {
        name
    }
    println(ranFish)

    val appliedFish = fish.apply {
        name = "sharky"
    }
    println(appliedFish.name)

    val letFish = fish.let { it.name.capitalize() }
            .let { it + "fish" }
            .let { it.length }
            .let { it + 31 }
    println(letFish)
    println(fish)
}


fun main() {
    fishExamples()
    fishExamples2()
    moreBuiltInExtensions()
}