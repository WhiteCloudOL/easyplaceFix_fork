package org.uiop.easyplacefix.until;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.PlayerInputC2SPacket;
import net.minecraft.util.PlayerInput;

public class PlayerInputAction {

    public static void SetShift(boolean isPressed) {
        PlayerInput playerInput = MinecraftClient.getInstance().player.getLastPlayerInput();
        MinecraftClient.getInstance().getNetworkHandler().sendPacket(
                new PlayerInputC2SPacket(
                        new PlayerInput(
                                playerInput.forward(),
                                playerInput.backward(),
                                playerInput.left(), playerInput.
                                right(), playerInput.jump(),
                                isPressed,
                                playerInput.sprint()
                        ))
        );

    }
}
