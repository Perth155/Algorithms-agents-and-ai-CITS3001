import java.util.*;
import java.io.*;

public class Main
{

    private static String[] readLines(String filename) throws IOException
    {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();
        return lines.toArray(new String[lines.size()]);
    }

    private static void printStrArr(String inStrArr[])
    {
    	System.out.print("[");

    	for(int i = 0; i < inStrArr.length; i++)
    	{
    		System.out.print(inStrArr[i] + " ");
    	}

    	System.out.print("]\n");

    }

	public static void main(String args[])
	{
		String Dict[] = null;
		try {
			Dict = readLines("DICT.txt");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		Scanner scin = new Scanner(System.in);
		int numCases = scin.nextInt();

		for(int i = 0; i < numCases; i++)
		{
			String start = scin.next();
			String target = scin.next();
			WordChess wc = new WordChessImp();
			String[] ans = wc.findPath(Dict, start, target);
			printStrArr(ans);
		}

		scin.close();
	}
}
