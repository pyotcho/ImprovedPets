package com.pyotcho.improvedpets.renderer.screens;

import com.pyotcho.improvedpets.util.init.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PetPickerMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public final static HashMap<String, Object> guiState = new HashMap<>();
    public final Level world;
    public final Player entity;
    public int x, y, z;
    private final Map<Integer, Slot> customSlots = new HashMap<>();

    public PetPickerMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(ModMenus.MENU_PET_PICKER, id);
        this.entity = inv.player;
        this.world = inv.player.level;
        BlockPos pos;
        if (extraData != null) {
            pos = extraData.readBlockPos();
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
        }
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }

    public Map<Integer, Slot> get() {
        return customSlots;
    }
}
