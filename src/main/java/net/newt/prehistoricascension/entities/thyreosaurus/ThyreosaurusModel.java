
package net.newt.prehistoricascension.entities.thyreosaurus;

import net.minecraft.resources.ResourceLocation;
import net.newt.prehistoricascension.PrehistoricAscension;
import net.newt.prehistoricascension.entities.balaur.Balaur;
import software.bernie.geckolib.model.GeoModel;


public class ThyreosaurusModel extends GeoModel<Thyreosaurus> {

    public enum Variant {
        MALE(new ResourceLocation(PrehistoricAscension.MOD_ID, "textures/entity/thyreosaurus/thyreosaurus_male.png")),
        FEMALE(new ResourceLocation(PrehistoricAscension.MOD_ID, "textures/entity/thyreosaurus/thyreosaurus_female.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }


    public static final ResourceLocation MODEL = new ResourceLocation(PrehistoricAscension.MOD_ID, "geo/thyreosaurus/thyreosaurus.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PrehistoricAscension.MOD_ID, "animations/thyreosaurus/thyreosaurus.animation.json");

    @Override
    public ResourceLocation getModelResource(Thyreosaurus object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Thyreosaurus object) {
        return object.getTextureResource();
    }


    @Override
    public ResourceLocation getAnimationResource(Thyreosaurus animatable) {
        return ANIMATION;
    }
}
