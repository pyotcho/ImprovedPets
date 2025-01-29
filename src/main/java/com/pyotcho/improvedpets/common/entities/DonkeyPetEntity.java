package com.pyotcho.improvedpets.common.entities;

import com.pyotcho.improvedpets.common.ModEntities;
import com.pyotcho.improvedpets.common.entities.misc.DonkeyCartEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class DonkeyPetEntity extends TamableAnimal implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    public DonkeyPetEntity(EntityType<? extends TamableAnimal> entity, Level level) {
        super(entity, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.ATTACK_DAMAGE, 0f)
                .add(Attributes.ATTACK_SPEED, 0f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f).build();
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel player, @NotNull AgeableMob mob) {
        return null;
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand handItem) {
        return InteractionResult.PASS;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.donkey.walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.donkey.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
        this.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 0.15F, 1.0F);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.DONKEY_AMBIENT;
    }

    protected SoundEvent getHurtSound(@NotNull DamageSource damageSourceIn) {
        return SoundEvents.DONKEY_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.DONKEY_DEATH;
    }

    protected float getSoundVolume() {
        return 0.2F;
    }

    @Override
    public InteractionResult interactAt(@NotNull Player player, @NotNull Vec3 vec, @NotNull InteractionHand itemInHand) {
        if(level.isClientSide || !player.isCrouching() || !player.getItemInHand(itemInHand).is(Items.LEAD))
            return super.interactAt(player, vec, itemInHand);
        float yaw = this.getYRot();
        double yawRad = Math.toRadians(yaw);

        double offsetX = -Math.sin(yawRad) * this.getBbWidth() * 2;
        double offsetZ = Math.cos(yawRad) * this.getBbWidth() * 2;
        Vec3 pos = this.position().add(offsetX, 0, offsetZ);

        this.setPos(pos.x, pos.y, pos.z);
        DonkeyCartEntity cart = new DonkeyCartEntity(ModEntities.DONKEY_CART.get(), level);

        cart.setPos(pos);
        this.level.addFreshEntity(cart);
        cart.attachToDonkey(this);

        return InteractionResult.SUCCESS;
    }
}
