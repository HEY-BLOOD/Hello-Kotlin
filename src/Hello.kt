import java.util.*    // Random requires import

// ====================================
// 了解有关函数的更多信息
// ====================================

fun randomDay(): String {
    val week = arrayOf(
            "Monday", "Tuesday", "Wednesday", "Thursday",
            "Friday", "Saturday", "Sunday"
    )
    return week[Random().nextInt(week.size)]
}

fun fishFood(day: String): String {
    return when (day) {
        "Monday" -> "flakes"
        "Wednesday" -> "redworms"
        "Thursday" -> "granules"
        "Friday" -> "mosquitoes"
        "Sunday" -> "plankton"
        else -> "nothing"
    }
}

fun feedTheFish() {
    val day = randomDay()
    val food = fishFood(day)
    println("Today is $day and the fish eat $food")
    println("Change water: ${shouldChangeWater(day)}")
    println("Change water: ${shouldChangeWater(day, temperature = 35)}")
    println("Change water: ${shouldChangeWater(day, dirty = 31)}")
}

// ====================================
// 探索默认值和紧凑函数
// ====================================

fun swim(speed: String = "fast") {
    println("swimming $speed")
}

fun shouldChangeWater(day: String, temperature: Int = 22, dirty: Int = 20): Boolean {
    return when {
        isTooHot(temperature) -> true
        isDirty(dirty) -> true
        isSunday(day) -> true
        else -> false
    }
}

fun isTooHot(temperature: Int) = temperature > 30

fun isDirty(dirty: Int) = dirty > 30

fun isSunday(day: String) = day == "Sunday"

// ====================================
// 开始使用过滤器
// ====================================

val decorations = listOf("rock", "pagoda", "plastic plant", "alligator", "flowerpot")

fun startFilter() {
    // eager, creates a new list
    val eager = decorations.filter { it[0] == 'p' }
    println("eager: $eager")

    // lazy, will wait until asked to evaluate
    val filtered = decorations.asSequence().filter { it[0] == 'p' }
    println("filtered: $filtered")

    // force evaluation of the lazy list
    val newList = filtered.toList()
    println("new list: $newList")

    val lazyMap = decorations.asSequence().map {
        println("access: $it")
        it
    }
    println("lazy: $lazyMap")
    println("-----")
    println("first: ${lazyMap.first()}")
    println("-----")
    println("all: ${lazyMap.toList()}")
    val lazyMap2 = decorations.asSequence().filter { it[0] == 'p' }.map {
        println("access: $it")
        it
    }
    println("-----")
    println("filtered: ${lazyMap2.toList()}")
}


// ====================================
// 开始使用 lambda 和高阶函数
// ====================================

fun updateDirty(dirty: Int, operation: (Int) -> Int): Int {
    return operation(dirty)
}

fun lambdaAndHighOrderFunctions() {
    val waterFilter: (Int) -> Int = { dirty -> dirty / 2 }
    println(updateDirty(30, waterFilter))

    fun increaseDirty(start: Int) = start + 1
    println(updateDirty(15, ::increaseDirty))

    var dirtyLevel = 19;
    dirtyLevel = updateDirty(dirtyLevel) { dirtyLevel -> dirtyLevel + 23 }
    println(dirtyLevel)
}


/**
 * 程序入口函数
 */
fun main(args: Array<String>) {
    // 了解有关函数的更多信息
    feedTheFish()
    swim()   // uses default speed
    swim("slow")   // positional argument
    swim(speed = "turtle-like")   // named parameter

    // 开始使用过滤器
    startFilter()

    // 开始使用 lambda 和高阶函数
    lambdaAndHighOrderFunctions()
}
