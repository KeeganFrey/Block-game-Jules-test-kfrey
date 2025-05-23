package com.example.examplemod.worldgen;

import com.example.examplemod.ExampleMod;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import com.example.examplemod.core.registries.ModStructurePieceTypes;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece; // Already covered by SinglePieceStructure but good for clarity
import net.minecraft.world.level.levelgen.structure.pieces.SinglePieceStructure;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class DungeonPiece extends SinglePieceStructure {

    private static final ResourceLocation DUNGEON_NBT_RL = new ResourceLocation(ExampleMod.MODID, "dungeon_1");

    public DungeonPiece(StructureTemplateManager structureTemplateManager, ResourceLocation resourceLocation, BlockPos pos, Rotation rotation, Mirror mirror) {
        super(
            ModStructurePieceTypes.DUNGEON.get(), // Use registered type
            0, // generationDepth
            structureTemplateManager,
            resourceLocation,
            null, // template name, unused in this constructor
            makeSettings(rotation, mirror),
            pos
        );
    }

    public DungeonPiece(StructurePieceSerializationContext context, CompoundTag nbt) {
        super(
            ModStructurePieceTypes.DUNGEON.get(), // Use registered type
            nbt,
            context.structureTemplateManager(),
            (rl) -> makeSettings(Rotation.valueOf(nbt.getString("Rot")), Mirror.valueOf(nbt.getString("Mi"))) // Function to recreate settings
        );
    }

    private static StructurePlaceSettings makeSettings(Rotation rotation, Mirror mirror) {
        return new StructurePlaceSettings()
            .setRotation(rotation)
            .setMirror(mirror)
            .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK); // Example processor
    }

    @Override
    protected void handleDataMarker(String function, BlockPos pos, ServerLevelAccessor levelAccessor, RandomSource random, BoundingBox sbb) {
        // This method is used to process any special blocks (data markers)
        // you might have in your structure NBT (e.g., for mob spawning).
        // For now, it's left empty.
        // Example:
        // if (function.equals("mob_spawner")) {
        //     levelAccessor.setBlock(pos, Blocks.SPAWNER.defaultBlockState(), 3);
        //     BlockEntity blockentity = levelAccessor.getBlockEntity(pos);
        //     if (blockentity instanceof SpawnerBlockEntity) {
        //         ((SpawnerBlockEntity)blockentity).getSpawner().setEntityId(EntityType.ZOMBIE);
        //     }
        // }
    }

    @Override
    public StructurePieceType getType() {
        return ModStructurePieceTypes.DUNGEON.get();
    }
}
