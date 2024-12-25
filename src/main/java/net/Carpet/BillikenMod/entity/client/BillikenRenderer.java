package net.Carpet.BillikenMod.entity.client;

import net.Carpet.BillikenMod.BillikenMod;
import net.Carpet.BillikenMod.entity.custom.BillikenEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class BillikenRenderer extends MobRenderer<BillikenEntity, BillikenModel<BillikenEntity>> {

    public BillikenRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BillikenModel<>(pContext.bakeLayer(BillikenModel.LAYER_LOCATION)),0.85f);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return ResourceLocation.fromNamespaceAndPath(BillikenMod.MOD_ID, "textures/billiken/billiken.png");
    }

}
