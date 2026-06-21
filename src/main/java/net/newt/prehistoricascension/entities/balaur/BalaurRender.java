package net.newt.prehistoricascension.entities.balaur;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.newt.prehistoricascension.entities.balaur.Balaur;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BalaurRender extends GeoEntityRenderer<Balaur> {

    public BalaurRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BalaurModel());
    }
}