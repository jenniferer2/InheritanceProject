import java.util.ArrayList;
import java.util.UUID;

public class VolumeGroups extends LVM {
private ArrayList<PhysicalVolume> PVs;
private ArrayList<LogicalVolume> LVs;


    public VolumeGroups (String n, String u, ArrayList<PhysicalVolume> PVs, ArrayList<LogicalVolume> LVs) {
        super(n,u);
        this.PVs = PVs;
        this.LVs = LVs;
}
public ArrayList<PhysicalVolume> getPVs () {
        return PVs;
}
    public ArrayList<LogicalVolume> getLVs () {
        return LVs;
    }

    public int VGsize ()
    {
        int total = 0;
        for (PhysicalVolume p : PVs) {
            String x = p.getSize().substring(0 , p.getSize().indexOf("G"));
            int y = Integer.parseInt(x);
            total = total +  y;
        }
        return total;
    }

    public int freeSpace ()
    {
        int total = VGsize();
        for (LogicalVolume s: LVs) {
            total = total - s.getSize();
        }
        return total;

    }
}
