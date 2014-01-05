package hexaguin.advancedKinetics;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockAccumulatorBlock extends Block {

	public BlockAccumulatorBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.slipperiness = 1.1F;
	}
}
