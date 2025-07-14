package net.newt.prehistoricascension.block;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.newt.prehistoricascension.PrehistoricAscension;
import net.newt.prehistoricascension.block.custom.*;
import net.newt.prehistoricascension.item.ModItems;
import net.newt.prehistoricascension.util.ModWoodTypes;
import net.newt.prehistoricascension.worldgen.tree.AncientGinkgoTreeGrower;
import net.newt.prehistoricascension.worldgen.tree.GinkgoTreeGrower;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PrehistoricAscension.MOD_ID);

        public static final RegistryObject<Block> GINKGO_LOG = registerBlock("ginkgo_log",
                () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LOG).strength(3f)));
    public static final RegistryObject<Block> GINKGO_WOOD = registerBlock("ginkgo_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WOOD).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_GINKGO_LOG = registerBlock("stripped_ginkgo_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_SPRUCE_LOG).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_GINKGO_WOOD = registerBlock("stripped_ginkgo_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_SPRUCE_WOOD).strength(3f)));
    public static final RegistryObject<Block> GINKGO_PLANKS = registerBlock("ginkgo_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SPRUCE_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> GINKGO_LEAVES = registerBlock("ginkgo_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> GINKGO_STAIRS = registerBlock("ginkgo_stairs",
            () -> new StairBlock(() -> ModBlocks.GINKGO_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.SPRUCE_STAIRS)));
    public static final RegistryObject<Block> GINKGO_SLAB = registerBlock("ginkgo_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_SLAB)));

    public static final RegistryObject<Block> GINKGO_BUTTON = registerBlock("ginkgo_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_BUTTON),
                    BlockSetType.SPRUCE, 20, true));
    public static final RegistryObject<Block> GINKGO_PRESSURE_PLATE = registerBlock("ginkgo_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.SPRUCE_PRESSURE_PLATE),
                    BlockSetType.SPRUCE));

    public static final RegistryObject<Block> GINKGO_FENCE = registerBlock("ginkgo_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_FENCE)));
    public static final RegistryObject<Block> GINKGO_FENCE_GATE = registerBlock("ginkgo_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));

    public static final RegistryObject<Block> GINKGO_SIGN = BLOCKS.register("ginkgo_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_SIGN), ModWoodTypes.GINKGO));
    public static final RegistryObject<Block> GINKGO_WALL_SIGN = BLOCKS.register("ginkgo_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_SIGN), ModWoodTypes.GINKGO));

    public static final RegistryObject<Block> GINKGO_DOOR = registerBlock("ginkgo_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_DOOR).noOcclusion(), BlockSetType.SPRUCE));
    public static final RegistryObject<Block> GINKGO_TRAPDOOR = registerBlock("ginkgo_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_TRAPDOOR).noOcclusion(), BlockSetType.SPRUCE));

    public static final RegistryObject<Block> GINKGO_HANGING_SIGN = BLOCKS.register("ginkgo_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_HANGING_SIGN), ModWoodTypes.GINKGO));
    public static final RegistryObject<Block> GINKGO_WALL_HANGING_SIGN = BLOCKS.register("ginkgo_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_HANGING_SIGN), ModWoodTypes.GINKGO));

    public static final RegistryObject<Block> GINKGO_KNUCKLES = BLOCKS.register("ginkgo_knuckles",
            () -> new GinkgoKnucklesBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_PLANKS).noOcclusion()));


    public static final RegistryObject<Block> DEEPSLATE_FOSSIL_BLOCK = registerBlock("deepslate_fossil_block",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(3f).requiresCorrectToolForDrops(), UniformInt.of(1, 1)));


    public static final RegistryObject<Block> GINKGO_SAPLING = registerBlock("ginkgo_sapling",
            () -> new SaplingBlock(new GinkgoTreeGrower(), BlockBehaviour.Properties.copy(Blocks.SPRUCE_SAPLING)));
    public static final RegistryObject<Block> ANCIENT_GINKGO_SAPLING = registerBlock("ancient_ginkgo_sapling",
            () -> new SaplingBlock(new AncientGinkgoTreeGrower(), BlockBehaviour.Properties.copy(Blocks.ACACIA_SAPLING)));

    public static final RegistryObject<Block> ANCIENT_GINKGO_LEAVES = registerBlock("ancient_ginkgo_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });




    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }




    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
