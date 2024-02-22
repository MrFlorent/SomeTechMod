package flo.sometechmod.registry;

import flo.sometechmod.SomeTechMod;
import flo.sometechmod.blockentity.screen.CrusherScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreens
{
    public static final ScreenHandlerType<CrusherScreenHandler> CRUSHER_SCREEN_HANDLER = Registry.register(
            Registries.SCREEN_HANDLER,
            new Identifier(SomeTechMod.MOD_ID, "crusher_screen_handler"),
            new ExtendedScreenHandlerType<>(CrusherScreenHandler::new));

    public static void register()
    {
        SomeTechMod.LOGGER.info("Registering Mod Screens from " + SomeTechMod.MOD_ID);
    }
}
