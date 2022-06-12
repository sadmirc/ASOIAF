package com.sadmirc.asoiaf.event;

import com.sadmirc.asoiaf.init.ItemInit;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ServerHandler {
	public static void register() {
		MinecraftForge.EVENT_BUS.register(ServerHandler.class);
	}
	@SubscribeEvent
	public static void valSteelAttack(AttackEntityEvent event) {
		System.out.println("Attacked!");
		if (event.getPlayer().isHolding(ItemInit.longClaw.get())) {
			System.out.println("Longclaw!");
		}
	}
}
