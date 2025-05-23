package com.example.examplemod.data;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.core.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ExampleMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Register models for all ore blocks to render as simple cubes
        simpleBlockWithItem(ModBlocks.ADAMANTITE_ORE);
        simpleBlockWithItem(ModBlocks.DEEPSLATE_ADAMANTITE_ORE);
        simpleBlockWithItem(ModBlocks.TROPHILITE_ORE);
        simpleBlockWithItem(ModBlocks.DEEPSLATE_TROPHILITE_ORE);
        simpleBlockWithItem(ModBlocks.XARIUM_ORE);
        simpleBlockWithItem(ModBlocks.DEEPSLATE_XARIUM_ORE);
    }

    private void simpleBlockWithItem(DeferredBlock<Block> blockRegistryObject) {
       simpleBlock(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
       // Also register the item model for the block item
       itemModels().withExistingParent(blockRegistryObject.getId().getPath(), modLoc("block/" + blockRegistryObject.getId().getPath()));
    }
}
