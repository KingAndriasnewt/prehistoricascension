package net.newt.prehistoricascension.entity.client;

import net.minecraftforge.event.level.PistonEvent;
import net.newt.prehistoricascension.PrehistoricAscension;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {

    public static final ModelLayerLocation GINKGO_BOAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(PrehistoricAscension.MOD_ID, "boat/pine"), "main");
    public static final ModelLayerLocation GINKGO_CHEST_BOAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(PrehistoricAscension.MOD_ID, "chest_boat/pine"), "main");

}