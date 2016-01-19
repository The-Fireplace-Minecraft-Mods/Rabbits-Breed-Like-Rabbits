package the_fireplace.rblr.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import the_fireplace.rblr.RBLR;

/**
 * @author The_Fireplace
 */
public class RBLRConfigGui extends GuiConfig {
	public RBLRConfigGui(GuiScreen parentScreen) {
		super(parentScreen, new ConfigElement(RBLR.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), RBLR.MODID, false,
				false, GuiConfig.getAbridgedConfigPath(RBLR.config.toString()));
	}
}
