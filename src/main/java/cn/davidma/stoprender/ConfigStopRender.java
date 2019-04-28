package cn.davidma.stoprender;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;

@Config(modid = StopRender.MOD_ID)
public class ConfigStopRender {
	
	@Comment({
		"The full class name for tile entities whose TESR is to be removed.",
		"E.g. \"net.minecraft.tileentity.TileEntityChest\" for chest."
	})
	public static String[] TILE_ENTITIES = {};
}
