package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Items;
import com.example.demo.Entity.Users;
import com.example.demo.Repositry.ItemRepository;
import com.example.demo.Repositry.UserRepository;

@Controller
public class AccountController {

	@Autowired
	HttpSession session;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	ItemRepository itemRepository;
	// top画面
	@RequestMapping("/")
	public String top() {
		session.invalidate();
		return "top";
	}

	// ログイン画面表示
	@RequestMapping("/login")
	public String login() {
		session.invalidate();
		return "login";
	}

	// ログインボタンを押したときの処理
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("name") String name, @RequestParam("pass") String pass,

			ModelAndView mv) {
		if (isNull(name) || isNull(pass)) {
			// 未入力の場合はログイン画面に戻る
			mv.addObject("message", "未入力です");
			mv.setViewName("login");
			return mv;
		} else {
			//新規登録で入力した情報を導入
//			Users users=(Users)session.getAttribute("users");
//			if(users.getName()==name) {
//				mv.setViewName("showItem");
//			}
			//nameとpassをとってくる
			List<Users> userlist=userRepository.findByNameAndPass(name,pass);
			//もしuserlistが一致しなかったら
			if(userlist.size()==0) {
				mv.addObject("message1", "一致しません");
				mv.setViewName("login");
			}
			//一致したら
			else {
				//アイテム一覧を表示
				List<Items> itemList=itemRepository.findAll();
				mv.addObject("items",itemList);
				mv.setViewName("showItem");
			}
			
			
			
//			mv.addObject("name",name);
//			mv.addObject("pass",pass);
			//mv.setViewName("showItem");
		}

		return mv;
	}

	// 新規画面表示
	@RequestMapping("/newAccount")
	public String newAccount() {
		session.invalidate();
		return "newAccount";
	}

	// 新規登録ボタンを押したときの処理
	@RequestMapping(value = "/newAccount", method = RequestMethod.POST)
	public ModelAndView newAccount(@RequestParam("name") String name,
			@RequestParam("address") String address, @RequestParam("tel") String tel,
			@RequestParam("email") String email, @RequestParam("pass") String pass,

			ModelAndView mv) {
		if (isNull(name) || isNull(address) || isNull(tel) || isNull(email) || isNull(pass)) {
			// 未入力の場合はログイン画面に戻る
			mv.addObject("message", "未入力です");
			mv.setViewName("newAccount");
			return mv;
		} else {
			//データベースに保存
			Users user=new Users(null,name,address,tel,email,pass);
			userRepository.saveAndFlush(user);
			
			
//			mv.addObject("name",name);
//			mv.addObject("sex",sex);
//			mv.addObject("address",address);
//			mv.addObject("tel",tel);
//			mv.addObject("email",email);
//			mv.addObject("password",password);
			mv.setViewName("login");
		}
		return mv;
	}
	
	

	public boolean isNull(String text) {
		return (text == null || text.length() == 0);
	}
	//ログアウト
	@RequestMapping("/logout")
	public String logout() {
		return login();
	}

}
