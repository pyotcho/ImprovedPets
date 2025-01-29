package com.pyotcho.improvedpets.renderer.entities.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.pyotcho.improvedpets.ImprovedPets;
import com.pyotcho.improvedpets.common.entities.misc.DonkeyCartEntity;
import com.pyotcho.improvedpets.renderer.entities.model.DonkeyCartModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DonkeyCartRenderer extends GeoEntityRenderer<DonkeyCartEntity> {
    public DonkeyCartRenderer(EntityRendererProvider.Context context) {
        super(context, new DonkeyCartModel());
        this.shadowRadius = 1f;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull DonkeyCartEntity entity) {
        return new ResourceLocation(ImprovedPets.MOD_ID + ":textures/entity/donkey_cart/donkey_cart_texture.png");
    }

    @Override
    public RenderType getRenderType(DonkeyCartEntity animatable, float ticks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(2F, 2F, 2F);
        return super.getRenderType(animatable, ticks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
