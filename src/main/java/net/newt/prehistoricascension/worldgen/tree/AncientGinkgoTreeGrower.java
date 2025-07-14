package net.newt.prehistoricascension.worldgen.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.newt.prehistoricascension.worldgen.ModConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

public class AncientGinkgoTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
        return ModConfiguredFeatures.ANCIENT_GINKGO_KEY;
    }
}
