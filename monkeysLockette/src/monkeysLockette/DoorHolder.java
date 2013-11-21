package monkeysLockette;

import org.bukkit.block.Block;

public class DoorHolder {
	public Block Door = null;
	public int ticker = 0;
	public boolean invalidate = false;
	public DoorHolder(Block _door, int _ticks)
	{
		Door = _door;
		ticker = _ticks;
	}
	public boolean dec()
	{
		if (invalidate)
		{
			return false;
		}
		ticker--;
		if (ticker > 0)
		{
			return true;
		}
		Util.FlipDoor(Door, false);
		return false;
	}
}
