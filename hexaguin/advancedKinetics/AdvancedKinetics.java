package hexaguin.advancedKinetics;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = AdvancedKinetics.modid, name = "Advanced Kinetics", version = "Beta 0.3")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class AdvancedKinetics {
	public static final String modid = "Hexaguin_AdvancedKinetics";
	
	public static Block acceleratorBlock;
	public static Block launcherBlock;
	public static Block heartSandBlock;
	public static Block gravityInverterBlock;
	public static Block directionalLauncherBlock;
	public static Block gravityWellBlock;
	public static Block pearlActivatorBlock;
	public static Block gravityNullifierBlock;
	public static Block detectorBlock;
	
	public static Item itemRuggedPearl;
	public static Item itemNegator;
	
	public static int acceleratorID;
	public static int launcherID;
	public static int heartSandID;
	public static int gravityInverterID;
	public static int directionalLauncherID;
	public static int gravityWellID;
	public static int pearlActivatorID;
	public static int ruggedPearlID;
	public static int gravityNullifierID;
	public static int negatorID;
	public static int detectorID;
	
	public static Property gravityInverterRange;
	public static Property gravityWellRange;
	public static Property gravityWellPower;
	public static Property detectorRange;
	public static Property ruggedPearlLifespan;
	public static Property airResistanceInNullifier;
	
	private static int modEntityID = 0;

	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		
		acceleratorID = config.getBlock("acceleratorID", 530).getInt();
		launcherID = config.getBlock("launcherID", 531).getInt();
		heartSandID = config.getBlock("heartSandID", 532).getInt();
		gravityInverterID = config.getBlock("gravityInverterID", 533).getInt();
		directionalLauncherID = config.getBlock("directionalLauncherID", 534).getInt();
		gravityWellID = config.getBlock("gravityWellID", 535).getInt();
		pearlActivatorID = config.getBlock("pearlActivatorID", 536).getInt();
		ruggedPearlID = config.getItem("ruggedPearlID", 537).getInt();
		gravityNullifierID = config.getBlock("gravityNullifierID", 538).getInt();
		negatorID = config.getItem("negatorID", 539).getInt();
		detectorID = config.getBlock("detectorID", 540).getInt();
		
		gravityInverterRange = config.get("tweaks", "gravityInverterRange", 8);
		gravityWellRange = config.get("tweaks", "gravityWellRange", 16);
		gravityWellPower = config.get("tweaks", "gravityWellPower", 100);
		detectorRange = config.get("tweaks", "detectorRange", 8);
		
		ruggedPearlLifespan = config.get("tweaks", "ruggedPearlLifespan", 1600);
		airResistanceInNullifier = config.get("tweaks", "airResistanceInNullifier", false);
		
		config.save();
	}
	
	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		//tileentity registration
		GameRegistry.registerTileEntity(hexaguin.advancedKinetics.TileEntityGravityInverter.class, "TileEntityGravityInverter");
		GameRegistry.registerTileEntity(hexaguin.advancedKinetics.TileEntityGravityWell.class, "TileEntityGravityWell");
		GameRegistry.registerTileEntity(hexaguin.advancedKinetics.TileEntityGravityNullifier.class, "TileEntityGravityNullifier");
		GameRegistry.registerTileEntity(hexaguin.advancedKinetics.TileEntityDetector.class, "TileEntityDetector");

		
        //adding blocks
		acceleratorBlock = new BlockAcceleratorBlock(acceleratorID,Material.iron)
		.setUnlocalizedName("acceleratorBlock")
		.setHardness(1.5F)
		.setTextureName("hexaguin_advancedkinetics:acceleratorBlock");
        GameRegistry.registerBlock(acceleratorBlock, modid + acceleratorBlock.getUnlocalizedName().substring(5));
        LanguageRegistry.addName(acceleratorBlock, "Kinetic Accelerator");
        MinecraftForge.setBlockHarvestLevel(acceleratorBlock, "pickaxe", 1);
        
        launcherBlock = new BlockLauncherBlock(launcherID,Material.glass)
        .setUnlocalizedName("launcherBlock")
        .setHardness(3F)
        .setTextureName("hexaguin_advancedkinetics:launcherBlock");
        GameRegistry.registerBlock(launcherBlock, modid + launcherBlock.getUnlocalizedName().substring(5));
        LanguageRegistry.addName(launcherBlock, "Kinetic Vertical Velocity Enhancer");
        MinecraftForge.setBlockHarvestLevel(launcherBlock, "pickaxe", 1);
        
        heartSandBlock = new HeartSandBlock(heartSandID,Material.sand)
        .setUnlocalizedName("heartSandBlock");
        GameRegistry.registerBlock(heartSandBlock, modid + heartSandBlock.getUnlocalizedName().substring(5));
        LanguageRegistry.addName(heartSandBlock, "Heartsand [WIP]");
        
        gravityInverterBlock = new GravityInverterBlock(gravityInverterID,Material.iron)
        .setHardness(1.5F)
        .setTextureName("hexaguin_advancedkinetics:gravityInverterBlock")
        .setUnlocalizedName("gravityInverterBlock");
        GameRegistry.registerBlock(gravityInverterBlock, modid + gravityInverterBlock.getUnlocalizedName().substring(5));
        LanguageRegistry.addName(gravityInverterBlock, "Kinetic Gravity Inverter");
        MinecraftForge.setBlockHarvestLevel(gravityInverterBlock, "pickaxe", 1);
        
        gravityWellBlock = new GravityWellBlock(gravityWellID,Material.iron)
        .setHardness(1.5F)
        .setTextureName("hexaguin_advancedkinetics:gravityWellBlock")
        .setUnlocalizedName("gravityWellBlock");
        GameRegistry.registerBlock(gravityWellBlock, modid + gravityWellBlock.getUnlocalizedName().substring(5));
        LanguageRegistry.addName(gravityWellBlock, "Kinetic Gravity Well");
        MinecraftForge.setBlockHarvestLevel(gravityWellBlock, "pickaxe", 1);
        
        directionalLauncherBlock = new DirectionalLauncherBlock(directionalLauncherID,Material.glass)
        .setUnlocalizedName("directionalLauncherBlock")
        .setHardness(3F);
        GameRegistry.registerBlock(directionalLauncherBlock, ItemDirectionalLauncherBlock.class, modid + (directionalLauncherBlock.getUnlocalizedName().substring(5)));
        LanguageRegistry.addName(new ItemStack(directionalLauncherBlock,1, 0), "Kinetic Northbound Velocity Enhancer");
        LanguageRegistry.addName(new ItemStack(directionalLauncherBlock,1, 1), "Kinetic Eastbound Velocity Enhancer");
        LanguageRegistry.addName(new ItemStack(directionalLauncherBlock,1, 2), "Kinetic Southbound Velocity Enhancer");
        LanguageRegistry.addName(new ItemStack(directionalLauncherBlock,1, 3), "Kinetic Westbound Velocity Enhancer");
        MinecraftForge.setBlockHarvestLevel(directionalLauncherBlock, "pickaxe", 1);
        
        pearlActivatorBlock = new pearlActivatorBlock(pearlActivatorID,Material.iron)
        .setHardness(1.5F)
        .setTextureName("hexaguin_advancedkinetics:pearlActivatorBlock")
        .setUnlocalizedName("pearlActivatorBlock");
        GameRegistry.registerBlock(pearlActivatorBlock, modid + pearlActivatorBlock.getUnlocalizedName().substring(5));
        LanguageRegistry.addName(pearlActivatorBlock, "Rugged Pearl Activator");
        MinecraftForge.setBlockHarvestLevel(pearlActivatorBlock, "pickaxe", 1);
        
        gravityNullifierBlock = new GravityNullifierBlock(gravityNullifierID,Material.iron)
        .setHardness(1.5F)
        .setTextureName("hexaguin_advancedkinetics:gravityNullifierBlock")
        .setUnlocalizedName("gravityNullifierBlock");
        GameRegistry.registerBlock(gravityNullifierBlock, modid + gravityNullifierBlock.getUnlocalizedName().substring(5));
        LanguageRegistry.addName(gravityNullifierBlock, "Kinetic Gravity Nullifier");
        MinecraftForge.setBlockHarvestLevel(gravityNullifierBlock, "pickaxe", 1);
        
       detectorBlock = new DetectorBlock(detectorID,Material.iron)
        .setHardness(1.5F)
        .setUnlocalizedName("detectorBlock");
        GameRegistry.registerBlock(detectorBlock, ItemDetectorBlock.class, modid + (detectorBlock.getUnlocalizedName().substring(5)));
        LanguageRegistry.addName(new ItemStack(detectorBlock,1, 0), "Owner Detector");
        LanguageRegistry.addName(new ItemStack(detectorBlock,1, 1), "Living Detector");
        LanguageRegistry.addName(new ItemStack(detectorBlock,1, 2), "Entity Detector");
        LanguageRegistry.addName(new ItemStack(detectorBlock,1, 3), "Player Detector");
        MinecraftForge.setBlockHarvestLevel(detectorBlock, "pickaxe", 1);
        
        //items
        itemRuggedPearl = new ItemRuggedPearl(ruggedPearlID)
        .setTextureName("hexaguin_advancedkinetics:ruggedPearl")
        .setUnlocalizedName("itemRuggedPearl");
        LanguageRegistry.addName(itemRuggedPearl, "Rugged Ender Pearl");
        
        itemNegator = new ItemNegator(negatorID)
        .setTextureName("hexaguin_advancedkinetics:negator")
        .setUnlocalizedName("itemNegator");
        LanguageRegistry.addName(itemNegator, "Kinetic Negator");
        
        //adding entities
        
        EntityRegistry.registerModEntity(EntityRuggedPearl.class, "Rugged Pearl", ++modEntityID, this, 64, 10, true);
        RenderingRegistry.registerEntityRenderingHandler(EntityRuggedPearl.class, new RenderSnowball(itemRuggedPearl));
       
        //adding Recipes
        ItemStack accelerator = new ItemStack(acceleratorBlock);
        ItemStack accelerator16 = new ItemStack(acceleratorBlock,16);
        ItemStack launcher = new ItemStack(launcherBlock);
        ItemStack northLauncher = new ItemStack(directionalLauncherBlock,1,0);
        ItemStack eastLauncher = new ItemStack(directionalLauncherBlock,1,1);
        ItemStack southLauncher = new ItemStack(directionalLauncherBlock,1,2);
        ItemStack westLauncher = new ItemStack(directionalLauncherBlock,1,3);
        ItemStack inverter = new ItemStack(gravityInverterBlock);
        ItemStack gravityWell = new ItemStack(gravityWellBlock);
        ItemStack ruggedPearl = new ItemStack(itemRuggedPearl);
        ItemStack pearlActivator = new ItemStack(pearlActivatorBlock);
        ItemStack nullifier = new ItemStack(gravityNullifierBlock);
        ItemStack nullifiers2 = new ItemStack(gravityNullifierBlock, 2);
        ItemStack negators64 = new ItemStack(itemNegator, 64);
        ItemStack anythingDetector = new ItemStack(detectorBlock,1,2);
        ItemStack livingDetector = new ItemStack(detectorBlock,1,1);
        ItemStack playerDetector = new ItemStack(detectorBlock,1,3);
        ItemStack ownerDetector = new ItemStack(detectorBlock,1,0);
        
        ItemStack quartz = new ItemStack(Item.netherQuartz);
        ItemStack redstone = new ItemStack(Item.redstone);
        ItemStack enderPearl = new ItemStack(Item.enderPearl);
        ItemStack ironIngot = new ItemStack(Item.ingotIron);
        ItemStack diamond = new ItemStack(Item.diamond);
        ItemStack spiderEye = new ItemStack(Item.spiderEye);
        ItemStack enderEye = new ItemStack(Item.eyeOfEnder);
        ItemStack blazeRod = new ItemStack(Item.blazeRod);
        ItemStack stone = new ItemStack(Block.stone);
        ItemStack redstoneTorch = new ItemStack(Block.torchRedstoneActive);
        ItemStack stonePressurePlate = new ItemStack(70,1,0);
        ItemStack ironPressurePlate = new ItemStack(148,1,0);
        ItemStack goldPressurePlate = new ItemStack(147,1,0);
        
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
        GameRegistry.addRecipe(inverter, new Object[] {
        		" Y ",
        		"XXX",
        		"ZZZ",
        		'X',launcher,
        		'Y',enderPearl,
        		'Z',accelerator
        });
        GameRegistry.addRecipe(gravityWell, new Object[] {
        		"XYX",
        		"YZY",
        		"XYX",
        		'X', inverter,
        		'Y', diamond,
        		'Z', enderPearl
        });
        GameRegistry.addShapelessRecipe(ruggedPearl, redstone, enderPearl, stone);
        GameRegistry.addRecipe(pearlActivator, new Object[] {
        		"XYX",
        		"XZX",
        		"XXX",
        		'X', ironIngot,
        		'Y', redstoneTorch,
        		'Z', ruggedPearl
        });
        GameRegistry.addShapelessRecipe(nullifiers2, inverter, redstone, stone, enderPearl);
        GameRegistry.addRecipe(negators64, new Object[] {
        		"X X",
        		" Y ",
        		"X X",
        		'X',enderPearl,
        		'Y',ruggedPearl
        });
        GameRegistry.addRecipe(anythingDetector, new Object[] {
        		"XYX",
        		"ZAZ",
        		"XYX",
        		'X',stonePressurePlate,
        		'Y',ironPressurePlate,
        		'Z',goldPressurePlate,
        		'A',inverter
        });
        GameRegistry.addRecipe(livingDetector, new Object[]{
        		"X",
        		"Y",
        		"X",
        		'X',spiderEye,
        		'Y',anythingDetector
        });
        GameRegistry.addShapelessRecipe(playerDetector, livingDetector, blazeRod);
        GameRegistry.addShapelessRecipe(ownerDetector, playerDetector, enderEye);
        
   	}
}

