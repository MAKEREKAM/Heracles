package kr.vanilage.main

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
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

        embeddedServer(Netty, port = 8080) {
            routing {
                get("/") {
                    call.respondText("Hello, world!")
                }
            }
        }.start(wait = false)
    }

    private fun registerEvents() {
        // Discord
        Bukkit.getPluginManager().registerEvents(ConnectDiscord(), this)
    }
}