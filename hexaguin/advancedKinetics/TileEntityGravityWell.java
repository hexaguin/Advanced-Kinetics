package hexaguin.advancedKinetics;

import java.util.Iterator;
import java.util.List;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;


public class TileEntityGravityWell extends TileEntity {
	private double strength = 0;
	
	public TileEntityGravityWell(double multiplier) {
		strength = multiplier;
	}

	public void updateEntity(){
		if (!worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord)){
			if (strength>0){
				pullEntities(AdvancedKinetics.gravityWellRange.getInt(), AdvancedKinetics.gravityWellPower.getInt()*0.01*0.025); //0.01 to make a percent, 0.025 is base power
			} else {
				pushEntities(AdvancedKinetics.gravityWellRange.getInt(), 0.01D);
			}
		}
	}

	public void pullEntities(int range, double speed){
		//non-players
		AxisAlignedBB area = AxisAlignedBB.getAABBPool().getAABB((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, (double)(this.xCoord + 1), (double)(this.yCoord + 1), (double)(this.zCoord + 1)).expand(range, range, range);
		List inarea = this.worldObj.getEntitiesWithinAABB(Entity.class, area);
		Iterator iterator = inarea.iterator();
		Entity entity;
		
		while (iterator.hasNext())
        {
			entity = (Entity)iterator.next();
			if (entity instanceof EntityPlayer) {
				if ( !(((EntityPlayer)entity).isPotionActive(Potion.jump)) ) {
					entity.motionX+=strength*speed*(0.5+this.xCoord-entity.posX);
					entity.motionY+=strength*speed*(0.5+this.yCoord-entity.posY);
					entity.motionZ+=strength*speed*(0.5+this.zCoord-entity.posZ);
				}
				
			} else {
				entity.motionX+=strength*speed*(0.5+this.xCoord-entity.posX);
				entity.motionY+=strength*speed*(0.5+this.yCoord-entity.posY);
				entity.motionZ+=strength*speed*(0.5+this.zCoord-entity.posZ);
			}
        }
	}
	
	public void pushEntities(int range, double speed){
		//non-players
		AxisAlignedBB area = AxisAlignedBB.getAABBPool().getAABB((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, (double)(this.xCoord + 1), (double)(this.yCoord + 1), (double)(this.zCoord + 1)).expand(range, range, range);
		List inarea = this.worldObj.getEntitiesWithinAABB(Entity.class, area);
		Iterator iterator = inarea.iterator();
		Entity entity;
		
		double proposedSpeedX;
		double proposedSpeedY;
		double proposedSpeedZ;
		double biggestProposed;
		
		while (iterator.hasNext())
        {
			entity = (Entity)iterator.next();
			if (entity instanceof EntityPlayer) {
				if ( !(((EntityPlayer)entity).isPotionActive(Potion.jump)) ) {
					proposedSpeedX=entity.motionX+strength*speed*(0.5+this.xCoord-entity.posX);
					proposedSpeedY=entity.motionY+strength*speed*(0.5+this.yCoord-entity.posY);
					proposedSpeedZ=entity.motionZ+strength*speed*(0.5+this.zCoord-entity.posZ);
					
					biggestProposed = getLargest(proposedSpeedX,proposedSpeedY,proposedSpeedZ);
					
					proposedSpeedX/=biggestProposed;
					proposedSpeedY/=biggestProposed;
					proposedSpeedZ/=biggestProposed;
					
					entity.motionX+=proposedSpeedX*speed;
					entity.motionY+=proposedSpeedY*speed;
					entity.motionZ+=proposedSpeedZ*speed;
				}
				
			} else {
				entity.motionX+=strength*speed*(0.5+this.xCoord-entity.posX);
				entity.motionY+=strength*speed*(0.5+this.yCoord-entity.posY);
				entity.motionZ+=strength*speed*(0.5+this.zCoord-entity.posZ);
			}
        }
	}

	
	private double getLargest(double x, double y, double z) {
		double max;
		max=x;
		if(y>max){max=y;}
		if(z>max){max=z;}
		return max;
	}
}
