package com.example.test_block.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

// Imports for new methods
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level; // Added as per instruction, though context.getLevel() is primary

public class ConnectedTextureBlock extends Block {

    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    // Optional: public static final BooleanProperty UP = BooleanProperty.create("up");
    // Optional: public static final BooleanProperty DOWN = BooleanProperty.create("down");

    public ConnectedTextureBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .strength(1.5F, 6.0F)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops());
        
        this.registerDefaultState(this.stateDefinition.any()
            .setValue(NORTH, Boolean.valueOf(false))
            .setValue(SOUTH, Boolean.valueOf(false))
            .setValue(EAST, Boolean.valueOf(false))
            .setValue(WEST, Boolean.valueOf(false)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST);
        // If UP/DOWN were added: builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        boolean north = level.getBlockState(pos.north()).is(this);
        boolean south = level.getBlockState(pos.south()).is(this);
        boolean east = level.getBlockState(pos.east()).is(this);
        boolean west = level.getBlockState(pos.west()).is(this);

        return this.defaultBlockState()
            .setValue(NORTH, Boolean.valueOf(north))
            .setValue(SOUTH, Boolean.valueOf(south))
            .setValue(EAST, Boolean.valueOf(east))
            .setValue(WEST, Boolean.valueOf(west));
    }

    @Override
    public BlockState updateShape(BlockState currentState, Direction facing, BlockState facingState,
                                  LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        // Only update if the change is in one of the cardinal horizontal directions
        if (facing == Direction.NORTH || facing == Direction.SOUTH ||
            facing == Direction.EAST || facing == Direction.WEST) {

            boolean north = level.getBlockState(currentPos.north()).is(this);
            boolean south = level.getBlockState(currentPos.south()).is(this);
            boolean east = level.getBlockState(currentPos.east()).is(this);
            boolean west = level.getBlockState(currentPos.west()).is(this);

            return currentState
                .setValue(NORTH, Boolean.valueOf(north))
                .setValue(SOUTH, Boolean.valueOf(south))
                .setValue(EAST, Boolean.valueOf(east))
                .setValue(WEST, Boolean.valueOf(west));
        }
        
        return super.updateShape(currentState, facing, facingState, level, currentPos, facingPos);
    }
}
