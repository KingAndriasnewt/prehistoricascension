package net.newt.prehistoricascension.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.newt.prehistoricascension.block.ModBlocks;

public class GinkgoKnucklesItem extends BlockItem {



    private static final Block[] ALLOWED_BLOCKS = { // Add whatever blocks you want in here for the Ginkgo Knuckles to be placed on
            ModBlocks.GINKGO_LOG.get(),
            ModBlocks.GINKGO_WOOD.get(),
            ModBlocks.STRIPPED_GINKGO_LOG.get(),
            ModBlocks.STRIPPED_GINKGO_WOOD.get(),
            ModBlocks.GINKGO_PLANKS.get()
    };

    public GinkgoKnucklesItem(Block block, Item.Properties props) {
        super(block, props);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Level level        = ctx.getLevel();
        BlockPos hitPos    = ctx.getClickedPos();
        Direction hitFace  = ctx.getClickedFace();
        BlockState hitState = level.getBlockState(hitPos);

        if (hitFace != Direction.DOWN) return InteractionResult.FAIL;

        boolean okBase = false;
        for (Block b : ALLOWED_BLOCKS) if (hitState.is(b)) { okBase = true; break; }
        if (!okBase) return InteractionResult.FAIL;

        BlockPos placePos = hitPos.below();
        if (!level.getBlockState(placePos).canBeReplaced()) return InteractionResult.FAIL;

        BlockState placeState = getBlock().defaultBlockState()
                .setValue(GinkgoKnucklesBlock.FACING, Direction.DOWN);

        if (level.setBlock(placePos, placeState, 11)) {
            ctx.getItemInHand().shrink(1);
            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        return InteractionResult.FAIL;
    }
}