package com.adibarra.enchanttweaker.mixin.server.tweak;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @description Remove extra self-damage from swords when used to break blocks tagged as #sword_efficient.
 * @environment Server
 */
@Mixin(value=SwordItem.class)
public abstract class SwordEfficientToolsMixin {

    @Inject(
        method="postMine(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/LivingEntity;)Z",
        at=@At("HEAD"),
        cancellable=true)
    private void enchanttweaker$swordEfficientTools$modifySelfDamage(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, CallbackInfoReturnable<Boolean> cir) {
        if (!world.isClient && state.isIn(BlockTags.SWORD_EFFICIENT) && state.getHardness(world, pos) != 0.0F) {
            stack.damage(1, miner, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            cir.setReturnValue(true);
        }
    }
}
