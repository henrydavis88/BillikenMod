package net.Carpet.BillikenMod.entity.custom;

import net.Carpet.BillikenMod.blocks.ModBlocks;
import net.Carpet.BillikenMod.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class BillikenEntity extends Animal {

    public final AnimationState idleAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;

    public BillikenEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0,new FloatGoal(this));
        this.goalSelector.addGoal(1,new PanicGoal(this,3.0));
        this.goalSelector.addGoal(2,new TemptGoal(this,1.5,stack -> stack.is(ModBlocks.TUITION_BLOCK.get().asItem()), false));
        this.goalSelector.addGoal(3,new TemptGoal(this,1.5,stack -> stack.is(ModItems.TUITION.get()), false));
        this.goalSelector.addGoal(4,new LookAtPlayerGoal(this, Player.class, 40.0f));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH,30).add(Attributes.MOVEMENT_SPEED, 4).add(Attributes.FOLLOW_RANGE, 25);
    }


    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }


    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <=0) {
            this.idleAnimationTimeout = 35;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }
}
