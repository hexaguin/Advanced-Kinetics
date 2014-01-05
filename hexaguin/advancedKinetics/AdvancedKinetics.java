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

@Mod(modid = AdvancedKinetics.modid, name = "Advanced Kinetics", version = "0.2")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class AdvancedKinetics {
	public static final String modid = "Hexaguin_AdvancedKinetics";
	
	public static Block acceleratorBlock;
	public static Block launcherBlock;
	public static Block heartSandBlock;
	public static Block deflectorBlock;
	public static Block northLauncherBlock;
	public static Block eastLauncherBlock;
	public static Block southLauncherBlock;
	public static Block westLauncherBlock;
	
	public static int acceleratorID;
	public static int launcherID;
	public static int heartSandID;
	public static int deflectorID;
	public static int northLauncherID;
	public static int eastLauncherID;
	public static int southLauncherID;
	public static int westLauncherID;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		
		acceleratorID = config.getBlock("acceleratorID", 530).getInt();
		launcherID = config.getBlock("launcherID", 531).getInt();
		heartSandID = config.getBlock("heartSandID", 532).getInt();
		deflectorID = config.getBlock("deflectorID", 533).getInt();
		northLauncherID = config.getBlock("northLauncherID", 534).getInt();
		eastLauncherID = config.getBlock("eastLauncherID", 535).getInt();
		southLauncherID = config.getBlock("southLauncherID", 536).getInt();
		westLauncherID = config.getBlock("westLauncherID", 537).getInt();
		
		config.save();
	}
	
	//TODO: Tools and weapons
	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
			
        //adding blocks
		acceleratorBlock = new BlockAcceleratorBlock(acceleratorID,Material.iron)
		.setUnlocalizedName("acceleratorBlock")
		.setHardness(1.5F)
		.setTextureName("advancedkinetics:acceleratorBlock");
        GameRegistry.registerBlock(acceleratorBlock, modid + acceleratorBlock.getUnlocalizedName().substring(5));
        LanguageRegistry.addName(acceleratorBlock, "Kinetic Accelerator");
        MinecraftForge.setBlockHarvestLevel(acceleratorBlock, "pickaxe", 1);
        
        launcherBlock = new BlockLauncherBlock(launcherID,Material.iron)
        .setUnlocalizedName("launcherBlock")
        .setHardness(1.5F)
        .setTextureName("advancedkinetics:launcherBlock");
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
        
        northLauncherBlock = new NorthLauncherBlock(northLauncherID,Material.iron)
        .setUnlocalizedName("northLauncherBlock")
        .setHardness(1.5F)
        .setTextureName("advancedkinetics:northLauncher");
        GameRegistry.registerBlock(northLauncherBlock, modid + northLauncherBlock.getUnlocalizedName().substring(5));
        LanguageRegistry.addName(northLauncherBlock, "Kinetic Northbound Velocity Enhancer");
        MinecraftForge.setBlockHarvestLevel(northLauncherBlock, "pickaxe", 1);
        
        eastLauncherBlock = new EastLauncherBlock(eastLauncherID,Material.iron)
        .setUnlocalizedName("eastLauncherBlock")
        .setHardness(1.5F)
        .setTextureName("advancedkinetics:eastLauncher");
        GameRegistry.registerBlock(eastLauncherBlock, modid + eastLauncherBlock.getUnlocalizedName().substring(5));
        LanguageRegistry.addName(eastLauncherBlock, "Kinetic Eastbound Velocity Enhancer");
        MinecraftForge.setBlockHarvestLevel(eastLauncherBlock, "pickaxe", 1);
        
        southLauncherBlock = new SouthLauncherBlock(southLauncherID,Material.iron)
        .setUnlocalizedName("southLauncherBlock")
        .setHardness(1.5F)
        .setTextureName("advancedkinetics:southLauncher");
        GameRegistry.registerBlock(southLauncherBlock, modid + southLauncherBlock.getUnlocalizedName().substring(5));
        LanguageRegistry.addName(southLauncherBlock, "Kinetic Southbound Velocity Enhancer");
        MinecraftForge.setBlockHarvestLevel(southLauncherBlock, "pickaxe", 1);
        
        westLauncherBlock = new WestLauncherBlock(westLauncherID,Material.iron)
        .setUnlocalizedName("westLauncherBlock")
        .setHardness(1.5F)
        .setTextureName("advancedkinetics:westLauncher");
        GameRegistry.registerBlock(westLauncherBlock, modid + westLauncherBlock.getUnlocalizedName().substring(5));
        LanguageRegistry.addName(westLauncherBlock, "Kinetic Westbound Velocity Enhancer");
        MinecraftForge.setBlockHarvestLevel(westLauncherBlock, "pickaxe", 1);
        
        //adding Recipes
        ItemStack accelerator = new ItemStack(acceleratorBlock);
        ItemStack accelerator16 = new ItemStack(acceleratorBlock,16);
        ItemStack launcher = new ItemStack(launcherBlock);
        ItemStack northLauncher = new ItemStack(northLauncherBlock);
        ItemStack eastLauncher = new ItemStack(eastLauncherBlock);
        ItemStack southLauncher = new ItemStack(southLauncherBlock);
        ItemStack westLauncher = new ItemStack(westLauncherBlock);
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

