package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Items;
import com.example.demo.Repositry.ItemRepository;

@Controller
public class ItemController {
	@Autowired
	HttpSession session;
	@Autowired
	ItemRepository itemRepository;
	//詳細ページに飛ぶ
	//商品詳細を押したときの処理
	@RequestMapping(value="/itemDetail/{code}" )
	public ModelAndView itemDetail(
			@PathVariable(name="code")int code,
			
			ModelAndView mv) {
		//１つのitemだけ取ってくる
		mv.addObject("item",itemRepository.findById(code).get());
		
		mv.setViewName("itemDetail");
		return mv;
	}
	//カートに入れるボタンを押したときの処理
//	@RequestMapping(value="/itemDetail/addCart/{code}",method=RequestMethod.POST)
//	public ModelAndView addcart(
//			@PathVariable("code")int code,
//			@RequestParam(name="quantity",defaultValue="1")int quantity,
//			ModelAndView mv) {
//		//セッションスコープからカート情報を取得する
//		Cart cart=(Cart)session.getAttribute("cart");
//		if(cart==null) {
//			cart=new Cart();
//			session.setAttribute("cart", cart);
//		}
//		
//		//Cart cart=getCartFromSession();
//		//商品コードをキーに商品情報を取得し、カートに追加する
//		Items items=itemRepository.findById(code).get();
//		
//		
//		cart.addCart(items,1);
//		
//		mv.addObject("items",cart.getItems());
//		mv.addObject("total",cart.getTotal());
//		mv.setViewName("cart");
//		
//		return mv;
//	}
	
	//購入ぼたんを押したとき
//	@RequestMapping(value="/purchaseCart")
//	public ModelAndView purchaseCart(
//			
//			ModelAndView mv) {
//
//		mv.setViewName("purchaseCart");
//		return mv;
//	}
//	
//	//削除ボタンが押されたとき
//	@RequestMapping(value="/itemDetail/deleteCart/{code}",method=RequestMethod.POST)
//	public ModelAndView deletecart(
//			@PathVariable("code")int code,
//			ModelAndView mv) {
//		Cart cart=(Cart)session.getAttribute("cart");
//		cart.deleteCart(code);
//		mv.addObject("items",cart.getItems());
//		mv.setViewName("cart");
//		return mv;
//	}
//	
	
	//カートの中身ページで商品一覧商品一覧に戻りたいときの処理
	@RequestMapping(value="/showItem")
	public ModelAndView showItem(ModelAndView mv) {
		//session.invalidate();
		List<Items> itemList=itemRepository.findAll();
		mv.addObject("items",itemList);
		mv.setViewName("showItem");
		return mv;
	}
}
