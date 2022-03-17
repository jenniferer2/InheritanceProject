import java.util.ArrayList;
import java.util.UUID;

public class VolumeGroups extends LVM {
private String name;
private UUID u;


    public VolumeGroups ( ArrayList<String> PV, ArrayList<String> LV, String n) {
        super(PV,LV);
    name = n;
    u = UUID.randomUUID();

}

//public int calculateSize () {
    //    return PV.size();
//}
/*public int totalSpace () {
        int total = 0;
        for (int i = 0; i < PV.size(); i ++) {
        //    total = total +(PV.get(i).getH()).getSize();
        }
}
public int leftOver () {

}

 */
}
