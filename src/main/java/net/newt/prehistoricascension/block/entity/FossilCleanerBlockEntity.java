package net.newt.prehistoricascension.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.newt.prehistoricascension.recipe.FossilCleaningRecipe;
import net.newt.prehistoricascension.screen.FossilCleanerMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FossilCleanerBlockEntity extends BlockEntity implements MenuProvider {
    private static final int WATER_BUCKET_SLOT = 0;
    private static final int FOSSIL_SLOT = 4;
    private static final int OUTPUT_SLOT1 = 1;

    private final ItemStackHandler itemHandler = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }

            if (slot == WATER_BUCKET_SLOT) {
                ItemStack stack = getStackInSlot(slot);
                if (!stack.isEmpty()) {
                    if (isWaterBucket(stack)) {
                        if (!stack.hasTag() || !stack.getTag().contains("Uses")) {
                            stack.getOrCreateTag().putInt("Uses", 8);
                        }
                    } else if (isWaterBottle(stack)) {
                        if (!stack.hasTag() || !stack.getTag().contains("Uses")) {
                            stack.getOrCreateTag().putInt("Uses", 3);
                        }
                    }
                }
            }
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            if (slot == WATER_BUCKET_SLOT) {
                return isAllowedWaterInput(stack);
            }
            if (slot == FOSSIL_SLOT) {
                return true;
            }
            return slot != OUTPUT_SLOT1 && slot != OUTPUT_SLOT1 + 1 && slot != OUTPUT_SLOT1 + 2;
        }
    };

    private final IItemHandler externalItemHandler = new IItemHandler() {
        @Override
        public int getSlots() {
            return itemHandler.getSlots();
        }

        @Override
        public @NotNull ItemStack getStackInSlot(int slot) {
            return itemHandler.getStackInSlot(slot);
        }

        @Override
        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            return itemHandler.insertItem(slot, stack, simulate);
        }

        @Override
        public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (slot == WATER_BUCKET_SLOT || slot == FOSSIL_SLOT) {
                return ItemStack.EMPTY;
            }
            return itemHandler.extractItem(slot, amount, simulate);
        }

        @Override
        public int getSlotLimit(int slot) {
            return itemHandler.getSlotLimit(slot);
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return itemHandler.isItemValid(slot, stack);
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private LazyOptional<IItemHandler> lazyItemHandlerExternal = LazyOptional.empty();

    protected final ContainerData data;

    private int progress = 0;
    private int maxProgress = 78;

    private int brewTime = 0;

    public FossilCleanerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(net.newt.prehistoricascension.block.entity.ModBlockEntities.FOSSIL_CLEANER.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> FossilCleanerBlockEntity.this.progress;
                    case 1 -> FossilCleanerBlockEntity.this.maxProgress;
                    case 2 -> FossilCleanerBlockEntity.this.brewTime;
                    case 3 -> FossilCleanerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> FossilCleanerBlockEntity.this.progress = pValue;
                    case 1 -> FossilCleanerBlockEntity.this.maxProgress = pValue;
                    case 2 -> FossilCleanerBlockEntity.this.brewTime = pValue;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    public ItemStack getRenderStack() {
        return itemHandler.getStackInSlot(FOSSIL_SLOT);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.prehistoricascension.fossil_cleaner");
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return (side == null ? lazyItemHandler : lazyItemHandlerExternal).cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyItemHandlerExternal = LazyOptional.of(() -> externalItemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyItemHandlerExternal.invalidate();
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new FossilCleanerMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("fossil_cleaner.progress", progress);
        pTag.putInt("fossil_cleaner.brewTime", brewTime);
        pTag.putInt("fossil_cleaner.maxProgress", maxProgress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("fossil_cleaner.progress");
        brewTime = pTag.getInt("fossil_cleaner.brewTime");
        if (pTag.contains("fossil_cleaner.maxProgress")) {
            maxProgress = pTag.getInt("fossil_cleaner.maxProgress");
        }
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if (pLevel.isClientSide()) return;

        boolean canClean = hasRecipe();

        if (canClean) {
            if (brewTime <= 0) {
                brewTime = maxProgress;
                progress = 0;
            } else {
                brewTime--;
                progress = maxProgress - brewTime;

                if (brewTime <= 0) {
                    craftItem();
                    progress = 0;
                }
            }
            setChanged();
        } else {
            if (brewTime != 0 || progress != 0) {
                brewTime = 0;
                progress = 0;
                setChanged();
            }
        }
    }

    private void craftItem() {
        Optional<FossilCleaningRecipe> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;

        if (!canAcceptOutputs(recipe.get())) {
            return;
        }

        ItemStack waterStack = this.itemHandler.getStackInSlot(WATER_BUCKET_SLOT);
        ItemStack fossilStack = this.itemHandler.getStackInSlot(FOSSIL_SLOT);

        if (waterStack.isEmpty() || fossilStack.isEmpty()) return;

        boolean isWaterBucket = isWaterBucket(waterStack);
        boolean isWaterBottle = isWaterBottle(waterStack);

        if (!isWaterBucket && !isWaterBottle) return;

        int uses = waterStack.getOrCreateTag().getInt("Uses");
        if (uses <= 0) return;

        this.itemHandler.extractItem(FOSSIL_SLOT, 1, false);

        uses--;
        if (uses <= 0) {
            if (isWaterBucket) {
                this.itemHandler.setStackInSlot(WATER_BUCKET_SLOT, new ItemStack(Items.BUCKET));
            } else if (isWaterBottle) {
                this.itemHandler.setStackInSlot(WATER_BUCKET_SLOT, new ItemStack(Items.GLASS_BOTTLE));
            }
        } else {
            waterStack.getTag().putInt("Uses", uses);
        }

        for (int i = 0; i < 3; i++) {
            ItemStack result = recipe.get().assemble(makeContainerFromHandler(), level.registryAccess());
            if (result.isEmpty()) continue;

            for (int s = 0; s < 3; s++) {
                int slot = OUTPUT_SLOT1 + s;
                ItemStack current = this.itemHandler.getStackInSlot(slot);

                if (current.isEmpty()) {
                    this.itemHandler.setStackInSlot(slot, result.copy());
                    break;
                } else if (ItemStack.isSameItemSameTags(current, result) &&
                        current.getCount() < current.getMaxStackSize()) {
                    int space = current.getMaxStackSize() - current.getCount();
                    int toAdd = Math.min(space, result.getCount());
                    current.grow(toAdd);
                    result.shrink(toAdd);
                    if (result.isEmpty()) break;
                }
            }
        }

        setChanged();
    }

    private boolean hasRecipe() {
        Optional<FossilCleaningRecipe> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;

        ItemStack waterStack = this.itemHandler.getStackInSlot(WATER_BUCKET_SLOT);
        if (waterStack.isEmpty() || !isAllowedWaterInput(waterStack)) return false;

        int uses = waterStack.getOrCreateTag().getInt("Uses");
        if (uses <= 0) return false;

        if (this.itemHandler.getStackInSlot(FOSSIL_SLOT).isEmpty()) return false;

        return canAcceptOutputs(recipe.get());
    }

    private boolean canAcceptOutputs(FossilCleaningRecipe fossilCleaningRecipe) {
        for (int i = 0; i < 3; i++) {
            if (this.itemHandler.getStackInSlot(OUTPUT_SLOT1 + i).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private Optional<FossilCleaningRecipe> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(FossilCleaningRecipe.Type.INSTANCE, makeContainerFromHandler(), level);
    }

    private SimpleContainer makeContainerFromHandler() {
        SimpleContainer container = new SimpleContainer(this.itemHandler.getSlots());
        for (int i = 0; i < this.itemHandler.getSlots(); i++) {
            container.setItem(i, this.itemHandler.getStackInSlot(i));
        }
        return container;
    }

    private static boolean isWaterBucket(ItemStack stack) {
        return stack.is(Items.WATER_BUCKET);
    }

    private static boolean isWaterBottle(ItemStack stack) {
        return stack.is(Items.POTION) && PotionUtils.getPotion(stack) == Potions.WATER;
    }

    private static boolean isAllowedWaterInput(ItemStack stack) {
        return isWaterBucket(stack) || isWaterBottle(stack);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }
}