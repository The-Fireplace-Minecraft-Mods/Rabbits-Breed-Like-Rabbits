package the_fireplace.rblr;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import the_fireplace.rblr.events.ForgeEvents;

@Mod(modid=RBLR.MODID, name=RBLR.MODNAME, version=RBLR.VERSION)
public class RBLR {
	public static final String MODID = "rblr";
	public static final String MODNAME = "Rabbits Breed Like Rabbits";
	public static final String VERSION = "2.0.0.1";
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		MinecraftForge.EVENT_BUS.register(new ForgeEvents());
	}
}
