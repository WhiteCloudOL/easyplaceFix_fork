package org.uiop.easyplacefix.until;

import com.tick_ins.packet.Ping2Server;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.uiop.easyplacefix.EasyPlaceFix;

import java.util.Map;
import java.util.concurrent.*;

public class PlayerBlockAction {
    // 创建单线程线程池

    public static class openScreenAction {
        public static volatile int count = 0;

        public static boolean run() {
            return count == 0;
        }
    }

    public static class openSignEditorAction {
        public static volatile int count = 0;

        public static boolean run() {
            return count == 0;

        }

    }

    public static class useItemOnAction {
        public static volatile float yawLock, pitchLock = 0;
        public static boolean modifyBoolean = false;
        // 初始化线程安全的Set
        public static Map<BlockPos, Long> lastPlacementTimeMap = new ConcurrentHashMap<>();
        public static BlockState pistonBlockState = null;
        //   TODO 总感觉不靠谱 ^

        public static boolean isPlacementCooling(BlockPos pos) {
            long now = System.currentTimeMillis();

            if (lastPlacementTimeMap.containsKey(pos)) {
                long lastPlaceTime = lastPlacementTimeMap.get(pos);
                if (now - lastPlaceTime > Ping2Server.getRtt() + 100) {
                    lastPlacementTimeMap.put(pos, now);//超时就刷新缓存
                    return false;
                } else {
                    return true;
                }
            }
            lastPlacementTimeMap.put(pos, now);//没有就添加缓存
            return false;

        }
    }
}
