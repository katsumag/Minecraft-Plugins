package Utils;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class Spacial_Manipulation {
	
	public List<Block> GetBlocksInCube(Location middle, int _x, int _y, int _z) {
        List<Block> selectedBlocks = new ArrayList<>();

        Location iterator = middle.clone();
        iterator.subtract(new Vector(_x, _y, _z));
        Location reset_point = iterator.clone();

        // LoopSize
        int loopsizeX = _x * 2 + 1,
            loopsizeY = _y * 2 + 1,
            loopsizeZ = _z * 2 + 1;

        for ( int x = 0; x < loopsizeX; x++ ) {
            iterator.setX(reset_point.getX() + x );
            for ( int y = 0; y < loopsizeY; y++ ) {
                iterator.setY(reset_point.getY() + y );
                for ( int z = 0; z < loopsizeZ; z++ ) {
                    iterator.setZ(reset_point.getZ() + z );
                    Block sel = middle.getWorld().getBlockAt(iterator);
                    selectedBlocks.add(sel);
                }
                iterator.setZ(reset_point.getZ());
            }
            iterator.setY(reset_point.getY());
        }

        return selectedBlocks;
    }
	
}
