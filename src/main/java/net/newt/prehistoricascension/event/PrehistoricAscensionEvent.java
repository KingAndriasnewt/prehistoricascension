package net.newt.prehistoricascension.event;

import net.newt.prehistoricascension.PrehistoricAscension;
import net.newt.prehistoricascension.entities.EntityTypes;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.newt.prehistoricascension.entities.balaur.Balaur;
import net.newt.prehistoricascension.entities.balaur.BalaurRender;
import net.newt.prehistoricascension.entities.saurichthys.Saurichthys;
import net.newt.prehistoricascension.entities.saurichthys.SaurichthysRender;
import net.newt.prehistoricascension.entities.thyreosaurus.Thyreosaurus;
import net.newt.prehistoricascension.entities.thyreosaurus.ThyreosaurusRender;

@Mod.EventBusSubscriber(modid = PrehistoricAscension.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PrehistoricAscensionEvent {

    @SubscribeEvent
    public static void entityAttrbiuteCreationEvent(EntityAttributeCreationEvent event) {
        event.put(EntityTypes.SAURICHTHYS_ENTITY.get(), Saurichthys.createAttributes().build());
        event.put(EntityTypes.BALAUR_ENTITY.get(), Balaur.createAttributes().build());
        event.put(EntityTypes.THYREOSAURUS_ENTITY.get(), Thyreosaurus.createAttributes().build());

    }

    @SubscribeEvent
    public static void clientSetupEvent(FMLClientSetupEvent event) {
        EntityRenderers.register(EntityTypes.SAURICHTHYS_ENTITY.get(), SaurichthysRender::new);
        EntityRenderers.register(EntityTypes.BALAUR_ENTITY.get(), BalaurRender::new);
        EntityRenderers.register(EntityTypes.THYREOSAURUS_ENTITY.get(), ThyreosaurusRender::new);

    }
}