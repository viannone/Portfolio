using System;

namespace FunctionalFizzBuzz
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			FunctionalFizzBuzz (1, 100, "Functional");
			IfLessAssignmentLessFizzBuzz (1, 100, "Functional, No If's, Ands, or Buts");
		}
		public static void FunctionalFizzBuzz(int current, int max, string thusFar)
		{
			if (current > max) {
				Console.Write (thusFar + "\n");
			} else if (current % 3 == 0 && current % 5 == 0) {
				FunctionalFizzBuzz (current + 1, max, thusFar + "\n" + current + " FizzBuzz");
			} else if (current % 3 == 0) {
				FunctionalFizzBuzz (current + 1, max, thusFar + "\n" + current + " Fizz");
			} else if (current % 5 == 0) {
				FunctionalFizzBuzz (current + 1, max, thusFar + "\n" + current + " Buzz");
			} else {
				FunctionalFizzBuzz (current + 1, max, thusFar + "\n" + current);
			}
		}





		/*
		 * 
		 * Here Be Dragons
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * Spoilers Below
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * */

		//format ({option 1, option 2}, condition)

		public static void IfLessAssignmentLessFizzBuzz(int current, int max, string thusFar)
		{
			MakeSelection (new Action[] {
				() => Console.Write (thusFar + "\n"),//option 1, exit



				() => {//option 2, keep going

					MakeSelection (new Action[] {
						
						//goes evenly in 3
						() => MakeSelection (new Action[] {
							//goes evenly in both?
							() => 
							{
							IfLessAssignmentLessFizzBuzz (current + 1, max, thusFar + "\n" + current + " FizzBuzz");
							},
							() => {
							//only 3 goes evenly:
							IfLessAssignmentLessFizzBuzz (current + 1, max, thusFar + "\n" + current + " Fizz");
							}
						}, GoesEvenly (current, 5)),



						//doesn't go evenly in 3, goes evenly in 5?
						() => MakeSelection (new Action[] {
							() => IfLessAssignmentLessFizzBuzz (current + 1, max, thusFar + "\n" + current + " Buzz"),
							//doesn't go evenly in 3 or 5
							() => IfLessAssignmentLessFizzBuzz (current + 1, max, thusFar + "\n" + current)
						}, GoesEvenly (current, 5))
					
					
					}, GoesEvenly (current, 3)
					);
				}
			},
				GoesEvenly (current, max)
			);
		}

		public static int GoesEvenly(int current, int denominator){//returns 1 or 0 
			return Math.Min (current % denominator, 1);
		}

		public static void MakeSelection(System.Action[] choices, int choice)
		{
			//0 for first choice, 1 for second
			choices [choice]();
		}
	}
}
