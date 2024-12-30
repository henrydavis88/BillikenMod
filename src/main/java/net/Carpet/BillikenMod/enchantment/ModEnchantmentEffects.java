package net.Carpet.BillikenMod.enchantment;

import com.mojang.serialization.MapCodec;
import net.Carpet.BillikenMod.BillikenMod;
import net.Carpet.BillikenMod.enchantment.custom.BillikenBountyEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentLocationBasedEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentLocationBasedEffect>> LOCATION_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_LOCATION_BASED_EFFECT_TYPE, BillikenMod.MOD_ID);

    public static final RegistryObject<MapCodec<? extends EnchantmentLocationBasedEffect>> BILLIKEN_BOUNTY =
            LOCATION_ENCHANTMENT_EFFECTS.register("billiken_bounty", () -> BillikenBountyEnchantmentEffect.CODEC);


    public static void register(IEventBus eventBus) {
        LOCATION_ENCHANTMENT_EFFECTS.register(eventBus);
    }
}
