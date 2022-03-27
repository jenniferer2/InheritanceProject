import java.util.ArrayList;
import java.util.UUID;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        String user = "";
        ArrayList<HardDrive> hDs = new ArrayList<HardDrive>();
        ArrayList<PhysicalVolume> pVs = new ArrayList<PhysicalVolume>();
        ArrayList<VolumeGroups> vGs = new ArrayList<VolumeGroups>();
        System.out.println("Welcome to the LVM system! Enter your commands: " );
        while (!(user.equals("exit"))) {
            Scanner in = new Scanner(System.in);
            System.out.print("\n" + "cmd# ");
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
                    System.out.println("Drive " + name + " installed");
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
                if (hd == null) {
                    System.out.println("Error : This Physical Volume could not be created");
                } else {

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
                        System.out.println(name + " created");

                    }
                }


            }
            if (user.contains("pvlist")) {
                for (PhysicalVolume pv : pVs) {
                    pv.printAll();
                    // ^ needs fixing to adjust to VG

                }
            }
            if (user.contains("vgcreate")) {
                String extract = user.substring(user.indexOf(" ") + 1);
                String name = extract.substring(0, extract.indexOf(" "));
                String pvName = extract.substring(extract.indexOf(" ") + 1);
                boolean checker = false;
                //check if vg name already exists
                for (VolumeGroups vgs : vGs) {
                    if (name.equals(vgs.getName())) {
                        checker = true;
                        break;
                    }
                }
                //check if pv name exists
                PhysicalVolume theOne = null;

                for (PhysicalVolume pv : pVs) {
                    if (!(pvName.equals(pv.getName()))) {
                        checker = true;
                    }
                    else {
                        theOne = pv;
                        checker = false;
                    }
                }
                if (theOne != null) {
                    int count = 0;
                    UUIDGenerator u = new UUIDGenerator ();
                    VolumeGroups v = new VolumeGroups (name, u.getUUID(), theOne);
                    vGs.add(v);
                    for (VolumeGroups vs : vGs) {
                       ArrayList<PhysicalVolume> pp =  vs.getPVs();
                       for (PhysicalVolume p : pp) {
                           if (p.getName().equals(theOne.getName())) {
                               count++;
                           }
                       }
                    }
                    if (count != 1) {
                        System.out.println("Error : This volume group could not be created");
                    }

                }
                if (checker) {
                    System.out.println("Error : This volume group could not be created");
                }

            }


        }
    }
}

