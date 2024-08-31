package kr.vanilage.main.discord

import kr.vanilage.main.Main
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.OptionMapping
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.Commands

object Bot {
    private lateinit var token : String
    lateinit var channelId : String
    lateinit var adminChannelId : String
    lateinit var jda : JDA

    fun run() {
        try {
            token = Main.configuration.getString("BOT_TOKEN")!!
            channelId = Main.configuration.getString("CHANNEL_ID")!!
            adminChannelId = Main.configuration.getString("ADMIN_CHANNEL_ID")!!

            jda = JDABuilder.createDefault(token)
                .addEventListeners(
                    MessageListener(),
                    SlashCommandListener()
                )
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

class SlashCommandListener : ListenerAdapter() {
    override fun onSlashCommandInteraction(event : SlashCommandInteractionEvent) {
        when (event.name) {
            "say" -> {
                val content = event.getOption("content", OptionMapping::getAsString)
                event.channel.sendMessage(content!!).queue()
            }
        }
    }
}

class MessageListener : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (!event.author.isBot && event.channel.id == Bot.channelId) event.channel.sendMessage("asdf").queue()
    }
}