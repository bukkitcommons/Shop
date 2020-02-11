package cc.bukkit.shop.viewer;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;
import cc.bukkit.shop.ContainerShop;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShopViewer {
  @Nullable
  private final ContainerShop shop;
  
  public static ShopViewer of(@Nullable ContainerShop shop) {
    return new ShopViewer(shop);
  }
  
  public static ShopViewer empty() {
    return new ShopViewer(null);
  }
  
  private boolean fails;
  
  public ShopViewer reset() {
    fails = false;
    return this;
  }
  
  public ShopViewer nonNull() {
    fails = shop == null;
    return this;
  }
  
  public ShopViewer accept(Consumer<ContainerShop> consumer) {
    if (!fails) consumer.accept(shop);
    return this;
  }
  
  public boolean test(Predicate<ContainerShop> predicate, boolean def) {
    return fails ? def : !predicate.test(shop);
  }
  
  public <R> R apply(Function<ContainerShop, R> function, R def) {
    return fails ? def : function.apply(shop);
  }
  
  public ShopViewer filter(Predicate<ContainerShop> predicate) {
    fails = fails ? true : !predicate.test(shop);
    return this;
  }
  
  public boolean isEmpty() {
    return shop == null;
  }
  
  public ShopViewer ifPresent(Consumer<ContainerShop> consumer) {
    if (!fails && shop != null)
      consumer.accept(shop);
    return this;
  }
  
  public ShopViewer ifPresent(Runnable runnable) {
    if (!fails && shop != null)
      runnable.run();
    return this;
  }
  
  public boolean isPresent() {
    return shop != null;
  }

  public ContainerShop get() {
    return shop;
  }
}
