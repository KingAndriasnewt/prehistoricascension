package net.newt.prehistoricascension.entities.balaur;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.fml.common.Mod;
import net.newt.prehistoricascension.item.ModFoods;
import net.newt.prehistoricascension.item.ModItems;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import javax.annotation.Nullable;
import java.util.Random;

public class Balaur extends Animal implements GeoEntity {
    private static final EntityDataAccessor<Integer> DATA_VARIANT = SynchedEntityData.defineId(Balaur.class, EntityDataSerializers.INT);
    public static final String VARIANT_TAG = "Variant";

    public Balaur(EntityType<? extends Balaur> type, Level level) {
        super(type, level);
        this.noCulling = true;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT, 0);
    }
    @Override
    public boolean canMate(Animal animal) {
        return false;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1F));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    public SoundEvent getAmbientSound() {
        return SoundEvents.CHICKEN_AMBIENT;
    }

    public SoundEvent getDeathSound() {
        return SoundEvents.CHICKEN_DEATH;
    }

    public SoundEvent getHurtSound(DamageSource p_29795_) {
        return SoundEvents.CHICKEN_HURT;
    }





    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
        Random random = new Random();
        this.setVariant(random.nextInt(BalaurModel.Variant.values().length));
        return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
    }





    public ResourceLocation getTextureLocation() {
        return BalaurModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
    }


    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    protected <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        double x = this.getX() - this.xo;
        double z = this.getZ() - this.zo;
        boolean isMoving = (x * x + z * z) > 0.0001;
        double currentSpeed = this.getDeltaMovement().lengthSqr();
        double speedThreshold = 0.01;

        AnimationController<T> controller = tAnimationState.getController();

        if (!this.onGround()) {
            controller.setAnimation(RawAnimation.begin().then("balaur_idle", Animation.LoopType.LOOP));
            controller.setAnimationSpeed(1.5);
        }
        if (isMoving) {
            if (currentSpeed > speedThreshold) {
                controller.setAnimation(RawAnimation.begin().then("balaur_walk", Animation.LoopType.LOOP));
                controller.setAnimationSpeed(0.5);
            }
        } else {
            controller.setAnimation(RawAnimation.begin().then("balaur_idle", Animation.LoopType.LOOP));
            controller.setAnimationSpeed(0.8);
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
        return BalaurModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
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
    }
}