package hexaguin.advancedKinetics;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class DirectionalLauncherBlock extends Block {

	public DirectionalLauncherBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
		par5Entity.motionY=0.5;
		
		if (par1World.getBlockMetadata(par2, par3, par4) == 0) {
			par5Entity.motionZ=-1;
		} else if (par1World.getBlockMetadata(par2, par3, par4) == 1) {
			par5Entity.motionX=+1;
		} else if (par1World.getBlockMetadata(par2, par3, par4) == 2) {
			par5Entity.motionZ=+1;
		} else if (par1World.getBlockMetadata(par2, par3, par4) == 3) {
			par5Entity.motionX=-1;
		}

    }
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
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
		case 0: //northbound
			{
				switch(par1){
				case 0: //bottom
					return icons[0]; 
				case 1: //top
					return icons[0];
				case 2: //north
					return icons[4]; 
				case 3: //south
					return icons[0];
				case 4: //west
					return icons[3];
				case 5: //east
					return icons[1];
				default:
					return icons[0];
				}
			}
		case 1: //eastbound
			{
				switch(par1){
				case 0:
					return icons[1]; 
				case 1: 
					return icons[1];
				case 2:
					return icons[3];
				case 3:
					return icons[1];
				case 4:
					return icons[0];
				case 5:
					return icons[4];
				default:
					return icons[1];
				}
			}
		case 2: //southbound
			{
				switch(par1){
				case 0: //bottom
					return icons[2]; 
				case 1: //top
					return icons[2];
				case 2: //north
					return icons[0]; 
				case 3: //south
					return icons[4];
				case 4: //west
					return icons[1];
				case 5: //east
					return icons[3];
				default:
					return icons[0];
				}
			}
		case 3: //westbound
			{
				switch(par1){
				case 0:
					return icons[3]; 
				case 1: 
					return icons[3];
				case 2:
					return icons[1];
				case 3:
					return icons[3];
				case 4:
					return icons[4];
				case 5:
					return icons[0];
				default:
					return icons[1];
				}
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
	    for (int var4 = 0; var4 < 4; ++var4)
	    {
	        par3List.add(new ItemStack(par1, 1, var4));
	    }
	}
	
	public int damageDropped(int par1)
	{
	    return par1;
	}
}
