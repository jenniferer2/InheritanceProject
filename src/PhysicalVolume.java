import java.util.UUID;
public class PhysicalVolume{
private String name;
    private HardDrive h;
    private UUID u;

    public PhysicalVolume (String n, HardDrive h) {

        this.h = h;
        u = UUID.randomUUID();
        name= n ;
    }
    public UUID getU ()
    {
        return u;
    }
    public String getName() {
        return name;
    }
    public HardDrive getH()
    {
        return h;
    }

    public int getSizeofHD () {
    return h.getSize();
    }
}
