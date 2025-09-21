package net.newt.prehistoricascension.entities.saurichthys;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.newt.prehistoricascension.item.ModItems;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import javax.annotation.Nullable;
import java.util.Random;

public class Saurichthys extends AbstractSchoolingFish implements GeoEntity {
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Saurichthys.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_VARIANT = SynchedEntityData.defineId(Saurichthys.class, EntityDataSerializers.INT);
    public static final String VARIANT_TAG = "Variant";

    public Saurichthys(EntityType<? extends Saurichthys> type, Level level) {
        super(type, level);
        this.noCulling = true;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT, 0);
        this.entityData.define(FROM_BUCKET, false);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D);
    }

    // Save the variant to the bucket tag
    public void saveToBucketTag(ItemStack pStack) {
        Bucketable.saveDefaultDataToBucketTag(this, pStack);
        CompoundTag compoundTag = pStack.getOrCreateTag();
        compoundTag.putInt(VARIANT_TAG, this.getVariant());
    }

    // Load the variant from the bucket tag
    public void loadFromBucketTag(CompoundTag pTag) {
        Bucketable.loadDefaultDataFromBucketTag(this, pTag);
        if (pTag.contains(VARIANT_TAG)) {
            this.setVariant(pTag.getInt(VARIANT_TAG));
        }
    }

    public int getMaxSchoolSize() {
        return 8;
    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.BUCKET_OF_SAURICHTHYS.get());
    }

    public SoundEvent getAmbientSound() {
        return SoundEvents.SALMON_AMBIENT;
    }

    public SoundEvent getDeathSound() {
        return SoundEvents.SALMON_DEATH;
    }

    public SoundEvent getHurtSound(DamageSource p_29795_) {
        return SoundEvents.SALMON_HURT;
    }

    public SoundEvent getFlopSound() {
        return SoundEvents.SALMON_FLOP;
    }

    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    public void setFromBucket(boolean pFromBucket) {
        this.entityData.set(FROM_BUCKET, pFromBucket);
    }

    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromBucket();
    }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
        Random random = new Random();
        this.setVariant(random.nextInt(SaurichthysModel.Variant.values().length));
        return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        return Bucketable.bucketMobPickup(pPlayer, pHand, this).orElse(super.mobInteract(pPlayer, pHand));
    }

    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_AXOLOTL;
    }

    public ResourceLocation getTextureLocation() {
        return SaurichthysModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
    }

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        double currentSpeed = this.getDeltaMovement().lengthSqr();
        double speedThreshold = 0.01;

        AnimationController<T> controller = tAnimationState.getController();

        if (tAnimationState.isMoving()) {
            if (currentSpeed > speedThreshold) {
                controller.setAnimation(RawAnimation.begin().then("saurichthys_swimsprint", Animation.LoopType.LOOP));
            } else {
                controller.setAnimation(RawAnimation.begin().then("saurichthys_swim", Animation.LoopType.LOOP));
            }
        }
        return PlayState.CONTINUE;
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 2, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    public ResourceLocation getTextureResource() {
        return SaurichthysModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
    }

    public int getVariant() {
        return this.entityData.get(DATA_VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(DATA_VARIANT, variant);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt(VARIANT_TAG, this.getVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains(VARIANT_TAG)) {
            this.setVariant(tag.getInt(VARIANT_TAG));
        }
        this.setFromBucket(tag.getBoolean("FromBucket"));
    }
}