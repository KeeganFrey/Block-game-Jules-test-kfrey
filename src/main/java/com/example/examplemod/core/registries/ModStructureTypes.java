package com.example.examplemod.core.registries;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.worldgen.DungeonStructure;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryObject;
import net.neoforged.bus.api.IEventBus;

public class ModStructureTypes {

    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, ExampleMod.MODID);

    // This will cause a compilation error until DungeonStructure.CODEC is defined.
    // I will proceed with this and address DungeonStructure.java next.
    public static final RegistryObject<StructureType<DungeonStructure>> DUNGEON =
            STRUCTURE_TYPES.register("dungeon", () -> () -> DungeonStructure.CODEC);

    public static void register(IEventBus eventBus) {
        STRUCTURE_TYPES.register(eventBus);
    }
}
