package net.Carpet.BillikenMod.entity.client;

import net.Carpet.BillikenMod.BillikenMod;
import net.Carpet.BillikenMod.entity.custom.BillikenEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class BillikenRenderer extends MobRenderer<BillikenEntity, BillikenRenderState, BillikenModel> {

    private static final ResourceLocation BILLIKEN_LOCATION = ResourceLocation.fromNamespaceAndPath("billikenmod","textures/entity/billiken/billiken.png");

    public BillikenRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BillikenModel(pContext.bakeLayer(BillikenModel.LAYER_LOCATION)),0.85f);
    }

    public ResourceLocation getTextureLocation(BillikenRenderState state) {
        return BILLIKEN_LOCATION;
    }

    public BillikenRenderState createRenderState() {
        return new BillikenRenderState();
    }




}
