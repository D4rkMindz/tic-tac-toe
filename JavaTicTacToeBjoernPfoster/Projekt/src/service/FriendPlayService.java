package service;

import forms.VsFriendForm;

/**
 * Class FriendPlayService.
 * This class is for the logic behind the VsFriendGameGui responsible.
 */
public class FriendPlayService {
    /**
     * Check for the winner.
     * <p>
     * This function checks, if someone already won the game.
     *
     * @param positions String Array with all positions and their state
     * @return boolean true if someone won already
     */
    public boolean checkWinner(String[] positions) {
        if (this.horizontalRows(positions)|| this.verticalRows(positions)) {
            return true;
        }
        return this.crosses(positions);
    }

    /**
     * Check horizontal rows.
     * <p>
     * This function checks, if someone already won by placing all his three signs on a horizonal row
     *
     * @param positions String Array with all positions and their state
     * @return boolean true if someone managed it to win
     */
    private boolean horizontalRows(String[] positions) {
        VsFriendForm t = new VsFriendForm();
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
     * Check vertical rows.
     * <p>
     * This function checks, if someone already won by placing all his three signs on a vertical row
     *
     * @param positions String Array with all positions and their state
     * @return boolean true if someone managed it to win
     */
    private boolean verticalRows(String[] positions) {
        VsFriendForm t = new VsFriendForm();
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
     * Check crosses.
     * <p>
     * This function checks, if someone already won by placing all his three signs like a cross on the field
     *
     * @param positions String Array with all positions and their state
     * @return boolean true if someone managed it to win
     */
    private boolean crosses(String[] positions) {
        VsFriendForm t = new VsFriendForm();
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
