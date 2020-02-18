package cc.bukkit.shop.feature;

import org.jetbrains.annotations.NotNull;
import cc.bukkit.shop.hologram.Display;
import cc.bukkit.shop.hologram.DisplayScheme;

public interface Displayed {
  /**
   * Get instance of the virtual display item.
   * @return The display.
   */
  @NotNull
  Display display();
  
  @NotNull
  DisplayScheme scheme();
}
