import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Hotel myHotel = new Hotel("Volga");

        ComfortRoom room2 = new ComfortRoom(2, 2, 1, 2000, 2);
        StandardRoom room1 = new StandardRoom(1, 1, 4, 1000, false);
        LuxuryRoom room3 = new LuxuryRoom(3, 3, 2, 5000, 10, 20);

        myHotel.addRoom(room2);
        myHotel.addRoom(room1);
        myHotel.addRoom(room3);


        System.out.println("Unsorted: ");
        myHotel.showRooms();
        System.out.println();

        System.out.println("Sorted by price: ");
        myHotel.sortRoomsByPrice();
        myHotel.showRooms();
        System.out.println();

        System.out.println("Sorted by capacity: ");
        myHotel.sortRoomsByCapacity();
        myHotel.showRooms();
        System.out.println();
    }
}

class Hotel {
    private String name;
    private ArrayList<Room> rooms;

    Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<Room>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public void showRooms() {
        for (Room room : this.rooms) {
            System.out.println(room);
        }
    }

    public void sortRoomsByPrice() {
        Collections.sort(this.rooms, new Comparator<Room>() {
            @Override
            public int compare(Room r1, Room r2) {
                return Double.compare(r1.getPrice(), r2.getPrice());
            }
        });
    }

    public void sortRoomsByCapacity() {
        Collections.sort(this.rooms, new Comparator<Room>() {
            @Override
            public int compare(Room r1, Room r2) {
                return Integer.compare(r1.getCapacity(), r2.getCapacity());
            }
        });
    }
}

abstract class Room {
    private int id;
    private int number;
    private int capacity;
    private double price;

    Room (int id, int number, int capacity, double price) {
        this.id = id;
        this.number = number;
        this.capacity = capacity;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity <= 0) {
            System.err.println("Capacity must be greater than zero!");
            return;
        }

        this.capacity = capacity;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            System.err.println("Price must be non negative value");
            return;
        }

        this.price = price;
    }
}

class StandardRoom extends Room {
    private boolean includedTV;

    public StandardRoom (int id, int number, int capacity, double price, boolean includedTV) {
        super(id, number, capacity, price);
        this.includedTV = includedTV;
    }

    public void includeTV() {
        this.includedTV = true;
    }

    public void excludeTV() {
        this.includedTV = false;
    }

    public boolean isIncludedTV() {
        return this.includedTV;
    }

    @Override
    public String toString() {
        return String.format(
        "[Standard Room] id: %d number: %d capacity: %d price:%.1f includedTV: %b",
        this.getId(), this.getNumber(), this.getCapacity(), this.getPrice(), this.isIncludedTV()
        );
    }
}

class ComfortRoom extends Room {
    private int bathroomsCount;

    public ComfortRoom (int id, int number, int capacity, double price, int bathroomsCount) {
        super(id, number, capacity, price);
        this.bathroomsCount = bathroomsCount;
    }

    public int getBathroomsCount() {
        return this.bathroomsCount;
    }

    public void setBathroomsCount(int count) throws Exception {
        if (count <= 0) {
            throw new Exception("Bathrooms count must be grater than 0");
        }

        this.bathroomsCount = count;
    }

    @Override
    public String toString() {
        return String.format(
        "[Comfort Room] id: %d number: %d capacity: %d price:%.2f bathroomsCount: %d",
        this.getId(), this.getNumber(), this.getCapacity(), this.getPrice(), this.getBathroomsCount()
        );
    }
}

class LuxuryRoom extends Room {
    private int minimalDaysRent;
    private int maximumDaysRent;

    public LuxuryRoom (
        int id, int number, int capacity, double price,
        int minimalDaysRent, int maximumDaysRent
    ) {
        super(id, number, capacity, price);
        this.minimalDaysRent = maximumDaysRent;
        this.maximumDaysRent = maximumDaysRent;
    }

    public int getMinimalsDaysRent() {
        return this.minimalDaysRent;
    }

    public void setMinimalsDaysRent(int days) {
        this.minimalDaysRent = days;
    }

    public int getMaximumDaysRent() {
        return this.maximumDaysRent;
    }

    public void setMaximumDaysRent(int days) {
        this.maximumDaysRent = days;
    }

    @Override
    public String toString() {
        return String.format(
        "[Luxury room] id: %d number: %d capacity: %d price:%.2f minimumDaysRent: %d maximumDaysRent: %d",
        this.getId(), this.getNumber(), this.getCapacity(), this.getPrice(), this.getMaximumDaysRent(), this.getMinimalsDaysRent()
        );
    }
}