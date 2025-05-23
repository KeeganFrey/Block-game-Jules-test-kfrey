package com.example.examplemod.core;

import com.example.examplemod.ExampleMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.bus.api.IEventBus;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ExampleMod.MODID);

    // Adamantite
    public static final DeferredBlock<Block> ADAMANTITE_ORE = BLOCKS.registerSimpleBlock("adamantite_ore", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F));
    public static final DeferredBlock<Block> DEEPSLATE_ADAMANTITE_ORE = BLOCKS.registerSimpleBlock("deepslate_adamantite_ore", BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().strength(4.5F, 3.0F).sound(net.minecraft.world.level.block.SoundType.DEEPSLATE));

    // Trophilite
    public static final DeferredBlock<Block> TROPHILITE_ORE = BLOCKS.registerSimpleBlock("trophilite_ore", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F));
    public static final DeferredBlock<Block> DEEPSLATE_TROPHILITE_ORE = BLOCKS.registerSimpleBlock("deepslate_trophilite_ore", BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().strength(4.5F, 3.0F).sound(net.minecraft.world.level.block.SoundType.DEEPSLATE));

    // Xarium
    public static final DeferredBlock<Block> XARIUM_ORE = BLOCKS.registerSimpleBlock("xarium_ore", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F));
    public static final DeferredBlock<Block> DEEPSLATE_XARIUM_ORE = BLOCKS.registerSimpleBlock("deepslate_xarium_ore", BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().strength(4.5F, 3.0F).sound(net.minecraft.world.level.block.SoundType.DEEPSLATE));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
