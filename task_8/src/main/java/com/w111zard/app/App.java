package com.w111zard.app;

/**
 * Hello world!
 */
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class App {

    public static void main(String[] args) {
        Hotel myHotel = new Hotel("Volga");

        ComfortRoom room2 = new ComfortRoom(2, 2, 1, 2000, 2);
        StandardRoom room1 = new StandardRoom(1, 1, 4, 1000, false);
        LuxuryRoom room3 = new LuxuryRoom(3, 3, 2, 5000, 10, 20);

        myHotel.addRoom(room2);
        myHotel.addRoom(room1);
        myHotel.addRoom(room3);

        myHotel.saveRoomsToJSON("rooms.json");
        myHotel.loadRoomsFromJSON("rooms.json");

        myHotel.showRooms();
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

    public void saveRoomsToJSON(String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(this.rooms, writer);
            System.out.println("Rooms have benn successfully saved to the " + fileName);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void loadRoomsFromJSON(String fileName) {
        Path path = Paths.get(fileName);

        try {
            String jsonContent = new String(Files.readAllBytes(path));

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Room.class, new RoomDeserializer())  // Register the custom deserializer
                    .create();

            Type personListType = new TypeToken<List<Room>>() {}.getType();
            List<Room> rooms = gson.fromJson(jsonContent, personListType);

            for (Room room : rooms) {
                this.addRoom(room);
            }

            System.out.println("Rooms have been loaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class RoomDeserializer implements JsonDeserializer<Room> {
    @Override
    public Room deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        if (type.equals("standard")) {
            return context.deserialize(json, StandardRoom.class);
        } else if (type.equals("comfort")) {
            return context.deserialize(json, ComfortRoom.class);
        } else if (type.equals("luxury")) {
            return context.deserialize(json, LuxuryRoom.class);
        } else {
            throw new JsonParseException("Unknown type: " + type);
        }
    }
}

class Room {
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
    private String type;

    public StandardRoom (int id, int number, int capacity, double price, boolean includedTV) {
        super(id, number, capacity, price);
        this.includedTV = includedTV;
        this.type = "standard";
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
                "[Standard Room] id: %d number: %d capacity: %d price:%.2f includedTV: %b",
                this.getId(), this.getNumber(), this.getCapacity(), this.getPrice(), this.isIncludedTV()
        );
    }
}

class ComfortRoom extends Room {
    private int bathroomsCount;
    private String type;

    public ComfortRoom (int id, int number, int capacity, double price, int bathroomsCount) {
        super(id, number, capacity, price);
        this.bathroomsCount = bathroomsCount;
        this.type = "comfort";
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
    private String type;

    public LuxuryRoom (
            int id, int number, int capacity, double price,
            int minimalDaysRent, int maximumDaysRent
    ) {
        super(id, number, capacity, price);
        this.minimalDaysRent = maximumDaysRent;
        this.maximumDaysRent = maximumDaysRent;
        this.type = "luxury";
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