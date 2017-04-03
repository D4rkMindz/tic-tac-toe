package Services;

import java.util.Random;

/**
 * Class EasyPlayService.
 */
public class EasyPlayService {
    /**
     * Play Easy.
     * <p>
     * Randomly generated number between 0 and 8. The generated number's button is going to be fired (btn.fire())
     *
     * @param btnActive     boolean Array with all active buttons
     * @param playsPossible int with number of possible plays
     * @return result int with button to fire (btn.fire())
     */
    public int playEasy(boolean[] btnActive, int playsPossible) {
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
