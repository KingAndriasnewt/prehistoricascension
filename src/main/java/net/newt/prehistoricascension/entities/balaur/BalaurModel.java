
package net.newt.prehistoricascension.entities.balaur;

import net.minecraft.resources.ResourceLocation;
import net.newt.prehistoricascension.PrehistoricAscension;
import software.bernie.geckolib.model.GeoModel;


public class BalaurModel extends GeoModel<Balaur> {

    public enum Variant {
        FORESTM(new ResourceLocation(PrehistoricAscension.MOD_ID, "textures/entity/balaur/balaur_forestm.png")),
        FORESTF(new ResourceLocation(PrehistoricAscension.MOD_ID, "textures/entity/balaur/balaur_forestf.png")),
        GROWTHM(new ResourceLocation(PrehistoricAscension.MOD_ID, "textures/entity/balaur/balaur_growthm.png")),
        GROWTHF(new ResourceLocation(PrehistoricAscension.MOD_ID, "textures/entity/balaur/balaur_growthf.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }


    public static final ResourceLocation MODEL = new ResourceLocation(PrehistoricAscension.MOD_ID, "geo/balaur/balaur.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PrehistoricAscension.MOD_ID, "animations/balaur/balaur.animation.json");

    @Override
    public ResourceLocation getModelResource(Balaur object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Balaur object) {
        return object.getTextureResource();
    }


    @Override
    public ResourceLocation getAnimationResource(Balaur animatable) {
        return ANIMATION;
    }
}
