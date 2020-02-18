package cc.bukkit.shop.viewer;

public enum ViewAction {
  /**
   * Equals with <code>continue</code> commonly and
   * <code>return</code> for lambda in Java,
   * indicates skipping the current viewer action.
   */
  NEXT,
  
  /**
   * Equals with <code>break</code> in Java,
   * indicates breaking the current viewer action.
   */
  BREAK;
}
