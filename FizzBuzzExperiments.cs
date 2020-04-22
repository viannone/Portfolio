using System;
/* This program was a fun way to introduce someone new at programming to different programming paradigms.
* Here, there are two different ways to output the classic children's game "Fizz-Buzz".
* The first way uses only pure functions with no state
* The second way is not only stateless, but uses no assignment operators either
*/
namespace FunctionalFizzBuzz
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			//Version 1
			FunctionalFizzBuzz (1, 100, "Functional");
			//Version 2
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
		
		//the bottom one is a little trickier to follow, because it uses arrays and indexes as opposed to if-statements
		//note the heavy use of lambda expressions - this would not be necessary in a language like Javascript where functions are first-order variables
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
