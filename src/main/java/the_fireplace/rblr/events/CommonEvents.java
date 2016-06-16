package the_fireplace.rblr.events;

import net.minecraft.block.BlockFarmland;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemFood;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.rblr.RBLR;
import the_fireplace.rblr.config.ConfigValues;
@SuppressWarnings("unused")
public class CommonEvents {
	@SubscribeEvent
	public void livingUpdate(LivingUpdateEvent event){
		if(event.getEntityLiving() instanceof EntityRabbit && !event.getEntityLiving().worldObj.isRemote && ConfigValues.RBLR)
			if(!event.getEntityLiving().isChild() && !((EntityAnimal)event.getEntityLiving()).isInLove() && event.getEntityLiving().worldObj.getWorldTime() % 4800 == 0)
				if(!ConfigValues.LIMITER || !RBLR.shouldLimit((EntityRabbit)event.getEntityLiving()))
					((EntityRabbit) event.getEntityLiving()).setInLove(null);
		if(event.getEntityLiving() instanceof EntityRabbit && ConfigValues.RGUQ)
			if(event.getEntityLiving().isChild())
				((EntityRabbit)event.getEntityLiving()).addGrowth(2);
		if(event.getEntityLiving() instanceof EntityRabbit && ConfigValues.RJOC && ((EntityRabbit) event.getEntityLiving()).worldObj.getGameRules().getBoolean("mobGriefing"))
			if(((EntityRabbit) event.getEntityLiving()).worldObj.getBlockState(event.getEntityLiving().getPosition().down()).getBlock() instanceof BlockFarmland) {
				event.getEntityLiving().setJumping(true);
				((EntityRabbit) event.getEntityLiving()).worldObj.setBlockState(event.getEntityLiving().getPosition().down(), Blocks.DIRT.getDefaultState());
			}
	}
	@SubscribeEvent
	public void entityInteract(PlayerInteractEvent.EntityInteract event){
		if(event.getTarget() instanceof EntityRabbit && ConfigValues.REAF && event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND) != null)
			if(event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemFood){
				event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND).stackSize--;
				if(((EntityRabbit) event.getTarget()).getHealth() < ((EntityRabbit) event.getTarget()).getMaxHealth())
					((EntityRabbit) event.getTarget()).heal(1);
				if(((EntityRabbit)event.getTarget()).isChild() && ConfigValues.RGUQ)
					((EntityRabbit)event.getTarget()).addGrowth(2);
			}
	}
	@SubscribeEvent
	public void livingJump(LivingEvent.LivingJumpEvent event){
		if(event.getEntity() instanceof EntityRabbit && ConfigValues.RJRH)
			((EntityRabbit)event.getEntity()).jumpMovementFactor += 0.5;
	}
	@SubscribeEvent
	public void livingFall(LivingFallEvent event){
		if(event.getEntity() instanceof EntityRabbit && ConfigValues.RJRH)
			((EntityRabbit)event.getEntity()).jumpMovementFactor -= 0.5;
	}
	@SubscribeEvent
	public void configChanged(ConfigChangedEvent event){
		if(event.getModID().equals(RBLR.MODID))
			RBLR.syncConfig();
	}
}
