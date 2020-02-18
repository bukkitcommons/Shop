package cc.bukkit.shop.manager;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public interface PermissionManager {
  /**
   * Check the permission for sender
   *
   * @param sender The CommandSender you want check
   * @param permission The permission node wait to check
   * @return The result of check
   */
  default boolean has(@NotNull CommandSender sender, @NotNull String permission) {
    return sender.isOp() ? true : hasExact(sender, permission);
  }
  
  boolean hasExact(@NotNull CommandSender sender, @NotNull String permission);
}
