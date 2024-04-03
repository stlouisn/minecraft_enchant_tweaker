package com.adibarra.enchanttweaker.mixin.server.registry;

import net.minecraft.enchantment.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
//import net.minecraft.registry.Registries;
//import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
//import java.util.Optional;

import static com.adibarra.enchanttweaker.EnchantRegistry.addEnchantmentEntry;

/**
 * @description Registers enchantments to make them available at the enchantment table
 * @environment Server
 */
@Mixin(value=EnchantmentHelper.class)
public abstract class TridentWeaponsHelperMixin {

    @Inject(
        method = "getPossibleEntries",
        at = @At("RETURN"))
    private static void addTridentWeaponsEnchantments(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {

        List<EnchantmentLevelEntry> entries = cir.getReturnValue();

        if (stack.getItem() instanceof TridentItem) {

            // vanilla enchantments
            addEnchantmentEntry(entries, power, Enchantments.LOOTING);

//            // modded enchantments
//            for (Identifier id : Registries.ENCHANTMENT.getIds()) {
//                if (!id.getNamespace().equals("minecraft")) {
//                    Optional<Enchantment> enchantment = Registries.ENCHANTMENT.getOrEmpty(id);
//                    if (enchantment.isPresent() && enchantment.get().target == EnchantmentTarget.WEAPON) {
//                        addEnchantmentEntry(entries, power, enchantment.get());
//                    }
//                }
//            }
        }
    }
}
