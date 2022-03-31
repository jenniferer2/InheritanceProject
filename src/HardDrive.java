public class HardDrive {
    String size;
    String name;

    public HardDrive(String n , String s) {
        name = n;
        size = s;
    }
    public String getSize()
    {
        return size;
    }
    public String getName() {
        return name;
    }

    public void printAll () {
        System.out.println(name + " [" + size + "]");
    }
}
