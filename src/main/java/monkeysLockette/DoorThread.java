package monkeysLockette;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.scheduler.BukkitRunnable;

public class DoorThread extends BukkitRunnable {
	public static ArrayList<DoorHolder> doors = new ArrayList<DoorHolder>();
	public static int doorindex(Block door)
	{
		for (int i = 0;i < doors.size();i++)
		{
			if (door.getLocation().equals(doors.get(i).Door.getLocation()))
			{
				return i;
			}
		}
		return -1;
	}
	public static void removedoor(Block door)
	{
		Block ddoor = door.getRelative(BlockFace.DOWN);
		if (ddoor.getType() != door.getType())
		{
			ddoor = door;
		}
		int ind = doorindex(ddoor);
		if (ind > -1)
		{
			doors.remove(ind);
		}
	}
	public static boolean toggledoor(Block door)
	{
		Block ddoor = door.getRelative(BlockFace.DOWN);
		if (ddoor.getType() != door.getType() || (door.getType() != Material.IRON_DOOR_BLOCK && door.getType() != Material.WOODEN_DOOR))
		{
			ddoor = door;
		}
		int ind = doorindex(ddoor);
		if (ind > -1)
		{
			doors.remove(ind);
			monkeysLockette.DebugInfo("Door remove!");
			return false;
		}
		else
		{
			doors.add(new DoorHolder(ddoor, monkeysLockette.CloseDoorsAfter));
			monkeysLockette.DebugInfo("Door add!");
			return true;
		}
	}
	@Override
	public void run()
	{
		int size = doors.size();
		for (int i = 0;i < size;i++)
		{
			if (!doors.get(i).dec())
			{
				doors.remove(i);
				size = doors.size();
				i--;
			}
		}
	}
}
