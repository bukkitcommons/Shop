package cc.bukkit.shop.feature;

/**
 * The dual price for buying and selling.
 * @param <P> the buying price
 * @param <Q> the selling price
 */
public interface DualPriced extends Priced {
  <P> P buyingPrice();
  
  <Q> Q sellingPrice();
  
  <P> void setBuyingPrice(P newPrice);
  
  <Q> void setSellingPrice(Q newPrice);
}
