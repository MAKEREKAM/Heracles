package kr.vanilage.main.discord

import club.minnced.discord.webhook.WebhookClientBuilder
import club.minnced.discord.webhook.send.WebhookMessageBuilder
import kr.vanilage.main.Main
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

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
            .setAvatarUrl("https://cravatar.eu/helmhead/${e.player.name}/600.png")
            .setContent(e.message)
            .build()

        client.send(message)
    }

    @EventHandler
    fun onJoin(e : PlayerJoinEvent) {
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
            .setUsername("${e.player.name} 님이 접속하셨습니다.")
            .setAvatarUrl("https://cravatar.eu/helmhead/${e.player.name}/600.png")
            .setContent("­")
            .build()

        client.send(message)
    }

    @EventHandler
    fun onQuit(e : PlayerQuitEvent) {
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
            .setUsername("${e.player.name} 님이 퇴장하셨습니다.")
            .setAvatarUrl("https://cravatar.eu/helmhead/${e.player.name}/600.png")
            .setContent("­")
            .build()

        client.send(message)
    }

    @EventHandler
    fun onClearAdvancements(e : PlayerAdvancementDoneEvent) {
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
            .setUsername("${e.player.name} 님이 발전 과제를 달성했습니다.")
            .setAvatarUrl("https://cravatar.eu/helmhead/${e.player.name}/600.png")
            .setContent("**${e.advancement.key.key}**")
            .build()

        client.send(message)
    }
}