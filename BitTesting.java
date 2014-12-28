public class BitTesting { 

    //All bits moved to left by value, 0s added as padding
    public static int bitIncrease(final int number, final int value) { 
        //number * 2^value
        return number << value;
    }

    //All bits moved to right by value, 0s added as padding
    public static int bitDecrease(final int number, final int value) { 
        //number / 2^value
        return number >> value;
    }

    //For each bit, returns their '&&' value
    public static int ampersand(final int number, final int number2) { 
        //Number =  01001000
        //Number2 = 10111000
        //Result =  00001000
        return number & number2;
    }

    //For each bit, return their '||' value
    public static int or(final int number, final int number2) { 
        //number =  01001000
        //number2 = 01001000
        //Result = 11111000

        return number | number2;
    }

    //Convert number to binary
    public static String toBinary(int number) {
        final Stack<Integer> bits = new Stack<Integer>();

        for(; number > 0 || bits.size() < 8; number /= 2) { 
            bits.push(number % 2);
        }

        final StringBuilder toString = new StringBuilder("");
        while(!bits.isEmpty()) { 
            toString.append(bits.pop());
        }

        return toString.toString();
    }

    //Convert binary to whole number
    public static int fromBinary(final String binary) { 
        int result = 0, counter = 0;

        for(Character c : binary.toCharArray()) { 
            result += Math.pow(2, counter) * Character.getNumericValue(c);
            counter++;
        }

        return result;
    }

    //Tilde flips every bit
    public static int tilde(final int original) { 
        //01010101 becomes 10101010
        return ~original;
    }

   //XOR, returns 1 if either input is 0, not both
   public static int XOR(final int num1, final int num2) { 
       //011110010 ^ 10101010 --> 11011000
       return num1 ^ num2;
   }

   //Check if number is a power of 2
   public static boolean isPowerOf2(final int number) { 
       //Logic: 1 subtracted from any power of 2, in binary, will have more than one '1' in it
       //And that 1 will be in a different location than where it originally was
       //So, taking the & of that should return 0 if it is a power of 2
       return ((number - 1) & number) == 0;
   }

    public static void main(String[] ryan) {

        final int original = 8;

        //Check some number conversions
        System.out.println(bitIncrease(original, 3));
        System.out.println(bitDecrease(original, 3));
        System.out.println(ampersand(72, 184));
        System.out.println(or(72, 184));
        System.out.println(fromBinary("10011001"));
        System.out.println(toBinary(fromBinary("10011001")));

        //More conversions with actual binary
        System.out.println("Original:\t" + toBinary(72));
        System.out.println("Original:\t" + toBinary(184));
        System.out.println("Ampersan:\t" + toBinary(ampersand(72, 184)));
        System.out.println("Or:\t\t" + toBinary(or(72, 184)));

        System.out.println();
        
        //Check powers of 2 (results should all be true)
        for(int i = 1; i < 8; i++) { 
            System.out.print(Math.pow(2, i) + ": " + isPowerOf2((int)Math.pow(2, i)) + "\t");
        }

        System.out.println();

        //This was essentially my notes on:
        // http://www.cprogramming.com/tutorial/bitwise_operators.html
    }
}
