package net.Carpet.BillikenMod.entity;

import net.Carpet.BillikenMod.BillikenMod;
import net.Carpet.BillikenMod.entity.custom.BillikenEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BillikenMod.MOD_ID);

    public static final RegistryObject<EntityType<BillikenEntity>> BILLIKEN =
            ENTITY_TYPES.register("billiken", () -> EntityType.Builder.of(BillikenEntity::new, MobCategory.CREATURE)
                    .sized(1.5f, 1.5f)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("billikenmod:billiken"))));

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}
