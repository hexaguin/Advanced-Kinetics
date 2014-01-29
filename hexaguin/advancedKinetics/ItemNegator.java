package hexaguin.advancedKinetics;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemNegator extends Item {
	public ItemNegator(int par1) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		--par1ItemStack.stackSize;
		par3EntityPlayer.addPotionEffect(new PotionEffect(8, 600, 3, true)); //Add Jump Boost (id 8) for 180 ticks, at level 3
		return par1ItemStack;
	}
	
}
