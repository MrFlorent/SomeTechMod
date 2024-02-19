package flo.sometechmod.registry;

import flo.sometechmod.SomeTechMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems
{
    // Instances
    public static final Item TIN_INGOT = registerItem("tin_ingot", new Item(new FabricItemSettings()));
    public static final Item RAW_TIN = registerItem("raw_tin", new Item(new FabricItemSettings()));

    // Item Registering
    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, new Identifier(SomeTechMod.MOD_ID, name), item);
    }

    public static void registerModItems()
    {
        SomeTechMod.LOGGER.info("Registering Mod Items from " + SomeTechMod.MOD_ID);
    }
}
