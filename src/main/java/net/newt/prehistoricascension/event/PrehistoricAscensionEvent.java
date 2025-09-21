package net.newt.prehistoricascension.event;

import net.newt.prehistoricascension.PrehistoricAscension;
import net.newt.prehistoricascension.entities.EntityTypes;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.newt.prehistoricascension.entities.saurichthys.Saurichthys;
import net.newt.prehistoricascension.entities.saurichthys.SaurichthysRender;

@Mod.EventBusSubscriber(modid = PrehistoricAscension.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PrehistoricAscensionEvent {

    @SubscribeEvent
    public static void entityAttrbiuteCreationEvent(EntityAttributeCreationEvent event) {
        event.put(EntityTypes.SAURICHTHYS_ENTITY.get(), Saurichthys.createAttributes().build());

    }

    @SubscribeEvent
    public static void clientSetupEvent(FMLClientSetupEvent event) {
        EntityRenderers.register(EntityTypes.SAURICHTHYS_ENTITY.get(), SaurichthysRender::new);

    }
}