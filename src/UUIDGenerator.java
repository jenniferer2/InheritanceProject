import java.util.UUID;


public class UUIDGenerator {
    private String u ;

    public UUIDGenerator() {
         u = String.valueOf(UUID.randomUUID());

    }
    public String getUUID() {
        return u;
    }
    }

