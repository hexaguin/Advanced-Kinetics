package hexaguin.advancedKinetics;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityGravityNullifier extends TileEntity {
	public void updateEntity(){
		if (!worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord)){
			addEntityYSpeed(AdvancedKinetics.gravityInverterRange.getInt(),0.075D);
		}
	}
	
	public void addEntityYSpeed(int range, double speed){
		AxisAlignedBB area = AxisAlignedBB.getAABBPool().getAABB((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, (double)(this.xCoord + 1), (double)(this.yCoord + 1), (double)(this.zCoord + 1)).expand(range, range, range);
		List inarea = this.worldObj.getEntitiesWithinAABB(Entity.class, area);
		Iterator iterator = inarea.iterator();
		Entity entity;
		
		double airFactor= 1.09;
		double upFactor= 1.0649;
		
		while (iterator.hasNext())
        {
			entity = (Entity)iterator.next();
			
			if (entity instanceof EntityPlayer) {
				if ( !(((EntityPlayer)entity).isPotionActive(Potion.jump)) ) {
					entity.motionY+=speed;
					if (AdvancedKinetics.airResistanceInNullifier.getBoolean(false) == false) {
						
						entity.motionX *= airFactor;
						
						if (entity.motionY>0){
							entity.motionY *= upFactor;
						} 
						
						entity.motionZ *= airFactor;
					}
				}} else {
					entity.motionY+=speed;
					if (AdvancedKinetics.airResistanceInNullifier.getBoolean(false) == false) {
						
						entity.motionX *= airFactor;
						
						if (entity.motionY>0){
							entity.motionY *= upFactor;
						} 
						
						entity.motionZ *= airFactor;
					}
				}
			
        }
	}
}
