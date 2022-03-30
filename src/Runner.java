import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        String user = "";
        ArrayList<HardDrive> hDs = new ArrayList<HardDrive>();
        ArrayList<PhysicalVolume> pVs = new ArrayList<PhysicalVolume>();
        ArrayList<VolumeGroups> vGs = new ArrayList<VolumeGroups>();
        ArrayList<LogicalVolume> lVs = new ArrayList<LogicalVolume> ();
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
                    System.out.println("Error : This hard drive is already installed.");
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
                    System.out.println("Error : This Physical Volume could not be created because this hard drive does not exist.");
                } else {

                    UUIDGenerator u = new UUIDGenerator();
                    PhysicalVolume pv = new PhysicalVolume(name, u.getUUID(), hd);
                    boolean c = false;
                    for (PhysicalVolume p : pVs) {
                        if (p.getH().getName().equals(pv.getH().getName())) {
                            System.out.println("Error : This Physical Volume could not be created becayse this hard drive is associated with an already created PV.");
                            c = true;
                            break;
                        }
                        if (p.getName().equals(pv.getName())) {
                            System.out.println("Error : This Physical Volume could not be created because this PV already exists.");
                            c = true;
                            break;
                        }

                    }
                    if (!c) {
                        pVs.add(pv);
                        System.out.println(name + " created");
                    }

                }


            }
            if (user.contains("pvlist")) {
                for (PhysicalVolume pv : pVs) {
                    VolumeGroups choose = null;
                    for (VolumeGroups v : vGs) {
                        for (PhysicalVolume vv : v.getpVs()) {
                            if (pv.getName().equals(vv.getName())) {
                                choose = v;
                                break;
                            }
                        }
                    }
                    if (choose != null) {
                        pv.printAll(choose);
                    }
                    else {
                        pv.printAll();
                    }

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
                if (checker) {
                    System.out.println("This VG could not be created because it already exists");
                }
                else {
                    //check if pv name exists
                    PhysicalVolume theOne = null;

                    for (PhysicalVolume pv : pVs) {
                        if (!(pvName.equals(pv.getName()))) {
                            checker = true;
                        } else {
                            theOne = pv;
                            checker = false;
                        }
                    }
                    if (theOne == null) {
                        System.out.println("Error : This volume group could not be created because this PV does not exist");

                    }
                    if (theOne != null) {
                        int count = 0;
                        UUIDGenerator u = new UUIDGenerator();
                        VolumeGroups v = new VolumeGroups(name, u.getUUID(), theOne);
                        vGs.add(v);
                        for (VolumeGroups vs : vGs) {
                            ArrayList<PhysicalVolume> pp = vs.getpVs();
                            for (PhysicalVolume p : pp) {
                                if (p.getName().equals(theOne.getName())) {
                                    count++;
                                }
                            }
                        }
                        if (count != 1) {
                            System.out.println("Error : This volume group could not be created because this PV is associated with another VG.");
                            vGs.remove(v);
                        } else {
                            checker = false;
                        }

                    }
                    if (!checker) {
                        System.out.println(name + " created");
                    }

                }
            }
            if (user.contains("vgextend")) {
                String extract = user.substring(user.indexOf(" ") + 1);
                String name = extract.substring(0, extract.indexOf(" "));
                String pvName = extract.substring(extract.indexOf(" ") + 1);
                boolean letsgo1 = false;
                boolean letsgo2 = false;

                VolumeGroups v = null;
                PhysicalVolume p = null;
                for (VolumeGroups vv : vGs) {
                    if(name.equals(vv.getName())) {
                        letsgo1 = true;
                        v = vv;
                    }
                }
                for (PhysicalVolume pp : pVs) {
                    if (pvName.equals(pp.getName())) {
                        letsgo2 = true;
                        p = pp;
                    }
                }


                if (!letsgo1) {
                    System.out.println("Error : Volume Group not found");
                }
                if (!letsgo2) {
                    System.out.println("Error : Physical Volume not found");
                }
                if (v != null && p != null ) {
                    int count = 0;
                    for (VolumeGroups vs : vGs) {
                        ArrayList<PhysicalVolume> pp = vs.getpVs();
                        for (PhysicalVolume pe : pp) {
                            if (pe.getName().equals(p.getName())) {
                                count++;
                            }
                        }
                    }
                    if (count == 0) {
                        ArrayList<PhysicalVolume> per = v.getpVs();
                        per.add(p);
                        System.out.println(name + " has been extended");
                    }
                    else {
                        System.out.println("Error : this PV is part of another VG");
                    }
                }
            }

            if (user.contains("vglist")) {
                for (VolumeGroups v : vGs) {
                    System.out.print(v.getName() + ":" + " total: [" + v.VGsize() + "G] available: [" + v.freeSpace()+"G] " );
                    v.printpVs();
                    System.out.println("");
                }

            }
            if (user.contains("lvcreate")) {
                String extract = user.substring(user.indexOf(" ") + 1);
                String name = extract.substring(0, extract.indexOf(" "));
                String size = extract.substring(extract.indexOf(" ") + 1, extract.indexOf("G") +1);
                String vgname = extract.substring(extract.indexOf("G") + 2);

                boolean check = false;
                LogicalVolume l = null;
                // check if lv name alr exist
                for (LogicalVolume lv : lVs) {
                    if (name.equals(lv.getName())) {
                        check = true;
                    }
                }
                // check if vg name exits
                VolumeGroups vv = null;
                int count = 0;
                for (VolumeGroups v : vGs) {
                    if (v.getName().equals(vgname)) {
                        vv = v;
                    }
                }
                if (vv == null) {
                    System.out.println("Error : VG not found");
                }
                if (check) {
                    System.out.println("Error : LV already exists");

                }

                if (vv != null && !check) {
                    UUIDGenerator u = new UUIDGenerator();
                    LogicalVolume ll = new LogicalVolume (name,u.getUUID(), size, vv );
                    int s = Integer.parseInt(size.substring(0, size.indexOf("G")));
                    if (vv.freeSpace() < s) {
                        System.out.println("Error: the volume group does not have enough space.");
                    }
                    else {
                        vv.addtoLVs(ll);
                        lVs.add(ll);
                        System.out.println("LV created");

                    }
                }

            }
            if (user.contains("lvlist")) {
                for (LogicalVolume l : lVs) {
                    System.out.println(l.getName() + ": [" + l.getSize() + "] [" + l.getV() + "] [" + l.getU() + "]" );
                }
            }


        }
        System.out.println("Saving data. Good-bye!");

    }



}


