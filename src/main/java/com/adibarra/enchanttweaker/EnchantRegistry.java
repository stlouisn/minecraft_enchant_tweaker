package com.adibarra.enchanttweaker;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;

import java.util.List;

public class EnchantRegistry {

    public static void addEnchantmentEntry(List<EnchantmentLevelEntry> entries, int power, Enchantment enchantment) {

        // don't add if already in the pool
        if (entries.stream().anyMatch(entry -> entry.enchantment == enchantment)) return;

        // add appropriate enchantment level for the given power
        for (int level = enchantment.getMaxLevel(); level >= enchantment.getMinLevel(); level--) {
            if (enchantment.getMinPower(level) <= power && power <= enchantment.getMaxPower(level)) {
                entries.add(new EnchantmentLevelEntry(enchantment, level));
                break;
            }
        }
    }

}
