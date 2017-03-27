package Service;

import java.util.Random;


public class EasyPlayService {
    public int playEasy(boolean[] btnActive, int playsPossible){
        int result;
        Random r = new Random();
        boolean played = false;
        do {
            int randomBtn = r.nextInt(8);
            if (btnActive[randomBtn]) {
                played = true;
            }
            if (playsPossible < 2) {
                played = true;
            }
            result = randomBtn;
        } while (!played);
        return result;
    }
}
