import java.util.ArrayList;
import java.util.UUID;
public class LVM {
    private UUID u;
    private String name;

    public LVM(String n, UUID u) {
        name = n;
        this.u = u;

    }

    public UUID getU () {
            return u;
}
public String getName() {
        return name;
}
    }

