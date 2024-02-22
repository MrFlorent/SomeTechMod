package flo.sometechmod;

import flo.sometechmod.blockentity.screen.CrusherScreen;
import flo.sometechmod.registry.ModScreens;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class SomeTechModClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        HandledScreens.register(ModScreens.CRUSHER_SCREEN_HANDLER, CrusherScreen::new);
    }
}
