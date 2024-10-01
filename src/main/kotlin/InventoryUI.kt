package dev.lucas

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.plugin.Plugin

/**
 * Represents a custom inventory UI, allowing the addition of interactive components,
 * opening the inventory for players, and reacting to open events.
 *
 * @param plugin Reference to the Main plugin instance.
 * @param size The size of the inventory. Must be a multiple of 9.
 * @param title The title of the inventory, displayed to the player when opened.
 * @param onOpen A lambda function that is triggered when the inventory is opened by a player.
 */
class InventoryUI(
    plugin: Plugin,
    size: Int,
    title: Component,
    private val onOpen: (Player) -> Unit = {}
) : InventoryHolder, Listener {

    // Underlying Bukkit Inventory instance associated with this InventoryUI.
    private val inventory: Inventory = Bukkit.createInventory(this, size, title)

    // Map to store components associated with inventory slots.
    val components: MutableMap<Int, InventoryUIComponent> = mutableMapOf()

    /**
     * Opens the inventory for the specified player.
     * onOpen event is triggered when this method is called.
     *
     * @param player Player for whom the inventory will be opened.
     */
    fun open(player: Player) {
        onOpen(player)
        player.openInventory(inventory)
    }

    /**
     * Adds a component to a specified slot in the inventory.
     * Also updates the inventory with the component's item representation.
     *
     * @param component InventoryUIComponent to add.
     * @param slot Slot index where the component will be placed.
     * @return Current InventoryUI instance for chaining.
     */
    fun addComponent(component: InventoryUIComponent, slot: Int): InventoryUI {
        components[slot] = component
        inventory.setItem(slot, component.itemStack)

        return this
    }

    /**
     * Removes a component from a specified slot in the inventory.
     * The slot will be cleared in the inventory UI.
     *
     * @param slot Slot index from which the component will be removed.
     * @return Current InventoryUI instance for chaining.
     */
    fun removeComponent(slot: Int): InventoryUI {
        components.remove(slot)
        inventory.setItem(slot, null)

        return this
    }

    /**
     * Returns the underlying Bukkit Inventory instance.
     *
     * @return Bukkit Inventory object representing this InventoryUI.
     */
    override fun getInventory(): Inventory {
        return inventory
    }
}
