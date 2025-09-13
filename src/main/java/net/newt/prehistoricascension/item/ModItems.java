package net.newt.prehistoricascension.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.newt.prehistoricascension.PrehistoricAscension;
import net.newt.prehistoricascension.block.ModBlocks;
import net.newt.prehistoricascension.block.custom.GinkgoKnucklesItem;
import net.newt.prehistoricascension.entity.custom.ModBoatEntity;
import net.newt.prehistoricascension.item.custom.ModBoatItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PrehistoricAscension.MOD_ID);

    public static final RegistryObject<Item> BALAUR_SKULL_FOSSIL = ITEMS.register("balaur_skull_fossil",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRIOCERATITIES_SHELL = ITEMS.register("crioceratities_shell",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SQUID = ITEMS.register("raw_squid",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_SQUID)));
    public static final RegistryObject<Item> COOKED_SQUID = ITEMS.register("cooked_squid",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_SQUID)));
    public static final RegistryObject<Item> CRIOCERATITIES_DNA = ITEMS.register("crioceratities_dna",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_FOSSIL = ITEMS.register("deepslate_fossil",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GINKGO_SIGN = ITEMS.register("ginkgo_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.GINKGO_SIGN.get(), ModBlocks.GINKGO_WALL_SIGN.get()));
    public static final RegistryObject<Item> GINKGO_HANGING_SIGN = ITEMS.register("ginkgo_hanging_sign",
                () -> new HangingSignItem(ModBlocks.GINKGO_HANGING_SIGN.get(), ModBlocks.GINKGO_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> GINKGO_KNUCKLES_ITEM = ITEMS.register("ginkgo_knuckles",
            () -> new GinkgoKnucklesItem(ModBlocks.GINKGO_KNUCKLES.get(), new Item.Properties()));

//    public static final RegistryObject<Item> DUTCHMANS_PIPE_ITEM = ITEMS.register("dutchmans_pipe",
//    () -> new BlockItem(ModBlocks.DUTCHMANS_PIPE.get(), new Item.Properties()));

    public static final RegistryObject<Item> GINKGO_BOAT = ITEMS.register("ginkgo_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.GINKGO,new Item.Properties()));
    public static final RegistryObject<Item> GINKGO_CHEST_BOAT = ITEMS.register("ginkgo_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.GINKGO,new Item.Properties()));

    public static final RegistryObject<Item> BALAUR_DNA = ITEMS.register("balaur_dna",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BALAUR_EGG = ITEMS.register("balaur_egg",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
