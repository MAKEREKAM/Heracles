package kr.vanilage.main

import kr.vanilage.main.discord.Bot
import kr.vanilage.main.discord.ConnectDiscord
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    companion object {
        lateinit var instance : Main
        lateinit var configuration : FileConfiguration
    }

    override fun onEnable() {
        Bukkit.getConsoleSender().sendMessage("Hello, World!")

        this.saveDefaultConfig()

        instance = this
        configuration = this.config

        Bot.run()

        registerEvents()
    }

    private fun registerEvents() {
        // Discord
        Bukkit.getPluginManager().registerEvents(ConnectDiscord(), this)
    }
}