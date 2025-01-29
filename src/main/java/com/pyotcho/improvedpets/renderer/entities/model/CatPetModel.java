package com.pyotcho.improvedpets.renderer.entities.model;

import com.pyotcho.improvedpets.ImprovedPets;
import com.pyotcho.improvedpets.common.entities.CatPetEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CatPetModel extends AnimatedGeoModel<CatPetEntity> {
    @Override
    public ResourceLocation getModelLocation(CatPetEntity catPetEntity) {
        return new ResourceLocation(ImprovedPets.MOD_ID + ":geo/cat_pet.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CatPetEntity catPetEntity) {
        return new ResourceLocation(ImprovedPets.MOD_ID + ":textures/entity/cat_pet/cat_pet_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CatPetEntity catPetEntity) {
        return new ResourceLocation(ImprovedPets.MOD_ID + ":animations/cat_pet.animation.json");
    }
}
