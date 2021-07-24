package io.freeze_dolphin.auto_spawner.utils

import java.util.UUID
import org.bukkit.inventory.{ItemFlag, ItemStack}
import org.bukkit.persistence.PersistentDataType
import org.bukkit.{Bukkit, Material, NamespacedKey}
import redempt.redlib.itemutils.ItemBuilder
import redempt.redlib.misc.FormatUtils.color

object SpawnerUtl {

    def getDefaultSpawnerItem(uid: UUID, variety: String): ItemStack = {
        new ItemBuilder(Material.SPAWNER)
                .setName(color("&e超级刷怪笼"))
                .addLore(color("&7将该物品放置在地上来使用"))
                .addLore(color("&7你将获得一个超棒的可升级的刷怪笼"))
                .addLore(color(" "))
                .addLore(color("&f刷怪笼主人 -  &3" + Bukkit.getPlayer(uid).getName))
                .addLore(color(" "))
                .addLore(color("  &7生物种类 &f-  &a" + getTranslation(variety)))
                .addLore(color("  &7冷却时间 &f-  &a60 秒"))
                .addLore(color("  &7刷怪上限 &f- &a2 &7个"))
                .addLore(color("  &7工作范围 &f- &a16 &7格内"))
                .addLore(color("  &7实体上限 &f- &a8 &7个"))
                .addItemFlags(ItemFlag.HIDE_ENCHANTS)
                .addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .addPersistentTag(getKey("bought"), PersistentDataType.STRING, "")
                .addPersistentTag(getKey("l"), PersistentDataType.STRING, "\"SPAWN_INTERVAL:1\",\"PLAYER_RANGE:1\",\"NEARBY_ENTITIES:1\",\"PERIOD_AMOUNT:1\"")
                .addPersistentTag(getKey("uspawners"), PersistentDataType.INTEGER, Integer.valueOf(1))
                .addPersistentTag(getKey("entities"), PersistentDataType.INTEGER, Integer.valueOf(0))
                .addPersistentTag(getKey("o"), PersistentDataType.STRING, uid.toString)
                .addPersistentTag(getKey("period"), PersistentDataType.LONG, java.lang.Long.valueOf(1))
                .addPersistentTag(getKey("entity"), PersistentDataType.STRING, variety)
    }

    private def getKey(id: String): NamespacedKey = {
        new NamespacedKey(Bukkit.getPluginManager.getPlugin("UpgradeableSpawners"), id)
    }

    def getTranslation(variety: String): String = variety match {
        case "ZOMBIE" =>
            "僵尸"
        case "SKELETON" =>
            "骷髅"
        case "CAVE_SPIDER" =>
            "洞穴蜘蛛"
        case "SILVERFISH" =>
            "蠹虫"
        case "BLAZE" =>
            "烈焰人"
        case "SPIDER" =>
            "蜘蛛"
        case "MAGMA_CUBE" =>
            "岩浆怪"
    }

}
