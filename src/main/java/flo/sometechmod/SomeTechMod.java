package flo.sometechmod;

import flo.sometechmod.registry.*;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SomeTechMod implements ModInitializer
{
	public static final String MOD_ID = "sometechmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{
		ModItems.register();
		ModBlocks.register();

		ModBlockEntities.register();
		ModScreens.register();

		ModItemGroups.register();
	}
}
