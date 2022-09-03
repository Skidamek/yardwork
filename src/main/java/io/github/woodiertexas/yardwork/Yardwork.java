package io.github.woodiertexas.yardwork;

import io.github.woodiertexas.yardwork.items.Weedwhacker;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Yardwork implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final TagKey<Block> MACHINE_HARVESTABLE = TagKey.of(Registry.BLOCK_KEY, new Identifier("yardwork", "machine_harvestable"));
	public static final TagKey<Block> NON_MACHINE_HARVESTABLE = TagKey.of(Registry.BLOCK_KEY, new Identifier("yardwork", "non_machine_harvestable"));
	public static final Weedwhacker WEEDWHACKER = new Weedwhacker(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1));

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("yardwork", "weedwhacker"), WEEDWHACKER);
	}
}
