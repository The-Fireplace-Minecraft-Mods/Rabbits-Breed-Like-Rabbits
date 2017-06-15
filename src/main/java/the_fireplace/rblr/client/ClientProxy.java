package the_fireplace.rblr.client;

import net.minecraft.client.resources.I18n;
import the_fireplace.rblr.CommonProxy;

public class ClientProxy extends CommonProxy {
	@Override
	public String format(String s, Object... args) {
		return I18n.format(s, args);
	}
}
