package com.sadmirc.asoiaf.init;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

import com.sadmirc.asoiaf.Asoiaf;
@Mod.EventBusSubscriber(modid = Asoiaf.modId)
public class OreGenInit {
    public static void generateOres(final BiomeLoadingEvent event) {
        List<Holder<PlacedFeature>> base =
                event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

        base.add(OreInit.valSteelPlaced);
    }
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
    	generateOres(event);
    }
}