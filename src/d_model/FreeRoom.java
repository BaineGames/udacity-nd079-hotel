package d_model;

public class FreeRoom extends Room{
    public FreeRoom() {
        this.price = (double) 0;
    }

    @Override
    public String toString() {
        return "FreeRoom{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", roomType=" + roomType +
                '}';
    }
}
