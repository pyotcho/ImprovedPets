package com.pyotcho.improvedpets.renderer.entities.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.pyotcho.improvedpets.ImprovedPets;
import com.pyotcho.improvedpets.common.entities.CatPetEntity;
import com.pyotcho.improvedpets.renderer.entities.model.CatPetModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CatPetRenderer extends GeoEntityRenderer<CatPetEntity> {
    public CatPetRenderer(EntityRendererProvider.Context context) {
        super(context, new CatPetModel());
        this.shadowRadius = 0.1f;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull CatPetEntity entity) {
        return new ResourceLocation(ImprovedPets.MOD_ID + ":textures/entity/cat_pet/cat_pet_texture.png");
    }

    @Override
    public RenderType getRenderType(CatPetEntity animatable, float ticks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(0.8F, 0.8F, 0.8F);
        return super.getRenderType(animatable, ticks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
