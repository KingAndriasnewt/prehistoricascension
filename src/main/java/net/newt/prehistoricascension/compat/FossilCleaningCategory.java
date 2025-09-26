package net.newt.prehistoricascension.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.newt.prehistoricascension.PrehistoricAscension;
import net.newt.prehistoricascension.block.ModBlocks;
import net.newt.prehistoricascension.recipe.FossilCleaningRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FossilCleaningCategory implements IRecipeCategory<FossilCleaningRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(PrehistoricAscension.MOD_ID, "fossil_cleaning");
    public static final ResourceLocation TEXTURE = new ResourceLocation(PrehistoricAscension.MOD_ID,
            "textures/gui/fossil_cleaner_gui.png");

    public static final RecipeType<FossilCleaningRecipe> FOSSIL_CLEANING_TYPE =
            new RecipeType<>(UID, FossilCleaningRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public FossilCleaningCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 140, 83);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.FOSSIL_CLEANER.get()));
    }

    @Override
    public RecipeType<FossilCleaningRecipe> getRecipeType() {
        return FOSSIL_CLEANING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.prehistoricascension.fossil_cleaner");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FossilCleaningRecipe recipe, IFocusGroup focuses) {
        // Water Bucket / Bottle Slot
        builder.addSlot(RecipeIngredientRole.INPUT, 17, 17)
                .addIngredients(recipe.getWaterIngredient());

        // Fossil Slot
        builder.addSlot(RecipeIngredientRole.INPUT, 79, 17)
                .addIngredients(recipe.getIngredients().get(1));

        // Output Slot
        RegistryAccess registryAccess = Minecraft.getInstance().level != null ?
                Minecraft.getInstance().level.registryAccess() : null;

        if (registryAccess != null) {
            List<ItemStack> allOutputs = recipe.getAllPossibleOutputs(registryAccess);

            // Output slots
            builder.addSlot(RecipeIngredientRole.OUTPUT, 56, 58)
                    .addItemStacks(allOutputs);

            builder.addSlot(RecipeIngredientRole.OUTPUT, 79, 58)
                    .addItemStacks(allOutputs);

            builder.addSlot(RecipeIngredientRole.OUTPUT, 102, 58)
                    .addItemStacks(allOutputs);
        } else {
            builder.addSlot(RecipeIngredientRole.OUTPUT, 56, 58)
                    .addItemStack(recipe.getResultItem(null));

            builder.addSlot(RecipeIngredientRole.OUTPUT, 79, 58)
                    .addItemStack(recipe.getResultItem(null));

            builder.addSlot(RecipeIngredientRole.OUTPUT, 102, 58)
                    .addItemStack(recipe.getResultItem(null));
        }
    }
}