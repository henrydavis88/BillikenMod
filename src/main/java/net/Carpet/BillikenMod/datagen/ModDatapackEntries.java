package net.Carpet.BillikenMod.datagen;

import net.Carpet.BillikenMod.BillikenMod;
import net.Carpet.BillikenMod.enchantment.ModEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackEntries extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder().add(Registries.ENCHANTMENT, ModEnchantments::bootstrap);

    public ModDatapackEntries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(BillikenMod.MOD_ID));
    }
}
