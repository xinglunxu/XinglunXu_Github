public class Pair<F, S>
{
	private F first;
	private S second;
	public F getFirst(){return first;}
	public S getSecond(){return second;}
	public void setFirst(F first)
	{
		this.first = first;
	}
	
	public void setSecond(S second)
	{
		this.second = second;
	}
	
	
	public static void main(String args[])
	{
		Pair<String, Integer> pair = new Pair<>();
		pair.setFirst("pair first string");
		pair.setSecond(2);
		Pair<Integer, String> pair1 = new Pair<Integer, String>();
		System.out.print("pair: first "+pair.getFirst()+" second "+pair.getSecond());
	}
}