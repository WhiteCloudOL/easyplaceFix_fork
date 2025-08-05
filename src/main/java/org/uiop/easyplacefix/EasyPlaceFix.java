package org.uiop.easyplacefix;

import fi.dy.masa.litematica.gui.GuiConfigs;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.uiop.easyplacefix.Mixin.config.ConfigGuiTabAccessor;
import org.uiop.easyplacefix.config.Hotkeys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Predicate;

public class EasyPlaceFix implements ModInitializer {

    public static final GuiConfigs.ConfigGuiTab EASY_FIX = ConfigGuiTabAccessor.init("EASY_FIX", 6, "litematica.gui.button.config_gui.easy_fix");
    public static List<Boolean> crafterSlot = new ArrayList<>(Arrays.asList(false, false, false, false, false, false, false, false, false));
    public static boolean crafterOperation = false;
    public static volatile int screenId=1;

    @Override
    public void onInitialize() {

        Hotkeys.init();
//        ClientCommandRegistrationCallback.
//                EVENT.
//                register((dispatcher, registryAccess) ->
//                                dispatcher.register(ClientCommandManager.literal("loosenMode").executes(context -> {
////                            if (loosenMode){
////                                context.getSource().sendFeedback(Text.literal("loosenModeSetting OFF"));
////                                loosenMode=false;
////                            }else {
////                                context.getSource().sendFeedback(Text.literal("loosenModeSetting ON"));
////                                loosenMode=true;
////                            }
//                                    MinecraftClient client = MinecraftClient.getInstance();
//                                    client.getMessageHandler().onGameMessage(Text.of("已删除功能"),false);
////                            client.send(()-> client.setScreen(screen));
//                                    return 1;
//                                }))
//                );
    }

    public static ItemStack findBlockInInventory(PlayerInventory inv, Predicate<Block> predicate) {
        for (int slot = 0; slot < inv.size(); slot++) {
            ItemStack stack = inv.getStack(slot);
            if (!stack.isEmpty()) {
                Block block = Block.getBlockFromItem(stack.getItem());
                if (predicate.test(block)) {
//                    InventoryUtils.setPickedItemToHand(slot, stack, MinecraftClient.getInstance());
                    return stack; // 找到满足条件的物品堆，返回其槽位
                }
            }
        }
        return null; // 如果没有找到，返回 null
    }

}
