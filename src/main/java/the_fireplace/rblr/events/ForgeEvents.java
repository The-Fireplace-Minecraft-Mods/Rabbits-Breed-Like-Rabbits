package the_fireplace.rblr.events;

import net.minecraft.block.BlockFarmland;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemFood;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.rblr.config.ConfigValues;
@SuppressWarnings("unused")
public class ForgeEvents {
	@SubscribeEvent
	public void livingUpdate(LivingUpdateEvent event){
		if(event.entityLiving instanceof EntityRabbit && !event.entityLiving.worldObj.isRemote && ConfigValues.RBLR)
			if(!event.entityLiving.isChild() && !((EntityAnimal)event.entityLiving).isInLove() && event.entityLiving.worldObj.getWorldTime() % 4800 == 0)
				((EntityRabbit) event.entityLiving).setInLove(null);
		if(event.entityLiving instanceof EntityRabbit && ConfigValues.RGUQ)
			if(event.entityLiving.isChild())
				((EntityRabbit)event.entityLiving).addGrowth(2);
		if(event.entityLiving instanceof EntityRabbit && ConfigValues.RJOC)
			if(((EntityRabbit) event.entityLiving).worldObj.getBlockState(event.entityLiving.getPosition().down()).getBlock() instanceof BlockFarmland) {
				event.entityLiving.setJumping(true);
				((EntityRabbit) event.entityLiving).worldObj.setBlockState(event.entityLiving.getPosition().down(), Blocks.dirt.getDefaultState());
			}
	}
	@SubscribeEvent
	public void entityInteract(EntityInteractEvent event){
		if(event.target instanceof EntityRabbit && ConfigValues.REAF && event.entityPlayer.getHeldItem() != null)
			if(event.entityPlayer.getHeldItem().getItem() instanceof ItemFood){
				event.entityPlayer.getHeldItem().stackSize--;
				if(((EntityRabbit) event.target).getHealth() < ((EntityRabbit) event.target).getMaxHealth())
					((EntityRabbit) event.target).heal(1);
				if(((EntityRabbit)event.target).isChild() && ConfigValues.RGUQ)
					((EntityRabbit)event.target).addGrowth(2);
			}
	}
	@SubscribeEvent
	public void livingJump(LivingEvent.LivingJumpEvent event){
		if(event.entity instanceof EntityRabbit && ConfigValues.RJRH)
			((EntityRabbit)event.entity).jumpMovementFactor += 0.5;
	}
	@SubscribeEvent
	public void livingFall(LivingFallEvent event){
		if(event.entity instanceof EntityRabbit && ConfigValues.RJRH)
			((EntityRabbit)event.entity).jumpMovementFactor -= 0.5;
	}
}
