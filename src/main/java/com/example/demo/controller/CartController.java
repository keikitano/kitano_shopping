package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Items;
import com.example.demo.Repositry.ItemRepository;
import com.example.demo.classies.Cart;
@Controller
public class CartController {
	@Autowired
	HttpSession session;
	@Autowired
	ItemRepository itemRepository;
	
	//カートに入れるボタンを押したときの処理
		@RequestMapping(value="/itemDetail/addCart/{code}",method=RequestMethod.POST)
		public ModelAndView addcart(
				@PathVariable("code")int code,
				@RequestParam(name="quantity",defaultValue="1")int quantity,
				ModelAndView mv) {
			//セッションスコープからカート情報を取得する
			Cart cart=(Cart)session.getAttribute("cart");
			//カートの中に同じ商品がなかったら
			if(cart==null) {
				//セッションに保存する
				cart=new Cart();
				session.setAttribute("cart", cart);
			}
			
			//Cart cart=getCartFromSession();
			//商品コードをキーに商品情報を取得し、カートに追加する
			Items items=itemRepository.findById(code).get();
			
			//cartクラスのaddCartメソッドから処理を呼び出す
			//変数items１から数えれるようにする
			cart.addCart(items,1);
			//
			mv.addObject("items",cart.getItems());
			mv.addObject("total",cart.getTotal());
			mv.setViewName("cart");
			
			return mv;
		}
}
