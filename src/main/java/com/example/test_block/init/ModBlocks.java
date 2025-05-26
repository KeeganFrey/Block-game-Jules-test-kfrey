package com.example.test_block.init;

import com.example.test_block.block.ConnectedTextureBlock;
import com.example.test_block.TestBlockMod; // Assuming main mod class is TestBlockMod

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
        DeferredRegister.create(ForgeRegistries.BLOCKS, TestBlockMod.MOD_ID);

    // Example of how ConnectedTextureBlock would be registered
    public static final RegistryObject<Block> CONNECTED_TEXTURE_BLOCK = BLOCKS.register("connected_texture_block",
        () -> new ConnectedTextureBlock());

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
