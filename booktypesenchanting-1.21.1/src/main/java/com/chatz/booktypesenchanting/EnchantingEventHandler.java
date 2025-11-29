package com.chatz.booktypesenchanting;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.EnchantingTableBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EventBusSubscriber(modid = BookTypesEnchanting.MODID)
public class EnchantingEventHandler {

    // Store which type book each player is using
    private static final Map<UUID, EnchantmentBookType> playerTypeBooks = new HashMap<>();

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        BlockPos pos = event.getPos();

        // Check if player clicked on an enchanting table
        if (event.getLevel().getBlockState(pos).getBlock() instanceof EnchantingTableBlock) {
            ItemStack heldItem = event.getItemStack();

            // Check if player is holding a Type Book
            if (heldItem.getItem() instanceof TypeBookItem typeBook) {
                // Store the book type for this player
                playerTypeBooks.put(player.getUUID(), typeBook.getBookType());

                // Consume the book (optional - you can comment this out if you want reusable books)
                if (!player.isCreative()) {
                    heldItem.shrink(1);
                }
            } else {
                // Clear any stored book type if not holding a Type Book
                playerTypeBooks.remove(player.getUUID());
            }
        }
    }

    public static EnchantmentBookType getPlayerTypeBook(UUID playerUUID) {
        return playerTypeBooks.get(playerUUID);
    }

    public static void clearPlayerTypeBook(UUID playerUUID) {
        playerTypeBooks.remove(playerUUID);
    }
}

