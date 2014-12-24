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

    public static void main(String[] ryan) {

        final int original = 8;

        System.out.println(bitIncrease(original, 3));
        System.out.println(bitDecrease(original, 3));
    }
}
