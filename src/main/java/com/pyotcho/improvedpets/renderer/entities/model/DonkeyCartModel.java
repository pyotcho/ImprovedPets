package com.pyotcho.improvedpets.renderer.entities.model;

import com.pyotcho.improvedpets.ImprovedPets;
import com.pyotcho.improvedpets.common.entities.DogPetEntity;
import com.pyotcho.improvedpets.common.entities.misc.DonkeyCartEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DonkeyCartModel extends AnimatedGeoModel<DonkeyCartEntity> {
    @Override
    public ResourceLocation getModelLocation(DonkeyCartEntity donkeyCartEntity) {
        return new ResourceLocation(ImprovedPets.MOD_ID + ":geo/donkey_cart.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DonkeyCartEntity donkeyCartEntity) {
        return new ResourceLocation(ImprovedPets.MOD_ID + ":textures/entity/donkey_cart/donkey_cart_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DonkeyCartEntity donkeyCartEntity) {
        return new ResourceLocation(ImprovedPets.MOD_ID + ":animations/donkey_cart.animation.json");
    }
}
