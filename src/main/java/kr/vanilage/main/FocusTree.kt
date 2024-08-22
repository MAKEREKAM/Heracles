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
}