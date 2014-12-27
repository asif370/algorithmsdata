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


    public static void main(String[] ryan) {

        final int original = 8;

        //System.out.println(bitIncrease(original, 3));
        //System.out.println(bitDecrease(original, 3));
        //System.out.println(ampersand(72, 184));
        //System.out.println(or(72, 184));
        
        System.out.println("Original:\t" + toBinary(72));
        System.out.println("Original:\t" + toBinary(184));
        System.out.println("Ampersan:\t" + toBinary(ampersand(72, 184)));
        System.out.println("Or:\t\t" + toBinary(or(72, 184)));
    }
}
