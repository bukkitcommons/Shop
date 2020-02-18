package cc.bukkit.shop.misc;

import java.io.Serializable;

public final class Void implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Void() {}
  
  private static final class Singleton {
    private static final Void INSTANCE = new Void();
  }
  
  public static Void singleton() {
    return Singleton.INSTANCE;
  }
}
