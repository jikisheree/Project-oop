package CARIN.Model;

import java.util.*;

public interface Body {
    void addAntibody(int[] location);
    void addVirus();
    void addVirus(Virus virus,int[] loc);
    void run();
    int[][] getCellLoc();
    List<Host> getOrganism();
    void moveAntibody(int[] location, int[] newLocation);
    int getVirusNum();
    int getAntibodyNum();
    void setAntiCredit();
}
