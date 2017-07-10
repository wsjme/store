package domain;

import java.io.Serializable;

// 订单下的订单商品项
public class Orderitem implements Serializable
{
	 	private String itemid; //主键 uuid
	 	private Integer count; // session=cart
	 	private double subtotal; //session=cart 
	 	// 订单项下的订单商品
	 	private Product product;  // session =cart
	    // 该订单项所属的订单
	    private Order order; // order
		
	    public String getItemid() {
			return itemid;
		}
		public void setItemid(String itemid) {
			this.itemid = itemid;
		}
		public Integer getCount() {
			return count;
		}
		public void setCount(Integer count) {
			this.count = count;
		}
		public double getSubtotal() {
			return subtotal;
		}
		public void setSubtotal(double subtotal) {
			this.subtotal = subtotal;
		}
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		public Order getOrder() {
			return order;
		}
		public void setOrder(Order order) {
			this.order = order;
		} 
	    
	    
}
