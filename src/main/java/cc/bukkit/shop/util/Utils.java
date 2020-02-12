package cc.bukkit.shop.util;

import java.util.UUID;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public abstract class Utils {
  /**
   * Gets an unique key of a chunk based on its coordinates.
   * 
   * @param x X Coordinate
   * @param z Z Coordinate
   * @return Chunk coordinates packed into a long
   * @author Aikar
   */
  public static long chunkKey(int chunkX, int chunkZ) {
    return (long) chunkX & 0xffffffffL | ((long) chunkZ & 0xffffffffL) << 32;
  }
  
  public static boolean isUUID(@NotNull String string) {
    if (string.length() != 36 && string.length() != 32) {
      return false;
    }
    try {
      UUID.fromString(string);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  /**
   * Gets an unique key of a block based on its coordinates.
   * 
   * @param x X Coordinate
   * @param z Z Coordinate
   * @return Chunk coordinates packed into a long
   * @author Spottedleaf
   */
  public static long blockKey(int x, int y, int z) {
    return ((long) x & 0x7FFFFFF) | (((long) z & 0x7FFFFFF) << 27) | ((long) y << 54);
  }
  
  public static long blockKey(Location loc) {
    return blockKey(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
  }
}
