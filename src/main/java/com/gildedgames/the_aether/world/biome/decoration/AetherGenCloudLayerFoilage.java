package com.gildedgames.the_aether.world.biome.decoration;

import java.util.Random;

import com.gildedgames.the_aether.blocks.natural.BlockAercloudLayer;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class AetherGenCloudLayerFoilage extends WorldGenerator {

	private Block cloudState;

	public AetherGenCloudLayerFoilage() {
		super();
	}

	public void setPlantBlock(Block state) {
		this.cloudState = state;
		
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		
		
		
		for (int l = 0; l < 64; l++) {
			int i1 = (x + random.nextInt(8)) - random.nextInt(8);
			int j1 = (y + random.nextInt(4)) - random.nextInt(4);
			int k1 = (z + random.nextInt(8)) - random.nextInt(8);

			if (world.isAirBlock(i1, j1, k1) && ((BlockAercloudLayer) this.cloudState).canBlockStay(world, i1, j1, k1)) {
				this.setBlockAndNotifyAdequately(world, i1, j1, k1, this.cloudState, 0);
				}
			}
		
		return true;
	}
		
}