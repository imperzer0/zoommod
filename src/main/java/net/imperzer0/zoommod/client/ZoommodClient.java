package net.imperzer0.zoommod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.util.InputUtil;

@Environment(EnvType.CLIENT)
public class ZoommodClient implements ClientModInitializer
{
	private static final MinecraftClient mc = MinecraftClient.getInstance();
	private static final double level_default = 0.20;
	private static KeyBinding binding;
	private static double level = level_default;
	private static Double mouse_sens_default = null;
	
	public static double apply_level(double fov)
	{
		SimpleOption<Double> mouse_sens = mc.options.getMouseSensitivity();
		
		if (!isZooming())
		{
			reset_zoom();
			
			if (mouse_sens_default != null)
			{
				mouse_sens.setValue(mouse_sens_default);
				mouse_sens_default = null;
			}
			
			return fov;
		}
		
		if (mouse_sens_default == null) mouse_sens_default = mouse_sens.getValue();
		
		mouse_sens.setValue(mouse_sens_default * level + Math.abs(Math.exp(level) - 1.1) / 2.0);
		
		return fov * level;
	}
	
	public static boolean isZooming() { return binding.isPressed(); }
	
	private static void reset_zoom() { level = level_default; }
	
	public static void mouse_scroll(double move)
	{
		if (!binding.isPressed()) return;
		
		if (move < 0.0) level *= 1.15;
		else level *= 0.85;
		
		if (level > 1.0) level = 1.0;
		if (level < 0.01) level = 0.01;
	}
	
	@Override
	public void onInitializeClient()
	{
		binding = KeyBindingHelper.registerKeyBinding(
				new KeyBinding("key.zoommod.zoom", InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_C, "category.zoommod.zoom"));
	}
}
