package com.chatz.booktypesenchanting;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(BookTypesEnchanting.MODID)
public class BookTypesEnchanting {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "booktypesenchanting";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "booktypesenchanting" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "booktypesenchanting" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "booktypesenchanting" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);


    // Creative tab for Type Books
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TYPE_BOOKS_TAB = CREATIVE_MODE_TABS.register("type_books_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.booktypesenchanting.type_books"))
            .withTabsBefore(CreativeModeTabs.TOOLS_AND_UTILITIES)
            .icon(() -> ModItems.SWORD_BOOK.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ModItems.SWORD_BOOK.get());
                output.accept(ModItems.PICKAXE_BOOK.get());
                output.accept(ModItems.AXE_BOOK.get());
                output.accept(ModItems.SHOVEL_BOOK.get());
                output.accept(ModItems.HOE_BOOK.get());
                output.accept(ModItems.HELMET_BOOK.get());
                output.accept(ModItems.CHESTPLATE_BOOK.get());
                output.accept(ModItems.LEGGINGS_BOOK.get());
                output.accept(ModItems.BOOTS_BOOK.get());
                output.accept(ModItems.BOW_BOOK.get());
                output.accept(ModItems.CROSSBOW_BOOK.get());
                output.accept(ModItems.TRIDENT_BOOK.get());
                output.accept(ModItems.FISHING_ROD_BOOK.get());
            }).build());

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public BookTypesEnchanting(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Initialize ModItems to ensure Type Books are registered
        ModItems.init();

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (BookTypesEnchanting) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Common setup code
        LOGGER.info("Book Types Enchanting mod initialized");
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
