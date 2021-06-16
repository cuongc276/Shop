package com.shop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shop.DTO.OrderDetailDTO;
import com.shop.entity.Order;
import com.shop.entity.OrderDetail;
import com.shop.entity.Product;
import com.shop.entity.User;
import com.shop.mappers.OrderDetailMapper;
import com.shop.repositories.OrderRepository;
import com.shop.utils.DateFormat;

@Service
public class UserProductSevice {

	@Autowired
	private OrderDetailMapper orderDetailMapper;
	
	@Autowired
	@Qualifier("newOrder")
	private Order order;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private DateFormat dateFormat;
	
	public List<OrderDetailDTO> addOrderDetailDTO(HttpServletRequest request, Product pro) {
		HttpSession session = request.getSession();
		Object ob = session.getAttribute("listOrderDetails");
		List<OrderDetailDTO> list = new ArrayList<OrderDetailDTO>();
		if(ob != null) {
			list = (List<OrderDetailDTO>) ob; 
			for(OrderDetailDTO od: list) {
				if(od.getProduct().getId() == pro.getId()) {
					System.out.println("b");
					od.setQuantity(od.getQuantity() + 1);
					session.setAttribute("listOrderDetails", list);
					return list;
				}
			}
			OrderDetailDTO od = new OrderDetailDTO(null,1,pro.getPrice(),null,pro);
			list.add(od);
			session.setAttribute("listOrderDetails", list);
			return list;
		}else {
			OrderDetailDTO od = new OrderDetailDTO(null,1,pro.getPrice(),null,pro);
			list.add(od);
			session.setAttribute("listOrderDetails", list);
			return list;
		}
	}
	
	public List<OrderDetailDTO> removeOrderDetailDTO(HttpServletRequest request, Product pro){
		HttpSession session = request.getSession();
		List<OrderDetailDTO> list = getListOrderDetailDTO(request); 
		for(OrderDetailDTO od: list) {
			if(od.getProduct().getId() == pro.getId()) {
				if(od.getQuantity() > 1) {
					od.setQuantity(od.getQuantity() - 1);
				}else {
					list.remove(od);
				}
				session.setAttribute("listOrderDetails", list);
				return list;
			}
		}
		session.setAttribute("listOrderDetails", list);
		return list;
	}
	
	public List<OrderDetailDTO> getListOrderDetailDTO(HttpServletRequest request){
		HttpSession session = request.getSession();
		Object ob = session.getAttribute("listOrderDetails");
		List<OrderDetailDTO> list = (List<OrderDetailDTO>) ob; 
		return list;
	}
	
	public Order saveOrder(HttpServletRequest request, String address) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<OrderDetailDTO> list = getListOrderDetailDTO(request);
		if(list == null) {
			return null;
		}else {
			List<OrderDetail> listEntity = orderDetailMapper.convertToListEntity(list);
			String date = dateFormat.toString(new Date(), "yyyy-MM-dd");
			order.setCreate_date(date);
			order.setAddress(address);
			order.setUser(user);
			orderRepository.save(order);
			Order order1 = orderRepository.getOrderByFiled(date, user, address);
			for(OrderDetail o: listEntity) {
				o.setOrder(order1);
			}
			order1.setOrder_details(listEntity);
			orderRepository.save(order1);
			return order1;
		}
	}
	
	public Double getTotal(HttpServletRequest request) {
		List<OrderDetailDTO> listEntity = getListOrderDetailDTO(request);
		Double total=0.0;
		if(listEntity != null) {
			for (OrderDetailDTO o : listEntity) {
				total += (o.getPrice() * o.getQuantity());
			}
		}
		return total;
	}
	
	public void removeAll(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("listOrderDetails");
	}
	
}
