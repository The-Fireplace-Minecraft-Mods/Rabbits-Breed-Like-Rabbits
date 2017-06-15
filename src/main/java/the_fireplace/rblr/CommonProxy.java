package the_fireplace.rblr;

import net.minecraft.util.text.translation.I18n;

public class CommonProxy {
	public String format(String s, Object... args){
		return I18n.translateToLocalFormatted(s, args);
	}
}
