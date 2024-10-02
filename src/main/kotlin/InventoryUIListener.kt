package dev.lucas

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

/**
 * Listen InventoryUI events.
 */
class InventoryUIListener() : Listener {

    /**
     * Function that checks whether the inventory is a UI and handles its basic actions.
     */
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val clickedInventory = event.clickedInventory ?: return
        val clickedItem = event.currentItem ?: return
        val player = event.whoClicked as? Player ?: return

        if (clickedInventory.holder !is InventoryUI) return

        val ui = clickedInventory.holder as InventoryUI

        val slot = event.slot
        val component = ui.components[slot] ?: return
        event.isCancelled = true
        when (component) {
            is InventoryUIButton -> {
                component.handleClick(player)
            }
        }
    }
}