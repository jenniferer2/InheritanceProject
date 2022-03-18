import java.util.UUID;
public class PhysicalVolume extends LVM{
    private HardDrive h;


    public PhysicalVolume (String n, UUID u, HardDrive h) {
        super(n ,u);
        this.h = h;
    }

    public HardDrive getH()
    {
        return h;
    }

    public String getSize () {
        return h.getSize();
    }

}
