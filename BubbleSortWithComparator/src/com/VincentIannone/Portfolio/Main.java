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
		/*
		 * OUTPUT:
		 * -4562
		 * -54
		 * -35
		 * 1
		 * 3
		 * 3
		 * 4
		 * 6
		 * 6
		 * 7
		 * 7
		 * 46
		 * 47
		 * 726763
		 */
		System.out.println(BinarySearch(list, c, 3));
		/*
		 * OUTPUT:
		 * 4
		 */
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
	static <T> int BinarySearch(List<T> sortedList, Comparator<? super T> comparator, T element) {
		int min = 0;
		int pivot = (int) (sortedList.size() - 1)/ 2;
		int max = sortedList.size() - 1;
		while(true) {
			if(max - min == 1) {
				return -1;
			}
			if(comparator.compare(element, sortedList.get(pivot)) < 0){
				max = pivot;
				pivot = (int)  (max + min) / 2;
			}else if (comparator.compare(element, sortedList.get(pivot)) > 0) {
				min = pivot;
				pivot = (int)  (max + min) / 2;
			}else {
				return pivot;
			}
		}
	}
	
	static <T> void Swap(List<T> list, int a, int b) {
		T temp = list.set(a,  list.get(b));
		list.set(b, temp);
	}
	

}
