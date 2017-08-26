public class main
{
	public static void main(String args[])
	{
		Knapsack k = new KnapsackImp();
		int val[] = {60,100,120};
		int weight[] = {10,20,30};
		int cap = 50;
		int kd = k.discreteKnapsack(weight, val, cap);
		int kf = k.fractionalKnapsack(weight, val, cap);
		System.out.println(kf);
		System.out.println(kd);
		System.exit(0);
	}
}
