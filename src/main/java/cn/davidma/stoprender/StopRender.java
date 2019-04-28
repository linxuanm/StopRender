package cn.davidma.stoprender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = StopRender.MOD_ID, name = StopRender.NAME, version = StopRender.VERSION, clientSideOnly = true)
public class StopRender {
	
	public static final String MOD_ID = "stoprender";
	public static final String NAME = "Stop Render";
	public static final String VERSION = "1.0.0";
	
	@Instance
	public static StopRender instance;
	
	public static Logger logger;
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		logger = LogManager.getLogger();
		
		for (String className: ConfigStopRender.TILE_ENTITIES) {
			unbindTESR(className);
		}
	}
	
	private static void unbindTESR(String className) {
		try {
			unbindTESR(Class.forName(className));
		} catch (ClassNotFoundException e) {
			logger.warn(String.format("%s is not found. Skipping.", className));
		}
	}
	
	private static void unbindTESR(Class<?> tileClass) {
		logger.debug(String.format("Removing TESR for %s.", tileClass.toString()));
		TileEntitySpecialRenderer renderer = TileEntityRendererDispatcher.instance.renderers.get(tileClass);
		TileEntityRendererDispatcher.instance.renderers.remove(tileClass);
		renderer.setRendererDispatcher(null);
	}
}
