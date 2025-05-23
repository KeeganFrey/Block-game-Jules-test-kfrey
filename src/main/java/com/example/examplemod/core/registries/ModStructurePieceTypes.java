package com.example.examplemod.core.registries;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.worldgen.DungeonPiece;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryObject;
import net.neoforged.bus.api.IEventBus;

public class ModStructurePieceTypes {

    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_PIECE, ExampleMod.MODID);

    // Registering DungeonPiece.
    // The DungeonPiece(StructurePieceSerializationContext, CompoundTag) constructor is
    // compatible with the StructurePieceType.StructureTemplateType functional interface.
    public static final RegistryObject<StructurePieceType> DUNGEON =
            STRUCTURE_PIECE_TYPES.register("dungeon_piece", () -> (StructurePieceType.StructureTemplateType) DungeonPiece::new);

    public static void register(IEventBus eventBus) {
        STRUCTURE_PIECE_TYPES.register(eventBus);
    }
}
