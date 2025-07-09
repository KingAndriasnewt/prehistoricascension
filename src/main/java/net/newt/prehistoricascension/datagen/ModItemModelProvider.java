package net.newt.prehistoricascension.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.newt.prehistoricascension.PrehistoricAscension;
import net.newt.prehistoricascension.block.ModBlocks;
import net.newt.prehistoricascension.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PrehistoricAscension.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.BALAUR_SKULL_FOSSIL);
        simpleItem(ModItems.DEEPSLATE_FOSSIL);
        simpleItem(ModItems.CRIOCERATITIES_SHELL);
        simpleItem(ModItems.RAW_SQUID);
        simpleItem(ModItems.COOKED_SQUID);
        simpleItem(ModItems.CRIOCERATITIES_DNA);

        fenceItem(ModBlocks.GINKO_FENCE, ModBlocks.GINKO_PLANKS);
        buttonItem(ModBlocks.GINKO_BUTTON, ModBlocks.GINKO_PLANKS);

        evenSimplerBlockItem(ModBlocks.GINKO_STAIRS);
        evenSimplerBlockItem(ModBlocks.GINKO_SLAB);
        evenSimplerBlockItem(ModBlocks.GINKO_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.GINKO_FENCE_GATE);

        simpleBlockItem(ModBlocks.GINKO_DOOR);
        trapdoorItem(ModBlocks.GINKO_TRAPDOOR);


        simpleItem(ModItems.GINKO_SIGN);
        simpleItem(ModItems.GINKO_HANGING_SIGN);

    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(PrehistoricAscension.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(PrehistoricAscension.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PrehistoricAscension.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PrehistoricAscension.MOD_ID,"item/" + item.getId().getPath()));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(PrehistoricAscension.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }


}