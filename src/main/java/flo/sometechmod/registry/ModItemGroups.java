package flo.sometechmod.registry;

import flo.sometechmod.SomeTechMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups
{
    private static final ItemGroup ITEM_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(SomeTechMod.MOD_ID, "sometechmod_group"), FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.TIN_INGOT))
            .displayName(Text.translatable("itemgroup.sometechmod_group"))
            .entries((displayContext, entries) ->
            {
                entries.add(ModItems.TIN_INGOT);
                entries.add(ModItems.RAW_TIN);
                entries.add(ModBlocks.TIN_ORE);
                entries.add(ModBlocks.DEEPSLATE_TIN_ORE);

                entries.add(ModBlocks.CRUSHER_BLOCK);
            })
            .build());

    public static void register()
    {
        SomeTechMod.LOGGER.info("Registering Mod Item Group from " + SomeTechMod.MOD_ID);
    }
}
