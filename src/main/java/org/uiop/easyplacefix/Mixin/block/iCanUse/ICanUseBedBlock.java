package org.uiop.easyplacefix.Mixin.block.iCanUse;

import net.minecraft.block.BedBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.uiop.easyplacefix.ICanUse;
@Mixin(BedBlock.class)
public class ICanUseBedBlock implements ICanUse {
}
