package de.amondev.strider.instance;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class InstanceChunkGenerator extends ChunkGenerator {

    @Override
    public @NotNull List<BlockPopulator> getDefaultPopulators(@NotNull World world) {
        return Collections.<BlockPopulator>emptyList();
    }

    @Override
    public @NotNull ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int chunkX, int chunkZ, @NotNull BiomeGrid biome) {
        ChunkData chunkData = super.createVanillaChunkData(world, chunkX, chunkZ);

        for(int x = 0; x < 16; x++) {
            for(int z = 0; z < 16; z++) {
                biome.setBiome(x, z, Biome.PLAINS);
            }
        }

        return chunkData;
    }

    @Override
    public boolean canSpawn(@NotNull World world, int x, int z) {
        return false;
    }

    @Override
    public @Nullable Location getFixedSpawnLocation(@NotNull World world, @NotNull Random random) {
        return new Location(world, 0, 100, 0);
    }
}
