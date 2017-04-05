package service;

import form.VsPcForm;

/**
 * Class PcPlayService
 * This class is for the logic behind the VsPcGameGui responsible.
 */
public class PcPlayService {
    /**
     * This function manages the algorithms, which are checking if someone already won. In case of a victory, there will
     * be returned true.
     *
     * @param positions String Array with all positions and their state
     * @return true boolen if someone already won
     */
    public boolean checkWinner(String[] positions) {
        if (this.horizontalRows(positions) || this.verticalRows(positions)) {
            return true;
        }
        return this.crosses(positions);

    }

    /**
     * Algorithm to check the horizontal rows, if someone already won.
     *
     * @param positions String Array with all positions and their state
     * @return true boolean if there is a victory
     */
    private boolean horizontalRows(String[] positions) {
        VsPcForm t = new VsPcForm();
        if (positions[0].equals("circle") && positions[1].equals("circle") && positions[2].equals("circle")) {
            t.setWinner('o');
            return true;
        }
        if (positions[0].equals("cross") && positions[1].equals("cross") && positions[2].equals("cross")) {
            t.setWinner('x');
            return true;
        }
        if (positions[3].equals("circle") && positions[4].equals("circle") && positions[5].equals("circle")) {
            t.setWinner('o');
            return true;
        }
        if (positions[3].equals("cross") && positions[4].equals("cross") && positions[5].equals("cross")) {
            t.setWinner('x');
            return true;
        }
        if (positions[6].equals("circle") && positions[7].equals("circle") && positions[8].equals("circle")) {
            t.setWinner('o');
            return true;
        }
        if (positions[6].equals("cross") && positions[7].equals("cross") && positions[8].equals("cross")) {
            t.setWinner('x');
            return true;
        }
        return false;
    }

    /**
     * Algorithm to check the vertical rows, if someone already won.
     *
     * @param positions String Array with all positions and their state
     * @return true boolean if there is a victory
     */
    private boolean verticalRows(String[] positions) {
        VsPcForm t = new VsPcForm();
        if (positions[0].equals("circle") && positions[3].equals("circle") && positions[6].equals("circle")) {
            t.setWinner('o');
            return true;
        }
        if (positions[0].equals("cross") && positions[3].equals("cross") && positions[6].equals("cross")) {
            t.setWinner('x');
            return true;
        }
        if (positions[1].equals("circle") && positions[4].equals("circle") && positions[7].equals("circle")) {
            t.setWinner('o');
            return true;
        }
        if (positions[1].equals("cross") && positions[4].equals("cross") && positions[7].equals("cross")) {
            t.setWinner('x');
            return true;
        }
        if (positions[2].equals("circle") && positions[5].equals("circle") && positions[8].equals("circle")) {
            t.setWinner('o');
            return true;
        }
        if (positions[2].equals("cross") && positions[5].equals("cross") && positions[8].equals("cross")) {
            t.setWinner('x');
            return true;
        }
        return false;
    }

    /**
     * Algorithm to check the crosses, if someone already won.
     *
     * @param positions String Array with all positions and their state
     * @return true boolean if there is a victory
     */
    private boolean crosses(String[] positions) {
        VsPcForm t = new VsPcForm();
        if (positions[0].equals("circle") && positions[4].equals("circle") && positions[8].equals("circle")) {
            t.setWinner('o');
            return true;
        }
        if (positions[0].equals("cross") && positions[4].equals("cross") && positions[8].equals("cross")) {
            t.setWinner('x');
            return true;
        }
        if (positions[2].equals("circle") && positions[4].equals("circle") && positions[6].equals("circle")) {
            t.setWinner('o');
            return true;
        }
        if (positions[2].equals("cross") && positions[4].equals("cross") && positions[6].equals("cross")) {
            t.setWinner('x');
            return true;
        }
        return false;
    }
}