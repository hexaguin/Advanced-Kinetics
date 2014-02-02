package hexaguin.advancedKinetics;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

//TODO stop killing everything.

public class DetectorBlock extends BlockContainer {

	public DetectorBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	    {
		 if(!par1World.isRemote)
	        {
	                TileEntityDetector t = (TileEntityDetector) par1World.getBlockTileEntity(par2, par3, par4);
	                t.processActivate(par5EntityPlayer, par1World);
	        }
	        return true;
	    }
	
    public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
    	 TileEntityDetector t = (TileEntityDetector) par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
    	 return t.power;
    }
    
    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
    	 TileEntityDetector t = (TileEntityDetector) par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
    	 return t.power;
    }
    
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityDetector();
	}
	
	
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	      
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
	       icons = new Icon[5];
	            
	       for(int i = 0; i < icons.length; i++)
	       {
	        icons[i] = par1IconRegister.registerIcon(AdvancedKinetics.modid + ":" + (this.getUnlocalizedName().substring(5)) + i);
	       }
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
		switch(par2){
		case 0: //Owner
			{
				return icons[0]; 
			}
		case 1: //Living
			{
				return icons[1]; 
			}
		case 2: //Any
			{
				return icons[2]; 
			}
		 default:
         {
                System.out.println("Invalid metadata for " + this.getUnlocalizedName());
                return icons[0];
         }
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
	    for (int var4 = 0; var4 < 3; ++var4)
	    {
	        par3List.add(new ItemStack(par1, 1, var4));
	    }
	}
	
	public int damageDropped(int par1)
	{
	    return par1;
	}
}
