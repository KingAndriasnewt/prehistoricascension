package net.newt.prehistoricascension.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties RAW_SQUID = new FoodProperties.Builder().nutrition(4).alwaysEat()
            .saturationMod(1).effect(() -> new MobEffectInstance(MobEffects.POISON,60), 0.3f).build();
    public static final FoodProperties COOKED_SQUID = new FoodProperties.Builder().nutrition(8).alwaysEat()
            .saturationMod(1).build();
}
