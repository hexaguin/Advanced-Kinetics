package hexaguin.advancedKinetics;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemDirectionalLauncherBlock extends ItemBlock {

	public ItemDirectionalLauncherBlock(int par1) {
		super(par1);
		setHasSubtypes(true);
	}
	
	public String getUnlocalizedName(ItemStack itemstack)
	{
	       String name = "";
	       switch(itemstack.getItemDamage())
	       {
	             case 0:
	             {
	                    name = "north";
	                    break;
	             }
	             case 1:
	             {
	                    name = "east";
	                    break;
	             }
	             case 2:
	             {
	                    name = "south";
	                    break;
	             }
	             case 3:
	             {
	                    name = "west";
	                    break;
	             }
	             default: name = "broken";
	       }
	       return getUnlocalizedName() + "." + name;
	       
	       
	}
	
	public int getMetadata(int par1)
	{
	      return par1;
	}

}
