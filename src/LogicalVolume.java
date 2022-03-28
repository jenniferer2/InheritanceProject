import java.util.UUID;

public class LogicalVolume extends LVM {
    private String size;
    private VolumeGroups v;
    public LogicalVolume(String n, String u, String s, VolumeGroups v) {
        super(n,u);
        size = s;
        this.v = v;
    }
    public String getSize()
    {
        return size;
    }
    public int getSizeint() {
        return    Integer.parseInt(size.substring(0,size.indexOf("G")));
    }
    public VolumeGroups getV () {
        return v;
    }


}
