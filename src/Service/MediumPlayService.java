package Service;

import javafx.scene.control.Button;

import java.util.Random;

public class MediumPlayService {
    private boolean[] checkedRows = new boolean[8];

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

    public int playMedium(String[] positions, char computersChar, int playsPossible, boolean[] btnActive, int chanceForHuman1, int chanceForHuman2) {
        int result = this.checkForChances(positions, computersChar, chanceForHuman1, chanceForHuman2);
        if (result == 123) {
            EasyPlayService easyPlayService = new EasyPlayService();
            result = easyPlayService.playEasy(btnActive, playsPossible);
        }
        return result;
    }

    public void validateCheckedRows(String[] positions){
        if (!positions[0].equals("empty") && !positions[1].equals("empty") && !positions[2].equals("empty")){
            checkedRows[0] = true;
        }
        if (!positions[3].equals("empty") && !positions[4].equals("empty") && !positions[5].equals("empty")){
            checkedRows[1] = true;
        }
        if (!positions[6].equals("empty") && !positions[7].equals("empty") && !positions[8].equals("empty")){
            checkedRows[2] = true;
        }
        if (!positions[0].equals("empty") && !positions[3].equals("empty") && !positions[6].equals("empty")){
            checkedRows[3] = true;
        }
        if (!positions[1].equals("empty") && !positions[4].equals("empty") && !positions[7].equals("empty")){
            checkedRows[4] = true;
        }
        if (!positions[2].equals("empty") && !positions[5].equals("empty") && !positions[8].equals("empty")){
            checkedRows[5] = true;
        }
        if (!positions[0].equals("empty") && !positions[4].equals("empty") && !positions[8].equals("empty")){
            checkedRows[6] = true;
        }
        if (!positions[2].equals("empty") && !positions[4].equals("empty") && !positions[6].equals("empty")){
            checkedRows[7] = true;
        }
    }

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
