package com.example.examplemod.worldgen;

import com.example.examplemod.ExampleMod;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_ADAMANTITE_ORE = registerKey("add_adamantite_ore");
    public static final ResourceKey<BiomeModifier> ADD_TROPHILITE_ORE = registerKey("add_trophilite_ore");
    public static final ResourceKey<BiomeModifier> ADD_XARIUM_ORE = registerKey("add_xarium_ore");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        // Add Adamantite to all Overworld biomes (excluding Nether and End)
        context.register(ADD_ADAMANTITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD), // Targets all Overworld biomes
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ADAMANTITE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        // Add Trophilite specifically to Jungle biomes
        context.register(ADD_TROPHILITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_JUNGLE), // Targets Jungle biomes
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TROPHILITE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        
        // Add Xarium to all Overworld biomes (excluding Nether and End)
        context.register(ADD_XARIUM_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD), // Targets all Overworld biomes
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.XARIUM_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(ExampleMod.MODID, name));
    }
}
