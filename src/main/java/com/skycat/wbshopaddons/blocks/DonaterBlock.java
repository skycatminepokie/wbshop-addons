package com.skycat.wbshopaddons.blocks;

import com.skycat.wbshop.econ.Economy;
import com.skycat.wbshop.util.LogLevel;
import com.skycat.wbshop.util.Utils;
import eu.pb4.polymer.core.api.block.SimplePolymerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPointerImpl;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DonaterBlock extends SimplePolymerBlock implements BlockEntityProvider {
    public static final BooleanProperty TRIGGERED = BooleanProperty.of("triggered");

    public DonaterBlock(Settings settings, Block polymerBlock) {
        super(settings, polymerBlock);
        setDefaultState(getDefaultState().with(TRIGGERED, false));
    }

    private static void tryDropVoucher(BlockPos pos, ServerWorld world) {
        BlockPointerImpl blockPointer = new BlockPointerImpl(world, pos);
        DonaterBlockEntity blockEntity = blockPointer.getBlockEntity();
        tryDropVoucher(pos, world, blockEntity);
    }

    private static void tryDropVoucher(BlockPos pos, ServerWorld world, DonaterBlockEntity blockEntity) {
        if (blockEntity == null) {
            Utils.log("Block entity at " + pos.toShortString() + "didn't have a block entity, skipping voucher drop. If this happens again, please report it.", LogLevel.WARN);
            return;
        }
        if (blockEntity.getPoints() > 0) {
            dropStack(world, pos, Direction.DOWN, Economy.makeVoucher(blockEntity.getPoints()));
            blockEntity.setPoints(0);
        }
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.afterBreak(world, player, pos, state, blockEntity, tool);
        if (world instanceof ServerWorld serverWorld && blockEntity instanceof DonaterBlockEntity donaterBlock) {
            tryDropVoucher(pos, serverWorld, donaterBlock);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TRIGGERED);
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
}
