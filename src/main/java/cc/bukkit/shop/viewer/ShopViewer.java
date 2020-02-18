package cc.bukkit.shop.viewer;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;
import cc.bukkit.shop.ShopImage;
import cc.bukkit.shop.stack.Stack;
import cc.bukkit.shop.stack.Stacked;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class ShopViewer {
  @Nullable
  private final Stacked shop;
  
  public static ShopViewer of(@Nullable ShopImage shop) {
    return new ShopViewer(Stack.of(shop));
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
  
  public <T extends ShopImage> ShopViewer accept(Consumer<T> consumer) {
    if (!fails) consumer.accept(shop.stack());
    return this;
  }
  
  public <T extends ShopImage> boolean test(Predicate<T> predicate, boolean def) {
    return fails ? def : !predicate.test(shop.stack());
  }
  
  public <T extends ShopImage, R> R apply(Function<T, R> function, R def) {
    return fails ? def : function.apply(shop.stack());
  }
  
  public <T extends ShopImage> ShopViewer filter(Predicate<T> predicate) {
    fails = fails ? true : !predicate.test(shop.stack());
    return this;
  }
  
  public boolean isEmpty() {
    return shop == null;
  }
  
  public <T> ShopViewer ifPresent(Consumer<T> consumer) {
    if (!fails && shop != null)
      consumer.accept(shop.stack());
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

  public <T extends ShopImage> T get() {
    return shop.stack();
  }
}
