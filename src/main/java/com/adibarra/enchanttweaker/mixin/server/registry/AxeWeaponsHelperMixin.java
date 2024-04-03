package com.adibarra.enchanttweaker.mixin.server.registry;

import java.util.List;
//import java.util.Optional;
import net.minecraft.enchantment.*;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
//import net.minecraft.registry.Registries;
//import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.adibarra.enchanttweaker.EnchantRegistry.addEnchantmentEntry;

/**
 * @description Registers enchantments to make them available at the enchantment table
 * @environment Server
 */
@Mixin(value=EnchantmentHelper.class)
public abstract class AxeWeaponsHelperMixin {

    @Inject(
        method = "getPossibleEntries",
        at = @At("RETURN"))
    private static void addAxeWeaponsEnchantments(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {

        List<EnchantmentLevelEntry> entries = cir.getReturnValue();

        if (stack.getItem() instanceof AxeItem) {

            // vanilla enchantments
            addEnchantmentEntry(entries, power, Enchantments.LOOTING);
            addEnchantmentEntry(entries, power, Enchantments.KNOCKBACK);
            addEnchantmentEntry(entries, power, Enchantments.FIRE_ASPECT);

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
