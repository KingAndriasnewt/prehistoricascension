
package net.newt.prehistoricascension.entities.saurichthys;

import net.minecraft.resources.ResourceLocation;
import net.newt.prehistoricascension.PrehistoricAscension;
import software.bernie.geckolib.model.GeoModel;


public class SaurichthysModel extends GeoModel<Saurichthys> {

    public enum Variant {
        V1(new ResourceLocation(PrehistoricAscension.MOD_ID, "textures/entity/saurichthys/saurichthys_v1.png")),
        V2(new ResourceLocation(PrehistoricAscension.MOD_ID, "textures/entity/saurichthys/saurichthys_v2.png")),
        V3(new ResourceLocation(PrehistoricAscension.MOD_ID, "textures/entity/saurichthys/saurichthys_v3.png")),
        V4(new ResourceLocation(PrehistoricAscension.MOD_ID, "textures/entity/saurichthys/saurichthys_v4.png")),
        V5(new ResourceLocation(PrehistoricAscension.MOD_ID, "textures/entity/saurichthys/saurichthys_v5.png")),
        V6(new ResourceLocation(PrehistoricAscension.MOD_ID, "textures/entity/saurichthys/saurichthys_v6.png")),
        V7(new ResourceLocation(PrehistoricAscension.MOD_ID, "textures/entity/saurichthys/saurichthys_v7.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }


    public static final ResourceLocation MODEL = new ResourceLocation(PrehistoricAscension.MOD_ID, "geo/saurichthys/saurichthys.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PrehistoricAscension.MOD_ID, "animations/saurichthys/saurichthys.animation.json");

    @Override
    public ResourceLocation getModelResource(Saurichthys object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Saurichthys object) {
        return object.getTextureResource();
    }


    @Override
    public ResourceLocation getAnimationResource(Saurichthys animatable) {
        return ANIMATION;
    }
}
