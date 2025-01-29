package com.pyotcho.improvedpets.common.entities.misc;

import com.pyotcho.improvedpets.common.entities.DonkeyPetEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
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

import java.util.UUID;

public class DonkeyCartEntity extends TamableAnimal implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    private UUID donkeyUUID;
    private DonkeyPetEntity donkey;

    public DonkeyCartEntity(@NotNull EntityType<DonkeyCartEntity> entity, Level level) {
        super(entity, level);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.ATTACK_DAMAGE, 0f)
                .add(Attributes.ATTACK_SPEED, 0f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f).build();
    }

    @Override
    public void tick() {
        super.tick();

        if(donkeyUUID != null && (donkey == null || !donkey.isAlive())) {
            donkey = (DonkeyPetEntity) ((ServerLevel) level).getEntity(donkeyUUID);
        }

        if (donkey != null) {
            float yaw = donkey.getYRot();
            double yawRad = Math.toRadians(yaw);

            double offsetX = -Math.sin(yawRad) * 1.5;
            double offsetZ = Math.cos(yawRad) * 1.5;

            double newX = donkey.getX() - offsetX;
            double newY = donkey.getY();
            double newZ = donkey.getZ() - offsetZ;

            this.setPos(newX, newY, newZ);
            this.yRotO = yaw;
            this.setYRot(yaw);
        }
    }

    public void attachToDonkey(DonkeyPetEntity entity) {
        this.donkeyUUID = entity.getUUID();
        this.donkey = entity;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
        return null;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.donkeycart.walk", true));
            return PlayState.CONTINUE;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        if (compound.hasUUID("DonkeyUUID")) {
            this.donkeyUUID = compound.getUUID("DonkeyUUID");
        }
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        if (donkeyUUID != null) {
            compound.putUUID("DonkeyUUID", donkeyUUID);
        }
    }
}
