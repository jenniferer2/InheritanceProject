import java.util.ArrayList;
import java.util.UUID;
public class LVM  {
    private ArrayList<String> PV;
    private ArrayList<String> LV;
    private UUID u;
    public LVM (ArrayList<String> PV, ArrayList<String> LV ) {
     this.PV =PV;
     this.LV = LV;
    }
    public LVM (String p, String L) {
        PV.add(p);
        LV.add(L);
    }
    public ArrayList<String>  getPV() {
        return PV;
    }
    public ArrayList<String> getLV () {
            return LV;}

}
