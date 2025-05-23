package com.example.examplemod.worldgen;

import com.example.examplemod.ExampleMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.BiomeFilter; // For biome specific placement
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;


import java.util.List;

public class ModPlacedFeatures {
    // Adamantite
    public static final ResourceKey<PlacedFeature> ADAMANTITE_PLACED_KEY = registerKey("adamantite_placed");
    // Trophilite
    public static final ResourceKey<PlacedFeature> TROPHILITE_PLACED_KEY = registerKey("trophilite_placed");
    // Xarium
    public static final ResourceKey<PlacedFeature> XARIUM_PLACED_KEY = registerKey("xarium_placed");


    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // Adamantite: Below Y -32
        register(context, ADAMANTITE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ADAMANTITE_ORE_KEY),
                commonOrePlacement(12, // Veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(-32))));

        // Trophilite: Only in Jungles (BiomeFilter handled by BiomeModifier), spread out, below Y 0
        // For Trophilite, the BiomeFilter is applied in the Biome Modifier. Here we define its general placement rules.
        register(context, TROPHILITE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TROPHILITE_ORE_KEY),
                commonOrePlacement(8, // Veins per chunk in targeted biomes
                        HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(0)))); // Spawn from bottom of world up to Y=0

        // Xarium: Below Y -32, less common
        register(context, XARIUM_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.XARIUM_ORE_KEY),
                commonOrePlacement(7, // Veins per chunk, less than Adamantite
                        HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(-32))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(ExampleMod.MODID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
        context.register(key, new PlacedFeature(feature, placementModifiers));
    }

    // Helper methods for common ore placement configurations
    private static List<PlacementModifier> orePlacement(PlacementModifier countPlacement, PlacementModifier heightPlacement) {
        return List.of(countPlacement, InSquarePlacement.spread(), heightPlacement, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier heightPlacement) {
        return orePlacement(CountPlacement.of(count), heightPlacement);
    }
}
