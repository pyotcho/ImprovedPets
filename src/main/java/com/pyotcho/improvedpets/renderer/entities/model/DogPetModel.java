package com.pyotcho.improvedpets.renderer.entities.model;

import com.pyotcho.improvedpets.ImprovedPets;
import com.pyotcho.improvedpets.common.entities.DogPetEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DogPetModel extends AnimatedGeoModel<DogPetEntity> {
    @Override
    public ResourceLocation getModelLocation(DogPetEntity dogPetEntity) {
        return new ResourceLocation(ImprovedPets.MOD_ID + ":geo/dog_pet.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DogPetEntity dogPetEntity) {
        return new ResourceLocation(ImprovedPets.MOD_ID + ":textures/entity/dog_pet/dog_pet.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DogPetEntity dogPetEntity) {
        return new ResourceLocation(ImprovedPets.MOD_ID + ":animations/dog_pet.animation.json");
    }
}
