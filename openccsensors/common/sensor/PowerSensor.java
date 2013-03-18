package openccsensors.common.sensor;

import java.util.HashMap;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import openccsensors.api.IRequiresIconLoading;
import openccsensors.api.ISensor;
import openccsensors.api.ISensorTier;
import openccsensors.common.util.UniversalElectricityUtils;

public class PowerSensor extends TileSensor implements ISensor, IRequiresIconLoading {

	private Icon icon;
	Class UEApi = null;
	
	public PowerSensor() {
		try {
			UEApi = Class.forName("universalelectricity.core.block.IVoltage");
		} catch (ClassNotFoundException e) {
		}
	}
	
	@Override
	public boolean isValidTarget(TileEntity target) {
		return UEApi != null && UniversalElectricityUtils.isValidTarget(target);
	}

	@Override
	public HashMap getDetails(World world, Object obj, boolean additional) {
		HashMap response = super.getDetails((TileEntity)obj);
		if (UEApi != null) {
			response.putAll(UniversalElectricityUtils.getDetails(world, obj, additional));
		}
		return response;
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
		return "OpenCCSensors:powerCard";
	}

	@Override
	public Icon getIcon() {
		return icon;
	}

	@Override
	public ItemStack getUniqueRecipeItem() {
		return new ItemStack(Item.coal);
	}

	@Override
	public void loadIcon(IconRegister iconRegistry) {
		icon = iconRegistry.func_94245_a("OpenCCSensors:power");
	}
}
