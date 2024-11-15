public class Main {

    public static void main(String[] args) {
        try {
          Room myRoom = new Room(0, 21, 2);
          myRoom.setComfortType("economy");
          myRoom.setPrice(2000);
          System.out.println(myRoom);
        } catch (Exception e) {
          System.err.println(e);
        }
    }
}

class Room {
    private int id;
    private int codeNumber;
    private int numberPeople;
    private String comfortType;
    private double price;

    Room(int id, int codeNumber, int numberPeople) {
        this.id = id;
        this.codeNumber = codeNumber;
        this.numberPeople = numberPeople;
    }

    @Override
    public java.lang.String toString() {
        return String.format(
                "id: %d codeNumber: %d numberPeople: %d comfortType: %s price: $%.2f",
                this.id, this.codeNumber, this.numberPeople, this.comfortType, this.price);
    }

    public int getId() {
      return this.id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public int getCodeNumber() {
      return this.codeNumber;
    }

    public void setCodeNumber(int codeNumber) {
      this.codeNumber = codeNumber;
    }

    public int getNumberPeople() {
      return this.codeNumber;
    }

    public void setNumberPeople(int numberPeople) throws Exception {
      if (numberPeople <= 0) {
        throw new Exception("Number people must be greather then 0");
      }
      this.numberPeople = numberPeople;
    }

    public void setComfortType(String type) {
      this.comfortType = type;
    }

    public String getComfortType() {
      return this.comfortType;
    }

    public void setPrice(double price) throws Exception {
      if (price < 0) {
        throw new Exception("Price can't negative value");
      }
      this.price = price;
    }

    public double getPrice() {
      return this.price;
    }
}
