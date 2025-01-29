package com.pyotcho.improvedpets.util.init;

import com.pyotcho.improvedpets.renderer.screens.PetPickerMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.IContainerFactory;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModMenus {
    private static final List<MenuType<?>> REGISTRY = new ArrayList<>();
    public static final MenuType<PetPickerMenu> MENU_PET_PICKER = register("pet_picker",
            (id, inv, extraData) -> new PetPickerMenu(id, inv, extraData));

    private static <T extends AbstractContainerMenu> MenuType<T> register(String registryName, IContainerFactory<T> containerFactory) {
        MenuType<T> menuType = new MenuType<T>(containerFactory);
        menuType.setRegistryName(registryName);
        REGISTRY.add(menuType);
        return menuType;
    }

    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<MenuType<?>> event) {
        event.getRegistry().registerAll(REGISTRY.toArray(new MenuType[0]));
    }
}
