package com.example.demo.classies;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.Entity.Items;

public class Cart {
//カートの配列
	private Map<Integer, Items> items = new HashMap<>();
	private int total;

	// 合計金額
	public Map<Integer, Items> getItems() {
		return items;
	}

	public int getTotal() {
		return total;
	}

	public void addCart(Items item, int quantity) {
		// 付箋番号ついてるかどうか
		Items existedItem = items.get(item.getCode());
		// 付箋番号ついてなかったら追加しよう
		if (existedItem == null) {
			item.setQuantity(quantity);
			items.put(item.getCode(), item);
		} else {
			int i = existedItem.getQuantity();
			i += quantity;
			existedItem.setQuantity(i);
		}
		recalcTotal();
	}

	// 削除
	public void deleteCart(int itemCode) {
		items.remove(itemCode);
		recalcTotal();

	}
	// 購入
//	public void purchaseCart(int itemCode) {
//		items.remove();
//		
//	}
//	

	// 合計金額
	public void recalcTotal() {
		total = 0;
		for (Items item : items.values()) {
			total += item.getPrice() * item.getQuantity();
		}
	}
}
