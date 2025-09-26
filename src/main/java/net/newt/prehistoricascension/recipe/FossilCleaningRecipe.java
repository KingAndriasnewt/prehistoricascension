package net.newt.prehistoricascension.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.newt.prehistoricascension.PrehistoricAscension;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FossilCleaningRecipe implements Recipe<SimpleContainer> {
    private final NonNullList<Ingredient> inputItems;
    private final ResourceLocation id;

    public FossilCleaningRecipe(NonNullList<Ingredient> inputItems, ResourceLocation id) {
        this.inputItems = inputItems;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        if (container.getContainerSize() < 5) return false;

        ItemStack waterInput = container.getItem(0);
        boolean waterOk = waterInput.is(Items.WATER_BUCKET) ||
                (waterInput.is(Items.POTION) && PotionUtils.getPotion(waterInput) == Potions.WATER);

        return waterOk && inputItems.size() >= 2 && inputItems.get(1).test(container.getItem(4));
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess registryAccess) {
        return getRandomOutput(registryAccess);
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return new ItemStack(Items.BONE);
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public Ingredient getWaterIngredient() {
        ItemStack waterBucket = new ItemStack(Items.WATER_BUCKET);
        ItemStack waterBottle = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER);
        return Ingredient.of(waterBucket, waterBottle);
    }

    public List<ItemStack> getAllPossibleOutputs(RegistryAccess registryAccess) {
        List<ItemStack> outputs = new ArrayList<>();

        outputs.add(new ItemStack(Items.BONE));
        outputs.add(new ItemStack(Items.BONE_MEAL));
        outputs.add(new ItemStack(Items.COAL));
        outputs.add(new ItemStack(Items.DEEPSLATE));
        outputs.add(new ItemStack(Items.COBBLESTONE));
        outputs.add(new ItemStack(Items.SAND));

        TagKey<Item> fossilTag = TagKey.create(BuiltInRegistries.ITEM.key(),
                new ResourceLocation("prehistoricascension", "fossils"));

        Optional<net.minecraft.core.HolderSet.Named<Item>> fossilItems =
                registryAccess.lookupOrThrow(BuiltInRegistries.ITEM.key()).get(fossilTag);

        if (fossilItems.isPresent()) {
            List<Item> fossils = fossilItems.get().stream()
                    .map(holder -> holder.value())
                    .toList();

            for (Item fossil : fossils) {
                outputs.add(new ItemStack(fossil));
            }
        }

        return outputs;
    }

    private ItemStack getRandomOutput(RegistryAccess registryAccess) {
        List<WeightedEntry<ItemStack>> outputs = new ArrayList<>();
        RandomSource random = RandomSource.create();

        outputs.add(new WeightedEntry<>(new ItemStack(Items.BONE), 20));
        outputs.add(new WeightedEntry<>(new ItemStack(Items.BONE_MEAL), 15));
        outputs.add(new WeightedEntry<>(new ItemStack(Items.COAL), 10));
        outputs.add(new WeightedEntry<>(new ItemStack(Items.DEEPSLATE), 8));
        outputs.add(new WeightedEntry<>(new ItemStack(Items.COBBLESTONE), 8));
        outputs.add(new WeightedEntry<>(new ItemStack(Items.SAND), 8));

        TagKey<Item> fossilTag = TagKey.create(BuiltInRegistries.ITEM.key(),
                new ResourceLocation("prehistoricascension", "fossils"));

        Optional<net.minecraft.core.HolderSet.Named<Item>> fossilItems =
                registryAccess.lookupOrThrow(BuiltInRegistries.ITEM.key()).get(fossilTag);

        if (fossilItems.isPresent()) {
            List<Item> fossils = fossilItems.get().stream()
                    .map(holder -> holder.value())
                    .toList();

            if (!fossils.isEmpty()) {
                Item randomFossil = fossils.get(random.nextInt(fossils.size()));
                outputs.add(new WeightedEntry<>(new ItemStack(randomFossil), 10));
            }
        }

        return getWeightedRandom(outputs, random);
    }

    private ItemStack getWeightedRandom(List<WeightedEntry<ItemStack>> entries, RandomSource random) {
        if (entries.isEmpty()) return ItemStack.EMPTY;

        double totalWeight = entries.stream().mapToDouble(e -> e.weight).sum();
        double r = random.nextDouble() * totalWeight;
        double cumulative = 0;

        for (WeightedEntry<ItemStack> entry : entries) {
            cumulative += entry.weight;
            if (r <= cumulative) return entry.item.copy();
        }

        return entries.get(entries.size() - 1).item.copy();
    }

    private static class WeightedEntry<T> {
        public final T item;
        public final double weight;

        public WeightedEntry(T item, double weight) {
            this.item = item;
            this.weight = weight;
        }
    }

    public static class Type implements RecipeType<FossilCleaningRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "fossil_cleaning";
    }

    public static class Serializer implements RecipeSerializer<FossilCleaningRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(PrehistoricAscension.MOD_ID, "fossil_cleaning");

        @Override
        public FossilCleaningRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new FossilCleaningRecipe(inputs, recipeId);
        }

        @Override
        public @Nullable FossilCleaningRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            int size = buffer.readInt();
            NonNullList<Ingredient> inputs = NonNullList.withSize(size, Ingredient.EMPTY);

            for (int i = 0; i < size; i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            return new FossilCleaningRecipe(inputs, recipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, FossilCleaningRecipe recipe) {
            buffer.writeInt(recipe.inputItems.size());
            for (Ingredient ingredient : recipe.inputItems) {
                ingredient.toNetwork(buffer);
            }
        }
    }
}