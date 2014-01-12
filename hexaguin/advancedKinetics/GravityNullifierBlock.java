package hexaguin.advancedKinetics;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GravityNullifierBlock extends BlockContainer {
	
	public GravityNullifierBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityGravityNullifier();
	}
}
