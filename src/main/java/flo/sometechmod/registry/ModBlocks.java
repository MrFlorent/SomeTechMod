package flo.sometechmod.registry;

import flo.sometechmod.SomeTechMod;
import flo.sometechmod.block.CrusherBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks
{
    // Instances
    public static final Block TIN_ORE = registerBlock("tin_ore", new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE)));
    public static final Block DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore", new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE)));

    public static final Block CRUSHER_BLOCK = registerBlock("crusher_block", new CrusherBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));

    // Block Registering
    private static Block registerBlock(String name, Block block)
    {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(SomeTechMod.MOD_ID, name), block);
    }

    // BlockItem Registering
    private static Item registerBlockItem(String name, Block block)
    {
        return Registry.register(Registries.ITEM, new Identifier(SomeTechMod.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
    }

    public static void register()
    {
        SomeTechMod.LOGGER.info("Registering Blocks & Block Items from " + SomeTechMod.MOD_ID);
    }
}
