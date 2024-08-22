package kr.vanilage.main

class FocusTree {
    val focusList : ArrayList<String> = ArrayList()
    val focusInfo : HashMap<String, String> = HashMap()
    val exclusive : ArrayList<Array<String>> = ArrayList()
    val antecedentAnd : HashMap<String, Array<String>> = HashMap()
    val antecedentOr : HashMap<String, Array<String>> = HashMap()

    fun addFocus(name : String, info : String) : FocusTree {
        focusList.add(name)
        focusInfo[name] = info

        return this
    }

    fun antecedent(name : String, antecedent : Array<String>, and : Boolean) {
        if (and) {
            antecedentAnd[name] = antecedent
        }

        else {
            antecedentOr[name] = antecedent
        }
    }

    fun exclusive(exclusive : Array<String>) {
        this.exclusive.add(exclusive)
    }

    fun isFocusAvailable(nation : Nation, focus : String) : Boolean {
        if (nation.focusCompleted.contains(focus)) return false // if already completed it

        if (antecedentAnd[focus] != null) {
            val array = antecedentAnd[focus]!!

            for (i in array) {
                if (!nation.focusCompleted.contains(i)) return false
            }

            return true
        }

        if (antecedentOr[focus] != null) {
            val array = antecedentOr[focus]!!

            for (i in array) {
                if (nation.focusCompleted.contains(i)) return true
            }

            return false
        }

        TODO("EXCLUSIVE")

        return true
    }
}