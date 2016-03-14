package com.fatalitiii.adventureplus;

import com.fatalitiii.adventureplus.Blocks.AdventureBlocks;
import com.fatalitiii.adventureplus.Utils.*;

import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.VERSION, dependencies = ModInfo.DEPENDENCIES)
public class AdventurePlus {

	@Instance(ModInfo.MOD_ID)
	public static AdventurePlus instance;

	@SidedProxy(clientSide = ModInfo.Client_Proxy_Class, serverSide = ModInfo.Server_Proxy_Class)
	public static CommonProxy proxy;

	public static final AdventureTab tab = new AdventureTab("tabAdventure");

	public static SimpleNetworkWrapper channel;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModMetadata data = event.getModMetadata();
		data.autogenerated = false;
		data.name = EnumChatFormatting.RED + "Adventure Plus";
		data.credits = EnumChatFormatting.BLUE + "Fatalitiii";
		data.authorList.add("Fatalitiii");
		data.description = EnumChatFormatting.RED + "Adventure Plus " + EnumChatFormatting.WHITE
				+ "is a mod developed for adventure map creaters so that they can add new ways to enhance their maps. But also more ways to help in the maps development.";
		data.logoFile = "assets/adventureplus/textures/misc/logo.png";

		AdventureBlocks.init();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		channel = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.CHANNEL);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
		proxy.registerRenders();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

}
