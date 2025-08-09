package net.newt.prehistoricascension.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.newt.prehistoricascension.PrehistoricAscension;
import net.newt.prehistoricascension.block.ModBlocks;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PrehistoricAscension.MOD_ID);

    public static final RegistryObject<CreativeModeTab> PA_ITEMS = CREATIVE_MODE_TABS.register("pa_items",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BALAUR_SKULL_FOSSIL.get()))
                    .title(Component.translatable("creativetab.pa_items"))
                    .displayItems((pParameters, pOutput) -> {
                    pOutput.accept(ModBlocks.DEEPSLATE_FOSSIL_BLOCK.get());

                    pOutput.accept(ModItems.DEEPSLATE_FOSSIL.get());
                    pOutput.accept(ModItems.BALAUR_SKULL_FOSSIL.get());
                    pOutput.accept(ModItems.CRIOCERATITIES_DNA.get());
                    pOutput.accept(ModItems.RAW_SQUID.get());
                    pOutput.accept(ModItems.COOKED_SQUID.get());
                    pOutput.accept(ModItems.CRIOCERATITIES_SHELL.get());

                    pOutput.accept(ModBlocks.GINKGO_LOG.get());
                    pOutput.accept(ModBlocks.GINKGO_WOOD.get());
                    pOutput.accept(ModBlocks.STRIPPED_GINKGO_LOG.get());
                    pOutput.accept(ModBlocks.STRIPPED_GINKGO_WOOD.get());
                    pOutput.accept(ModBlocks.GINKGO_PLANKS.get());
                    pOutput.accept(ModBlocks.GINKGO_STAIRS.get());
                    pOutput.accept(ModBlocks.GINKGO_SLAB.get());
                    pOutput.accept(ModBlocks.GINKGO_FENCE.get());
                    pOutput.accept(ModBlocks.GINKGO_FENCE_GATE.get());
                    pOutput.accept(ModBlocks.GINKGO_DOOR.get());
                    pOutput.accept(ModBlocks.GINKGO_TRAPDOOR.get());
                    pOutput.accept(ModBlocks.GINKGO_PRESSURE_PLATE.get());
                    pOutput.accept(ModBlocks.GINKGO_BUTTON.get());
                    pOutput.accept(ModBlocks.GINKGO_SIGN.get());
                    pOutput.accept(ModBlocks.GINKGO_HANGING_SIGN.get());
                    pOutput.accept(ModItems.GINKGO_KNUCKLES_ITEM.get());
                    pOutput.accept(ModItems.GINKGO_BOAT.get());
                    pOutput.accept(ModItems.GINKGO_CHEST_BOAT.get());
                    pOutput.accept(ModBlocks.GINKGO_SAPLING.get());
                    pOutput.accept(ModBlocks.ANCIENT_GINKGO_SAPLING.get());

                    pOutput.accept(ModBlocks.GINKGO_LEAVES.get());

                    pOutput.accept(ModBlocks.CLUSTERED_BROWN_MUSHROOM.get());




                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
