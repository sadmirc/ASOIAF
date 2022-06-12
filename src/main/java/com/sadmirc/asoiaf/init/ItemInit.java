package com.sadmirc.asoiaf.init;

import com.google.common.base.Supplier;
import com.sadmirc.asoiaf.Asoiaf;
import com.sadmirc.asoiaf.item.ValSteelSword;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Asoiaf.modId);
	public static final RegistryObject<Item> valSteel = register("valyrian_steel", () -> new Item(new Item.Properties().tab(Asoiaf.tutorialTab).setNoRepair().fireResistant()));
	public static final RegistryObject<Item> longClaw = register("longclaw", () -> new ValSteelSword(TierInit.valSteel, 3, 1.5f, new Item.Properties().stacksTo(1).tab(Asoiaf.tutorialTab)));
	
	private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item) {
		return ITEMS.register(name, item);
	}	
}