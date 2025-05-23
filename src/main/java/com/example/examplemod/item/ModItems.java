package com.example.examplemod.item;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.entity.ModEntities;
import net.minecraft.core.registries.Registries; // Added
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem; // Changed to vanilla SpawnEggItem
import net.neoforged.neoforge.registries.DeferredRegister;
// import net.neoforged.neoforge.registries.NeoForgeRegistries; // No longer needed for ITEMS
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder; // Changed from RegistryObject

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, ExampleMod.MODID); // Changed

    public static final DeferredHolder<Item, Item> CRYSTALLIZED_NECTAR = ITEMS.register("crystallized_nectar", // Changed
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> CHIPPED_AMETHYST = ITEMS.register("chipped_amethyst", // Changed
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> SWAMP_MOSS = ITEMS.register("swamp_moss", // Changed
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> JUNGLE_MOSS = ITEMS.register("jungle_moss", // Changed
            () -> new Item(new Item.Properties()));

    // Spawn Eggs
    public static final DeferredHolder<Item, Item> CAVE_CRAWLER_SPAWN_EGG = ITEMS.register("cave_crawler_spawn_egg", // Changed
            () -> new SpawnEggItem(ModEntities.CAVE_CRAWLER.get(), // Removed color args
                    new Item.Properties()));
    public static final DeferredHolder<Item, Item> SWAMPSTER_SPAWN_EGG = ITEMS.register("swampster_spawn_egg", // Changed
            () -> new SpawnEggItem(ModEntities.SWAMPSTER.get(), // Removed color args
                    new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
