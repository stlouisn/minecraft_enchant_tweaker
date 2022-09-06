package com.adibarra.enchanttweaker;

import com.adibarra.enchanttweaker.commands.EnchantTweakerCommands;
import com.magistermaks.simpleconfig.SimpleConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnchantTweaker implements ModInitializer {

    public static SimpleConfig config;
    public static final Logger LOGGER = LoggerFactory.getLogger("EnchantTweaker");

    public static boolean MOD_ENABLED;

    @Override
    public void onInitialize() {
        EnchantTweaker.config = SimpleConfig
                .of("enchant-tweaker")
                .provider(EnchantTweaker::getDefaultConfig)
                .request();

        EnchantTweaker.MOD_ENABLED = getConfig().getOrDefault("mod_enabled", true);
        if (EnchantTweaker.MOD_ENABLED) {
            LOGGER.info("EnchantTweaker Enabled");
        } else {
            LOGGER.warn("EnchantTweaker Disabled");
        }

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> EnchantTweakerCommands.register(dispatcher));
    }

    public static SimpleConfig getConfig() {
        return config;
    }

    @SuppressWarnings("SameReturnValue")
    public static String getDefaultConfig(String filename) {
        if (filename.equals("enchant-tweaker")) {
            return """
                   ####################################################################################################
                   #### Enchant Tweaker Config
                   ####################################################################################################
                   
                   
                   
                       ################################################################################
                       #### Master Switch:
                       ####
                       ####    Enable or disable the mod.
                                   mod_enabled=true
                       ####
                       ################################################################################
                   
                   
                   
                       ################################################################################
                       #### Enchanting Tweaks:
                       #### Multiple small enchantment related tweaks.
                       ####
                       ####
                       ####        ########################################
                       ####        ##  Allow god armor:
                       ####        ##      Allow the combination of damage negation enchantments that normally
                       ####        ##      can not be added together. Enabling this tweak allows you to combine the
                       ####        ##      following enchantments: Protection, Blast Protection, Fire Protection,
                       ####        ##      and Projectile Projection.
                                               allow_god_armor=true
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  Allow god weapons:
                       ####        ##      Allow the combination of damage enhancement enchantments that normally
                       ####        ##      can not be added together. Enabling this tweak allows you to combine the
                       ####        ##      following enchantments: Sharpness, Smite, and Bane of Arthropods.
                                               allow_god_weapons=true
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  Tridents are weapons:
                       ####        ##      Allow the addition of some weapon enchantments that normally can not be
                       ####        ##      added to tridents. Enabling this tweak allows you to add the following
                       ####        ##      enchantments to tridents: Sharpness, Smite, Bane of Arthropods,
                       ####        ##      Fire Aspect, Knockback, and Looting.
                                               tridents_are_weapons=true
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  Axes are weapons:
                       ####        ##      Allow the addition of some weapon enchantments that that normally can not
                       ####        ##      be added onto axes. Enabling this tweak allows you to add the following
                       ####        ##      enchantments to axes: Fire Aspect, Knockback, and Looting.
                                               axes_are_weapons=true
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  Axes are not tools:
                       ####        ##      Normally axes are treated as tools even when used in combat. This causes
                       ####        ##      them to take double durability damage when they are used in combat. Enabling
                       ####        ##      this tweak removes the double durability damage penalty.
                                               axes_are_not_tools=true
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  Names are cheap:
                       ####        ##      Normally renaming an item will cost a similar amount of levels as adding an
                       ####        ##      enchantment onto an item. Enabling this will force the cost for renaming items
                       ####        ##      to always be one level. I don't enjoy spending nineteen levels to rename
                       ####        ##      a pickaxe... again.
                                               names_are_cheap=true
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  Nothing is expensive:
                       ####        ##      Normally once an item's enchant/repair cost reaches 40 levels you can no longer
                       ####        ##      enchant or repair it. Enabling this tweak alters the "Too Expensive!" mechanic
                       ####        ##      in the anvil changing the level it activates at to one of your choosing.
                                               nothing_is_expensive=true
                                               this_is_too_expensive=2147483647
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  Prior work goes unpaid:
                       ####        ##      Normally when enchanting/repairing an item, each operation will double the
                       ####        ##      cost of the next action. Enabling this tweak completely disables the prior
                       ####        ##      work penalty for items enchanted/repaired at an anvil. This means that
                       ####        ##      the enchant/repair cost for an item will always stay at the minimum value for
                       ####        ##      that given procedure.
                                               prior_work_goes_unpaid=false
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  Prior work is cheaper:
                       ####        ##      Normally when enchanting/repairing an item, each operation will double the
                       ####        ##      cost of the next action. Enabling this tweak will reduce the penalty,
                       ####        ##      this is preferred to fully disabling it. The penalty will continue
                       ####        ##      increasing, but at a rate decided by you. The coefficient will replace the
                       ####        ##      2 in the cost formula (next_cost=2*current_cost+1).
                                               prior_work_is_cheaper=true
                                               prior_work_coefficient=1.33
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  Sturdy anvils:
                       ####        ##      Normally an anvil has a 12% chance to take damage when used. Enabling this
                       ####        ##      tweak will modify the damage chance to a value set by you.
                                               anvils_last_longer=true
                                               anvil_damage_chance=0.06
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  Whats the max level again:
                       ####        ##      Normally you know what the maximum level is for any given enchantment. However
                       ####        ##      if the maximum levels are changed your only choice is checking the config or
                       ####        ##      trial and error if you are a regular player on a server. Enabling this tweak
                       ####        ##      displays tooltips of max level enchantments in yellow with a "charged" effect.
                                               whats_the_max_level_again=true
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  When in rome:
                       ####        ##      Normally the tooltips stop showing enchantment levels using roman numerals
                       ####        ##      once they pass level 10. This tweak adds numerals all the way up to level 50.
                       ####        ##          ALWAYS ENABLED
                       ####        ##
                       ####        ########################################
                       ####
                       ####
                       ################################################################################
                   
                   
                   
                       ################################################################################
                       #### More Enchantments:
                       #### Adds more enchantments to the game and tweaks some existing ones.
                       ####
                       ####
                       ####        ########################################
                       ####        ##  Bows are magic (infinity fix):
                       ####        ##      Normally you need to keep a lone arrow in your inventory for the infinity
                       ####        ##      enchantment to function. Enabling this tweak allows infinity bows to shoot
                       ####        ##      without requiring arrows. No need to waste an entire slot for a single arrow.
                                               bows_are_magic=true
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  Infinite Mending:
                       ####        ##      Normally you need to choose between having either mending or infinity.
                       ####        ##      Enabling this tweak allows both enchantments to coexist.
                                               infinite_mending=true
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  Loyal tridents fear the void:
                       ####        ##      Normally if you throw a trident with the loyalty enchantment into the void
                       ####        ##      it is lost forever. Enabling this tweak allows tridents with the loyalty
                       ####        ##      enchantment to return to you if you throw them into the void.
                                               loyal_tridents_fear_the_void=true
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  Thorns don't damage armor:
                       ####        ##      Normally if you are hit while wearing thorns armor, it has a chance of
                       ####        ##      activating the enchantment. Upon activation it reduces the armor's durability
                       ####        ##      by two point in addition to the durability point lost from getting hit.
                       ####        ##      This massively reduces the life expectancy of armor (without unbreaking)
                       ####        ##      which are enchanted with thorns. Enabling this tweak will disable the damage
                       ####        ##      the enchantment does to the armor it is on.
                                               thorns_dont_damage_armor=true
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  Soul speed doesn't damage armor:
                       ####        ##      Normally when running on soul sand or soul soil each block which is stepped
                       ####        ##      on has a 4% chance to reduce the armor's durability (without unbreaking).
                       ####        ##      Enabling this tweak will disable the damage dealt to armor by the enchantment.
                                               soul_speed_doesnt_damage_armor=true
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  Mended mending [Requires lvl cap increase]:
                       ####        ##      Normally mending only has one level. Enabling this tweak will make mending
                       ####        ##      repair items at a rate proportional to its level. Mending I is slower than
                       ####        ##      it is in vanilla, Mending II the same, and Mending III is faster.
                                               mended_mending=true
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  Channeling tridents love rain [Requires lvl cap increase]:
                       ####        ##      Normally if you throw a trident with the channeling I enchantment during a
                       ####        ##      thunderstorm it will summon lightning if it hits a mob. Enabling this tweak
                       ####        ##      will cause tridents with Channeling II (if enabled) to summon lightning
                       ####        ##      during normal rain.
                                               channeling_tridents_love_rain=true
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  Multi-multishot [Requires lvl cap increase]:
                       ####        ##      Normally if you fire a crossbow with multishot it will only shoot 3 arrows.
                       ####        ##      Enabling this tweak adds support for Multishot II which allows you to fire
                       ####        ##      5 arrows per crossbow shot.
                                               multi_multishot=true
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  Hotter arrows [Requires lvl cap increase]:
                       ####        ##      Normally the Flame enchantment only has one level. Enabling this tweak adds
                       ####        ##      support for Flame II increases the duration for which a target is on fire.
                                               hotter_arrows=true
                       ####        ##
                       ####        ########################################
                       ####
                       ####
                       ################################################################################
                   
                   
                   
                       ################################################################################
                       #### Modify Max Enchantment Levels:
                       #### Accepted values are:
                       ####     Any integer value: 1 - 255 (you can go higher but it will act like lvl.255)
                       ####     Disable enchant: 0
                       ####     Default: -1
                       ####
                       ####
                       ####        ########################################
                       ####        ##  Unsupported Enchantments (No special effects past lvl.1):
                                           curse_of_binding=-1
                                           curse_of_vanishing=-1
                                           silk_touch=-1
                                           infinity=-1
                                           aqua_affinity=-1
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##  General Enchantments:
                                           mending=3
                                           unbreaking=10
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##     Tool Enchantments:
                                           efficiency=10
                                           fortune=5
                                           luck_of_the_sea=5
                                           lure=-1
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##     Armor Enchantments:
                                           protection=10
                                           blast_protection=10
                                           fire_protection=10
                                           projectile_protection=10
                                           feather_falling=7
                                           soul_speed=5
                                           swift_sneak=4
                                           thorns=5
                                           respiration=10
                                           depth_strider=-1
                                           frost_walker=-1
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##     Melee Enchantments:
                                           sharpness=10
                                           smite=10
                                           bane_of_arthropods=10
                                           fire_aspect=3
                                           sweeping_edge=5
                                           looting=5
                                           knockback=-1
                                           impaling=-1
                                           loyalty=5
                       ####        ##
                       ####        ########################################
                       ####
                       ####        ########################################
                       ####        ##     Ranged Enchantments:
                                           power=10
                                           flame=2
                                           multishot=3
                                           channeling=2
                                           punch=-1
                                           piercing=5
                                           riptide=-1
                                           quick_charge=-1
                       ####        ##
                       ####        ########################################
                       ####
                       ####
                       ################################################################################
                   
                   
                   
                   ####################################################################################################
                   #### End of Enchant Tweaker config
                   ####################################################################################################
                   """;
        } else {
            return "";
        }
    }
}
