package kr.vanilage.main.utility

import kr.vanilage.main.nation.Cice
import kr.vanilage.main.nation.CiceGroup
import org.bukkit.Chunk
import org.bukkit.Location

object CiceUtility {
    fun Chunk.getCice(ciceGroup : CiceGroup) : Cice? {
        if (ciceGroup.table.containsKey(this.x)) {
            if (ciceGroup.table[this.x]!!.containsKey(this.z)) {
                return ciceGroup.table[x]!![z]
            }
        }

        return null
    }

    fun Location.getCice(ciceGroup : CiceGroup) : Cice? {
        return this.chunk.getCice(ciceGroup)
    }
}