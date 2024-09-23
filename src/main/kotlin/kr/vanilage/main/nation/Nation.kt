package kr.vanilage.main.nation

import java.util.UUID

interface Nation {
    val name : String
    val title : String
    val nationCode : String
    val description : String

    val ideology : IdeologyType

    val playerId : ArrayList<UUID>

    val focusTree : FocusTree

    val focusCompleted : ArrayList<String>
}