package com.example.examplemod.entity;

import com.example.examplemod.ExampleMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.core.registries.Registries; // Added
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.resources.ResourceKey; // Added
import net.minecraft.resources.ResourceLocation; // Added
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
// import net.neoforged.neoforge.registries.NeoForgeRegistries; // No longer needed for ENTITY_TYPE
import net.neoforged.neoforge.registries.DeferredHolder; // Changed from RegistryObject

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, ExampleMod.MODID); // Changed

    public static final DeferredHolder<EntityType<?>, EntityType<CaveCrawlerEntity>> CAVE_CRAWLER = // Changed
            ENTITY_TYPES.register("cave_crawler",
                    () -> EntityType.Builder.of(CaveCrawlerEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.95F) // Standard Zombie size
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "cave_crawler")))); // Changed

    public static final DeferredHolder<EntityType<?>, EntityType<SwampsterEntity>> SWAMPSTER = // Changed
            ENTITY_TYPES.register("swampster",
                    () -> EntityType.Builder.of(SwampsterEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.95F) // Standard Zombie size
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "swampster")))); // Changed

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
