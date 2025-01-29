package com.pyotcho.improvedpets.renderer.entities.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.pyotcho.improvedpets.ImprovedPets;
import com.pyotcho.improvedpets.common.entities.DonkeyPetEntity;
import com.pyotcho.improvedpets.renderer.entities.model.DonkeyPetModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DonkeyPetRenderer extends GeoEntityRenderer<DonkeyPetEntity> {
    public DonkeyPetRenderer(EntityRendererProvider.Context context) {
        super(context, new DonkeyPetModel());
        this.shadowRadius = 1f;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull DonkeyPetEntity entity) {
        return new ResourceLocation(ImprovedPets.MOD_ID + ":textures/entity/donkey_pet/donkey_pet_texture.png");
    }

    @Override
    public RenderType getRenderType(DonkeyPetEntity animatable, float ticks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1F, 1F, 1F);
        return super.getRenderType(animatable, ticks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
