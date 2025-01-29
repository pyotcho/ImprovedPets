package com.pyotcho.improvedpets.events;

import com.pyotcho.improvedpets.ImprovedPets;
import com.pyotcho.improvedpets.common.ModEntities;
import com.pyotcho.improvedpets.common.entities.CatPetEntity;
import com.pyotcho.improvedpets.common.entities.DogPetEntity;
import com.pyotcho.improvedpets.common.entities.DonkeyPetEntity;
import com.pyotcho.improvedpets.common.entities.misc.DonkeyCartEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ImprovedPets.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBus {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntities.DOG_PET.get(), DogPetEntity.setAttributes());
        event.put(ModEntities.CAT_PET.get(), CatPetEntity.setAttributes());
        event.put(ModEntities.DONKEY_PET.get(), DonkeyPetEntity.setAttributes());
        event.put(ModEntities.DONKEY_CART.get(), DonkeyCartEntity.setAttributes());
    }
}
