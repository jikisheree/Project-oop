package CARIN.Model;

import java.util.*;

public interface Body {
    void addAntibody(int[] location);
    void addAntiTurnVirus(String geneticCode, int[] loc);
    void addVirus();
    void addVirusTurnAntiCredit(int[] loc);
    void run();
    int[][] getCellLoc();
    List<Host> getOrganism();
    void move(int[] location, int[] newLocation);
    int getVirusNum();
    int getAntibodyNum();
    int[] getmn();
    void addvirus(int[] location);
    Host findOrganByLocation(int[] location);
    boolean findhost(int[] location);
}