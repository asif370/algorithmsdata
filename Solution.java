import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Solution {

    /** 
     * Written by Ryan D'souza
     * My solution to AirBnB's Hackerrank Property Search Coding Challenge */

    //Date format
    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] ryan) throws Exception {

        //To hold all the properties
        final HashMap<Integer, Properties> allProperties = new HashMap<Integer, Properties>();

        //To hold all the search queries
        final LinkedList<Searches> searches = new LinkedList<Searches>();

        //For reading input
        final Scanner scanner = new Scanner(System.in);

        String temp = "";

        //The first line is 'Properties'
        scanner.nextLine();

        //Read all of the properties and add them to the Map
        while(!(temp = scanner.nextLine()).equals("Dates")) {
            final Properties property = parseProperties(temp);
            allProperties.put(property.property_id, property);
        }

        //Then read all the Dates and add them to the Properties they belong to that are stored in the Map
        while(!(temp = scanner.nextLine()).equals("Searches")) {
            final Dates date = parseDates(temp);
            allProperties.get(date.property_id).dates.add(date);
        }

        //Read all of the Search queries and add them to the LinkedList
        while(scanner.hasNext()) {
            searches.add(parseSearches(scanner.nextLine()));
        }

        //Go through each search query
        for(Searches query : searches) {

            //Each query can return at most 10 items which should be sorted by price and geography (price first and foremost)
            //PriorityQueue is the most efficient data structure for this sort of operation
            final PriorityQueue<Properties> validProperties = new PriorityQueue<Properties>(10, new Properties.ComparePropertyPrices(query));

            //Now go through each of our properties
            for(Integer key : allProperties.keySet()) {
                Properties property = allProperties.get(key);

                //If the property is available
                if(property.propertyIsAvailable(query)) {
                    
                    //Add it to our Queue where it will be automatically sorted and/or deleted if it is the 11th highest price
                    validProperties.add(property);
                }
            }

            int ranking = 1;

            //Go through valid properties and print results
            for(Properties property : validProperties) {
                System.out.println(query.search_id + "," + ranking + "," + property.property_id + "," + property.totalCostOfStay(query));
                ranking++;
            }
        }

    }

    /** Returns 'Properties' object by parsing the input */
    public static Properties parseProperties(final String input) {
        final String[] files = input.split(",");
        return new Properties(Integer.parseInt(files[0]), Float.parseFloat(files[1]), 
                Float.parseFloat(files[2]), Integer.parseInt(files[3]));
    }

    /** Returns 'Dates' object by parsing the input */
    public static Dates parseDates(final String input) throws Exception {
        final String[] data = input.split(",");

        if(data.length == 3) {
            return new Dates(Integer.parseInt(data[0]), format.parse(data[1]), Integer.parseInt(data[2]), 0);
        }

        return new Dates(Integer.parseInt(data[0]), format.parse(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
    }

    /** Returns 'Searches' object by parsing the input */
    public static Searches parseSearches(final String input) throws Exception {
        final String[] data = input.split(",");

        return new Searches(Integer.parseInt(data[0]), Float.parseFloat(data[1]), Float.parseFloat(data[2]), format.parse(data[3]), format.parse(data[4]));
    }


    private static class Properties {
        /**
         * Represents a 'Properties' object per specification */

        public final int property_id;
        public final Location location;
        public final int nightly_price;
        public final LinkedList<Dates> dates;

        /** Default constructor */
        public Properties(final int property_id, final float latitude, final float longitude, final int nightly_price) {
            this.property_id = property_id;
            this.location = new Location(latitude, longitude);
            this.nightly_price = nightly_price;
            this.dates = new LinkedList<Dates>();
        }

        @Override
        public String toString() {

            //String representation of all the dates
            final StringBuilder dateRepresentation = new StringBuilder("");
            for(Dates date : dates) {
                dateRepresentation.append(date.toString() + "\t");
            }

            return "Properties: " + property_id + "\t" + location + "\t" + nightly_price + "\t" + dateRepresentation.toString();
        }

        /** Returns the total cost of a stay */
        public int totalCostOfStay(final Searches query) {

            int totalPrice = 0;

            //Go through all of the dates
            for(Dates date : dates) {

                //If the date is in our range
                if(date.date.after(query.checkin) && date.date.before(query.checkout)) {

                    //Get the cost
                    int cost = date.price;

                    //If no price was entered
                    if(cost == 0) {

                        //It was the default price
                        totalPrice += this.nightly_price;
                    }

                    //If a a special cost was entered
                    else {
                        totalPrice += cost;
                    }
                }
            }

            return totalPrice;
        }

        /** A Comparator for comparing property prices on a certain query */ 
        public static class ComparePropertyPrices implements Comparator<Properties> {
            private Searches query;

            /** Constructor, needs query */
            public ComparePropertyPrices(Searches query) {
                this.query = query;
            }

            @Override
            public int compare(Properties p1, Properties p2) {

                //Compare by price
                int price = p1.totalCostOfStay(query);
                int price2 = p2.totalCostOfStay(query);

                //If the prices are the same
                if(price == price2) {
                    
                    //Sort based on property id
                    return p1.property_id - p2.property_id;
                }

                //Else, sort by price
                return price - price2;
            }
        };

        /** Returns true if a property is available */
        public boolean propertyIsAvailable(final Searches query) {

            //Go through all of the dates
            for(Dates date : dates) {

                //Look at unavailable dates
                if(date.availability != 1) {

                    //And if they are within our range
                    if(date.date.after(query.checkin) && date.date.before(query.checkout)) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    private static class Dates implements Comparator<Dates> {
        /**
         * Represents a 'Dates' object per specification */
        public final int property_id;
        public final Date date;
        public final int availability;
        public final int price;

        /** Constructor */
        public Dates(final int property_id, final Date date, final int availability, final int price) {
            this.property_id = property_id;
            this.date = date;
            this.availability = availability;
            this.price = price;
        }

        @Override
        public int compare(final Dates first, final Dates second) {

            //If the dates are the same
            if(first.date.equals(second.date)) {

                //Compare by price
                return first.price - second.price;
            }

            //Otherwise, return which date came first
            return first.date.compareTo(second.date);
         }

        @Override
        public String toString() {
            return "Dates: " + property_id + "\t" + date + "\t" + availability + "\t" + price;
        }
    }

    private static class Searches {
        /**
         * Represents a 'Searches' object per specification */
        public final int search_id;
        public final Location location;
        public final Date checkin, checkout;
        
        /** Constructor */
        public Searches(final int search_id, final float latitude, final float longitude, final Date checkin, final Date checkout) {
            this.search_id = search_id;
            this.location = new Location(latitude, longitude);
            this.checkin = checkin;
            this.checkout = checkout;
        }

        @Override
        public String toString() {
             return "Searches: " + search_id + "\t" + location + "\t" + checkin + "\t" + checkout;
        }
    }


    private static class Location {
        /**
         * Represents a Location that has a latitude and longitude */
        public final float latitude;
        public final float longitude;

        public Location(final float latitude, final float longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return "Latitude: " + latitude + " Longitude: " + longitude;
        }
    }
}
