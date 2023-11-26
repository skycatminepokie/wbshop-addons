package com.skycat.wbshopaddons.blocks;

import com.skycat.wbshop.econ.Economy;
import eu.pb4.polymer.core.api.block.SimplePolymerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPointerImpl;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class DonaterBlock extends SimplePolymerBlock implements BlockEntityProvider {
    public static final BooleanProperty TRIGGERED = BooleanProperty.of("triggered");

    public DonaterBlock(Settings settings, Block polymerBlock) {
        super(settings, polymerBlock);
        setDefaultState(getDefaultState().with(TRIGGERED, false));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DonaterBlockEntity(pos, state);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world instanceof ServerWorld serverWorld) {
            boolean receivingPower = world.isReceivingRedstonePower(pos);
            Boolean triggered = state.get(TRIGGERED);
            if (!receivingPower && triggered) { // Hey, we should turn off
                world.setBlockState(pos, state.with(TRIGGERED, false));
            } else {
                if (receivingPower && !triggered) { // Hey, we should turn on
                    world.setBlockState(pos, state.with(TRIGGERED, false));

                    tryDropVoucher(pos, serverWorld);
                }
            }
        }
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        if (world instanceof ServerWorld serverWorld) {
            tryDropVoucher(pos, serverWorld);
        }
    }

    private static void tryDropVoucher(BlockPos pos, ServerWorld serverWorld) {
        BlockPointerImpl blockPointer = new BlockPointerImpl(serverWorld, pos);
        DonaterBlockEntity blockEntity = blockPointer.getBlockEntity();
        if (blockEntity.getPoints() > 0) {
            dropStack(serverWorld, pos, Direction.DOWN, Economy.makeVoucher(blockEntity.getPoints()));
            blockEntity.setPoints(0);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TRIGGERED);
    }
}
