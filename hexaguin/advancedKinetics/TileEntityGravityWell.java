package hexaguin.advancedKinetics;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityGravityWell extends TileEntity {
	public void updateEntity(){
		if (!worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord)){
			pullEntities(AdvancedKinetics.gravityWellRange.getInt(),0.05D);
		}
	}
	
	public void pullEntities(int range, double speed){
		AxisAlignedBB area = AxisAlignedBB.getAABBPool().getAABB((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, (double)(this.xCoord + 1), (double)(this.yCoord + 1), (double)(this.zCoord + 1)).expand(range, range, range);
		List inarea = this.worldObj.getEntitiesWithinAABB(Entity.class, area);
		Iterator iterator = inarea.iterator();
		Entity entity;
		
		while (iterator.hasNext())
        { //TODO: make this stronger at center instead of edge
			entity = (Entity)iterator.next();
			
			entity.motionX+=speed*(this.xCoord-entity.posX);
			entity.motionY+=speed*(this.yCoord-entity.posY);
			entity.motionZ+=speed*(this.zCoord-entity.posZ);
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
