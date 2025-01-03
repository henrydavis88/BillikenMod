package net.Carpet.BillikenMod.entity.custom;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.mojang.logging.LogUtils;
import net.Carpet.BillikenMod.BillikenMod;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minidev.json.parser.JSONParser;
import org.slf4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

@Mod.EventBusSubscriber(modid = BillikenMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BillikenCraftsAssembler {



    public BillikenCraftsAssembler() {
        LOGGER.atInfo().log("starting assembly");
        final IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addListener(this::jsonReader);

    }

    private static Logger LOGGER = LogUtils.getLogger();

    public static List<BillikenCrafting> recipes = new ArrayList<>();



    private void jsonReader (AddReloadListenerEvent event) {
        LOGGER.atInfo().log("starting to read");


        Gson gson = new Gson();
        JsonObject craftingRecipesFile = null;

        try {

            craftingRecipesFile = gson.fromJson(new FileReader("billikenmod:billiken_crafting/billiken_crafts.json"), JsonObject.class);
            JsonArray recipeList = craftingRecipesFile.get("recipes").getAsJsonArray();
            for (int i = 0; i < recipeList.size(); i++) {
                JsonObject currentRecipe = recipeList.get(i).getAsJsonObject();
                Item startingItem = ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(currentRecipe.get("starting_item").getAsString()));
                Item endingItem = ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(currentRecipe.get("ending_item").getAsString()));
                Integer amount = currentRecipe.get("amount").getAsInt();
                Integer levels = currentRecipe.get("levels").getAsInt();
                recipes.add(new BillikenCrafting(startingItem, endingItem, amount, levels));
                LOGGER.atInfo().log("hello");
            }

        } catch (FileNotFoundException e) {
            LOGGER.atInfo().log("no files");
            throw new RuntimeException(e);
        }
    }

    public static List<BillikenCrafting> jsonReaderTwo() {
        LOGGER.atInfo().log("starting to read");

        ResourceLocation location = ResourceLocation.fromNamespaceAndPath("billikenmod","billiken_crafting/billiken_crafts.json");

        String path = BillikenCraftsAssembler.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        path = path + "resources\\data\\billikenmod\\billiken_crafting\\billiken_crafts.json";
        String sep = "\\";
        path = path.replace("build/","");
        path = path.replaceAll("/", Matcher.quoteReplacement(sep));
        path = path.replaceAll("sourcesSets", "src");
        LOGGER.atInfo().log(path);




        Gson gson = new Gson();
        JsonObject craftingRecipesFile = null;
        recipes.clear();

        try {
            craftingRecipesFile = gson.fromJson(new FileReader(path), JsonObject.class);
            JsonArray recipeList = craftingRecipesFile.get("recipes").getAsJsonArray();
            for (int i = 0; i < recipeList.size(); i++) {
                JsonObject currentRecipe = recipeList.get(i).getAsJsonObject();
                Item startingItem = ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(currentRecipe.get("starting_item").getAsString()));
                Item endingItem = ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(currentRecipe.get("ending_item").getAsString()));
                Integer amount = currentRecipe.get("amount").getAsInt();
                Integer levels = currentRecipe.get("levels").getAsInt();
                recipes.add(new BillikenCrafting(startingItem, endingItem, amount, levels));
            }
            LOGGER.atInfo().log(String.valueOf(recipes.size()));
            return (recipes);

        } catch (FileNotFoundException e) {
            LOGGER.atInfo().log("no files");
            throw new RuntimeException(e);
        }


    }


}
