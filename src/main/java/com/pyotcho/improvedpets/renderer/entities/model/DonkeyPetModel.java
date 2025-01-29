package com.pyotcho.improvedpets.renderer.entities.model;

import com.pyotcho.improvedpets.ImprovedPets;
import com.pyotcho.improvedpets.common.entities.CatPetEntity;
import com.pyotcho.improvedpets.common.entities.DonkeyPetEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DonkeyPetModel extends AnimatedGeoModel<DonkeyPetEntity> {
    @Override
    public ResourceLocation getModelLocation(DonkeyPetEntity donkey) {
        return new ResourceLocation(ImprovedPets.MOD_ID + ":geo/donkey_pet.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DonkeyPetEntity donkey) {
        return new ResourceLocation(ImprovedPets.MOD_ID + ":textures/entity/donkey_pet/donkey_pet_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DonkeyPetEntity donkey) {
        return new ResourceLocation(ImprovedPets.MOD_ID + ":animations/donkey_pet.animation.json");
    }
}
