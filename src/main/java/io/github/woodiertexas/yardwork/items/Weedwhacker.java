package io.github.woodiertexas.yardwork.items;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static io.github.woodiertexas.yardwork.Yardwork.MACHINE_HARVESTABLE;

public class Weedwhacker extends Item implements DyeableItem {
    public Weedwhacker(Settings settings) {
        super(settings);
    }

    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        if (!stack.hasNbt()) {
            stack.setNbt(new NbtCompound());

            NbtCompound weedWhackerNbt = stack.getNbt();
            assert weedWhackerNbt != null;
            if (!weedWhackerNbt.contains("CanDestroy", 9)) {
                NbtList canDestroyList = new NbtList();
                canDestroyList.add(NbtString.of("#yardwork:machine_harvestable"));
                weedWhackerNbt.put("CanDestroy", canDestroyList);
            }
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos position = context.getBlockPos();
        PlayerEntity player = context.getPlayer();

        assert player != null;
        if (!player.isSpectator()) {
            for (BlockPos pos : BlockPos.iterate(position.add(-1, 0, -1), position.add(1, 1, 1))) {
                if (world.getBlockState(pos).isIn(MACHINE_HARVESTABLE)) {
                    world.breakBlock(pos, true, player, 4);
                }
            }
            return ActionResult.CONSUME;
        }
        return ActionResult.PASS;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 999999;
    }
}