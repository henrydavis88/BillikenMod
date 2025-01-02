package net.Carpet.BillikenMod.entity.custom;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minidev.json.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class BillikenCraftsAssembler {

    public BillikenCraftsAssembler() {
        final IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addListener(this::jsonReader);

    }

    public static List<BillikenCrafting> recipes = new ArrayList<>();

    private void jsonReader (AddReloadListenerEvent event) {

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
                System.out.println(recipes);
            }

        } catch (FileNotFoundException e) {
            System.out.println("no file");
            throw new RuntimeException(e);
        }
    }
}
