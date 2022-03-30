import java.util.ArrayList;
import java.util.UUID;

public class VolumeGroups extends LVM {
private PhysicalVolume PVn;
private LogicalVolume LVn;
private ArrayList<PhysicalVolume> pVs = new ArrayList<PhysicalVolume>  () ;
private ArrayList<LogicalVolume> lVs = new ArrayList<LogicalVolume> ();


    public VolumeGroups (String n, String u, PhysicalVolume PVs) {
        super(n,u);
        pVs.add(PVs);

    }

public ArrayList<PhysicalVolume> getpVs () {
        return pVs;
}
    public ArrayList<LogicalVolume> getLVs () {
        return lVs;
    }
    public PhysicalVolume getPVn () {
        return PVn;
    }
    public LogicalVolume getLVn () {
        return LVn;
    }
    public void printpVs() {
        System.out.print("[");
       for (int i = 0; i < getpVs().size(); i++  ) {
            System.out.print(getpVs().get(i).getName());
            if (i != getpVs().size() -1) {
                System.out.print(",");
            }
        }
        System.out.print("] ");
       System.out.print("[" + super.getU() + "]");
    }


    public int VGsize ()
    {
        int total = 0;
        for (PhysicalVolume p : pVs) {
            String x = p.getSize().substring(0 , p.getSize().indexOf("G"));
            int y = Integer.parseInt(x);
            total = total +  y;
        }
        return total;
    }

    public void addtoLVs (LogicalVolume lv) {
        lVs.add(lv);
    }

    public int freeSpace ()
    {
        int total = VGsize();
        for (LogicalVolume s: lVs) {
            total = total - s.getSizeint();
        }
        return total;

    }
}
