package the_fireplace.rblr;

import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import the_fireplace.rblr.config.ConfigValues;
import the_fireplace.rblr.events.ForgeEvents;

@Mod(modid=RBLR.MODID, name=RBLR.MODNAME, guiFactory = "the_fireplace.rblr.config.RBLRGuiFactory")
public class RBLR {
	public static final String MODID = "rblr";
	public static final String MODNAME = "Rabbits Breed Like Rabbits";
	public static String VERSION;
	public static final String curseCode = "240916-rabbits-breed-like-rabbits";
	public static Configuration config;

	public static Property RBLR_PROPERTY;
	public static Property REAF_PROPERTY;
	public static Property RJRH_PROPERTY;
	public static Property RGUQ_PROPERTY;
	public static Property RJOC_PROPERTY;
	public static Property LIMITER_PROPERTY;

	public void syncConfig(){
		ConfigValues.RBLR = RBLR_PROPERTY.getBoolean();
		ConfigValues.REAF = REAF_PROPERTY.getBoolean();
		ConfigValues.RJRH = RJRH_PROPERTY.getBoolean();
		ConfigValues.RGUQ = RGUQ_PROPERTY.getBoolean();
		ConfigValues.RJOC = RJOC_PROPERTY.getBoolean();
		ConfigValues.LIMITER = LIMITER_PROPERTY.getBoolean();
		if(config.hasChanged())
			config.save();
	}

	@EventHandler
	@SuppressWarnings("unused")
	public void preInit(FMLPreInitializationEvent event){
		String[] version = event.getModMetadata().version.split("\\.");
		if(version[3].equals("BUILDNUMBER"))//Dev environment
			VERSION = event.getModMetadata().version.replace("BUILDNUMBER", "9001");
		else//Released build
			VERSION = event.getModMetadata().version;

		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		RBLR_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.RBLR_NAME, ConfigValues.RBLR_DEFAULT, I18n.translateToLocal(ConfigValues.RBLR_NAME+".tooltip"));
		REAF_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.REAF_NAME, ConfigValues.REAF_DEFAULT, I18n.translateToLocal(ConfigValues.REAF_NAME+".tooltip"));
		RJRH_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.RJRH_NAME, ConfigValues.RJRH_DEFAULT, I18n.translateToLocal(ConfigValues.RJRH_NAME+".tooltip"));
		RGUQ_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.RGUQ_NAME, ConfigValues.RGUQ_DEFAULT, I18n.translateToLocal(ConfigValues.RGUQ_NAME+".tooltip"));
		RJOC_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.RJOC_NAME, ConfigValues.RJOC_DEFAULT, I18n.translateToLocal(ConfigValues.RJOC_NAME+".tooltip"));
		LIMITER_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.LIMITER_NAME, ConfigValues.LIMITER_DEFAULT, I18n.translateToLocal(ConfigValues.LIMITER_NAME+".tooltip"));
		syncConfig();

		MinecraftForge.EVENT_BUS.register(new ForgeEvents());
	}

	public static boolean shouldLimit(EntityRabbit entity){
		return entity.isInvisible() || entity.worldObj.getChunkFromBlockCoords(entity.getPosition()).getEntityLists().length > 64;
	}
}
