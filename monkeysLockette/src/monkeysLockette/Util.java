package monkeysLockette;


import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

public class Util {
	public static boolean Contains(Material[] list, Material mat)
	{
		for (Material check: list)
		{
			if (check == mat)
			{
				return true;
			}
		}
		return false;
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
		door.setData((byte)(door.getData() ^ 4));
	}
	public static void FlipDoor(Block door, boolean openorclose)
	{
		Material mat = door.getType();
		if (mat == Material.WOODEN_DOOR || mat == Material.IRON_DOOR_BLOCK
				|| (mat.getId() >= 193 && mat.getId() <= 197))
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
		door.getWorld().playSound(door.getLocation(), openorclose ? Sound.DOOR_OPEN: Sound.DOOR_CLOSE, 2, 1);
	}
	public static Block findconnected(Block door)
	{
		Material mat = door.getType();
		if (mat == Material.WOODEN_DOOR || mat == Material.IRON_DOOR_BLOCK || (mat.getId() >= 193 && mat.getId() <= 197))
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
		  player.setMetadata(key, new FixedMetadataValue(monkeysLockette.plug,value));
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
