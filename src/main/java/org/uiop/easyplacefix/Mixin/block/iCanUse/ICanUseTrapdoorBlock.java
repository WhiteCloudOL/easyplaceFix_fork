package org.uiop.easyplacefix.Mixin.block.iCanUse;

import net.minecraft.block.TrapdoorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.uiop.easyplacefix.ICanUse;
@Mixin(TrapdoorBlock.class)
public class ICanUseTrapdoorBlock implements ICanUse {
}
