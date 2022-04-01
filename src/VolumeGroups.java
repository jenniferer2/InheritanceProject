import java.util.ArrayList;
import java.util.UUID;

public class VolumeGroups extends LVM {
private PhysicalVolume PVn;

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


    public String printpVs() {
        String s = "";
        s = s + "[";
       for (int i = 0; i < getpVs().size(); i++  ) {
            s = s + (getpVs().get(i).getName());
            if (i != getpVs().size() -1) {
                s = s + (",");
            }
        }
        s = s + "] ";
       s = s + "[" + super.getU() + "]";
       return s;
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

    public String toString () {
        return super.getName() + ":" + " total: [" + VGsize() + "G] available: [" + freeSpace()+"G] " + printpVs() + "\n";
    }
}
