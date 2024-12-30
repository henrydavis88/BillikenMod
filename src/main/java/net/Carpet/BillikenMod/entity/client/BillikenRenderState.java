package net.Carpet.BillikenMod.entity.client;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class BillikenRenderState extends LivingEntityRenderState {

    public final AnimationState idleAnimationState = new AnimationState();
    public BillikenRenderState() {

    }
}

