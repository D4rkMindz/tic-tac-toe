package Services;

import java.util.Random;

/**
 * Class MediumPlayService
 */
public class MediumPlayService {
    /**
     * Class variables.
     */
    private boolean[] checkedRows = new boolean[8];

    /**
     * Set Default values into variables.
     */
    private void setDefaults() {
        if (!checkedRows[0]) {
            checkedRows[0] = false;
        }
        if (!checkedRows[1]) {
            checkedRows[1] = false;
        }
        if (!checkedRows[2]) {
            checkedRows[2] = false;
        }
        if (!checkedRows[3]) {
            checkedRows[3] = false;
        }
        if (!checkedRows[4]) {
            checkedRows[4] = false;
        }
        if (!checkedRows[5]) {
            checkedRows[5] = false;
        }
        if (!checkedRows[6]) {
            checkedRows[6] = false;
        }
        if (!checkedRows[7]) {
            checkedRows[7] = false;
        }

        checkedRows[1] = false;
        checkedRows[2] = false;
        checkedRows[3] = false;
        checkedRows[4] = false;
        checkedRows[5] = false;
        checkedRows[6] = false;
        checkedRows[7] = false;
    }

    /**
     * Play medium.
     * <p>
     * This function calls the checkForChances function. If checkForChances returns 123 (same as nothing), the computer
     * plays with the easyPlay algorithm.
     *
     * @param positions       String Array with all positions and their state
     * @param computersChar   char with the character ('x' or 'o') the computer plays with
     * @param playsPossible   int with number of possible plays left
     * @param btnActive       boolean Array with all active buttons
     * @param chanceForHuman1 int (0-100), a more high value is a less chance for the human to win. Value for
     *                        checkForWinning function
     * @param chanceForHuman2 int (0-100), a more high value is a less chance for the human to win. Value for
     *                        check the doubles
     * @return int result with button to fire (btn.fire())
     */
    public int playMedium(String[] positions, char computersChar, int playsPossible, boolean[] btnActive, int chanceForHuman1, int chanceForHuman2) {
        int result = this.checkForChances(positions, computersChar, chanceForHuman1, chanceForHuman2);
        if (result == 123) {
            EasyPlayService easyPlayService = new EasyPlayService();
            result = easyPlayService.playEasy(btnActive, playsPossible);
        }
        return result;
    }

    /**
     * Validate checked rows.
     * <p>
     * This function validates, which row is empty.
     *
     * @param positions String Array with all positions and their state
     */
    private void validateCheckedRows(String[] positions) {
        if (!positions[0].equals("empty") && !positions[1].equals("empty") && !positions[2].equals("empty")) {
            checkedRows[0] = true;
        }
        if (!positions[3].equals("empty") && !positions[4].equals("empty") && !positions[5].equals("empty")) {
            checkedRows[1] = true;
        }
        if (!positions[6].equals("empty") && !positions[7].equals("empty") && !positions[8].equals("empty")) {
            checkedRows[2] = true;
        }
        if (!positions[0].equals("empty") && !positions[3].equals("empty") && !positions[6].equals("empty")) {
            checkedRows[3] = true;
        }
        if (!positions[1].equals("empty") && !positions[4].equals("empty") && !positions[7].equals("empty")) {
            checkedRows[4] = true;
        }
        if (!positions[2].equals("empty") && !positions[5].equals("empty") && !positions[8].equals("empty")) {
            checkedRows[5] = true;
        }
        if (!positions[0].equals("empty") && !positions[4].equals("empty") && !positions[8].equals("empty")) {
            checkedRows[6] = true;
        }
        if (!positions[2].equals("empty") && !positions[4].equals("empty") && !positions[6].equals("empty")) {
            checkedRows[7] = true;
        }
    }

    /**
     * Check for chances to win.
     * <p>
     * This function calculates, which button the computer should fire (btn.fire()). To calculate it, this function uses
     * following functions:
     *
     * @param positions       String Array with all positions and their state
     * @param computersChar   char with the character ('x' or 'o') the computer plays with
     * @param chanceForHuman1 int (0-100), a more high value is a less chance for the human to win. Value for
     *                        checkForWinning function
     * @param chanceForHuman2 int (0-100), a more high value is a less chance for the human to win. Value for
     *                        check the doubles
     * @return
     * @see MediumPlayService#checkForWinningHorizontal
     * @see MediumPlayService#checkForWinningVertical
     * @see MediumPlayService#checkForWinningCross
     * @see MediumPlayService#checkHorizontalDoubles
     * @see MediumPlayService#checkVerticalDoubles
     * <p>
     * The chanceForHuman is a randomly generated number between 0 and 100. It will be compared with the parameter
     * chanceForHuman1 and later with chanceForHuman2
     */
    private int checkForChances(String[] positions, char computersChar, int chanceForHuman1, int chanceForHuman2) {
        this.setDefaults();
        int result = 123;

        Random r = new Random();
        int chanceForHuman = r.nextInt(100);

        if (chanceForHuman < chanceForHuman1) {
            result = this.checkForWinningHorizontal(positions, computersChar);
            if (result != 123) {
                return result;
            }

            result = this.checkForWinningVertical(positions, computersChar);
            if (result != 123) {
                return result;
            }

            result = this.checkForWinningCross(positions, computersChar);
            if (result != 123) {
                return result;
            }
        }
        if (chanceForHuman < chanceForHuman2) {
            result = this.checkHorizontalDoubles(positions);
            if (result != 123) {
                return result;
            }

            result = this.checkVerticalDoubles(positions);
            if (result != 123) {
                return result;
            }

            result = this.checkCrossDoubles(positions);
        }
        return result;
    }

    /**
     * Check horizontally doubled checked fields.
     *
     * @param positions String Array with all positions and their state
     * @return int result with button number to fire (btn.fire())
     */
    private int checkHorizontalDoubles(String[] positions) {
        int result;
        result = this.checkRow(positions, 0, 1, 2, 0);
        if (result != 123) {
            return result;
        }
        result = this.checkRow(positions, 3, 4, 5, 1);
        if (result != 123) {
            return result;
        }
        result = this.checkRow(positions, 6, 7, 8, 2);
        return result;
    }

    /**
     * Check vertically doubled checked fields.
     *
     * @param positions String Array with all positions and their state
     * @return int result with button number to fire (btn.fire())
     */
    private int checkVerticalDoubles(String[] positions) {
        int result;
        result = this.checkRow(positions, 0, 3, 6, 3);
        if (result != 123) {
            return result;
        }
        result = this.checkRow(positions, 1, 4, 7, 4);
        if (result != 123) {
            return result;
        }
        result = this.checkRow(positions, 2, 5, 8, 5);
        return result;
    }

    /**
     * Check doubled checked fields over the cross.
     *
     * @param positions String Array with all positions and their state
     * @return int result with button number to fire (btn.fire())
     */
    private int checkCrossDoubles(String[] positions) {
        int result;
        result = this.checkRow(positions, 0, 4, 8, 6);
        if (result != 123) {
            return result;
        }
        result = this.checkRow(positions, 2, 4, 6, 7);
        if (result != 123) {
            return result;
        }

        return result;
    }

    /**
     * Check a single row for the twice occurrence of the same state
     *
     * @param positions         String Array with all positions and their state
     * @param nr1               int field number one to check
     * @param nr2               int field number two to check
     * @param nr3               int field number three to check
     * @param checkedRowsNumber int row number (first row = 1, second row = 2, third row = 3)
     * @return int result with button number to fire (btn.fire())
     */
    private int checkRow(String[] positions, int nr1, int nr2, int nr3, int checkedRowsNumber) {
        int result = 123;
        this.validateCheckedRows(positions);

        if (positions[nr1].equals("cross") && positions[nr2].equals("cross") && !this.checkedRows[checkedRowsNumber]) {
            this.checkedRows[checkedRowsNumber] = true;
            result = nr3;
        }
        if (positions[nr2].equals("cross") && positions[nr3].equals("cross") && !this.checkedRows[checkedRowsNumber]) {
            this.checkedRows[checkedRowsNumber] = true;
            result = nr1;
        }
        if (positions[nr1].equals("cross") && positions[nr3].equals("cross") && !this.checkedRows[checkedRowsNumber]) {
            this.checkedRows[checkedRowsNumber] = true;
            result = nr2;
        }

        if (positions[nr1].equals("circle") && positions[nr2].equals("circle") && !this.checkedRows[checkedRowsNumber]) {
            this.checkedRows[checkedRowsNumber] = true;
            result = nr3;
        }
        if (positions[nr2].equals("circle") && positions[nr3].equals("circle") && !this.checkedRows[checkedRowsNumber]) {
            this.checkedRows[checkedRowsNumber] = true;
            result = nr1;
        }
        if (positions[nr1].equals("circle") && positions[nr3].equals("circle") && !this.checkedRows[checkedRowsNumber]) {
            this.checkedRows[checkedRowsNumber] = true;
            result = nr2;
        }
        return result;
    }

    /**
     * @param positions         String Array with all positions and their state
     * @param nr1               int field number one to check
     * @param nr2               int field number two to check
     * @param nr3               int field number three to check
     * @param computersChar     char with computers character ('x' or 'o')
     * @param checkedRowsNumber int row number (first row = 1, second row = 2, third row = 3)
     * @return
     */
    private int checkRowForWinning(String[] positions, int nr1, int nr2, int nr3, char computersChar, int checkedRowsNumber) {
        int result = 123;
        this.validateCheckedRows(positions);
        if (this.checkedRows[checkedRowsNumber]) {
            return result;
        }
        String checkValue;
        if (computersChar == 'x') {
            checkValue = "cross";
        } else {
            checkValue = "circle";
        }
        if (positions[nr1].equals(checkValue) && positions[nr2].equals(checkValue) && positions[nr3].equals("empty")) {
            this.checkedRows[checkedRowsNumber] = true;
            return nr3;
        }
        if (positions[nr1].equals(checkValue) && positions[nr3].equals(checkValue) && positions[nr2].equals("empty")) {
            this.checkedRows[checkedRowsNumber] = true;
            return nr2;
        }
        if (positions[nr2].equals(checkValue) && positions[nr3].equals(checkValue) && positions[nr1].equals("empty")) {
            this.checkedRows[checkedRowsNumber] = true;
            return nr1;
        }
        return result;
    }

    /**
     *
     * @param positions
     * @param computersChar
     * @return
     */
    private int checkForWinningHorizontal(String[] positions, char computersChar) {
        int result = this.checkRowForWinning(positions, 0, 1, 2, computersChar, 0);
        if (result != 123) {
            return result;
        }
        result = this.checkRowForWinning(positions, 3, 4, 5, computersChar, 1);
        if (result != 123) {
            return result;
        }
        result = this.checkRowForWinning(positions, 6, 7, 8, computersChar, 2);
        return result;
    }

    private int checkForWinningVertical(String[] positions, char computersChar) {
        int result = this.checkRowForWinning(positions, 0, 3, 6, computersChar, 3);
        if (result != 123) {
            return result;
        }
        result = this.checkRowForWinning(positions, 1, 4, 7, computersChar, 4);
        if (result != 123) {
            return result;
        }
        result = this.checkRowForWinning(positions, 2, 5, 8, computersChar, 5);
        return result;
    }

    private int checkForWinningCross(String[] positions, char computersChar) {
        int result = this.checkRowForWinning(positions, 0, 4, 8, computersChar, 6);
        if (result != 123) {
            return result;
        }
        result = this.checkRowForWinning(positions, 2, 4, 6, computersChar, 7);
        return result;
    }
}
