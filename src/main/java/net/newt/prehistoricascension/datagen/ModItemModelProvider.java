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
        simpleItem(ModItems.RAW_SAURICHTHYS);
        simpleItem(ModItems.COOKED_SAURICHTHYS);

        simpleItem(ModItems.CRIOCERATITIES_DNA);
        simpleItem(ModItems.BALAUR_DNA);
        simpleItem(ModItems.SAURICHTHYS_DNA);

        simpleItem(ModItems.BALAUR_EGG);

        fenceItem(ModBlocks.GINKGO_FENCE, ModBlocks.GINKGO_PLANKS);
        buttonItem(ModBlocks.GINKGO_BUTTON, ModBlocks.GINKGO_PLANKS);

        evenSimplerBlockItem(ModBlocks.GINKGO_STAIRS);
        evenSimplerBlockItem(ModBlocks.GINKGO_SLAB);
        evenSimplerBlockItem(ModBlocks.GINKGO_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.GINKGO_FENCE_GATE);

        simpleBlockItem(ModBlocks.GINKGO_DOOR);
        trapdoorItem(ModBlocks.GINKGO_TRAPDOOR);


        simpleItem(ModItems.GINKGO_SIGN);
        simpleItem(ModItems.GINKGO_HANGING_SIGN);


        simpleItem(ModItems.GINKGO_BOAT);
        simpleItem(ModItems.GINKGO_CHEST_BOAT);

        saplingItem(ModBlocks.GINKGO_SAPLING);
        saplingItem(ModBlocks.ANCIENT_GINKGO_SAPLING);

        simpleBlockItemBlockTexture(ModBlocks.CLUSTERED_BROWN_MUSHROOM);
        simpleBlockItemBlockTexture(ModBlocks.WEEPING_TAN_MUSHROOM);
        simpleBlockItemBlockTexture(ModBlocks.GREEN_BELL_MUSHROOM);
        simpleBlockItemBlockTexture(ModBlocks.ORANGE_TOP_MUSHROOM);
        simpleBlockItemBlockTexture(ModBlocks.PURPLE_TOP_HAT_MUSHROOM);

        simpleBlockItemBlockTexture(ModBlocks.HORSETAILS);

        simpleItem(ModItems.BUCKET_OF_SAURICHTHYS);


    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PrehistoricAscension.MOD_ID,"block/" + item.getId().getPath()));
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

    private ItemModelBuilder simpleBlockItemBlockTexture(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PrehistoricAscension.MOD_ID,"block/" + item.getId().getPath()));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(PrehistoricAscension.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }


}