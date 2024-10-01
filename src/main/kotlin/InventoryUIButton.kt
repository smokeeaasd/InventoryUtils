package dev.lucas

import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * Represents a custom Button Component that can be used in InventoryUI
 *
 * @param itemStack The item that will be shown in the InventoryUI
 * @param title Item title
 * @param lore Item lore
 * @param onClicked Function that will be executed when user clicks on button.
 */
class InventoryUIButton(
    itemStack: ItemStack,
    title: Component,
    lore: MutableList<Component> = mutableListOf(),
    private val onClicked: (Player) -> Unit = {}
) : InventoryUIComponent(itemStack, title, lore) {
    fun handleClick(player: Player) {
        onClicked(player)
    }
}