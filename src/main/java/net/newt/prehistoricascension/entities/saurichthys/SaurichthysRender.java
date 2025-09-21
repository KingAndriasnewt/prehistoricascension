package net.newt.prehistoricascension.entities.saurichthys;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SaurichthysRender extends GeoEntityRenderer<Saurichthys> {

    public SaurichthysRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SaurichthysModel());
    }

}