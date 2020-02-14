package cc.bukkit.shop.logger;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class ShopLogger extends ShopPluginLogger {
  public static void initalize(@NotNull Plugin plugin, boolean log4j) { // Make an enum
    new Singleton(plugin, log4j);
  }
  
  /**
   * Gets the instance of this logger, need initalizing.
   * @see ShopLogger#initalize(Plugin)
   * @return the shop logger.
   */
  public static ShopLogger instance() {
    return Singleton.INSTANCE;
  }
  
  private static class Singleton {
    private static ShopLogger INSTANCE;
    
    private Singleton(@NotNull Plugin plugin, boolean log4j) {
      INSTANCE = new ShopLogger(plugin, log4j);
    }
  }
  
  private ShopLogger(@NotNull Plugin plugin, boolean log4j) {
    super(plugin, log4j);
  }
}
