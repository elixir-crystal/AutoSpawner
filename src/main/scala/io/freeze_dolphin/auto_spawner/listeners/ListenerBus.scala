package io.freeze_dolphin.auto_spawner.listeners

import io.freeze_dolphin.auto_spawner.PlugGividado
import io.freeze_dolphin.auto_spawner.utils.SpawnerUtl
import org.bukkit.Material
import org.bukkit.block.CreatureSpawner
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.inventory.meta.BlockStateMeta
import org.bukkit.plugin.Plugin
import redempt.redlib.misc.EventListener

class ListenerBus(plug: Plugin) {

    new EventListener[EntityPickupItemEvent](plug, classOf[EntityPickupItemEvent], (evt: EntityPickupItemEvent) => {
        if (evt.getEntity.isInstanceOf[Player]) {
            if (!evt.isCancelled) {
                val itm = evt.getItem.getItemStack
                if (itm.getType.equals(Material.SPAWNER)) {
                    val im = itm.getItemMeta
                    if (!im.hasLore && !im.hasLocalizedName) {
                        val cm = im.asInstanceOf[BlockStateMeta].getBlockState.asInstanceOf[CreatureSpawner]
                        var matched = false
                        val vari = cm.getSpawnedType
                        for (i <- PlugGividado.varieties) {
                            if (vari.name().equals(i)) matched = true
                        }
                        if (matched) {
                            evt.getItem.setItemStack(SpawnerUtl.getDefaultSpawnerItem(evt.getEntity.getUniqueId, vari.name()))
                        }
                    }
                }
            }
        }
    })

}
