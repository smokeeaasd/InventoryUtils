package dev.lucas

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent

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
                when (event.click) {
                    ClickType.LEFT -> {
                        // Handle left click
                        component.handleLeftClick(player)
                    }
                    ClickType.RIGHT -> {
                        // Handle right click
                        component.handleRightClick(player)
                    }
                    else -> {
                        // Handle other click types
                        component.handleClick(player)
                    }
                }
            }
        }
    }

    /**
     * Function that checks wheter the inventory is a UI and handles drag event
     */
    @EventHandler
    fun onInventoryDrag(event: InventoryDragEvent) {
        val clickedInventory = event.inventory ?: return
        val player = event.whoClicked as? Player ?: return

        if (clickedInventory.holder !is InventoryUI) return

        event.isCancelled = true
    }
}