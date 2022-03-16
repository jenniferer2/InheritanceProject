import java.util.UUID;
public class PhysicalVolume {
    private UUID u;
    private String name;
    private HardDrive h;

    public PhysicalVolume (HardDrive h) {
        name = h.getName();
        this.h = h;
        u = UUID.randomUUID();
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


    }
