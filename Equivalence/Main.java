package com.VincentIannone.Equivalence;

import java.util.ArrayList;
import java.util.Hashtable;

/*
 * The purpose of this program is to illustrate equivalence relations between pairs of 
 *	 integers using the smallest amount of information possible.
 * An equivalence relation holds that two objects (in this case, integers) are connected.
 * Here, pairs of integers are input into a program: {(4, 3), (3,8), (6, 5), (4, 8)}.
 * The individual pairs are connected to each other: 4 is connected to 3, 3 is connected to 8, and 6
 *	 is connected to 5.
 * Also, every integer shares the connections of its own connections: 4 is connected to 8 via the
 * 	shared integer 3, and 4 is also connected to 8 explicitly in the 4th input pair.
 * This program will remove pairs of integers from its output so that all connections are displayed
 * 	with the least amount of redundant information possible. For example:
 * 		INPUT: (4, 3) (3, 8) (6, 5) (4, 8)
 * 		OUTPUT: (4, 3) (3, 8) (6, 5)
 * Here we see that the pair (4, 8) has been removed from output because it contains redundant information:
 * 	4 is already connected to 8 through the shared connection to 3 as shown in: (4, 3) (3, 8)
 * 
 * Emphasis is placed on readability and maintainability rather than efficiency.
 */
public class Main {

	public static void main(String[] args) {
		int[] inputPairs = new int[] { 4, 3, 3, 8, 6, 5, 9, 4, 2, 1, 8, 9, 5, 0, 7, 2, 6, 1, 1, 0, 6, 7};
		/*
		 * OUTPUT:
		 * 4 3
		 * 3 8
		 * 6 5
		 * 9 4
		 * 2 1
		 * 5 0
		 * 7 2
		 * 6 1
		 */
		ArrayList<Integer> outputPairs = new ArrayList<Integer>();
		if(inputPairs.length % 2 != 0) {
			System.out.println("ERROR! Odd number of integers");
			return;
		}
		int currGroup = 0;
		Hashtable<Integer, ValueGroup> intContainer = new Hashtable<Integer, ValueGroup>();
		for(int i = 0; i < inputPairs.length; i += 2) {
			int a = inputPairs[i];
			int b = inputPairs[i + 1];
			//check to see what's listed
			if(!intContainer.containsKey(a) && !intContainer.containsKey(b)) {
				//since neither member is in the list, add them both in a new group
				//all members within a group are interconnected
				intContainer.put(a, new ValueGroup(a, currGroup));
				intContainer.put(b, new ValueGroup(b, currGroup));
				outputPairs.add(a);
				outputPairs.add(b);
				currGroup++;
			}else if(intContainer.containsKey(a) && !intContainer.containsKey(b)) {
				//if a is in the list, but b isn't, add b to a's group
				intContainer.put(b, new ValueGroup(b, intContainer.get(a).group));
				outputPairs.add(a);
				outputPairs.add(b);
			}else if(intContainer.containsKey(b) && !intContainer.containsKey(a)) {
				//vice-versa
				intContainer.put(a, new ValueGroup(a, intContainer.get(b).group));
				outputPairs.add(a);
				outputPairs.add(b);
			}else {
				//both are already contained within the list
				//are they in the same group? if so, just ignore
				//are they in different groups? if so, make those two groups the same
				int aGroup = intContainer.get(a).group;
				int bGroup = intContainer.get(b).group;
				if(aGroup != bGroup) {
					//replace every instance of group b with group a
					for(int value : intContainer.keySet()) {
						if(intContainer.get(value).group == bGroup) {
							intContainer.put(value, new ValueGroup(value, aGroup));
						}
					}
					outputPairs.add(a);
					outputPairs.add(b);
				}
			}
		}
		//now print
		for(int i = 0; i < outputPairs.size(); i += 2) {
			System.out.println(outputPairs.get(i) + " " + outputPairs.get(i + 1));
		}

	}
}
