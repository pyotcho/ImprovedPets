package com.pyotcho.improvedpets.common;

import com.pyotcho.improvedpets.ImprovedPets;
import com.pyotcho.improvedpets.common.items.PetPickerTool;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ImprovedPets.MOD_ID);

    public static final RegistryObject<Item> PET_PICKER_TOOL = ITEMS.register("pet_picker_tool",
            () -> new PetPickerTool(new Item.Properties()
                    .tab(CreativeModeTab.TAB_TOOLS)
                    .stacksTo(1)
                    .rarity(Rarity.EPIC)
            ));
    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
