package net.newt.prehistoricascension.entities.thyreosaurus;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ThyreosaurusRender extends GeoEntityRenderer<Thyreosaurus> {

    public ThyreosaurusRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ThyreosaurusModel());
    }
}