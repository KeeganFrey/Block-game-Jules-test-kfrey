package com.example.test_block.init;

import com.example.test_block.TestBlockMod; // Assuming main mod class is TestBlockMod

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, TestBlockMod.MOD_ID);

    // Example of how the BlockItem for ConnectedTextureBlock would be registered
    public static final RegistryObject<Item> CONNECTED_TEXTURE_BLOCK_ITEM = ITEMS.register("connected_texture_block",
        () -> new BlockItem(ModBlocks.CONNECTED_TEXTURE_BLOCK.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
