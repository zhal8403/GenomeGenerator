import java.util.Scanner;
public class GenomeGenerator
{
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String name;
        while(true)
        {
            System.out.println("Enter a Name");
            name = reader.nextLine();
            System.out.println(name + "'s Genome: " + toGenome(extrapolateString(name), false));
            
        }
    }
    private static String extrapolateString(String str)
    {
        String [] chars = new String[] {" ", "a","b","c","d","e","f","g","h","i","j","k","l","m","o","p","q","r","s","t","u","v","w","x","y","z",".",",","'"};

        str = str.toLowerCase();
        String newStr = str;
        if (str.length() > 20)
            return str.substring(0,21);
        int i = 0;
        int extendAmount = 1;
        int total = 0;
        while(newStr.length() < 5000)
        {
            if (i <= newStr.length())
            {
                extendAmount = (int)(Math.pow(total, 1.1)) % (20);
                    
                if (indexArray(chars, newStr.substring(i, i+1)) + extendAmount >= chars.length)
                {
                    newStr += chars[indexArray(chars, newStr.substring(i, i+1)) + extendAmount - chars.length];
                    i++;
                    total++;
                }
                else
                {
                    newStr += chars[indexArray(chars, newStr.substring(i, i+1)) + extendAmount];
                    i++;
                    total++;
                }
            }
            else
                i = 0;
        }
        return newStr;
    }
    
    private static int indexArray(String [] array, String goal)
    {
        for (int i = 0; i < array.length; i++)
        {
            if (array[i].equals(goal))
                return i;
        }
        return -1;
    }
    private static String toGenome(String seed, boolean rna)
    {
        String [] chars = new String[] {" ", "a","b","c","d","e","f","g","h","i","j","k","l","m","o","p","q","r","s","t","u","v","w","x","y","z",".",",","'"};

        String genome = "";
        for (int i = 0; i < seed.length(); i ++)
        {
            int modulus = indexArray(chars, seed.substring(i, i+1)) % 4;
            if (modulus == 0)
                genome += "CG";
            if (modulus == 1)
                genome += "GC";
            if (modulus == 2 && !rna)
                genome += "AT";
            if (modulus == 3 && !rna)
                genome += "TA";
            if (modulus == 2 && rna)
                genome += "AU";
            if (modulus == 3 && rna)
                genome += "UA";
        }
        return genome;
    }
    
}