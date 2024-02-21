package flo.sometechmod.registry;

import flo.sometechmod.SomeTechMod;
import flo.sometechmod.entity.CrusherBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities
{
    public static final BlockEntityType<CrusherBlockEntity> CRUSHER_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(SomeTechMod.MOD_ID, "crusher_block_entity"),
            FabricBlockEntityTypeBuilder.create(CrusherBlockEntity::new, ModBlocks.CRUSHER_BLOCK).build()
    );

    public static void register()
    {
        SomeTechMod.LOGGER.info("Registering Block Entities from " + SomeTechMod.MOD_ID);
    }
}
