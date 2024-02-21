package flo.sometechmod.datagen;

import flo.sometechmod.registry.ModBlocks;
import flo.sometechmod.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider
{
    public ModModelProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator)
    {
        // SimpleCubeAll for when all faces share the same texture.
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TIN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_TIN_ORE);

        // WIP
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRUSHER_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator)
    {
        itemModelGenerator.register(ModItems.TIN_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_TIN, Models.GENERATED);
    }
}
