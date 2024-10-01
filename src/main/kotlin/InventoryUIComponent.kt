package dev.lucas

import com.google.common.collect.MultimapBuilder
import net.kyori.adventure.text.Component
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

/**
 * Represents a custom base Component that can be used in InventoryUI
 *
 * @param itemStack The item that will be shown in the InventoryUI
 * @param title Item title
 * @param lore Item lore
 */
open class InventoryUIComponent(
    val itemStack: ItemStack,
    private val title: Component,
    private val lore: MutableList<Component> = mutableListOf(),
) {

    init {
        itemStack.removeAllAttributes()

        itemStack.editMeta {
            it.lore(lore)
            it.itemName(title)
        }
    }

    /**
     * Removes all attributes from an ItemStack.
     * In more recent paper versions, you need to clear the attributes to use the HIDE_ATTRIBUTES flag.
     */
    private fun ItemStack.removeAllAttributes() {
        editMeta {
            it.attributeModifiers = MultimapBuilder
                .hashKeys()
                .hashSetValues()
                .build()

            it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
        }
    }
}