package org.uiop.easyplacefix.Mixin.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.MultifaceBlock;
import net.minecraft.block.enums.BlockFace;
import net.minecraft.client.MinecraftClient;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.uiop.easyplacefix.IBlock;
import org.uiop.easyplacefix.data.RelativeBlockHitResult;

@Mixin(MultifaceBlock.class)
public abstract class MixinMultifaceBlock implements IBlock {
    @Shadow
    protected abstract boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos);
    @Override
    public Pair<RelativeBlockHitResult, Integer> getHitResult(BlockState blockState, BlockPos blockPos, BlockState worldBlockState) {
        BlockFace blockFace = blockState.get(Properties.BLOCK_FACE);
        Direction direction = blockState.get(Properties.HORIZONTAL_FACING);
        return canPlaceAt(blockState, MinecraftClient.getInstance().world, blockPos) ?
                switch (blockFace) {//TODO 后续可以将null改为连锁放置，需要一个接受pos的轻松放置方法
                    case FLOOR -> new Pair<>(
                            new RelativeBlockHitResult(new Vec3d(0.5, 1, 0.5),
                                    Direction.UP,
                                    blockPos.down(), false
                            ), blockState.get(Properties.POWERED) ? 2 : 1);
                    case CEILING -> new Pair<>(
                            new RelativeBlockHitResult(new Vec3d(0.5, 0, 0.5),
                                    Direction.DOWN,
                                    blockPos.up(), false
                            ), blockState.get(Properties.POWERED) ? 2 : 1);

                    case WALL -> new Pair<>(
                            new RelativeBlockHitResult(
                                    switch (direction) {
                                        case EAST -> new Vec3d(1, 0.5, 0.5);
                                        case SOUTH -> new Vec3d(0.5, 0.5, 1);
                                        case WEST -> new Vec3d(0, 0.5, 0.5);
                                        default -> new Vec3d(0.5, 0.5, 0);
                                    },
                                    direction,
                                    blockPos.offset(direction.getOpposite()),
                                    false
                            ), blockState.get(Properties.POWERED) ? 2 : 1);
                } : null;
    }

}
