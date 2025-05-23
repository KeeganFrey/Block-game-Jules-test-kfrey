package com.example.examplemod.worldgen;

import com.example.examplemod.core.registries.ModStructureTypes;
import com.mojang.serialization.Codec;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.MobSpawnSettings;

import java.util.Optional;

public class DungeonStructure extends Structure {

    public static final Codec<DungeonStructure> CODEC = simpleCodec(DungeonStructure::new);

    // TODO: Replace with your own list of spawnable entities
    public static final WeightedRandomList<MobSpawnSettings.SpawnerData> STRUCTURE_MONSTERS = WeightedRandomList.create(
            new MobSpawnSettings.SpawnerData(EntityType.IRON_GOLEM, 10, 4, 4)
    );

    public DungeonStructure(Structure.StructureSettings config) {
        super(config);
    }

    @Override
    public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        // Check if the chunk can spawn the structure
        if (!DungeonStructure.canSpawn(context)) {
            return Optional.empty();
        }

        // Get the chunk coordinates
        ChunkPos chunkPos = context.chunkPos();

        // Get the height of the chunk
        int height = context.chunkGenerator().getBaseHeight(
            chunkPos.getMinBlockX(), 
            chunkPos.getMinBlockZ(), 
            Heightmap.Types.WORLD_SURFACE_WG,
            context.heightAccessor(),
            context.randomState()
        );

        // If the height is too low, don't spawn the structure
        if (height < 30) { // TODO: Adjust this value as needed
            return Optional.empty();
        }
        
        // Create a block position for the structure
        BlockPos blockPos = new BlockPos(chunkPos.getMinBlockX(), height, chunkPos.getMinBlockZ());

        // Return the generation stub
        return Optional.of(new GenerationStub(blockPos, (structurePiecesBuilder) -> {
            // For now, we'll leave this empty. We'll add the structure pieces in a later step.
            // TODO: Add structure pieces here
        }));
    }

    // TODO: Implement your own logic for determining if the structure can spawn
    private static boolean canSpawn(GenerationContext context) {
        // For now, we'll just return true
        return true;
    }

    @Override
    public StructureType<?> type() {
        return ModStructureTypes.DUNGEON.get();
    }
}
