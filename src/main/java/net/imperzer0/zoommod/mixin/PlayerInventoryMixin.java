package net.imperzer0.zoommod.mixin;

import net.imperzer0.zoommod.client.ZoommodClient;
import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin
{
	@Inject(at = { @At("HEAD") },
	        method = { "scrollInHotbar(D)V" },
	        cancellable = true)
	private void onScrollInHotbar(double scrollAmount, CallbackInfo ci)
	{
		if (ZoommodClient.isZooming())
			ci.cancel();
	}
}
