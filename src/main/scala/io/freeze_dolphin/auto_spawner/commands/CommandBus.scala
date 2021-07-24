package io.freeze_dolphin.auto_spawner.commands

import io.freeze_dolphin.auto_spawner.PlugGividado
import io.freeze_dolphin.auto_spawner.utils.SpawnerUtl
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import redempt.redlib.commandmanager.CommandHook

class CommandBus {

    @CommandHook("give")
    def give(sender: CommandSender, plr: String, variety: String): Unit = {
        var matched = false
        for (i <- PlugGividado.varieties) {
            if (variety.equals(i)) matched = true
        }

        if (matched) {
            val p = Bukkit.getPlayerExact(plr)
            if (p != null) {
                p.getInventory.addItem(SpawnerUtl.getDefaultSpawnerItem(p.getUniqueId, variety))
            } else {
                sender.sendMessage(PlugGividado.getPrefix + "Couldn't find this player.")
            }
        } else {
            sender.sendMessage(PlugGividado.getPrefix + "Unknown variety of spawner.")
            sender.sendMessage(PlugGividado.getPrefix + "Available varieties: ")
            for (i <- PlugGividado.varieties) {
                sender.sendMessage(PlugGividado.getPrefix + i)
            }
        }
    }

}
