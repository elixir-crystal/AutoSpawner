package io.freeze_dolphin.auto_spawner

import io.freeze_dolphin.auto_spawner.commands.CommandBus
import io.freeze_dolphin.auto_spawner.listeners.ListenerBus
import io.izzel.taboolib.loader.Plugin
import io.izzel.taboolib.module.dependency.Dependency
import org.bukkit.plugin.{Plugin => BukkitPlugin}
import redempt.redlib.commandmanager.CommandParser

object PlugGividado {

    val varieties: Array[String] = Array[String]("ZOMBIE", "SKELETON", "CAVE_SPIDER", "SILVERFISH", "BLAZE", "SPIDER", "MAGMA_CUBE")
    var plug: BukkitPlugin = _

    def getPrefix = "[AutoSpawner] "

}

@Dependency(maven = "org.scala-lang:scala-library:2.12.8")
@Dependency(maven = "org.scala-lang:scala-reflect:2.12.8")
class PlugGividado extends Plugin {

    override def onEnable(): Unit = {

        PlugGividado.plug = this.getPlugin

        new CommandParser(getPlugin.getResource("command.rdcml")).parse.register(getPlugin.getDescription.getName, new CommandBus)
        new ListenerBus(this.getPlugin)

    }

}
