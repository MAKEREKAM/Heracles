package kr.vanilage.main.discord

import kr.vanilage.main.Main
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.requests.GatewayIntent
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import java.text.SimpleDateFormat
import java.util.Date

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
                    MessageListener()
                )
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
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