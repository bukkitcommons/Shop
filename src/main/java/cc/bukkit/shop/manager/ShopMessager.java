package cc.bukkit.shop.manager;

import java.util.UUID;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface ShopMessager {
  public void clean();

  public void flushMessagesFor(@NotNull Player player);
  
  public void loadTransactionMessages();
  
  public void send(@NotNull UUID uuid, @NotNull String message);
}
