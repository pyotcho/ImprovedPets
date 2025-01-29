package com.pyotcho.improvedpets.renderer.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.pyotcho.improvedpets.ImprovedPets;
import com.pyotcho.improvedpets.util.network.PetPickerButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class PetPickerGui extends AbstractContainerScreen<PetPickerMenu> {
    private final static HashMap<String, Object> guiState = PetPickerMenu.guiState;

    private final int x, y, z;
    private final Player entity;
    EditBox pet_name;

    public PetPickerGui(PetPickerMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 260;
        this.imageHeight = 160;
    }

    private static final ResourceLocation texture = new ResourceLocation("improvedpets:textures/screens/pet_picker.png");

    @Override
    public void render(@NotNull PoseStack ms, int mouseX, int mouseY, float partialTicks) {
        super.render(ms, mouseX, mouseY, partialTicks);
        this.renderBackground(ms);
        this.renderTooltip(ms, mouseX, mouseY);
        pet_name.render(ms, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void renderBg(@NotNull PoseStack ms, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        RenderSystem.setShaderTexture(0, texture);
        blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

        RenderSystem.disableBlend();
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            assert this.minecraft != null;
            assert this.minecraft.player != null;
            this.minecraft.player.closeContainer();
            return true;
        }

        if (pet_name.isFocused()) {
            return pet_name.keyPressed(key, b, c);
        }
        return super.keyPressed(key, b, c);
    }

    @Override
    public void containerTick() {
        super.containerTick();
    }

    @Override
    protected void renderLabels(@NotNull PoseStack poseStack, int mouseX, int mouseY) {
        this.font.draw(poseStack, "Pick your pet", 90, 10, -16777215);
    }

    @Override
    public void onClose() {
        super.onClose();
        Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
    }

    @Override
    public void init() {
        super.init();
        assert this.minecraft != null;
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);

        pet_name = new EditBox(this.font, this.leftPos + 90, this.topPos + 10, 100, 20,
                new TextComponent("Pet name")) {
            {
                setSuggestion("Enter a name");
            }
            @Override
            public void insertText(@NotNull String text) {
                super.insertText(text);
                if (getValue().isEmpty())
                    setSuggestion("Enter a name");
                else
                    setSuggestion(null);
            }
            @Override
            public void moveCursorTo(int pos) {
                super.moveCursorTo(pos);
                if (getValue().isEmpty())
                    setSuggestion("Enter a name");
                else
                    setSuggestion(null);
            }
        };
        guiState.put("text:pet_name", pet_name);
        pet_name.setMaxLength(32767);
        this.addWidget(this.pet_name);

        this.addRenderableWidget(new Button(this.leftPos + 20, this.topPos + 25, 30, 20,
                new TextComponent("Dog"), e ->
        {
            ImprovedPets.PACKET_HANDLER.sendToServer(new PetPickerButton(0, x, y, z, pet_name.getValue()));
            PetPickerButton.handleButtonAction(entity, 0, x, y, z, pet_name.getValue());
        }));
        this.addRenderableWidget(new Button(this.leftPos + 45, this.topPos + 25, 30, 20,
                new TextComponent("Cat"), e ->
        {
            ImprovedPets.PACKET_HANDLER.sendToServer(new PetPickerButton(1, x, y, z, pet_name.getValue()));
            PetPickerButton.handleButtonAction(entity, 1, x, y, z, pet_name.getValue());
        }));
        this.addRenderableWidget(new Button(this.leftPos + 90, this.topPos + 25, 30, 20,
                new TextComponent("Parrot"), e ->
        {
            ImprovedPets.PACKET_HANDLER.sendToServer(new PetPickerButton(2, x, y, z, pet_name.getValue()));
            PetPickerButton.handleButtonAction(entity, 2, x, y, z, pet_name.getValue());
        }));
    }
}
