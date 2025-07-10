package net.newt.prehistoricascension.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.newt.prehistoricascension.PrehistoricAscension;
import net.newt.prehistoricascension.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, PrehistoricAscension.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {


        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.GINKGO_LOG.get().asItem())
                .add(ModBlocks.GINKGO_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_GINKGO_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_GINKGO_WOOD.get().asItem());


        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.GINKGO_PLANKS.get().asItem());

    }
}
