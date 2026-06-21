package net.newt.prehistoricascension.entities;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.newt.prehistoricascension.entities.balaur.Balaur;
import net.newt.prehistoricascension.entities.balaur.BalaurRender;
import net.newt.prehistoricascension.entities.saurichthys.Saurichthys;
import net.newt.prehistoricascension.entities.thyreosaurus.Thyreosaurus;

import static net.newt.prehistoricascension.PrehistoricAscension.MOD_ID;

public class EntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MOD_ID);

    public static final RegistryObject<EntityType<Saurichthys>> SAURICHTHYS_ENTITY = ENTITY_TYPES.register("saurichthys",
            () -> EntityType.Builder.of(Saurichthys::new,
                            MobCategory.WATER_AMBIENT)
                    .sized(0.7f, 0.3f)
                    .build(new ResourceLocation(MOD_ID,"saurichthys").toString()));

    public static final RegistryObject<EntityType<Balaur>> BALAUR_ENTITY = ENTITY_TYPES.register("balaur",
            () -> EntityType.Builder.of(Balaur::new,
                            MobCategory.CREATURE)
                    .sized(0.7f, 0.3f)
                    .build(new ResourceLocation(MOD_ID,"balaur").toString()));

    public static final RegistryObject<EntityType<Thyreosaurus>> THYREOSAURUS_ENTITY = ENTITY_TYPES.register("thyreosaurus",
            () -> EntityType.Builder.of(Thyreosaurus::new,
                            MobCategory.CREATURE)
                    .sized(1.5f, 2f)
                    .build(new ResourceLocation(MOD_ID,"thyreosaurus").toString()));

}