package com.sadmirc.asoiaf.init;

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class OreInit {
	public static final int overworldVein = 2;
	public static final int overwoldAmount = 2;
	public static final int deepslateVein = 4;
	public static final int deepslateAmount = 3;
	public static final int netherVein = 3;
	public static final int netherAmount = 3;
	public static final int endVein = 2;
	public static final int endAmount = 1;
	
	public static final List<OreConfiguration.TargetBlockState> overworldOres = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.valSteelOre.get().defaultBlockState()),
			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.valSteelOre.get().defaultBlockState())
			);
	
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> valOre = FeatureUtils.register("valyrian_steel_ore", Feature.ORE, new OreConfiguration(overworldOres, 8));
	public static final Holder<PlacedFeature> valSteelPlaced = PlacementUtils.register("valyrian_steel_ore_placed", valOre, OrePlacementInit.commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
	
}
