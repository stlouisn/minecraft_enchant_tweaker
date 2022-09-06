package com.adibarra.enchanttweaker.mixin.enchantments.custom.channeling_tridents_love_rain;

import com.adibarra.enchanttweaker.EnchantTweaker;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value=TridentEntity.class, priority=1543)
public abstract class ChannelingTridentsLoveRainMixin extends PersistentProjectileEntity {

    @Shadow
    private ItemStack tridentStack;

    protected ChannelingTridentsLoveRainMixin(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author adibarra
     * @return Changes channeling enchantment behavior depending on level
     */
    @SuppressWarnings("unused")
    @ModifyExpressionValue(method="onEntityHit(Lnet/minecraft/util/hit/EntityHitResult;)V", at=@At(value="INVOKE", target="Lnet/minecraft/world/World;isThundering()Z"))
    private boolean enchanttweaker$channelingTridentsLoveRain(boolean original) {
        boolean tweakEnabled = EnchantTweaker.getConfig().getOrDefault("channeling_tridents_love_rain", true);

        if(EnchantTweaker.MOD_ENABLED && tweakEnabled) {
            if(EnchantmentHelper.getLevel(Enchantments.CHANNELING, this.tridentStack) > 1) {
                return (original || this.world.isRaining());
            }
        }
        return original;
    }
}