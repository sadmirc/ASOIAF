package com.sadmirc.asoiaf.init;

import java.util.function.Function;

import com.google.common.base.Supplier;
import com.sadmirc.asoiaf.Asoiaf;
import com.sadmirc.asoiaf.block.ValSteelOre;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Asoiaf.modId);
	public static final DeferredRegister<Item> ITEMS = ItemInit.ITEMS;
	public static final RegistryObject<Block> valSteelOre = register("valyrian_steel_ore", () -> new ValSteelOre(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_GRAY).strength(3.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()), object -> () -> new BlockItem(object.get(),  new Item.Properties().tab(Asoiaf.tutorialTab)));
	
	private static <T extends Block> RegistryObject<T> registerBlock(final String name, final Supplier<? extends T> block) {
		return BLOCKS.register(name, block);
	}
	
	private static <T extends Block> RegistryObject<T> register(final String name, final Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
		RegistryObject<T> obj = registerBlock(name, block);
		ITEMS.register(name, item.apply(obj));
		return obj;
	}
}
