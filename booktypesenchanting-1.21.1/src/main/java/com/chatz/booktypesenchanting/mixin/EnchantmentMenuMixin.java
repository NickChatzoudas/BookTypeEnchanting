package com.chatz.booktypesenchanting.mixin;

import com.chatz.booktypesenchanting.EnchantmentBookType;
import com.chatz.booktypesenchanting.TypeBookItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentMenu.class)
public abstract class EnchantmentMenuMixin {

    @Unique
    private ItemStack booktypesenchanting$originalStack;

    @ModifyVariable(method = "getEnchantmentList", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private ItemStack booktypesenchanting$replaceStackForEnchanting(ItemStack stack) {
        // Store the original stack
        booktypesenchanting$originalStack = stack;

        // Check if the item being enchanted is a Type Book
        if (stack.getItem() instanceof TypeBookItem typeBook) {
            EnchantmentBookType bookType = typeBook.getBookType();

            // Replace the book with the corresponding item type for enchantment generation
            ItemStack replacementStack = switch (bookType) {
                case SWORD -> new ItemStack(Items.DIAMOND_SWORD);
                case PICKAXE -> new ItemStack(Items.DIAMOND_PICKAXE);
                case AXE -> new ItemStack(Items.DIAMOND_AXE);
                case SHOVEL -> new ItemStack(Items.DIAMOND_SHOVEL);
                case HOE -> new ItemStack(Items.DIAMOND_HOE);
                case HELMET -> new ItemStack(Items.DIAMOND_HELMET);
                case CHESTPLATE -> new ItemStack(Items.DIAMOND_CHESTPLATE);
                case LEGGINGS -> new ItemStack(Items.DIAMOND_LEGGINGS);
                case BOOTS -> new ItemStack(Items.DIAMOND_BOOTS);
                case BOW -> new ItemStack(Items.BOW);
                case CROSSBOW -> new ItemStack(Items.CROSSBOW);
                case TRIDENT -> new ItemStack(Items.TRIDENT);
                case FISHING_ROD -> new ItemStack(Items.FISHING_ROD);
            };

            // Copy count but ensure it's 1 for enchantment calculation
            replacementStack.setCount(1);
            return replacementStack;
        }

        return stack;
    }

    @Inject(method = "clickMenuButton", at = @At("RETURN"))
    private void booktypesenchanting$convertToEnchantedBook(Player player, int id, CallbackInfoReturnable<Boolean> cir) {
        // Only proceed if the enchanting was successful
        if (cir.getReturnValue()) {
            EnchantmentMenu menu = (EnchantmentMenu) (Object) this;
            ItemStack stack = menu.getSlot(0).getItem();

            // Check if the item in slot 0 is a Type Book (meaning it was just enchanted)
            if (stack.getItem() instanceof TypeBookItem) {
                // Get the enchantments that were applied to the Type Book
                ItemEnchantments enchantments = stack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);

                // Create a new vanilla enchanted book
                ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);

                // Store the enchantments in STORED_ENCHANTMENTS (for enchanted books)
                enchantedBook.set(DataComponents.STORED_ENCHANTMENTS, enchantments);

                // Replace the Type Book with the vanilla enchanted book in slot 0
                menu.getSlot(0).set(enchantedBook);
            }
        }
    }
}
