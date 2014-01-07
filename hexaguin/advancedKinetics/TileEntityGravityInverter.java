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

public class TileEntityGravityInverter extends TileEntity {
	public void updateEntity(){
		if (!worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord)){
			addEntityYSpeed(8,0.15D);
		}
	}
	
	public void addEntityYSpeed(int range, double speed){
		AxisAlignedBB area = AxisAlignedBB.getAABBPool().getAABB((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, (double)(this.xCoord + 1), (double)(this.yCoord + 1), (double)(this.zCoord + 1)).expand(range, range, range);
		List inarea = this.worldObj.getEntitiesWithinAABB(Entity.class, area);
		Iterator iterator = inarea.iterator();
		Entity entity;
		
		while (iterator.hasNext())
        {
			entity = (Entity)iterator.next();
            entity.motionY+=speed;
        }
	}
}
