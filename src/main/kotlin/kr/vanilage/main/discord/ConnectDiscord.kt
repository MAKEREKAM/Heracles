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
import org.bukkit.event.server.PluginDisableEvent
import org.bukkit.event.server.PluginEnableEvent

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
    fun onEnable(e : PluginEnableEvent) {
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
            .setUsername("서버가 시작되었습니다.")
            .setAvatarUrl("https://media.discordapp.net/attachments/1280076410081251419/1282197255335444542/image.png")
            .setContent("­")
            .build()

        client.send(message)
    }

    @EventHandler
    fun onDisable(e : PluginDisableEvent) {
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
            .setUsername("서버가 종료되었습니다.")
            .setAvatarUrl("https://media.discordapp.net/attachments/1280076410081251419/1282197255335444542/image.png")
            .setContent("­")
            .build()

        client.send(message)
    }
}