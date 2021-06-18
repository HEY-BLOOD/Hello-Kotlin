package example

import kotlin.reflect.full.*    // required import

annotation class ImAPlant

@Target(AnnotationTarget.PROPERTY_GETTER)
annotation class OnGet

@Target(AnnotationTarget.PROPERTY_SETTER)
annotation class OnSet

@ImAPlant
class Plant {
    @get:OnGet
    val isGrowing: Boolean = true

    @set:OnSet
    var needsFood: Boolean = false
}

fun testAnnotations() {
    val classObj = Plant::class
    for (m in classObj.declaredMemberFunctions) {
        println(m.name)
    }

    val plantObject = Plant::class
    for (a in plantObject.annotations) {
        println(a.annotationClass.simpleName)
    }

    val myAnnotationObject = plantObject.findAnnotation<ImAPlant>()
    println(myAnnotationObject)

    val plant = Plant()
    plant.needsFood = !plant.needsFood
    println(plant.needsFood)
    // Error: Val cannot be reassigned
    //plant.isGrowing = !plant.isGrowing
    println(plant.isGrowing)

}

fun breaks() {
    outerLoop@ for (i in 1..100) {
        for (j in 1..100) {
            if (i > 10) break@outerLoop  // breaks to outer loop
        }
        print("$i ")
    }
    println()
}

fun continues() {
    outerLoop@ for (i in 1..100) {
        if (i % 2 == 0) continue  // continues to next loop
        if (i > 10) break  // breaks out form nearest loop
        print("$i ")
    }
    println()
}


fun main() {
    testAnnotations()

    breaks()
    continues()
}