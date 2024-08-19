package kr.vanilage.main

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        Bukkit.getConsoleSender().sendMessage("Hello, World!")
    }
}