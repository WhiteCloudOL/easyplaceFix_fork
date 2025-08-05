package org.uiop.easyplacefix.Mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.uiop.easyplacefix.EasyPlaceFix;
import org.uiop.easyplacefix.until.PlayerBlockAction;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Inject(method = "onDisconnected()V",
            at = @At(value = "HEAD"))
    private void disconnect(CallbackInfo ci) {
        EasyPlaceFix.screenId = 1;
        PlayerBlockAction.openScreenAction.count = 0;
        PlayerBlockAction.useItemOnAction.modifyBoolean = false;
        PlayerBlockAction.useItemOnAction.lastPlacementTimeMap.clear();
        PlayerBlockAction.useItemOnAction.pistonBlockState = null;


    }
}
