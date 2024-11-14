public class Main {

    public static void main(String[] args) {

        Room myRoom = new Room(0, 21, 2, "economy", 2000);
        System.out.println(myRoom);
    }
}

class Room {
    int id;
    int codeNumber;
    int numberPeople;
    String comfortType;
    double price;

    Room(int id, int codeNumber, int numberPeople, String comfortType, double price) {
        this.id = id;
        this.codeNumber = codeNumber;
        this.numberPeople = numberPeople;
        this.comfortType = comfortType;
        this.price = price;
    }

    @Override
    public java.lang.String toString() {
        return String.format(
                "id: %d codeNumber: %d numberPeople: %d comfortType: %s price: $%.2f",
                this.id, this.codeNumber, this.numberPeople, this.comfortType, this.price);
    }
}