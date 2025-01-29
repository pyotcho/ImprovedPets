package com.pyotcho.improvedpets.events.entities;

import com.pyotcho.improvedpets.common.ModEntities;
import com.pyotcho.improvedpets.common.entities.CatPetEntity;
import com.pyotcho.improvedpets.common.entities.DogPetEntity;
import com.pyotcho.improvedpets.common.entities.DonkeyPetEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

public class SpawnPets {
    public static void spawnDog(Player player, LevelAccessor level, double x, double y, double z, String pet_name) {
        if(level instanceof ServerLevel _level) {
            Entity entity = new DogPetEntity(ModEntities.DOG_PET.get(), _level);

            spawnMob(player, entity, level, x, y, z, pet_name);

            player.closeContainer();
        }
    }

    public static void spawnCat(Player player, LevelAccessor level, double x, double y, double z, String pet_name) {
        if(level instanceof ServerLevel _level) {
            Entity entity = new CatPetEntity(ModEntities.CAT_PET.get(), _level);

            spawnMob(player, entity, level, x, y, z, pet_name);

            player.closeContainer();
        }
    }

    public static void spawnDonkey(Player player, LevelAccessor level, double x, double y, double z, String pet_name) {
        if(level instanceof ServerLevel _level) {
            Entity entity = new DonkeyPetEntity(ModEntities.DONKEY_PET.get(), _level);

            spawnMob(player, entity, level, x, y, z, pet_name);

            player.closeContainer();
        }
    }

    private static void spawnMob(Player player, Entity entity, LevelAccessor level, double x, double y, double z, String pet_name) {
        if(level instanceof ServerLevel _level) {

            entity.moveTo(x, y, z,0, 0);
            entity.setYBodyRot(0);
            entity.setYHeadRot(0);
            entity.setDeltaMovement(1, 1, 0);

            if(entity instanceof TamableAnimal animal) {
                animal.setOwnerUUID(player.getUUID());
            }

            entity.setCustomName(Component.nullToEmpty(pet_name));

            if(entity instanceof Mob _mob) {
                _mob.finalizeSpawn(_level, level.getCurrentDifficultyAt(entity.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            }

            level.addFreshEntity(entity);
        }
    }
}
