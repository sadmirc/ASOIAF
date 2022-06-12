package com.sadmirc.asoiaf.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemCraftedEvent;

public class ValSteelSword extends SwordItem {

	public ValSteelSword(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
		super(p_43269_, p_43270_, p_43271_, p_43272_);
		//add enchantment by default
		// TODO Auto-generated constructor stub
	}
	/*
	@Override
	public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
		// TODO Auto-generated method stub
		System.out.println("Swung");
		return super.onEntitySwing(stack, entity);
	}
	*/
	@Override
	public void onCraftedBy(ItemStack is, Level world, Player p) {
		// TODO Auto-generated method stub
		if (!world.isClientSide()) {
			System.out.println("Crafted by server");
			is.enchant(Enchantments.SMITE, 5);
		}
		super.onCraftedBy(is, world, p);
	}
}
