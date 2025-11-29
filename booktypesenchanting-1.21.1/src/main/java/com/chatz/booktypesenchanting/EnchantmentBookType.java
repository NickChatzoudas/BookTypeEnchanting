package com.chatz.booktypesenchanting;

import net.minecraft.world.item.*;

public enum EnchantmentBookType {
    SWORD("sword", item -> item instanceof SwordItem),
    PICKAXE("pickaxe", item -> item instanceof PickaxeItem),
    AXE("axe", item -> item instanceof AxeItem),
    SHOVEL("shovel", item -> item instanceof ShovelItem),
    HOE("hoe", item -> item instanceof HoeItem),
    HELMET("helmet", item -> item instanceof ArmorItem && ((ArmorItem) item).getType() == ArmorItem.Type.HELMET),
    CHESTPLATE("chestplate", item -> item instanceof ArmorItem && ((ArmorItem) item).getType() == ArmorItem.Type.CHESTPLATE),
    LEGGINGS("leggings", item -> item instanceof ArmorItem && ((ArmorItem) item).getType() == ArmorItem.Type.LEGGINGS),
    BOOTS("boots", item -> item instanceof ArmorItem && ((ArmorItem) item).getType() == ArmorItem.Type.BOOTS),
    BOW("bow", item -> item instanceof BowItem),
    CROSSBOW("crossbow", item -> item instanceof CrossbowItem),
    TRIDENT("trident", item -> item instanceof TridentItem),
    FISHING_ROD("fishing_rod", item -> item instanceof FishingRodItem);

    private final String name;
    private final ItemPredicate predicate;

    EnchantmentBookType(String name, ItemPredicate predicate) {
        this.name = name;
        this.predicate = predicate;
    }

    public String getName() {
        return name;
    }

    public boolean matches(Item item) {
        return predicate.test(item);
    }

    @FunctionalInterface
    public interface ItemPredicate {
        boolean test(Item item);
    }
}

