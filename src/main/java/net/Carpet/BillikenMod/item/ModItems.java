package net.Carpet.BillikenMod.item;

import net.Carpet.BillikenMod.BillikenMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BillikenMod.MOD_ID);

    public static final RegistryObject<Item> TUITION = ITEMS.register("tuition",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("billikenmod:tuition")))));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
