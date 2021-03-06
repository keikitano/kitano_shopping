package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Repositry.ItemRepository;
import com.example.demo.classies.Cart;
@Controller
public class OrderController {
	@Autowired
	HttpSession session;
	@Autowired
	ItemRepository itemRepository;
	//購入ありがとうございました。の画面表示
	@RequestMapping(value="/purchaseCart")
	public ModelAndView purchaseCart(
			
			ModelAndView mv) {
		session.invalidate();
		mv.setViewName("purchaseCart");
		return mv;
	}
	
	//削除ボタンが押されたとき
	@RequestMapping(value="/itemDetail/deleteCart/{code}",method=RequestMethod.POST)
	public ModelAndView deletecart(
			@PathVariable("code")int code,
			ModelAndView mv) {
		//カートクラスからセッション情報を引きだす
		Cart cart=(Cart)session.getAttribute("cart");
		//指定されたコードを消す
		cart.deleteCart(code);
		mv.addObject("items",cart.getItems());
		mv.addObject("total",cart.getTotal());
		mv.setViewName("cart");
		return mv;
	}
	
}
