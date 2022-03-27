import java.util.ArrayList;
import java.util.UUID;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        String user = "";
        ArrayList<HardDrive> hDs = new ArrayList<HardDrive>();
        ArrayList<PhysicalVolume> pVs = new ArrayList<PhysicalVolume>();
        System.out.println("Welcome to the LVM system! Enter your commands: " + "\n");
        while (!(user.equals("exit"))) {
            Scanner in = new Scanner(System.in);
            System.out.print("cmd# ");
            user = in.nextLine();
            if (user.contains("install-drive")) {
                boolean check = false;
                String extract = user.substring(user.indexOf(" ") + 1);
                String name = extract.substring(0, extract.indexOf(" "));
                String size = extract.substring(extract.indexOf(" ") + 1);
                HardDrive h = new HardDrive(name, size);
                for (HardDrive x : hDs) {
                    if (x.getName().equals(name)) {
                        check = true;
                        break;
                    }
                }
                if (check) {
                    System.out.println("This hard drive is already installed.");
                } else {
                    hDs.add(h);
                }

            }
            if (user.contains("list-drives")) {
                for (HardDrive h : hDs) {
                    System.out.print(h.getName());
                    System.out.println(" [" + h.getSize() + "]");

                }
            }
            if (user.contains("pvcreate")) {
                String extract = user.substring(user.indexOf(" ") + 1);
                String name = extract.substring(0, extract.indexOf(" "));
                String driveName = extract.substring(extract.indexOf(" ") + 1);
                HardDrive hd = null;
                for (HardDrive hs : hDs) {
                    if (driveName.equals(hs.getName())) {
                          hd = hs;
                        break;
                    }
                }
                if (hd == null){
                    System.out.println("Error : This Physical Volume could not be created");
                }
                else  {

                    UUIDGenerator u = new UUIDGenerator();
                    PhysicalVolume pv = new PhysicalVolume(name, u.getUUID(), hd);
                    boolean c = false;
                    for (PhysicalVolume p : pVs) {
                        if (p.getH().getName().equals(pv.getH().getName())) {
                            c = true;
                        }
                        if (p.getName().equals(pv.getName())) {
                            c = true;
                        }

                    }
                    if (c) {
                        System.out.println("Error : This Physical Volume could not be created.");
                    } else {
                        pVs.add(pv);
                    }
                }


            }
                if (user.contains("pvlist")) {
             for (PhysicalVolume pv : pVs) {
                pv.printAll();

             }
               }


        }


    }
}

