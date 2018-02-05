package com.VincentIannone.Portfolio;


import java.util.Comparator;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 6, 1, 6, 7, 4, 7, 3, 47, 726763, -35, 46, -4562, -54));
		Comparator c = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if(o1 < o2) {
					return -1;
				}else if (o1 > o2) {
					return 1;
				}
				return 0;
			}
			
		};
		BubbleSort(list, c);
		for (Integer i : list) {
		      System.out.println(i);
		}
	}
	
	static <T> void BubbleSort(List<T> list, Comparator<? super T> c) {
		boolean itemChanged = true;
		while(itemChanged) {
			itemChanged = false;
			for(int i = 0; i < list.size() - 1; i++) {
				if(c.compare(list.get(i), list.get(i+1)) > 0) {
					Main.Swap(list, i, i + 1);
					itemChanged = true;
				}
			}
		}
	}
	static <T> void Swap(List<T> list, int a, int b) {
		T temp = list.set(a,  list.get(b));
		list.set(b, temp);
	}
	

}
