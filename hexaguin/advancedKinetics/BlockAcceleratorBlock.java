package hexaguin.advancedKinetics;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockAcceleratorBlock extends Block {

	public BlockAcceleratorBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(CreativeTabs.tabTransport);
		this.slipperiness=1.11F;
	}
}
