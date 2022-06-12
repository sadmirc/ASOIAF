package com.sadmirc.asoiaf.world.biome;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.mojang.serialization.Codec;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate.Sampler;

public class BiomeProvider extends BiomeSource {
	public static Codec<BiomeProvider> codec = RegistryOps.retrieveRegistry(Registry.BIOME_REGISTRY).xmap(BiomeProvider::new, BiomeProvider::getBiomeRegistry).codec();
	public Holder<Biome> biome;
	private final Registry<Biome> biomeRegistry;
	private static List<ResourceKey<Biome>> spawn = Collections.singletonList(Biomes.PLAINS);
	
	private static List<Holder<Biome>> getStartBiomes(Registry<Biome> registry) {
		return spawn.stream().map(s -> registry.getHolderOrThrow(ResourceKey.create(BuiltinRegistries.BIOME.key(), s.location()))).collect(Collectors.toList());
	}	
	
	public BiomeProvider(Registry<Biome> biomeRegistry) {
		super(getStartBiomes(biomeRegistry));
		this.biomeRegistry = biomeRegistry;
		biome = biomeRegistry.getHolderOrThrow(Biomes.PLAINS);
	}
	
	@Override
	protected Codec<? extends BiomeSource> codec() {
		// TODO Auto-generated method stub
		return codec;
	}
	//public static Codec<BiomeProvider> cod() {
		//return codec;
	//}
	@Override
	public BiomeSource withSeed(long p_47916_) {
		// TODO Auto-generated method stub
		return this;
	}
	@Override
	public Holder<Biome> getNoiseBiome(int p_204238_, int p_204239_, int p_204240_, Sampler p_204241_) {
		// TODO Auto-generated method stub
		return biome;
	}
	
	public Registry<Biome> getBiomeRegistry() {
		return biomeRegistry;
	}
	
}
