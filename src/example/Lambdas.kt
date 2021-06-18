package example

// 简单的 lambda
val waterFilter = { dirty: Int -> dirty / 2 }

fun simpleLambdas() {
    println(waterFilter(30))
}

// 过滤器 lambda

data class Fish(val name: String)

val myFish = listOf(Fish("Flipper"), Fish("Moby Dick"), Fish("Dory"))

fun filterLambda() {
    val filteredLambda = myFish.filter { it.name.contains("i") }
    println(filteredLambda)

    val joinedLambda = filteredLambda.joinToString(", ") { it.name }
    println(joinedLambda)
}


fun main() {
    simpleLambdas()
    filterLambda()
}