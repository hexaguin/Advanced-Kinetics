package hexaguin.advancedKinetics;

import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityManipulatorPulse extends EntityThrowable {

	public EntityManipulatorPulse(World par1World)
    {
        super(par1World);
    }
	
	private byte mode = 0;
	private byte endType = 0;
	private byte deathTicks = 5;
	private boolean blockBroken = false;
	
	@Override
	protected float getGravityVelocity() {
		return 0;
	}

    public EntityManipulatorPulse(World par1World, EntityLivingBase par2EntityLivingBase, byte par3PulseMode)
    {
        super(par1World, par2EntityLivingBase);
        this.motionX*=1;
        this.motionY*=1;
        this.motionZ*=1;
        this.mode = par3PulseMode;
        if(par3PulseMode==2){
        	this.endType=2;
        	this.deathTicks=60;
        }
    }

    @SideOnly(Side.CLIENT)
    public EntityManipulatorPulse(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
    	if(mode == 1 && endType == 0) { // 3*3*3 block break
	    	for(byte xOffset=-1; xOffset<=1; xOffset++){
	    		for(byte yOffset=-1; yOffset<=1; yOffset++){
	    			for(byte zOffset=-1; zOffset<=1; zOffset++){
	    				if(this.worldObj.destroyBlock((int)this.posX+xOffset, (int)this.posY+yOffset, (int)this.posZ+zOffset, true)){
	    					blockBroken = true;
	    				}
	    				if(blockBroken){
	    					endType = 1;
	    				}
	    			}
	    		}
	    	}
	    	
    	} else {
    		this.setDead();
    	}
    }
   
   public void onEntityUpdate() {
	   if(this.endType==(byte)1){ //end of an aoe break, teleports items to user
		   AxisAlignedBB area = AxisAlignedBB.getAABBPool().getAABB((double)this.posX, (double)this.posY, (double)this.posZ, (double)(this.posX + 1), (double)(this.posY + 1), (double)(this.posZ + 1)).expand(9, 9, 9);
			List inarea = this.worldObj.getEntitiesWithinAABB(EntityItem.class, area);
			Iterator iterator = inarea.iterator();
			Entity entity;
			
			while (iterator.hasNext())
	        {
				entity = (EntityItem)iterator.next();
				entity.setPosition(this.getThrower().posX, this.getThrower().posY, this.getThrower().posZ);
	        }
			if(this.deathTicks<=0){
				this.setDead();
			} else {
				deathTicks--;
			}
	   }
	   
	   if(this.endType==(byte)2){
		   	double speed = AdvancedKinetics.gravityWellPower.getInt()*0.01*0.025;
		    int range = AdvancedKinetics.gravityWellRange.getInt();
		    
		   	AxisAlignedBB area = AxisAlignedBB.getAABBPool().getAABB((double)this.posX, (double)this.posY, (double)this.posZ, (double)(this.posX + 1), (double)(this.posY + 1), (double)(this.posZ + 1)).expand(range, range, range);
			List inarea = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, area);
			Iterator iterator = inarea.iterator();
			Entity entity;
			
			while (iterator.hasNext())
	        {
				entity = (Entity)iterator.next();
				if(entity instanceof EntityPlayerMP){
					EntityPlayerMP player = (EntityPlayerMP)entity;
					player.motionX+=speed*(0.5+this.posX-player.posX);
					player.motionY+=speed*(0.5+this.posY-player.posY);
					player.motionZ+=speed*(0.5+this.posZ-player.posZ);
				}else{
					entity.motionX+=speed*(0.5+this.posX-entity.posX);
					entity.motionY+=speed*(0.5+this.posY-entity.posY);
					entity.motionZ+=speed*(0.5+this.posZ-entity.posZ);
				}
	        }
		   
		   if(this.deathTicks<=0){
				this.setDead();
			} else {
				deathTicks--;
			}
	   }
   }

}
