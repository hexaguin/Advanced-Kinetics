package hexaguin.advancedKinetics;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = AdvancedKinetics.modid, name = "Advanced Kinetics", version = "0.3")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class AdvancedKinetics {
	public static final String modid = "Hexaguin_AdvancedKinetics";
	
	public static Block acceleratorBlock;
	public static Block launcherBlock;
	public static Block heartSandBlock;
	public static Block deflectorBlock;
	public static Block directionalLauncherBlock;
	
	public static int acceleratorID;
	public static int launcherID;
	public static int heartSandID;
	public static int deflectorID;
	public static int directionalLauncherID;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		
		acceleratorID = config.getBlock("acceleratorID", 530).getInt();
		launcherID = config.getBlock("launcherID", 531).getInt();
		heartSandID = config.getBlock("heartSandID", 532).getInt();
		deflectorID = config.getBlock("deflectorID", 533).getInt();
		directionalLauncherID = config.getBlock("directionalLauncherID", 534).getInt();
		
		config.save();
	}
	
	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
			
        //adding blocks
		acceleratorBlock = new BlockAcceleratorBlock(acceleratorID,Material.iron)
		.setUnlocalizedName("acceleratorBlock")
		.setHardness(1.5F)
		.setTextureName("hexaguin_advancedkinetics:acceleratorBlock");
        GameRegistry.registerBlock(acceleratorBlock, modid + acceleratorBlock.getUnlocalizedName().substring(5));
        LanguageRegistry.addName(acceleratorBlock, "Kinetic Accelerator");
        MinecraftForge.setBlockHarvestLevel(acceleratorBlock, "pickaxe", 1);
        
        launcherBlock = new BlockLauncherBlock(launcherID,Material.iron)
        .setUnlocalizedName("launcherBlock")
        .setHardness(1.5F)
        .setTextureName("hexaguin_advancedkinetics:launcherBlock");
        GameRegistry.registerBlock(launcherBlock, modid + launcherBlock.getUnlocalizedName().substring(5));
        LanguageRegistry.addName(launcherBlock, "Kinetic Vertical Velocity Enhancer");
        MinecraftForge.setBlockHarvestLevel(launcherBlock, "pickaxe", 1);
        
        heartSandBlock = new HeartSandBlock(heartSandID,Material.sand)
        .setUnlocalizedName("heartSandBlock");
        GameRegistry.registerBlock(heartSandBlock, modid + heartSandBlock.getUnlocalizedName().substring(5));
        LanguageRegistry.addName(heartSandBlock, "Heartsand [WIP]");
        
        deflectorBlock = new DeflectorBlock(deflectorID,Material.web)
        .setUnlocalizedName("deflectorBlock");
        GameRegistry.registerBlock(deflectorBlock, modid + deflectorBlock.getUnlocalizedName().substring(5));
        LanguageRegistry.addName(deflectorBlock, "Kinetic Deflector [WIP and SUPER buggy and broken]");
        
        directionalLauncherBlock = new DirectionalLauncherBlock(directionalLauncherID,Material.iron)
        .setUnlocalizedName("directionalLauncherBlock")
        .setHardness(1.5F)
        .setTextureName("advancedkinetics:northLauncher");
        GameRegistry.registerBlock(directionalLauncherBlock, ItemDirectionalLauncherBlock.class, modid + (directionalLauncherBlock.getUnlocalizedName().substring(5)));
        LanguageRegistry.addName(new ItemStack(directionalLauncherBlock,1, 0), "Kinetic Northbound Velocity Enhancer");
        LanguageRegistry.addName(new ItemStack(directionalLauncherBlock,1, 1), "Kinetic Eastbound Velocity Enhancer");
        LanguageRegistry.addName(new ItemStack(directionalLauncherBlock,1, 2), "Kinetic Southbound Velocity Enhancer");
        LanguageRegistry.addName(new ItemStack(directionalLauncherBlock,1, 3), "Kinetic Westbound Velocity Enhancer");
        MinecraftForge.setBlockHarvestLevel(directionalLauncherBlock, "pickaxe", 1);
       
        //adding Recipes
        ItemStack accelerator = new ItemStack(acceleratorBlock);
        ItemStack accelerator16 = new ItemStack(acceleratorBlock,16);
        ItemStack launcher = new ItemStack(launcherBlock);
        ItemStack northLauncher = new ItemStack(directionalLauncherBlock);
        ItemStack eastLauncher = new ItemStack(directionalLauncherBlock);
        ItemStack southLauncher = new ItemStack(directionalLauncherBlock);
        ItemStack westLauncher = new ItemStack(directionalLauncherBlock);
        ItemStack quartz = new ItemStack(Item.netherQuartz);
        ItemStack redstone = new ItemStack(Item.redstone);
        ItemStack enderPearl = new ItemStack(Item.enderPearl);
        ItemStack ironIngot = new ItemStack(Item.ingotIron);
        
        GameRegistry.addRecipe(accelerator16, new Object[] {
        	"XYX",
        	"YZY",
        	"XYX",
        	'X',quartz,
        	'Y',enderPearl,
        	'Z',ironIngot
        });
        GameRegistry.addRecipe(launcher, new Object[] {
            	" X ",
            	"YYY",
            	" X ",
            	'X',enderPearl,
            	'Y',accelerator
        });
        GameRegistry.addRecipe(northLauncher, new Object[] {
            	"ZX ",
            	" Y ",
            	"   ",
            	'X',enderPearl,
            	'Y',launcher,
            	'Z',quartz
        });
        GameRegistry.addRecipe(southLauncher, new Object[] {
            	"Z  ",
        		" Y ",
            	" X ",
            	'X',enderPearl,
            	'Y',launcher,
            	'Z',quartz
        });
        GameRegistry.addRecipe(eastLauncher, new Object[] {
            	"Z  ",
        		" YX",
        		"   ",
            	'X',enderPearl,
            	'Y',launcher,
            	'Z',quartz
        });
        GameRegistry.addRecipe(westLauncher, new Object[] {
        		"Z  ",
        		"XY ",
        		"   ",
            	'X',enderPearl,
            	'Y',launcher,
            	'Z',quartz
        });
   	}
}

