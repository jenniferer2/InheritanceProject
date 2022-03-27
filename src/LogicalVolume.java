import java.util.UUID;

public class LogicalVolume extends LVM {
    private int size;
    private VolumeGroups v;
    public LogicalVolume(String n, String u, int s, VolumeGroups v) {
        super(n,u);
        size =s;
        this.v = v;
    }
    public int getSize()
    {
        return size;
    }
    public VolumeGroups getV () {
        return v;
    }


}
