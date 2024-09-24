package kr.vanilage.main.discord

import kr.vanilage.main.Main
import kr.vanilage.main.discord.Authorization.Companion.authNumber
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.channel.ChannelType
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.requests.GatewayIntent
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import java.text.SimpleDateFormat
import java.util.*

object Bot {
    private lateinit var token : String
    lateinit var channelId : String
    lateinit var adminChannelId : String
    lateinit var jda : JDA

    lateinit var messageFormat : String

    fun run() {
        try {
            token = Main.configuration.getString("BOT_TOKEN")!!
            channelId = Main.configuration.getString("CHANNEL_ID")!!
            adminChannelId = Main.configuration.getString("ADMIN_CHANNEL_ID")!!

            messageFormat = Main.configuration.getString("MINECRAFT_MESSAGE_FORMAT")!!

            jda = JDABuilder.createDefault(token)
                .addEventListeners(
                    EnableListener(),
                    MessageListener(),
                    DirectMessageListener()
                )
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.DIRECT_MESSAGES)
                .build()

            val commands = jda.updateCommands()

            commands.addCommands(
                Commands.slash("say", "TEST")
                    .addOption(OptionType.STRING, "content", "What the bot should say", true),
            )

            commands.queue()
        }
        catch (e : Exception) {
            e.printStackTrace()
        }
    }
}

class EnableListener : ListenerAdapter() {
    override fun onReady(event : ReadyEvent) {
        event.jda.getTextChannelById(Bot.adminChannelId)!!.sendMessage("## SERVER OPEN.").queue()
    }
}

class MessageListener : ListenerAdapter() {
    override fun onMessageReceived(event : MessageReceivedEvent) {
        if (event.author.isBot) return
        if (event.message.contentDisplay == "") return

        if (event.channel.id == Bot.channelId) {
            val message = Bot.messageFormat
                .replace("\$author", event.author.effectiveName)
                .replace("\$username", event.author.name)
                .replace("\$id", event.author.id)
                .replace("\$time", getTime())
                .replace("\$content", event.message.contentDisplay)

            val component = MiniMessage.miniMessage().deserialize(message)

            Bukkit.broadcast(component)
        }
    }

    private fun getTime() : String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        return dateFormat.format(Date())
    }
}

class DirectMessageListener : ListenerAdapter() {
    override fun onMessageReceived(event : MessageReceivedEvent) {
        if (event.isFromType(ChannelType.PRIVATE)) {
            val content = event.message.contentDisplay

            if (authNumber.containsKey(content)) {
                for (i in Main.configuration.getConfigurationSection("AUTH")!!.getKeys(false)) {
                    if (Main.configuration.getString("AUTH.${i}") == event.author.id) {
                        event.channel.sendMessage(
                            "이미 해당 디스코드 계정과 연결된 마인크래프트 계정이 존재합니다."
                        ).queue()

                        return
                    }
                }

                if (Main.configuration.getString("AUTH.${authNumber[content]!!.uniqueId}") != null) {
                    event.channel.sendMessage(
                        "이미 해당 마인크래프트 계정이 연결되어 있습니다."
                    ).queue()

                    return
                }

                Main.configuration.set("AUTH.${authNumber[content]!!.uniqueId}", event.author.id)
                Main.instance.saveConfig()

                event.channel.sendMessage(
                    "마인크래프트 계정 ${authNumber[content]!!.name}(${authNumber[content]!!.uniqueId})와 연동이 완료되었습니다."
                ).queue()

                authNumber.remove(content)
            }
        }
    }
}