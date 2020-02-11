package cc.bukkit.shop.event;

import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import cc.bukkit.shop.ContainerShop;
import cc.bukkit.shop.hologram.DisplayData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class ShopDisplayItemDespawnEvent extends ShopEvent implements Cancellable {
  @NotNull
  private final ContainerShop shop;
  @NotNull
  private final ItemStack itemStack;
  @NotNull
  private final DisplayData displayData;

  @Setter
  private boolean cancelled;
}
