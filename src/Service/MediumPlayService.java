package Service;

/**
 * Created by Björn on 24.03.2017.
 */
public class MediumPlayService {
    public int checkVerticalDoubels(String[] positions) {
        int result;
        result = this.checkHoizontalRow(positions, 0, 1, 2);
        if (result != 123) {
            return result;
        }
        result = this.checkHoizontalRow(positions, 3, 4, 5);
        if (result != 123) {
            return result;
        }
        result = this.checkHoizontalRow(positions, 6, 7, 8);
        return result;
    }

    private int checkHoizontalRow(String[] positions, int nr1, int nr2, int nr3) {
        int result = 123;
        if (positions[nr1].equals("cross") && positions[nr2].equals("cross")) {
            result = 2;
        }
        if (positions[nr2].equals("cross") && positions[nr3].equals("cross")) {
            result = 0;
        }
        if (positions[nr1].equals("cross") && positions[nr3].equals("cross")) {
            result = 1;
        }

        if (positions[nr1].equals("circle") && positions[nr2].equals("circle")) {
            result = 2;
        }
        if (positions[nr2].equals("circle") && positions[nr3].equals("circle")) {
            result = 0;
        }
        if (positions[nr1].equals("circle") && positions[nr3].equals("circle")) {
            result = 1;
        }
        return result;
    }
}
