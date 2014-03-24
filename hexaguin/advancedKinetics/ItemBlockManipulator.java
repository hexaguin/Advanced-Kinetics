package hexaguin.advancedKinetics;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockManipulator extends Item {

	private byte mode;

	public ItemBlockManipulator(int par1, int type) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.mode = (byte)type;
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        --par1ItemStack.stackSize;
        par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!par2World.isRemote)
        {
            par2World.spawnEntityInWorld(new EntityManipulatorPulse(par2World, par3EntityPlayer, this.mode));
        }

        return par1ItemStack;
    }

}
