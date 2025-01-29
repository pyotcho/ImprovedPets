package com.pyotcho.improvedpets.util.network;

import com.pyotcho.improvedpets.ImprovedPets;
import com.pyotcho.improvedpets.events.entities.SpawnPets;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PetPickerButton {
    private final int buttonID, x, y, z;
    private final String pet_name;

    public PetPickerButton(FriendlyByteBuf buffer) {
        this.buttonID = buffer.readInt();
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.pet_name = buffer.readUtf();
    }

    public PetPickerButton(int buttonID, int x, int y, int z, String pet_name) {
        this.buttonID = buttonID;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pet_name = pet_name;
    }

    public static void buffer(PetPickerButton message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.buttonID);
        buffer.writeInt(message.x);
        buffer.writeInt(message.y);
        buffer.writeInt(message.z);
        buffer.writeUtf(message.pet_name);
    }

    public static void handler(PetPickerButton message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            Player entity = context.getSender();
            int buttonID = message.buttonID;
            int x = message.x;
            int y = message.y;
            int z = message.z;
            assert entity != null;
            handleButtonAction(entity, buttonID, x, y, z, message.pet_name);
        });
        context.setPacketHandled(true);
    }

    public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z, String pet_name) {
        Level world = entity.level;
        if (!world.hasChunkAt(new BlockPos(x, y, z)))
            return;

        switch (buttonID) {
            case 0:
                SpawnPets.spawnDog(entity, world, entity.getX(), entity.getY(), entity.getZ(), pet_name);
                break;
            case 1:
                SpawnPets.spawnCat(entity, world, entity.getX(), entity.getY(), entity.getZ(), pet_name);
                break;
            case 2:
                SpawnPets.spawnDonkey(entity, world, entity.getX(), entity.getY(), entity.getZ(), pet_name);
                break;
            default:
                break;
        }

    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        ImprovedPets.addNetworkMessage(PetPickerButton.class, PetPickerButton::buffer, PetPickerButton::new,
                PetPickerButton::handler);
    }
}
