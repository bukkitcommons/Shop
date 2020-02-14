package cc.bukkit.shop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.google.common.io.Files;

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
   * Read the file to the String
   *
   * @param cacheFile Target file.
   * @return Target file's content.
   */
  public static String readToString(@NotNull File file) {
    long filelength = file.length();
    byte[] filecontent = new byte[(int) filelength];
    try {
      file.createNewFile();
      FileInputStream in = new FileInputStream(file);
      in.read(filecontent);
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new String(filecontent, StandardCharsets.UTF_8);
  }
  
  public static String fillArgs(@Nullable String raw, @Nullable String... args) {
    if (raw == null) {
      return "Invalid message: null";
    }
    if (raw.isEmpty()) {
      return "";
    }
    if (args == null) {
      return raw;
    }
    for (int i = 0; i < args.length; i++) {
      raw = StringUtils.replace(raw, "{" + i + "}", args[i] == null ? "" : args[i]);
    }
    return raw;
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
