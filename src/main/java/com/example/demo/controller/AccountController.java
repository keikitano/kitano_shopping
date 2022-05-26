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
		// session.invalidate();
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
			// 新規登録で入力した情報を導入
//			Users users=(Users)session.getAttribute("users");
//			if(users.getName()==name) {
//				mv.setViewName("showItem");
//			}
			// nameとpassをとってくる
			List<Users> userlist = userRepository.findByNameAndPass(name, pass);
			// もしuserlistと入力された値が一致しなかったら
			if (userlist.size() == 0) {
				mv.addObject("message1", "一致しません");
				//ログインに戻る
				mv.setViewName("login");
			}
			// 一致したら
			else {
				//セッションに一時的にaccountInfoに情報を保存する
				session.setAttribute("accountInfo", userlist.get(0));
				// アイテム一覧を表示
				List<Items> itemList = itemRepository.findAll();
				//showitemHTMLのitemsitemsにitemListを追加する
				mv.addObject("items", itemList);
				mv.setViewName("showItem");
			}
		}
		return mv;
	}

	// 新規画面表示
	@RequestMapping("/newAccount")
	public String newAccount() {
		// session.invalidate();
		return "newAccount";
	}

	// 新規登録ボタンを押したときの処理
	@RequestMapping(value = "/newAccount", method = RequestMethod.POST)
	public ModelAndView newAccount(@RequestParam("name") String name, @RequestParam("address") String address,
			@RequestParam("tel") String tel, @RequestParam("email") String email, @RequestParam("pass") String pass,

			ModelAndView mv) {
		if (isNull(name) || isNull(address) || isNull(tel) || isNull(email) || isNull(pass)) {
			// 未入力の場合はログイン画面に戻る
			mv.addObject("message", "未入力です");
			mv.setViewName("newAccount");
			return mv;
		} else {
			// データベースに保存
			Users user = new Users(null, name, address, tel, email, pass);
			//更新する
			userRepository.saveAndFlush(user);
			mv.setViewName("login");
		}
		return mv;
	}

	// ログアウト
	@RequestMapping("/logout")
	public String logout() {
		return login();
	}

	// ユーザー情報閲覧の画面表示
	@RequestMapping("/accountInfo")
	public String accountInfo() {
		return "accountInfo";
	}

	// ユーザー情報閲覧のURLを押されたとき
	// ログインしたユーザー情報とってくる
	@RequestMapping(value = "/accountInfo", method = RequestMethod.POST)
	public ModelAndView accountInfo(@RequestParam("name") String name, @RequestParam("address") String address,
			@RequestParam("tel") String tel, @RequestParam("email") String email, @RequestParam("pass") String pass,
			ModelAndView mv) {

		// ユーザー情報変更
		// 入力漏れがあった場合
		if (isNull(name) || isNull(address) || isNull(tel) || isNull(email) || isNull(pass)) {
			// 未入力の場合はログイン画面に戻る
			mv.addObject("message", "未入力です");
			mv.setViewName("accountInfo");
			return mv;
		}
		// 入力されてたら
		else {

			// これから入力される情報の格納場所を作る
			// ログインでsetAttributeした変数"accountInfo"を受け取る
			Users u = (Users) session.getAttribute("accountInfo");
//			Users u = userRepository.findByCode(code).get(0);
//			session.setAttribute("accountInfo", );
			// アカウント情報のコードをゲットして変数名codeにする
			mv.addObject("code", u.getCode());
			// 入力された情報をセッションに格納する
			Users user = new Users(u.getCode(), name, address, tel, email, pass);
			// 更新する
			userRepository.saveAndFlush(user);
			mv.setViewName("login");
		}

		return mv;
	}

	// アカウント情報の削除ボタンを押されたとき
	@RequestMapping("/deleteAccount")
	public ModelAndView deleteAccount(

			ModelAndView mv) {
		// accountInfoのデータを受け取る
		Users u = (Users) session.getAttribute("accountInfo");
		// ログインしているユーザー情報のコードを指定
		userRepository.deleteById(u.getCode());
		// 削除
		userRepository.flush();
		mv.setViewName("top");
		return mv;
	}

	
	public boolean isNull(String text) {
		return (text == null || text.length() == 0);
	}

	
}
