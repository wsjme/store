package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// 订单
public class Order implements Serializable
{
	  private String oid;  // uuid
	  private String ordertime; //当前时间
	  private double total; // session-cart
	  private Integer state; // 0:未付款  1:已付款未发货  2 以发货未收货  3 以收货未评价  4 订单完成
	  private String address; // null
	  private String name;  // null 
	  private String telephone; // null
	  
	  // 该订单下的所有订单商品集合
	  private List<Orderitem> list=new ArrayList<Orderitem>();
	  // 所属用户
	  private User user;
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Orderitem> getList() {
		return list;
	}
	public void setList(List<Orderitem> list) {
		this.list = list;
	}
	  
	  
}
