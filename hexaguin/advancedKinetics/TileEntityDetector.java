package hexaguin.advancedKinetics;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class TileEntityDetector extends TileEntity {
	String owner="none";
	int range = AdvancedKinetics.detectorRange.getInt();
	int power = 0;
	int meta;
	
	boolean detected = false;
	boolean lastTick = false;
	
	public void updateEntity(){
		detectEntities();
	}
	
	public void processActivate(EntityPlayer par5EntityPlayer, World world) {
	    if(world.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) == 0 && owner=="none"){
	    	owner=par5EntityPlayer.getEntityName();
	    	par5EntityPlayer.addChatMessage("You are now the owner of this detector," + par5EntityPlayer.getEntityName());
	    	world.notifyBlockChange(xCoord, yCoord, zCoord, 2);
	    }
	}
	
	private void detectEntities() {
		meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		AxisAlignedBB area = AxisAlignedBB.getAABBPool().getAABB((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, (double)(this.xCoord + 1), (double)(this.yCoord + 1), (double)(this.zCoord + 1)).expand(range, range, range);
		List inarea = this.worldObj.getEntitiesWithinAABB(Entity.class, area);
		Iterator iterator = inarea.iterator();
		Entity entity;
		lastTick = detected;
		detected = false;
		
		while (iterator.hasNext())
        {
			entity = (Entity)iterator.next();
			
			
			if(meta==0)
			{//Owner only
				if(entity.getEntityName()==owner){
					detected=true;
				}
			} else if(meta==1)
			{//Living only
				if(entity instanceof EntityLivingBase){
					detected=true;
				}
			}else if(meta==2)
			{//Any entity
				detected=true;
			} else {//player only
				if(entity instanceof EntityPlayer){
					detected=true;
				}
			}
				
		}
		

		if (detected==true){
			power = 15;
		} else {
			power = 0;
		}
		
		if (detected!=lastTick){
			updateBlocks();
		}
	}
	
	private void updateBlocks() {
		for(int xOffset = -1; xOffset <=1; xOffset++){
			for(int yOffset = -1; yOffset <=1; yOffset++){
				for(int zOffset = -1; zOffset <=1; zOffset++){
					this.worldObj.notifyBlocksOfNeighborChange(xCoord+xOffset, yCoord+yOffset, zCoord+zOffset, this.worldObj.getBlockId(xCoord+xOffset, yCoord+yOffset, zCoord+zOffset));
				}
			}
		}
	}
	
	@Override
    public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.owner = nbt.getString("owner");
	}
	
	@Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setString("owner", owner);
    }
}