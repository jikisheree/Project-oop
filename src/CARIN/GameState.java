package CARIN;

import CARIN.Model.BodyImp;

import java.util.LinkedList;

public class GameState {
//    private static String state;
//    private static int time;
    private static BodyImp body;
    public static void main(String[] args){

        String gene = "virusLoc = virus " +
                "if (virusLoc / 10 - 2^2) " +
                "then " +
                "  if (virusLoc % 10 - 7) then move upleft " +
                "  else if (virusLoc % 10 - 6) then move left " +
                "  else if (virusLoc % 10 - 5) then move downleft " +
                "  else if (virusLoc % 10 - 4) then move down " +
                "  else if (virusLoc % 10 - 3) then move downright " +
                "  else if (virusLoc % 10 - 2) then move right " +
                "  else if (virusLoc % 10 - 1) then move upright " +
                "  else move up " +
                " else if (virusLoc) " +
                "then  " +
                "  if (virusLoc % 10 - 7) then shoot upleft " +
                "  else if (virusLoc % 10 - 6) then shoot left " +
                "  else if (virusLoc % 10 - 5) then shoot downleft " +
                "  else if (virusLoc % 10 - 4) then shoot down " +
                "  else if (virusLoc % 10 - 3) then shoot downright " +
                "  else if (virusLoc % 10 - 2) then shoot right " +
                "  else if (virusLoc % 10 - 1) then shoot upright " +
                "  else shoot up " +
                " else " +
                "{ " +
                "  dir = 10 % 8 " +
                "  if (dir - 6) then move upleft " +
                "  else if (dir - 5) then move left " +
                "  else if (dir - 4) then move downleft " +
                "  else if (dir - 3) then move down " +
                "  else if (dir - 2) then move downright " +
                "  else if (dir - 1) then move right " +
                "  else if (dir) then move upright " +
                "  else move up " +
                "} ";
        String gene2 = "antiLoc = antibody " +
                "if (antiLoc / 10 - 2^2) " +
                "then " +
                "  if (antiLoc % 10 - 7) then move upleft " +
                "  else if (antiLoc % 10 - 6) then move left " +
                "  else if (antiLoc % 10 - 5) then move downleft " +
                "  else if (antiLoc % 10 - 4) then move down " +
                "  else if (antiLoc % 10 - 3) then move downright " +
                "  else if (antiLoc % 10 - 2) then move right " +
                "  else if (antiLoc % 10 - 1) then move upright " +
                "  else move up " +
                " else if (antiLoc) " +
                "then  " +
                "  if (antiLoc % 10 - 7) then shoot upleft " +
                "  else if (antiLoc % 10 - 6) then shoot left " +
                "  else if (antiLoc % 10 - 5) then shoot downleft " +
                "  else if (antiLoc % 10 - 4) then shoot down " +
                "  else if (antiLoc % 10 - 3) then shoot downright " +
                "  else if (antiLoc % 10 - 2) then shoot right " +
                "  else if (antiLoc % 10 - 1) then shoot upright " +
                "  else shoot up " +
                " else " +
                "{ " +
                "  dir = 10 % 8 " +
                "  if (dir - 6) then move upleft " +
                "  else if (dir - 5) then move left " +
                "  else if (dir - 4) then move downleft " +
                "  else if (dir - 3) then move down " +
                "  else if (dir - 2) then move downright " +
                "  else if (dir - 1) then move right " +
                "  else if (dir) then move upright " +
                "  else move up " +
                "} ";
        LinkedList<String> geneticCodeAnti = new LinkedList<>();
        LinkedList<String> geneticCodeVirus = new LinkedList<>();
        geneticCodeVirus.add(gene);
        geneticCodeAnti.add(gene2);
        body = new BodyImp(geneticCodeAnti,geneticCodeVirus,5, 5, 20,2, 1, 0.8, 20,
                5, 2, 20, 3, 1);
        body.addVirus();
        body.addAntibody(new int[]{2, 3});
        body.addVirus();
        body.addAntibody(new int[]{1, 2});
        body.run();
    }
}

