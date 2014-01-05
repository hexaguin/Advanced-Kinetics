package hexaguin.advancedKinetics;
//TODO: Make this block actually do stuff
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class DeflectorBlock extends Block {

	public DeflectorBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
		return AxisAlignedBB.getAABBPool().getAABB((double)((float)par2 + 1), (double)par3+1, (double)((float)par4 + 1), (double)((float)(par2 + 1) - 1), (double)((float)(par3 + 1) - 1), (double)((float)(par4 + 1) - 1));
    }
}
