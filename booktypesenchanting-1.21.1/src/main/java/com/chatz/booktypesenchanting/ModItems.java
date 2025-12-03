package com.chatz.booktypesenchanting;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItems {
    // Pitchfork item - used for crafting trident books
    public static final DeferredItem<Item> PITCHFORK = BookTypesEnchanting.ITEMS.register("pitchfork",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SWORD_BOOK = BookTypesEnchanting.ITEMS.register("sword_book",
            () -> new TypeBookItem(EnchantmentBookType.SWORD));
    public static final DeferredItem<Item> PICKAXE_BOOK = BookTypesEnchanting.ITEMS.register("pickaxe_book",
            () -> new TypeBookItem(EnchantmentBookType.PICKAXE));
    public static final DeferredItem<Item> AXE_BOOK = BookTypesEnchanting.ITEMS.register("axe_book",
            () -> new TypeBookItem(EnchantmentBookType.AXE));
    public static final DeferredItem<Item> SHOVEL_BOOK = BookTypesEnchanting.ITEMS.register("shovel_book",
            () -> new TypeBookItem(EnchantmentBookType.SHOVEL));
    public static final DeferredItem<Item> HOE_BOOK = BookTypesEnchanting.ITEMS.register("hoe_book",
            () -> new TypeBookItem(EnchantmentBookType.HOE));
    public static final DeferredItem<Item> HELMET_BOOK = BookTypesEnchanting.ITEMS.register("helmet_book",
            () -> new TypeBookItem(EnchantmentBookType.HELMET));
    public static final DeferredItem<Item> CHESTPLATE_BOOK = BookTypesEnchanting.ITEMS.register("chestplate_book",
            () -> new TypeBookItem(EnchantmentBookType.CHESTPLATE));
    public static final DeferredItem<Item> LEGGINGS_BOOK = BookTypesEnchanting.ITEMS.register("leggings_book",
            () -> new TypeBookItem(EnchantmentBookType.LEGGINGS));
    public static final DeferredItem<Item> BOOTS_BOOK = BookTypesEnchanting.ITEMS.register("boots_book",
            () -> new TypeBookItem(EnchantmentBookType.BOOTS));
    public static final DeferredItem<Item> BOW_BOOK = BookTypesEnchanting.ITEMS.register("bow_book",
            () -> new TypeBookItem(EnchantmentBookType.BOW));
    public static final DeferredItem<Item> CROSSBOW_BOOK = BookTypesEnchanting.ITEMS.register("crossbow_book",
            () -> new TypeBookItem(EnchantmentBookType.CROSSBOW));
    public static final DeferredItem<Item> TRIDENT_BOOK = BookTypesEnchanting.ITEMS.register("trident_book",
            () -> new TypeBookItem(EnchantmentBookType.TRIDENT));
    public static final DeferredItem<Item> FISHING_ROD_BOOK = BookTypesEnchanting.ITEMS.register("fishing_rod_book",
            () -> new TypeBookItem(EnchantmentBookType.FISHING_ROD));

    public static void init() {
        // This method can be called to ensure the class is loaded
    }
}

