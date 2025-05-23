package com.example.examplemod.data;

import com.example.examplemod.core.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ModLootTableProvider {
    public static LootTableProvider create(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        return new LootTableProvider(packOutput, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)
        ), lookupProvider);
    }

    public static class ModBlockLootTables extends VanillaBlockLoot {
        public ModBlockLootTables(HolderLookup.Provider p_344062_) {
            super(p_344062_);
        }
        
        @Override
        protected void generate() {
            // Ores drop themselves
            this.dropSelf(ModBlocks.ADAMANTITE_ORE.get());
            this.dropSelf(ModBlocks.DEEPSLATE_ADAMANTITE_ORE.get());
            this.dropSelf(ModBlocks.TROPHILITE_ORE.get());
            this.dropSelf(ModBlocks.DEEPSLATE_TROPHILITE_ORE.get());
            this.dropSelf(ModBlocks.XARIUM_ORE.get());
            this.dropSelf(ModBlocks.DEEPSLATE_XARIUM_ORE.get());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            // Return all blocks from ModBlocks that should have loot tables generated
            return ModBlocks.BLOCKS.getEntries().stream()
                    .map(DeferredBlock::get)
                    .collect(Collectors.toList());
        }
    }
}
