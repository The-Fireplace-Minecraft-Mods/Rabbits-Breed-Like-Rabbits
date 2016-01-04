package the_fireplace.rblr.events;

import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ForgeEvents {
	@SubscribeEvent
	public void livingUpdate(LivingUpdateEvent event){
		if(event.entityLiving instanceof EntityRabbit && !event.entityLiving.worldObj.isRemote)
			if(!event.entityLiving.isChild() && !((EntityAnimal)event.entityLiving).isInLove() && event.entityLiving.worldObj.getWorldTime() % 4800 == 0)
				((EntityRabbit) event.entityLiving).setInLove(null);
	}
}
