package src.building;

public class Room extends Premise {
    private int countBeds;


    public Room(double area, Building building, int countBeds) {
        super (area, building);
        this.countBeds = countBeds;

    }

    public int getCountBeds() {
        return countBeds;
    }

    @Override
    public void capacity() {
        System.out.println("Room capacity" + this.countBeds);
        super.capacity();
    }

    public void getAreaAndCapacity(Building area, int countBeds) {
        System.out.println("Area: " + area);
        System.out.println("Capacity " + countBeds);
    }
}
