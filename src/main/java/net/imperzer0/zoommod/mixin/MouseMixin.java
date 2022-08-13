package net.imperzer0.zoommod.mixin;

import net.imperzer0.zoommod.client.ZoommodClient;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin
{
	@Inject(at = { @At("RETURN") }, method = { "onMouseScroll(JDD)V" })
	private void onOnMouseScroll(long long_1, double double_1, double double_2, CallbackInfo ci)
	{
		ZoommodClient.mouse_scroll(double_2);
	}
}
