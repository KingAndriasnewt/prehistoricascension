package net.newt.prehistoricascension.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.newt.prehistoricascension.block.ModBlocks;
import net.newt.prehistoricascension.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.GINKGO_LOG.get());
        this.dropSelf(ModBlocks.GINKGO_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_GINKGO_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_GINKGO_WOOD.get());
        this.dropSelf(ModBlocks.GINKGO_PLANKS.get());
        this.dropSelf(ModBlocks.GINKGO_KNUCKLES.get());
        this.dropSelf(ModBlocks.CLUBMOSS.get());
        this.dropSelf(ModBlocks.HORSETAILS.get());

        this.dropSelf(ModBlocks.FOSSIL_CLEANER.get());
        this.dropSelf(ModBlocks.DUTCHMANS_PIPE.get());


        this.add(ModBlocks.GINKGO_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.GINKGO_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBlocks.GINKGO_STAIRS.get());
        this.dropSelf(ModBlocks.GINKGO_BUTTON.get());
        this.dropSelf(ModBlocks.GINKGO_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.GINKGO_FENCE.get());
        this.dropSelf(ModBlocks.GINKGO_FENCE_GATE.get());

        this.dropSelf(ModBlocks.GINKGO_TRAPDOOR.get());
        this.add(ModBlocks.GINKGO_DOOR.get(),
                block -> createDoorTable(ModBlocks.GINKGO_DOOR.get()));


        this.add(ModBlocks.GINKGO_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.GINKGO_SLAB.get()));

        this.add(ModBlocks.GINKGO_SIGN.get(), block ->
                createSingleItemTable(ModItems.GINKGO_SIGN.get()));
        this.add(ModBlocks.GINKGO_WALL_SIGN.get(), block ->
                createSingleItemTable(ModItems.GINKGO_SIGN.get()));
        this.add(ModBlocks.GINKGO_HANGING_SIGN.get(), block ->
                createSingleItemTable(ModItems.GINKGO_HANGING_SIGN.get()));
        this.add(ModBlocks.GINKGO_WALL_HANGING_SIGN.get(), block ->
                createSingleItemTable(ModItems.GINKGO_HANGING_SIGN.get()));

        this.add(ModBlocks.DEEPSLATE_FOSSIL_BLOCK.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DEEPSLATE_FOSSIL_BLOCK.get(), ModItems.DEEPSLATE_FOSSIL.get()));

        this.dropSelf(ModBlocks.GINKGO_SAPLING.get());
        this.dropSelf(ModBlocks.ANCIENT_GINKGO_SAPLING.get());


        this.add(ModBlocks.ANCIENT_GINKGO_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.ANCIENT_GINKGO_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));


        this.dropSelf(ModBlocks.CLUSTERED_BROWN_MUSHROOM.get());
        this.add(ModBlocks.POTTED_CLUSTERED_BROWN_MUSHROOM.get(), createPotFlowerItemTable(ModBlocks.CLUSTERED_BROWN_MUSHROOM.get()));
        this.dropSelf(ModBlocks.GREEN_BELL_MUSHROOM.get());
        this.add(ModBlocks.POTTED_GREEN_BELL_MUSHROOM.get(), createPotFlowerItemTable(ModBlocks.GREEN_BELL_MUSHROOM.get()));
        this.dropSelf(ModBlocks.WEEPING_TAN_MUSHROOM.get());
        this.add(ModBlocks.POTTED_WEEPING_TAN_MUSHROOM.get(), createPotFlowerItemTable(ModBlocks.WEEPING_TAN_MUSHROOM.get()));
        this.dropSelf(ModBlocks.PURPLE_TOP_HAT_MUSHROOM.get());
        this.dropSelf(ModBlocks.ORANGE_TOP_MUSHROOM.get());

    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}