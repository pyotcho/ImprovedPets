package com.pyotcho.improvedpets;

import com.mojang.logging.LogUtils;
import com.pyotcho.improvedpets.common.ModEntities;
import com.pyotcho.improvedpets.common.ModItems;
import com.pyotcho.improvedpets.renderer.entities.renderer.CatPetRenderer;
import com.pyotcho.improvedpets.renderer.entities.renderer.DogPetRenderer;
import com.pyotcho.improvedpets.renderer.entities.renderer.DonkeyCartRenderer;
import com.pyotcho.improvedpets.renderer.entities.renderer.DonkeyPetRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;


@Mod(ImprovedPets.MOD_ID)
public class ImprovedPets {
    public static final String MOD_ID = "improvedpets";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry
            .newSimpleChannel(new ResourceLocation(MOD_ID, MOD_ID), () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    private static int messageID = 0;

    public ImprovedPets() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::setup);
        bus.addListener(this::clientSetup);

        ModItems.register(bus);
        ModEntities.register(bus);

        GeckoLib.initialize();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.DOG_PET.get(), DogPetRenderer::new);
        EntityRenderers.register(ModEntities.CAT_PET.get(), CatPetRenderer::new);
        EntityRenderers.register(ModEntities.DONKEY_PET.get(), DonkeyPetRenderer::new);
        EntityRenderers.register(ModEntities.DONKEY_CART.get(), DonkeyCartRenderer::new);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("Pre-init...");
    }

    public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder,
                                             BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
        messageID++;
    }
}
