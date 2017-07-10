
package domain;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

// 购物车
public class Cart implements Serializable
{
		// 商品的总金额
		private double total;

        public double getTotal()
        {

            return total;
        }
		// 购物项的集合
        Map<String,CartItem> map = new LinkedHashMap<>();

        public Map<String, CartItem> getMap() {
            return map;
        }

        public void setMap(Map<String, CartItem> map) {
            this.map = map;
        }

    // 添加购物项--- 总金额=总金额+购物项的小计
		public void add(CartItem item)
		{
            CartItem _item = map.get(item.getProduct().getPid());
            // 判断map里面有没有
			if(_item==null)
			{

				// 没有 map直接添加  修改总金额=总金额+添加商品的小计
                map.put(item.getProduct().getPid(),item);
                total = total + item.getSubtotal();
			}else
			{
				// 有    把数量累加了=小计自动变化      修改总金额=总金额+添加商品的小计
                _item.setCount(item.getCount()+_item.getCount());
                total = total + item.getSubtotal();
			}
	
		}
		
		// 删除购物项--- 总金额=总金额-被删除的购物项小计
		public void remove(String key)
		{
			// 删除购物项
            CartItem item = map.remove(key);
            // 总金额=总结额-被删除购物项的小计
            total = total - item.getSubtotal();
		}
		
		// 清空购物项---  总金额=0
		public void removeAll()
		{
			// 清空购物车
            map.clear();
			// 总金额为0
            total = 0;
		}
		
		// 待续
		
}

