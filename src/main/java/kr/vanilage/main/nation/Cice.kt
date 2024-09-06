package kr.vanilage.main.nation

class Cice(owner : Nation, x : Int, y : Int) {
    // Default Information
    val owner : Nation by lazy { owner }
    val x : Int by lazy { x }
    val y : Int by lazy { y }

    // Building Information
    var base = 0
    var privateFactory = 0
    var munitionFactory = 0
    var residence = 0
    var buildingSlot = 1
}