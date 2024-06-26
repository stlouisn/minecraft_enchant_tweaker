package com.adibarra.enchanttweaker.mixin.server.tweak;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @description Allow tridents to be enchanted with knockback and looting.
 * @environment Server
 */
@Mixin(value=Enchantment.class)
public abstract class TridentWeaponsMixin {

    @Inject(
        method="isAcceptableItem(Lnet/minecraft/item/ItemStack;)Z",
        at=@At("HEAD"),
        cancellable=true)
    private void enchanttweaker$tridentWeapons$allowLooting(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        boolean isTrident = stack.getItem() instanceof TridentItem;
        if (!isTrident) return;

        Enchantment enchantment = (Enchantment) (Object) this;
        if (enchantment == Enchantments.LOOTING) {
            cir.setReturnValue(true);
        }
    }
}
