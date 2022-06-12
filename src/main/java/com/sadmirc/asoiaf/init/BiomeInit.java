package com.sadmirc.asoiaf.init;

import com.sadmirc.asoiaf.Asoiaf;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BiomeInit {
	public static final DeferredRegister<Biome> biomeRegistry = DeferredRegister.create(ForgeRegistries.BIOMES, Asoiaf.modId);
	public static final RegistryObject<Biome> testBiome = biomeRegistry.register("test_biome", () -> Biome.BiomeBuilder.from(getBiomeFromName("plains")).build());
	private static ResourceKey<Biome> register(String biomeRegistry) {
		return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(Asoiaf.modId, biomeRegistry));
	}
	public static Biome getBiomeFromName(String name)
	{
		return ForgeRegistries.BIOMES.getValue(new ResourceLocation(name));
	}
}