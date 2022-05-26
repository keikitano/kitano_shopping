package com.example.demo.controller;

import java.util.List;

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
	
	
	//カートの中身ページで商品一覧に戻りたいときの処理
	@RequestMapping(value="/showItem")
	public ModelAndView showItem(ModelAndView mv) {
		//一覧を表示する
		List<Items> itemList=itemRepository.findAll();
		mv.addObject("items",itemList);
		mv.setViewName("showItem");
		return mv;
	}
	
	// 商品登録画面表示
		@RequestMapping("/addItem")
		public String addItem() {
			return "addItem";
		}

		// 出品情報の登録ボタンを押したときの処理
		@RequestMapping(value = "/addItem", method = RequestMethod.POST)
		public ModelAndView addItem(
				@RequestParam("name") String name, 
				@RequestParam(name="price",defaultValue="0") int price,
				@RequestParam(name="stock",defaultValue="0") int stock, 
				@RequestParam(name="delivaryDays",defaultValue="0") int delivaryDays,
				@RequestParam(name="categoryKey",defaultValue="0") int categoryKey,
				@RequestParam("picture") String picture, ModelAndView mv) {
			
			
			if (isNull(name) ||isZero(price)||isZero(stock)||isZero(delivaryDays)||isNull(picture)) {
				mv.addObject("message", "未入力です");
				mv.setViewName("addItem");
				return mv;
			} else {
				//データベースに保存する
				Items items = new Items(name, price, stock, delivaryDays, null,picture);
				// 更新する
				itemRepository.saveAndFlush(items);
				//商品一覧を表示
				List<Items> itemList=itemRepository.findAll();
				
				
				mv.addObject("items",itemList);
				mv.setViewName("showItem");
			}
			return mv;
		}
		
		//出品情報の閲覧の画面表示
		@RequestMapping("/itemInfo")
		public String itemInfo() {
			return "itemInfo";
		}
		
		//出品情報閲覧のURLを押したときの処理
		@RequestMapping(value="/itemInfo/{code}")
		public ModelAndView itemInfo(
				@PathVariable("code")int code,
				ModelAndView mv) {
			//１つのコードだけ持ってくる
			Items item = itemRepository.findById(code).get();
			//iteminfoHTMLのitemに変数itemを追加する
			mv.addObject("item",item);
//			Items i = (Items) session.getAttribute("itemInfo");
			//@PathVariableの変数名codeに１つだけ持ってきたコードをセットする
			mv.addObject("code", item.getCode());
			mv.setViewName("itemInfo");
			
			return mv;
		}
		//出品情報を編集する
		@RequestMapping(value="/itemInfo/{code}",method=RequestMethod.POST)
		public ModelAndView confirmItem(
				@PathVariable("code")int code,
				@RequestParam("name")String name,
				@RequestParam(name="price",defaultValue="0") int price,
				@RequestParam(name="stock",defaultValue="0") int stock, 
				@RequestParam(name="delivaryDays",defaultValue="0") int delivaryDays,
				//@RequestParam(name="categoryKey",defaultValue="0") int categoryKey,
				@RequestParam("picture") String picture,
				ModelAndView mv) {
			//もし未入力の場合
			if (isNull(name) ||isZero(price)||isZero(stock)||isZero(delivaryDays)||isNull(picture)) {
				mv.addObject("message", "未入力です");
				//１つのコードだけ持ってくる
				Items item = itemRepository.findById(code).get();
				//iteminfoHTMLのitemに変数itemを追加する
				mv.addObject("item",item);
				//@PathVariableの変数名codeに１つだけ持ってきたコードをセットする
				mv.addObject("code", item.getCode());
				mv.setViewName("itemInfo");
				return mv;
			//入力されていたら
			} else {
				//１つのコードだけ持ってくる
				Items item = itemRepository.findById(code).get();
				
				item.setName(name);
				item.setPrice(price);
				item.setPicture(picture);
				item.setStock(stock);
				item.setDelivaryDays(delivaryDays);
				
				
				// 更新する
				itemRepository.saveAndFlush(item);
				//リストを一覧する
				List<Items> itemList=itemRepository.findAll();
				mv.addObject("items",itemList);
				mv.addObject("code",item.getCode());
				mv.setViewName("showItem");
			}
			
			return mv;
		}
		
		// 出品情報の削除ボタンを押されたとき
		@RequestMapping(value="/deleteItem/{code}")
		public ModelAndView deleteItem(
				@PathVariable("code")int code,
				ModelAndView mv) {
			//Itemsのデータベースからコードをとってくる
			Items item = itemRepository.findById(code).get();
			
			// 表示している商品のコードを指定
			itemRepository.deleteById(item.getCode());
			// 削除
			itemRepository.flush();
			List<Items> itemList=itemRepository.findAll();
			mv.addObject("items",itemList);
			mv.addObject("code",item.getCode());
			mv.setViewName("showItem");
			return mv;
		}
		
		public boolean isNull(String text) {
			return (text == null || text.length() == 0);
		}

		public boolean isZero(int num) {
			return (num < 0);

		}


}
