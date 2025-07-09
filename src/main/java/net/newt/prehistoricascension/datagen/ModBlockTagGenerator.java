package net.newt.prehistoricascension.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.newt.prehistoricascension.PrehistoricAscension;
import net.newt.prehistoricascension.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, PrehistoricAscension.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.NEEDS_STONE_TOOL);

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.DEEPSLATE_FOSSIL_BLOCK.get());


        this.tag(BlockTags.NEEDS_DIAMOND_TOOL);


        this.tag(BlockTags.MINEABLE_WITH_SHOVEL);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.DEEPSLATE_FOSSIL_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_HOE);

        this.tag(BlockTags.MINEABLE_WITH_AXE);

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.GINKO_LOG.get())
                .add(ModBlocks.GINKO_WOOD.get())
                .add(ModBlocks.STRIPPED_GINKO_LOG.get())
                .add(ModBlocks.STRIPPED_GINKO_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.GINKO_PLANKS.get());

        this.tag(BlockTags.FENCES)
                .add(ModBlocks.GINKO_FENCE.get());

        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.GINKO_FENCE_GATE.get());




    }
}
