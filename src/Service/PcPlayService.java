package Service;

import Controller.VsPcController;

public class PcPlayService {
    public boolean checkWinner(String[] positions) {
        if (this.horizontalRows(positions)) {
            return true;
        }
        if (this.verticalRows(positions)) {
            return true;
        }
        return this.crosses(positions);

    }

    private boolean horizontalRows(String[] positions) {
        VsPcController t = new VsPcController();
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

    private boolean verticalRows(String[] positions) {
        VsPcController t = new VsPcController();
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

    private boolean crosses(String[] positions) {
        VsPcController t = new VsPcController();
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