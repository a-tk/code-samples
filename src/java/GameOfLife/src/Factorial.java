/**
 * Created by Hypnotropic on 5/9/2014.
 */
public class Factorial {

    /**
     *
     *
     * @param n
     * @return factorial of parameter n if n >= 0, -1 otherwise
     */
    public static long fact(long n)
    {
        if (n < 0)
            return -1L;
        if (n == 0)
            return 1;

        //5 * 4 * 3 * 2 * 1
        long result = n;

        while (n > 0) {
            n--;
            result *= n;

        }



        return result;
    }

    public static long recFact(int n){

        if (n == 0){
            return 1;
        }
        return n * recFact(n - 1);
    }
}
