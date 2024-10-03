package kr.vanilage.main.discord

import com.destroystokyo.paper.profile.PlayerProfile
import kr.vanilage.main.Main
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.event.player.PlayerJoinEvent
import java.util.UUID

class Authorization : Listener {
    companion object {
        val authNumber = HashMap<String, PlayerProfile>()
    }

    @EventHandler
    fun onJoin(e : AsyncPlayerPreLoginEvent) {
        if (Main.configuration.getString("AUTH.${e.playerProfile.uniqueId}") == null) {
            val number = UUID.randomUUID().toString().substring(0..7)

            authNumber[number] = e.playerProfile

            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST,
                MiniMessage.miniMessage().deserialize(
                    "<aqua>안녕하십니까, <yellow><bold>${e.playerProfile.name} 님,<reset><br>" +
                            "<aqua>이 서버에 접속하기 위해서는 인증이 필요하오니 <red><bold>${Bot.jda.selfUser.name}<reset><aqua> 봇에게<br>" +
                            "인증 코드 <yellow><bold>${number}<reset><aqua>를 보내어 인증을 진행해 주시길 부탁드립니다."
                )
            )
        }
    }
}