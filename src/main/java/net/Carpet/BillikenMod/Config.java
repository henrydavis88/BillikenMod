package net.Carpet.BillikenMod;

import net.Carpet.BillikenMod.entity.custom.BillikenCrafting;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config API
@Mod.EventBusSubscriber(modid = BillikenMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.ConfigValue<Boolean> BILLIKEN_BLOCK_BREAK_KILLS = BUILDER
            .comment("Set true to punish those that dare break the Billiken Block")
            .define("punish",true);

    public static final ForgeConfigSpec.ConfigValue<Integer> BILLIKEN_TRADE_RESET = BUILDER
            .comment("How long should the cooldown of the Billiken Trading be?")
            .define("length in seconds: ", 300);

    public static final ForgeConfigSpec.ConfigValue<Integer> BLOCK_TUITION_RESET_AMOUNT = BUILDER
            .comment("How much should the Tuition Block reset the trading cooldown")
            .define("length in seconds: ", 300);

    public static final ForgeConfigSpec.ConfigValue<Integer> TUITION_RESET_AMOUNT = BUILDER
            .comment("How much should the Tuition Item reset the trading cooldown")
            .define("length in seconds: ", 30);


    static final ForgeConfigSpec SPEC = BUILDER.build();


    public static Boolean billikenKills;
    public static Integer billikenTradeReset;
    public static Integer tuitionTradeReset;
    public static Integer tuitionBlockReset;


    public static List<BillikenCrafting> recipes = new ArrayList<>() {
    };


    private static boolean validateItemName(final Object obj) {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(ResourceLocation.tryParse(itemName));
    }



    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {

        billikenKills = BILLIKEN_BLOCK_BREAK_KILLS.get();
        billikenTradeReset = BILLIKEN_TRADE_RESET.get();
        tuitionBlockReset = BLOCK_TUITION_RESET_AMOUNT.get();
        tuitionTradeReset = TUITION_RESET_AMOUNT.get();

    }
}
