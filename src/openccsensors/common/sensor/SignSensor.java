package openccsensors.common.sensor;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.Icon;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import openccsensors.api.IRequiresIconLoading;
import openccsensors.api.ISensor;
import openccsensors.api.ISensorTier;

public class SignSensor extends TileSensor implements ISensor, IRequiresIconLoading {
	
	private Icon icon;

	@Override
	public HashMap getDetails(World world, Object obj, Vec3 sensorPos, boolean additional) {
		
		TileEntitySign sign = (TileEntitySign) obj;
		HashMap response = super.getDetails(sign, sensorPos);
		if (additional) {

			String signText = "";
			for (int i = 0; i < sign.signText.length; i++) {
				signText = signText + sign.signText[i];
				if (i < 3 && sign.signText[i] != "") {
					signText = signText + " ";
				}
			}
			response.put("Text", signText.trim());
		}
		
		return response;
	}
	
	@Override
	public boolean isValidTarget(Object target) {
		return target instanceof TileEntitySign;
	}

	@Override
	public String[] getCustomMethods(ISensorTier tier) {
		return null;
	}

	@Override
	public Object callCustomMethod(World world, Vec3 location, int methodID,
			Object[] args, ISensorTier tier) {
		return null;
	}

	@Override
	public String getName() {
		return "signCard";
	}

	@Override
	public Icon getIcon() {
		return icon;
	}

	@Override
	public void loadIcon(IconRegister iconRegistry) {
		icon = iconRegistry.registerIcon("openccsensors:sign");		
	}

	@Override
	public ItemStack getUniqueRecipeItem() {
		return new ItemStack(Block.signPost);
	}

}
