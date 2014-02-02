package hexaguin.advancedKinetics;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemDetectorBlock extends ItemBlock {

	public ItemDetectorBlock(int par1) {
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
	                    name = "owner";
	                    break;
	             }
	             case 1:
	             {
	                    name = "living";
	                    break;
	             }
	             case 2:
	             {
	                    name = "all";
	                    break;
	             }
	             case 3:
	             {
	            	 name = "player";
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
