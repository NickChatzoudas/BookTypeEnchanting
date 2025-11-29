package com.chatz.booktypesenchanting;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.List;

public class TypeBookItem extends Item {
    private final EnchantmentBookType bookType;

    public TypeBookItem(EnchantmentBookType bookType) {
        super(new Properties().stacksTo(64));
        this.bookType = bookType;
    }

    public EnchantmentBookType getBookType() {
        return bookType;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        // Show enchantments if this book has been enchanted
        EnchantmentHelper.getEnchantmentsForCrafting(stack).entrySet().forEach(entry -> {
            tooltipComponents.add(Component.translatable(entry.getKey().value().description().getString())
                .withStyle(ChatFormatting.GRAY));
        });

        // Show what type of enchantments this book accepts if not yet enchanted
        if (EnchantmentHelper.getEnchantmentsForCrafting(stack).isEmpty()) {
            tooltipComponents.add(Component.translatable("tooltip.booktypesenchanting.type_book",
                Component.translatable("item.booktypesenchanting." + bookType.getName() + "_book.type"))
                .withStyle(ChatFormatting.GRAY));
            tooltipComponents.add(Component.translatable("tooltip.booktypesenchanting.type_book.usage")
                .withStyle(ChatFormatting.DARK_GRAY));
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public int getEnchantmentValue() {
        return 15; // Same as diamond tools for good enchantability
    }
}
