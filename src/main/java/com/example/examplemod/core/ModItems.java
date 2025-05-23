package com.example.examplemod.core;

import com.example.examplemod.ExampleMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.bus.api.IEventBus;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExampleMod.MODID);

    // Smelted Materials
    public static final DeferredItem<Item> ADAMANTITE = ITEMS.registerSimpleItem("adamantite", new Item.Properties());
    public static final DeferredItem<Item> TROPHILITE = ITEMS.registerSimpleItem("trophilite", new Item.Properties());
    public static final DeferredItem<Item> XARIUM = ITEMS.registerSimpleItem("xarium", new Item.Properties());

    // BlockItems for Ores
    // Adamantite
    public static final DeferredItem<BlockItem> ADAMANTITE_ORE_ITEM = ITEMS.registerSimpleBlockItem("adamantite_ore", ModBlocks.ADAMANTITE_ORE);
    public static final DeferredItem<BlockItem> DEEPSLATE_ADAMANTITE_ORE_ITEM = ITEMS.registerSimpleBlockItem("deepslate_adamantite_ore", ModBlocks.DEEPSLATE_ADAMANTITE_ORE);
    
    // Trophilite
    public static final DeferredItem<BlockItem> TROPHILITE_ORE_ITEM = ITEMS.registerSimpleBlockItem("trophilite_ore", ModBlocks.TROPHILITE_ORE);
    public static final DeferredItem<BlockItem> DEEPSLATE_TROPHILITE_ORE_ITEM = ITEMS.registerSimpleBlockItem("deepslate_trophilite_ore", ModBlocks.DEEPSLATE_TROPHILITE_ORE);

    // Xarium
    public static final DeferredItem<BlockItem> XARIUM_ORE_ITEM = ITEMS.registerSimpleBlockItem("xarium_ore", ModBlocks.XARIUM_ORE);
    public static final DeferredItem<BlockItem> DEEPSLATE_XARIUM_ORE_ITEM = ITEMS.registerSimpleBlockItem("deepslate_xarium_ore", ModBlocks.DEEPSLATE_XARIUM_ORE);


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
