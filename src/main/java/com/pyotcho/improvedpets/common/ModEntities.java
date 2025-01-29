package com.pyotcho.improvedpets.common;

import com.pyotcho.improvedpets.ImprovedPets;
import com.pyotcho.improvedpets.common.entities.CatPetEntity;
import com.pyotcho.improvedpets.common.entities.DogPetEntity;
import com.pyotcho.improvedpets.common.entities.DonkeyPetEntity;
import com.pyotcho.improvedpets.common.entities.misc.DonkeyCartEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, ImprovedPets.MOD_ID);

    public static final RegistryObject<EntityType<DogPetEntity>> DOG_PET = ENTITIES.register("dog_pet",
            () -> EntityType.Builder.of(DogPetEntity::new, MobCategory.CREATURE)
                    .sized(1.0F, 1.0F)
            .build(new ResourceLocation(ImprovedPets.MOD_ID, "dog_pet").toString())
    );
    public static final RegistryObject<EntityType<CatPetEntity>> CAT_PET = ENTITIES.register("cat_pet",
            () -> EntityType.Builder.of(CatPetEntity::new, MobCategory.CREATURE)
                    .sized(0.5F, 0.5F)
                    .build(new ResourceLocation(ImprovedPets.MOD_ID, "cat_pet").toString())
    );
    public static final RegistryObject<EntityType<DonkeyPetEntity>> DONKEY_PET = ENTITIES.register("donkey_pet",
            () -> EntityType.Builder.of(DonkeyPetEntity::new, MobCategory.CREATURE)
                    .build(new ResourceLocation(ImprovedPets.MOD_ID, "donkey_pet").toString())
    );
    public static final RegistryObject<EntityType<DonkeyCartEntity>> DONKEY_CART = ENTITIES.register("donkey_cart",
            () -> EntityType.Builder.of(DonkeyCartEntity::new, MobCategory.MISC)
                    .sized(1F, 1F)
                    .build(new ResourceLocation(ImprovedPets.MOD_ID, "donkey_cart").toString())
    );

    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }
}
