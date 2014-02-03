package hexaguin.advancedKinetics;
//TODO no suffocation in launchers
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockLauncherBlock extends Block {

	public BlockLauncherBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(CreativeTabs.tabTransport);
	}
	
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		par5Entity.motionY=1;
	}
	
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
		par5Entity.motionY=1;
    }
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
}
