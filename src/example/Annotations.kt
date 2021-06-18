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


fun main() {
    testAnnotations()
}