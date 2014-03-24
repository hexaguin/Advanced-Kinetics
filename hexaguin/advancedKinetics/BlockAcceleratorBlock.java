package hexaguin.advancedKinetics;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class BlockAcceleratorBlock extends Block {

	public BlockAcceleratorBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(CreativeTabs.tabTransport);
		this.slipperiness=1.11F;
	}
	
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		if(AdvancedKinetics.acceleratorSpeedLimit.getInt()>0) {
			int limit = AdvancedKinetics.acceleratorSpeedLimit.getInt();
			if(par5Entity.motionX>limit){
				par5Entity.motionX=limit;
			} else if(par5Entity.motionX<(0-limit)){
				par5Entity.motionX=0-limit;
			}
			
			if(par5Entity.motionY>limit){
				par5Entity.motionY=limit;
			} else if(par5Entity.motionY<(0-limit)){
				par5Entity.motionY=0-limit;
			}
			
		}
	}
}
