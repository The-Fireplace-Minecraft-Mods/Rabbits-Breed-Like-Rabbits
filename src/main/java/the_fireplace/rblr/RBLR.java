package the_fireplace.rblr;

import net.minecraft.entity.passive.EntityRabbit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import the_fireplace.rblr.config.ConfigValues;
import the_fireplace.rblr.events.CommonEvents;

@Mod(modid = RBLR.MODID, name = RBLR.MODNAME, guiFactory = "the_fireplace.rblr.config.RBLRGuiFactory", updateJSON = "https://bitbucket.org/The_Fireplace/minecraft-mod-updates/raw/master/rblr.json", acceptedMinecraftVersions = "[1.11,)", version = "${version}")
public class RBLR {
	public static final String MODID = "rblr";
	public static final String MODNAME = "Rabbits Breed Like Rabbits";
	public static Configuration config;

	public static Property RBLR_PROPERTY;
	public static Property REAF_PROPERTY;
	public static Property RJRH_PROPERTY;
	public static Property RJRF_PROPERTY;
	public static Property RGUQ_PROPERTY;
	public static Property RJOC_PROPERTY;
	public static Property LIMITER_PROPERTY;

	@SidedProxy(clientSide = "the_fireplace.rblr.client.ClientProxy", serverSide = "the_fireplace.rblr.CommonProxy")
	public static CommonProxy proxy;

	public static void syncConfig() {
		ConfigValues.RBLR = RBLR_PROPERTY.getBoolean();
		ConfigValues.REAF = REAF_PROPERTY.getBoolean();
		ConfigValues.RJRH = RJRH_PROPERTY.getBoolean();
		ConfigValues.RJRF = RJRF_PROPERTY.getBoolean();
		ConfigValues.RGUQ = RGUQ_PROPERTY.getBoolean();
		ConfigValues.RJOC = RJOC_PROPERTY.getBoolean();
		ConfigValues.LIMITER = LIMITER_PROPERTY.getBoolean();
		if (config.hasChanged())
			config.save();
	}

	@EventHandler
	@SuppressWarnings("unused")
	public void preInit(FMLPreInitializationEvent event) {

		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		RBLR_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.RBLR_NAME, ConfigValues.RBLR_DEFAULT, proxy.format(ConfigValues.RBLR_NAME + ".tooltip"));
		REAF_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.REAF_NAME, ConfigValues.REAF_DEFAULT, proxy.format(ConfigValues.REAF_NAME + ".tooltip"));
		RJRH_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.RJRH_NAME, ConfigValues.RJRH_DEFAULT, proxy.format(ConfigValues.RJRH_NAME + ".tooltip"));
		RJRF_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.RJRF_NAME, ConfigValues.RJRF_DEFAULT, proxy.format(ConfigValues.RJRF_NAME + ".tooltip"));
		RGUQ_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.RGUQ_NAME, ConfigValues.RGUQ_DEFAULT, proxy.format(ConfigValues.RGUQ_NAME + ".tooltip"));
		RJOC_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.RJOC_NAME, ConfigValues.RJOC_DEFAULT, proxy.format(ConfigValues.RJOC_NAME + ".tooltip"));
		LIMITER_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.LIMITER_NAME, ConfigValues.LIMITER_DEFAULT, proxy.format(ConfigValues.LIMITER_NAME + ".tooltip"));
		syncConfig();

		MinecraftForge.EVENT_BUS.register(new CommonEvents());
	}

	public static boolean shouldLimit(EntityRabbit entity) {
		return entity.isInvisible() || entity.world.getChunkFromBlockCoords(entity.getPosition()).getEntityLists().length > 16;
	}
}
