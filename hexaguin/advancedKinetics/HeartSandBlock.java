package hexaguin.advancedKinetics;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class HeartSandBlock extends Block
{
    public HeartSandBlock(int par1, Material par2Material)
    {
        super(par1, par2Material);
        this.slipperiness=-1F;
    }
   
}
