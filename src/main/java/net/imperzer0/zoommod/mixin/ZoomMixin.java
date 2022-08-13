package net.imperzer0.zoommod.mixin;

import net.imperzer0.zoommod.client.ZoommodClient;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class ZoomMixin
{
	@Inject(method = "getFov(Lnet/minecraft/client/render/Camera;FZ)D", at = @At("RETURN"), cancellable = true)
	public void getZoomLevel(CallbackInfoReturnable<Double> callbackInfo)
	{
		double fov = callbackInfo.getReturnValue();
		callbackInfo.setReturnValue(ZoommodClient.apply_level(fov));
	}
}
