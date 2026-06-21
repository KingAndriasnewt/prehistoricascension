package net.newt.prehistoricascension.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.newt.prehistoricascension.PrehistoricAscension;
import net.newt.prehistoricascension.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PrehistoricAscension.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        logBlock(((RotatedPillarBlock) ModBlocks.GINKGO_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.GINKGO_WOOD.get()), blockTexture(ModBlocks.GINKGO_LOG.get()), blockTexture(ModBlocks.GINKGO_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_GINKGO_LOG.get()), blockTexture(ModBlocks.STRIPPED_GINKGO_LOG.get()),
               new ResourceLocation(PrehistoricAscension.MOD_ID, "block/stripped_ginkgo_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_GINKGO_WOOD.get()), blockTexture(ModBlocks.STRIPPED_GINKGO_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_GINKGO_LOG.get()));

        blockItem(ModBlocks.GINKGO_LOG);
        blockItem(ModBlocks.GINKGO_WOOD);
        blockItem(ModBlocks.STRIPPED_GINKGO_LOG);
        blockItem(ModBlocks.STRIPPED_GINKGO_WOOD);

        blockwithitem(ModBlocks.GINKGO_PLANKS);

        blockwithitem(ModBlocks.DEEPSLATE_FOSSIL_BLOCK);

        leavesBlock(ModBlocks.GINKGO_LEAVES);
        leavesBlock(ModBlocks.ANCIENT_GINKGO_LEAVES);

        stairsBlock(((StairBlock) ModBlocks.GINKGO_STAIRS.get()), blockTexture(ModBlocks.GINKGO_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.GINKGO_SLAB.get()), blockTexture(ModBlocks.GINKGO_PLANKS.get()), blockTexture(ModBlocks.GINKGO_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBlocks.GINKGO_BUTTON.get()), blockTexture(ModBlocks.GINKGO_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.GINKGO_PRESSURE_PLATE.get()), blockTexture(ModBlocks.GINKGO_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBlocks.GINKGO_FENCE.get()), blockTexture(ModBlocks.GINKGO_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.GINKGO_FENCE_GATE.get()), blockTexture(ModBlocks.GINKGO_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.GINKGO_DOOR.get()), modLoc("block/ginkgo_door_bottom"), modLoc("block/ginkgo_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.GINKGO_TRAPDOOR.get()), modLoc("block/ginkgo_trapdoor"), true, "cutout");

        signBlock(((StandingSignBlock) ModBlocks.GINKGO_SIGN.get()), ((WallSignBlock) ModBlocks.GINKGO_WALL_SIGN.get()),
        blockTexture(ModBlocks.GINKGO_PLANKS.get()));

        hangingSignBlock(ModBlocks.GINKGO_HANGING_SIGN.get(), ModBlocks.GINKGO_WALL_HANGING_SIGN.get(), blockTexture(ModBlocks.GINKGO_PLANKS.get()));

        saplingBlock(ModBlocks.GINKGO_SAPLING);
        saplingBlock(ModBlocks.ANCIENT_GINKGO_SAPLING);

        simpleBlockWithItem(ModBlocks.CLUSTERED_BROWN_MUSHROOM.get(), models().cross(blockTexture(ModBlocks.CLUSTERED_BROWN_MUSHROOM.get()).getPath(),
                blockTexture(ModBlocks.CLUSTERED_BROWN_MUSHROOM.get())).renderType("cutout"));
        simpleBlockWithItem(ModBlocks.POTTED_CLUSTERED_BROWN_MUSHROOM.get(), models().singleTexture("potted_clustered_brown_mushroom", new ResourceLocation("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.CLUSTERED_BROWN_MUSHROOM.get())).renderType("cutout"));

        simpleBlockWithItem(ModBlocks.WEEPING_TAN_MUSHROOM.get(), models().cross(blockTexture(ModBlocks.WEEPING_TAN_MUSHROOM.get()).getPath(),
                blockTexture(ModBlocks.WEEPING_TAN_MUSHROOM.get())).renderType("cutout"));
        simpleBlockWithItem(ModBlocks.POTTED_WEEPING_TAN_MUSHROOM.get(), models().singleTexture("potted_weeping_tan_mushroom", new ResourceLocation("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.WEEPING_TAN_MUSHROOM.get())).renderType("cutout"));

        simpleBlockWithItem(ModBlocks.GREEN_BELL_MUSHROOM.get(), models().cross(blockTexture(ModBlocks.GREEN_BELL_MUSHROOM.get()).getPath(),
                blockTexture(ModBlocks.GREEN_BELL_MUSHROOM.get())).renderType("cutout"));
        simpleBlockWithItem(ModBlocks.POTTED_GREEN_BELL_MUSHROOM.get(), models().singleTexture("potted_green_bell_mushroom", new ResourceLocation("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.GREEN_BELL_MUSHROOM.get())).renderType("cutout"));

        simpleBlockWithItem(ModBlocks.PURPLE_TOP_HAT_MUSHROOM.get(), models().cross(blockTexture(ModBlocks.PURPLE_TOP_HAT_MUSHROOM.get()).getPath(),
                blockTexture(ModBlocks.PURPLE_TOP_HAT_MUSHROOM.get())).renderType("cutout"));

        simpleBlockWithItem(ModBlocks.ORANGE_TOP_MUSHROOM.get(), models().cross(blockTexture(ModBlocks.ORANGE_TOP_MUSHROOM.get()).getPath(),
                blockTexture(ModBlocks.ORANGE_TOP_MUSHROOM.get())).renderType("cutout"));

        blockwithitem(ModBlocks.CLUBMOSS);

        simpleBlockWithItem(ModBlocks.HORSETAILS.get(), models().cross(blockTexture(ModBlocks.HORSETAILS.get()).getPath(),
                blockTexture(ModBlocks.HORSETAILS.get())).renderType("cutout"));

//        simpleBlock(ModBlocks.FOSSIL_CLEANER.get(),
//                new ModelFile.UncheckedModelFile(modLoc("block/fossil_cleaner")));

    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(PrehistoricAscension.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }
    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockwithitem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

}
