package monkeysLockette;

import java.util.HashSet;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Openable;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

public class Util {
	public static boolean Contains(HashSet<Material> list, Material mat)
	{
		return list.contains(mat);
		/*for (Material check: list)
		{
			if (check == mat)
			{
				return true;
			}
		}
		return false;*/
	}
	public static byte FaceToData(BlockFace face)
	{
		if (face == BlockFace.NORTH)
		{
			return 3;
		}
		else if (face == BlockFace.WEST)
		{
			return 5;
		}
		else if (face == BlockFace.SOUTH)
		{
			return 2;
		}
		else
		{
			return 4;
		}
	}
	public static BlockFace DataToFace(byte face)
	{
		if (face == 3)
		{
			return BlockFace.NORTH;
		}
		else if (face == 5)
		{
			return BlockFace.WEST;
		}
		else if (face == 2)
		{
			return BlockFace.SOUTH;
		}
		else
		{
			return BlockFace.EAST;
		}
	}
	private static void FlipDoorInternal(Block door)
	{
		Openable doorOpenable = (Openable) door.getBlockData();
		doorOpenable.setOpen(!doorOpenable.isOpen());
		door.setBlockData(doorOpenable);
	}
	public static void FlipDoor(Block door, boolean openorclose)
	{
		Material mat = door.getType();
		if (mat.name().endsWith("_DOOR")
				&& door.getBlockData() instanceof Openable)
		{
			if (door.getRelative(BlockFace.DOWN).getType() == mat)
			{
				FlipDoorInternal(door.getRelative(BlockFace.DOWN));
			}
			else
			{
				FlipDoorInternal(door);
			}
		}
		else
		{
			FlipDoorInternal(door);
		}
		door.getWorld().playSound(door.getLocation(), openorclose ? Sound.BLOCK_WOODEN_DOOR_OPEN: Sound.BLOCK_WOODEN_DOOR_CLOSE, 2, 1);
	}
	public static Block findconnected(Block door)
	{
		Material mat = door.getType();
		if (mat.name().endsWith("_DOOR")
				&& door.getBlockData() instanceof Openable)
		{
			Block tb = door.getRelative(BlockFace.NORTH);
			if (tb.getType() == mat)
			{
				return tb;
			}
			tb = door.getRelative(BlockFace.WEST);
			if (tb.getType() == mat)
			{
				return tb;
			}
			tb = door.getRelative(BlockFace.SOUTH);
			if (tb.getType() == mat)
			{
				return tb;
			}
			tb = door.getRelative(BlockFace.EAST);
			if (tb.getType() == mat)
			{
				return tb;
			}
		}
		return null;
	}
	public static void setMetadata(Player player, String key, Object value)
	{
		  player.setMetadata(key, new FixedMetadataValue(monkeysLockette.plug, value));
	}
	public static String getMetadata(Player player, String key)
	{
		  List<MetadataValue> values = player.getMetadata(key);
		  for(MetadataValue value : values)
		  {
		     if(value.getOwningPlugin().getDescription().getName().equals(monkeysLockette.plug.getDescription().getName()))
		     {
		        return (String) value.value();
		     }
		  }
		  return null;
	}
}
