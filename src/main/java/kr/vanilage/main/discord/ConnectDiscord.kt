package kr.vanilage.main.discord

import club.minnced.discord.webhook.WebhookClientBuilder
import club.minnced.discord.webhook.send.WebhookMessageBuilder
import kr.vanilage.main.Main
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChatEvent

class ConnectDiscord : Listener {
    @EventHandler
    fun onChat(e : PlayerChatEvent) {
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

        val message = WebhookMessageBuilder()
            .setUsername(e.player.name)
            .setAvatarUrl("https://minotar.net/helm/${e.player.name}")
            .setContent(e.message)
            .build()

        client.send(message)
    }
}