package net.newt.prehistoricascension.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.newt.prehistoricascension.PrehistoricAscension;
import net.newt.prehistoricascension.recipe.FossilCleaningRecipe;
import net.newt.prehistoricascension.screen.FossilCleanerScreen;

import java.util.List;

@JeiPlugin
public class JEIPrehistoricAscensionPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(PrehistoricAscension.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
     registration.addRecipeCategories(new FossilCleaningCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<FossilCleaningRecipe> fossilCleaningRecipes = recipeManager.getAllRecipesFor(FossilCleaningRecipe.Type.INSTANCE);
        registration.addRecipes(FossilCleaningCategory.FOSSIL_CLEANING_TYPE, fossilCleaningRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(FossilCleanerScreen.class,27, 50, 20, 20,
            FossilCleaningCategory.FOSSIL_CLEANING_TYPE);

    }
}
