package com.sadmirc.asoiaf.event;

import java.util.List;

import com.sadmirc.asoiaf.Asoiaf;
import com.sadmirc.asoiaf.init.ItemInit;
import com.sadmirc.asoiaf.misc.MathFuncs;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Asoiaf.modId)
public class EventHandler {
	@SubscribeEvent
	public static void valSteelTrade(VillagerTradesEvent event) {
		if (event.getType() == VillagerProfession.WEAPONSMITH) {
			int level = MathFuncs.randomInt(2, 4);
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
			trades.get(level).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, MathFuncs.randomInt(20, 40)), new ItemStack(ItemInit.longClaw.get(), 1), 1, 3, 2f));
			System.out.println("Traded");
		}
	}
}
