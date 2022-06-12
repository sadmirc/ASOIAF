package com.sadmirc.asoiaf.init;

import com.sadmirc.asoiaf.Asoiaf;
import com.sadmirc.asoiaf.world.biome.BiomeProvider;
import com.sadmirc.asoiaf.world.biome.ChunkGen;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.StructureSet;

public class DimensionInit {
	public static final ResourceKey<Level> dimensionLevel = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(Asoiaf.modId, "westeros"));
	public static final ResourceLocation westerosStructureRl = new ResourceLocation(Asoiaf.modId, "westeros_structure_set");
	public static final TagKey<StructureSet> westerosStructureSet = TagKey.create(Registry.STRUCTURE_SET_REGISTRY, westerosStructureRl);
	public static void register() {
		Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(Asoiaf.modId, "westeros_chunkgen"), ChunkGen.codec);
		Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(Asoiaf.modId, "biomes"), BiomeProvider.codec);
		System.out.println(Registry.CHUNK_GENERATOR.containsKey(new ResourceLocation(Asoiaf.modId, "westeros_chunkgen")) + " westeros registry " + new ResourceLocation(Asoiaf.modId, "westeros_chunkgen").getPath());
		//prints true and everything is good
	}
}
