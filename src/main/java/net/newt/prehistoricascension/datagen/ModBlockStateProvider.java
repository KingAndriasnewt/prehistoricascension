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
        logBlock(((RotatedPillarBlock) ModBlocks.GINKO_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.GINKO_WOOD.get()), blockTexture(ModBlocks.GINKO_LOG.get()), blockTexture(ModBlocks.GINKO_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_GINKO_LOG.get()), blockTexture(ModBlocks.STRIPPED_GINKO_LOG.get()),
               new ResourceLocation(PrehistoricAscension.MOD_ID, "block/stripped_ginko_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_GINKO_WOOD.get()), blockTexture(ModBlocks.STRIPPED_GINKO_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_GINKO_LOG.get()));

        blockItem(ModBlocks.GINKO_LOG);
        blockItem(ModBlocks.GINKO_WOOD);
        blockItem(ModBlocks.STRIPPED_GINKO_LOG);
        blockItem(ModBlocks.STRIPPED_GINKO_WOOD);

        blockwithitem(ModBlocks.GINKO_PLANKS);

        blockwithitem(ModBlocks.DEEPSLATE_FOSSIL_BLOCK);

        leavesBlock(ModBlocks.GINKO_LEAVES);

        stairsBlock(((StairBlock) ModBlocks.GINKO_STAIRS.get()), blockTexture(ModBlocks.GINKO_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.GINKO_SLAB.get()), blockTexture(ModBlocks.GINKO_PLANKS.get()), blockTexture(ModBlocks.GINKO_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBlocks.GINKO_BUTTON.get()), blockTexture(ModBlocks.GINKO_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.GINKO_PRESSURE_PLATE.get()), blockTexture(ModBlocks.GINKO_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBlocks.GINKO_FENCE.get()), blockTexture(ModBlocks.GINKO_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.GINKO_FENCE_GATE.get()), blockTexture(ModBlocks.GINKO_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.GINKO_DOOR.get()), modLoc("block/ginko_door_bottom"), modLoc("block/ginko_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.GINKO_TRAPDOOR.get()), modLoc("block/ginko_trapdoor"), true, "cutout");

        signBlock(((StandingSignBlock) ModBlocks.GINKO_SIGN.get()), ((WallSignBlock) ModBlocks.GINKO_WALL_SIGN.get()),
        blockTexture(ModBlocks.GINKO_PLANKS.get()));

        hangingSignBlock(ModBlocks.GINKO_HANGING_SIGN.get(), ModBlocks.GINKO_WALL_HANGING_SIGN.get(), blockTexture(ModBlocks.GINKO_PLANKS.get()));


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
