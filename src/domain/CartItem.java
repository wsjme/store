package domain;

import java.io.Serializable;

// 购物项
public class CartItem implements Serializable
{
		// 商品的对象
		private Product product;
		// 商品数量
		private int count;
		// 商品小计
		private double subtotal;
		
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public double getSubtotal() {
			// 算小计  小计=商品的价格*数量
			return product.getShop_price()*count;
		}
		/*public void setSubtotal(double subtotal) {
			this.subtotal = subtotal;
		}*/
		
		
		
		
}
