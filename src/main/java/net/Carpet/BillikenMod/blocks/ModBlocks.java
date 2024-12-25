package net.Carpet.BillikenMod.blocks;

import net.Carpet.BillikenMod.BillikenMod;
import net.Carpet.BillikenMod.blocks.custom.BillikenBlock;
import net.Carpet.BillikenMod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BillikenMod.MOD_ID);

    public static final RegistryObject<Block> TUITION_BLOCK = registerBlock("tuition_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.CROP).setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.parse("billikenmod:tuition_block")))));

    public static final RegistryObject<Block> BILLIKEN_BLOCK = registerBlock("billiken_block",
            () -> new BillikenBlock(BlockBehaviour.Properties.of()
                    .strength(4f).sound(SoundType.ANCIENT_DEBRIS).setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.parse("billikenmod:billiken_block")))));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        String resourceLocation = "billikenmod:" + name;
        registerBlockItems(name, toReturn, resourceLocation);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItems(String name, RegistryObject<T> block, String resourceLocation) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse(resourceLocation)))));
    }



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
