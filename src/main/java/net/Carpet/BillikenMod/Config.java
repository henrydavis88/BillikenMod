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

    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> BILLIKEN_CRAFTS_INPUT = BUILDER
            .comment("Add input for recipes for the Billiken Crafting.")
            .defineListAllowEmpty("input:", List.of("minecraft:golden_apple",
                    "minecraft:apple"), Config::validateItemName);

    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> BILLIKEN_CRAFTS_OUTPUT = BUILDER
            .comment("Add output of recipes for the Billiken Crafting.")
            .defineListAllowEmpty("output:", List.of("minecraft:enchanted_golden_apple",
                    "minecraft:golden_apple"), Config::validateItemName);

    private static final ForgeConfigSpec.ConfigValue<List<? extends Integer>> BILLIKEN_CRAFTS_NUMBER = BUILDER
            .comment("Add number of items output for recipes for the Billiken Crafting.")
            .define("number", List.of(1,
            1));

    private static final ForgeConfigSpec.ConfigValue<List<? extends Integer>> BILLIKEN_CRAFTS_LEVELS = BUILDER
            .comment("Add level cost for recipes for the Billiken Crafting.")
            .define("levels", List.of(10,
                    5));


    static final ForgeConfigSpec SPEC = BUILDER.build();


    public static List<Item> billikenInput;
    public static List<Item> billikenOutput;
    public static List<Integer> billikenNumber;
    public static List<Integer> billikenLevels;


    public static List<BillikenCrafting> recipes = new ArrayList<>() {
    };


    private static boolean validateItemName(final Object obj) {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(ResourceLocation.tryParse(itemName));
    }



    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {

        billikenInput = BILLIKEN_CRAFTS_INPUT.get().stream()
                .map(itemName -> ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(itemName)))
                .collect(Collectors.toList());
        billikenOutput = BILLIKEN_CRAFTS_OUTPUT.get().stream()
                .map(itemName -> ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(itemName)))
                .collect(Collectors.toList());
        billikenNumber = BILLIKEN_CRAFTS_NUMBER.get().stream().collect(Collectors.toList());
        billikenLevels = BILLIKEN_CRAFTS_LEVELS.get().stream().collect(Collectors.toList());

        if (billikenInput.size() == billikenOutput.size() && billikenLevels.size() == billikenNumber.size() && billikenInput.size() == billikenNumber.size()) {
            for (int i = 0; i < billikenInput.size(); i++) {
                recipes.add(new BillikenCrafting(billikenInput.get(i), billikenOutput.get(i), billikenNumber.get(i), billikenLevels.get(i)));
            }
        }

    }
}
