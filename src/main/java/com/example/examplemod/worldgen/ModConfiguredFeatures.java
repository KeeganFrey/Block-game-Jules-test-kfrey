package com.example.examplemod.worldgen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.core.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    // Adamantite
    public static final ResourceKey<ConfiguredFeature<?, ?>> ADAMANTITE_ORE_KEY = registerKey("adamantite_ore");
    // Trophilite
    public static final ResourceKey<ConfiguredFeature<?, ?>> TROPHILITE_ORE_KEY = registerKey("trophilite_ore");
    // Xarium
    public static final ResourceKey<ConfiguredFeature<?, ?>> XARIUM_ORE_KEY = registerKey("xarium_ore");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        // Adamantite Ore Configuration
        List<OreConfiguration.TargetBlockState> adamantiteOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.ADAMANTITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_ADAMANTITE_ORE.get().defaultBlockState()));
        register(context, ADAMANTITE_ORE_KEY, Feature.ORE, new OreConfiguration(adamantiteOres, 8)); // Vein size 8

        // Trophilite Ore Configuration - Spawns in stone variants only as per jungle biome typical floor
        List<OreConfiguration.TargetBlockState> trophiliteOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.TROPHILITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_TROPHILITE_ORE.get().defaultBlockState())); // Added deepslate for jungles that go deep
        register(context, TROPHILITE_ORE_KEY, Feature.ORE, new OreConfiguration(trophiliteOres, 6)); // Vein size 6

        // Xarium Ore Configuration
        List<OreConfiguration.TargetBlockState> xariumOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.XARIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_XARIUM_ORE.get().defaultBlockState()));
        register(context, XARIUM_ORE_KEY, Feature.ORE, new OreConfiguration(xariumOres, 4)); // Vein size 4, less common
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ExampleMod.MODID, name));
    }

    private static <FC extends net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration, F extends Feature<FC>> void register(
            BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
