package cc.bukkit.shop.hologram;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public interface DisplayItem {
  boolean isDisplayItem(Entity entity);

  void fixPosition();

  void remove();

  void respawn();

  void spawn();

  Entity getDisplay();

  Location getDisplayLocation();

  boolean isSpawned();

  boolean pendingRemoval();

  boolean isPendingRemoval();
}
