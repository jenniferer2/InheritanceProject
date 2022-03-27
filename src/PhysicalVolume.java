import java.util.UUID;
public class PhysicalVolume extends LVM{
    private HardDrive h;


    public PhysicalVolume (String n, String u, HardDrive h) {
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

    public void printAll () {
        System.out.println(super.getName() + ": [" + getSize() + "] [" + super.getU() + "]" );
    }




}
