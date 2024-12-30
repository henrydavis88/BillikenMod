package net.Carpet.BillikenMod.events;

import net.Carpet.BillikenMod.entity.ModEntities;
import net.Carpet.BillikenMod.entity.client.BillikenModel;
import net.Carpet.BillikenMod.entity.custom.BillikenEntity;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.Carpet.BillikenMod.BillikenMod;

@Mod.EventBusSubscriber(modid = BillikenMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BillikenModel.LAYER_LOCATION, BillikenModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.BILLIKEN.get(), BillikenEntity.createAttributes().build());
    }
}
