package CARIN.Model;

import java.util.Map;

public interface Body {
    void addAntibody(int location);
    void addVirus();
    void run();
    int[][] getCellLoc();
    Map<Integer,Host> getOrganism();
    void moveAntibody(int location, int newLocation);
    int getVirusNum();
    int getAntibodyNum();
}
