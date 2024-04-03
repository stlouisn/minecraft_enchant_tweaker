package com.adibarra.enchanttweaker;

import com.adibarra.utils.ADConfig;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.objectweb.asm.tree.ClassNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public final class ETMixinPlugin implements IMixinConfigPlugin {

    private static int numMixins = 0;
    private static boolean MOD_ENABLED = false;
    private static ADConfig CONFIG;
    private static final Logger LOGGER = LoggerFactory.getLogger(EnchantTweaker.MOD_NAME);
    private static final Map<String, String> KEYS = new HashMap<>();

    static {
        KEYS.put("MoreMendingMixin",           "more_mending");
        KEYS.put("MoreMultishotMixin",         "more_multishot");

        KEYS.put("AxesNotToolsMixin",          "axes_not_tools");
        KEYS.put("AxeWeaponsMixin",            "axe_weapons");
        KEYS.put("AxeWeaponsHelperMixin",      "axe_weapons");
        KEYS.put("BetterMendingMixin",         "better_mending");
        KEYS.put("BowInfinityFixMixin",        "bow_infinity_fix");
        KEYS.put("InfiniteMendingMixin",       "infinite_mending");
        KEYS.put("LoyalVoidTridentsMixin",     "loyal_void_tridents");
        KEYS.put("MultishotPiercingMixin",     "multishot_piercing");
        KEYS.put("NoSoulSpeedBacklashMixin",   "no_soul_speed_backlash");
        KEYS.put("NoThornsBacklashMixin",      "no_thorns_backlash");
        KEYS.put("SwordEfficientToolsMixin",   "sword_efficient_tools");
        KEYS.put("TridentWeaponsMixin",        "trident_weapons");
        KEYS.put("TridentWeaponsHelperMixin",  "trident_weapons");

        KEYS.put("DamageEnchantMixin",         "capmod_enabled");
        KEYS.put("GenericEnchantMixin",        "capmod_enabled");
        KEYS.put("LuckEnchantMixin",           "capmod_enabled");
        KEYS.put("ProtectionEnchantMixin",     "capmod_enabled");
        KEYS.put("SpecialEnchantMixin",        "capmod_enabled");

    }

    @Override
    public void onLoad(String mixinPackage) {
        reloadConfig();
        MOD_ENABLED = CONFIG.getOrDefault("mod_enabled", false);
        LOGGER.info(EnchantTweaker.PREFIX + "Mod {}",
            MOD_ENABLED ? "enabled! Enabling mixins..." : "disabled! No mixins will be applied.");
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (!MOD_ENABLED) return false;

        String mixinName = mixinClassName.substring(mixinClassName.lastIndexOf('.') + 1);
        return getMixinConfig(mixinName);
    }

    public static void reloadConfig() {
        if (CONFIG != null) LOGGER.info(EnchantTweaker.PREFIX + "Reloading config...");

        String internalDefaultConfigPath = "assets/" + EnchantTweaker.MOD_ID + "/enchant-tweaker.properties";
        CONFIG = new ADConfig(EnchantTweaker.MOD_NAME, "enchant-tweaker.properties", internalDefaultConfigPath);
    }

    public static String getMixinKey(String mixinName) {
        String key = KEYS.getOrDefault(mixinName, null);
        if (key == null) LOGGER.error(EnchantTweaker.PREFIX + "Unknown mixin name: {}", mixinName);
        return key;
    }

    public static boolean getMixinConfig(String mixinName) {
        return CONFIG.getOrDefault(getMixinKey(mixinName), false);
    }

    public static int getNumMixins() {
        return numMixins;
    }

    public static ADConfig getConfig() {
        return CONFIG;
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        numMixins++;
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) { }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }
}
