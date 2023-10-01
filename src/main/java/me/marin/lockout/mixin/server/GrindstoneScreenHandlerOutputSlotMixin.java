package me.marin.lockout.mixin.server;

import me.marin.lockout.Lockout;
import me.marin.lockout.lockout.Goal;
import me.marin.lockout.lockout.goals.workstation.UseGrindstoneGoal;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GrindstoneScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.screen.GrindstoneScreenHandler$4")
public class GrindstoneScreenHandlerOutputSlotMixin {

    @Inject(method="onTakeItem(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)V", at = @At("HEAD"))
    public void onTakeItem(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        if (FabricLoader.getInstance().getEnvironmentType() != EnvType.SERVER) return;
        if (!Lockout.isLockoutRunning()) return;

        Lockout lockout = Lockout.getInstance();

        for (Goal goal : lockout.getBoard().getGoals()) {
            if (goal == null) continue;
            if (goal.isCompleted()) continue;

            if (goal instanceof UseGrindstoneGoal) {
                if (player.currentScreenHandler instanceof GrindstoneScreenHandler grindstoneScreenHandler) {
                    if ((Object) this == grindstoneScreenHandler.slots.get(2)) {
                        lockout.completeGoal(goal, player);
                    }
                }
            }

        }
    }

}
