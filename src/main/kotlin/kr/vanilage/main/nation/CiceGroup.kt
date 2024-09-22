package kr.vanilage.main.nation

class CiceGroup {
    val table = HashMap<Int, HashMap<Int, Cice>>()

    fun addCice(x : Int, y : Int, cice : Cice) {
        if (!table.containsKey(x)) {
            table[x] = HashMap()
        }

        table[x]!![y] = cice
    }
}