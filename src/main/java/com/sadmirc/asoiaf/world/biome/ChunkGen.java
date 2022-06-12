package com.sadmirc.asoiaf.world.biome;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sadmirc.asoiaf.init.DimensionInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.Climate.Sampler;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.GenerationStep.Carving;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.RandomSupport;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.structure.StructureSet;

public class ChunkGen extends ChunkGenerator {
	private static final Codec<Settings> settingsCodec = RecordCodecBuilder.create(instance ->
		instance.group(
					Codec.INT.fieldOf("base").forGetter(Settings::baseHeight),
					Codec.FLOAT.fieldOf("verticalvarianace").forGetter(Settings::verticalVariance),
					Codec.FLOAT.fieldOf("horizontalvariance").forGetter(Settings::horizontalVariance)
				).apply(instance, Settings::new));
	
	public static final Codec<ChunkGen> codec = RecordCodecBuilder.create(instance ->
		instance.group(
			RegistryOps.retrieveRegistry(Registry.STRUCTURE_SET_REGISTRY).forGetter(ChunkGen::getStructureSetRegistry),
			RegistryOps.retrieveRegistry(Registry.BIOME_REGISTRY).forGetter(ChunkGen::getBiomeRegistry),
			settingsCodec.fieldOf("settings").forGetter(ChunkGen::getSettings)
		).apply(instance, ChunkGen::new));
	
	public ChunkGen(Registry<StructureSet> structureSetRegistry, Registry<Biome> biomeRegistry, Settings settings) {
		super(structureSetRegistry, getSet(structureSetRegistry), new BiomeProvider(biomeRegistry));
		this.settings = settings;
	}
	
	private final Settings settings;
	
	private record Settings(int baseHeight, float verticalVariance, float horizontalVariance) {}
	
	public Settings getSettings() {
		return settings;
	}
	
	public Registry<Biome> getBiomeRegistry() {
		return ((BiomeProvider) biomeSource).getBiomeRegistry();
	}
	
	public Registry<StructureSet> getStructureSetRegistry() {
		return structureSets;
	}
	
	private static Optional<HolderSet<StructureSet>> getSet(Registry<StructureSet> structureSetRegistry) {
		HolderSet.Named<StructureSet> structureSet = structureSetRegistry.getOrCreateTag(TagKey.create(Registry.STRUCTURE_SET_REGISTRY, DimensionInit.westerosStructureRl));
		return Optional.of(structureSet);
	}

	@Override
	protected Codec<? extends ChunkGenerator> codec() {
		// TODO Auto-generated method stub
		return codec;
	}

	@Override
	public ChunkGenerator withSeed(long p_62156_) {
		// TODO Auto-generated method stub
		return new ChunkGen(getStructureSetRegistry(), getBiomeRegistry(), settings);
	}

	@Override
	public Sampler climateSampler() {
		// TODO Auto-generated method stub
		return new Climate.Sampler(DensityFunctions.constant(0.0), DensityFunctions.constant(0.0), DensityFunctions.constant(0.0), DensityFunctions.constant(0.0), DensityFunctions.constant(0.0), DensityFunctions.constant(0.0), Collections.emptyList());
	}

	@Override
	public void applyCarvers(WorldGenRegion p_187691_, long p_187692_, BiomeManager p_187693_,
			StructureFeatureManager p_187694_, ChunkAccess p_187695_, Carving p_187696_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildSurface(WorldGenRegion region, StructureFeatureManager sFm, ChunkAccess chunkAccess) {
		// TODO Auto-generated method stub
		BlockState bedrock = Blocks.BEDROCK.defaultBlockState();
		BlockState stone = Blocks.STONE.defaultBlockState();
		ChunkPos cP = chunkAccess.getPos();
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
		int x;
		int z;
		for (x = 0; x < 16; x++) {
			for (z = 0; z < 16; z++) {
				chunkAccess.setBlockState(pos.set(x, 0, z), bedrock, false);
			}
		}
		for (x = 0; x < 16; x++) {
			for (z = 0; z < 16; z++) {
				for (int y = 1; y < getHeightAt(settings.baseHeight, settings.verticalVariance, settings.horizontalVariance, cP.x * 16 + x, cP.z * 16 + z); y++) {
					chunkAccess.setBlockState(pos.set(x, y, z), stone, false);
				}
			}
		}
 		
	}

	@Override
	public void spawnOriginalMobs(WorldGenRegion level) {
		// TODO Auto-generated method stub
		ChunkPos cP = level.getCenter();
		Holder<Biome> b = level.getBiome(cP.getWorldPosition().atY(level.getMaxBuildHeight() - 1));
		WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(RandomSupport.seedUniquifier()));
		random.setDecorationSeed(level.getSeed(), cP.getMinBlockX(), cP.getMinBlockZ());
		NaturalSpawner.spawnMobsForChunkGeneration(level, b, cP, random);
	}

	@Override
	public int getGenDepth() {
		// TODO Auto-generated method stub
		return 256;
	}

	@Override
	public CompletableFuture<ChunkAccess> fillFromNoise(Executor p_187748_, Blender p_187749_,
			StructureFeatureManager p_187750_, ChunkAccess chunkAccess) {
		// TODO Auto-generated method stub
		return CompletableFuture.completedFuture(chunkAccess);
	}

	@Override
	public int getSeaLevel() {
		// TODO Auto-generated method stub
		return 63;
	}

	@Override
	public int getMinY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBaseHeight(int x, int z, Types types, LevelHeightAccessor lHa) {
		// TODO Auto-generated method stub
		return getHeightAt(settings.baseHeight, settings.verticalVariance, settings.horizontalVariance, x, z);
	}

	private int getHeightAt(int baseHeight, float verticalVariance, float horizontalVariance, int x, int z) {
		// TODO Auto-generated method stub
		return (int) (baseHeight + Math.sin(x / horizontalVariance) + verticalVariance + Math.cos(z / horizontalVariance) + verticalVariance);
	}

	@Override
	public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor lHa) {
		// TODO Auto-generated method stub
		int y = getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, lHa);
		BlockState stone = Blocks.STONE.defaultBlockState();
		BlockState[] states = new BlockState[y];
		for (int i = 1; i < y; i++) {
			states[i] = stone;
		}
		return new NoiseColumn(lHa.getMinBuildHeight(), states);
	}

	@Override
	public void addDebugScreenInfo(List<String> p_208054_, BlockPos p_208055_) {
		// TODO Auto-generated method stub
		
	}
	
	
}
