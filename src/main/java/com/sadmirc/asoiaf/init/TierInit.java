package com.sadmirc.asoiaf.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class TierInit {
	public static final ForgeTier valSteel = new ForgeTier(2, 2000, 1.5f, 6, 15, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemInit.valSteel.get()));	
}
