package net.newt.prehistoricascension.worldgen.tree;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.newt.prehistoricascension.PrehistoricAscension;
import net.newt.prehistoricascension.worldgen.tree.custom.AncientGinkgoTrunkPlacer;

public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, PrehistoricAscension.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<AncientGinkgoTrunkPlacer>> ANCIENT_GINKGO_TRUNK_PLACER =
            TRUNK_PLACER.register("ancient_ginkgo_trunk_placer", () -> new TrunkPlacerType<>(AncientGinkgoTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus){
        TRUNK_PLACER.register(eventBus);
    }

}
