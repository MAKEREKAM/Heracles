package kr.vanilage.main.discord

import club.minnced.discord.webhook.WebhookClientBuilder
import club.minnced.discord.webhook.send.WebhookMessage
import club.minnced.discord.webhook.send.WebhookMessageBuilder
import kr.vanilage.main.Main
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.server.PluginDisableEvent
import org.bukkit.event.server.PluginEnableEvent

class ConnectDiscord : Listener {
    @EventHandler
    fun onChat(e : PlayerChatEvent) {
        val message = WebhookMessageBuilder()
            .setUsername(e.player.name)
            .setAvatarUrl("https://cravatar.eu/helmhead/${e.player.name}/600.png")
            .setContent(e.message)
            .build()

        sendWebHookMessage(message)
    }

    @EventHandler
    fun onJoin(e : PlayerJoinEvent) {
        val message = WebhookMessageBuilder()
            .setUsername("${e.player.name} 님이 접속하셨습니다.")
            .setAvatarUrl("https://cravatar.eu/helmhead/${e.player.name}/600.png")
            .setContent("­")
            .build()

        sendWebHookMessage(message)
    }

    @EventHandler
    fun onQuit(e : PlayerQuitEvent) {
        val message = WebhookMessageBuilder()
            .setUsername("${e.player.name} 님이 퇴장하셨습니다.")
            .setAvatarUrl("https://cravatar.eu/helmhead/${e.player.name}/600.png")
            .setContent("­")
            .build()

        sendWebHookMessage(message)
    }

    @EventHandler
    fun onEnable(e : PluginEnableEvent) {
        val message = WebhookMessageBuilder()
            .setUsername("서버가 시작되었습니다.")
            .setAvatarUrl("https://cdn.discordapp.com/attachments/1135441177013911662/1289155064925130804/image.png")
            .setContent("­")
            .build()

        sendWebHookMessage(message)
    }

    @EventHandler
    fun onDisable(e : PluginDisableEvent) {
        val message = WebhookMessageBuilder()
            .setUsername("서버가 종료되었습니다.")
            .setAvatarUrl("https://cdn.discordapp.com/attachments/1135441177013911662/1289155064925130804/image.png")
            .setContent("­")
            .build()

        sendWebHookMessage(message)
    }

    private fun sendWebHookMessage(message : WebhookMessage) {
        val webhookURL = Main.configuration.getString("WEBHOOK_URL")

        val builder = WebhookClientBuilder(webhookURL!!)

        builder.setThreadFactory { job ->
            val thread = Thread(job)
            thread.name = "Hello"
            thread.isDaemon = true
            thread
        }

        builder.setWait(true)

        val client = builder.build()

        client.send(message)
    }
}