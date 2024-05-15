package GameGUI;

import java.awt.*;

public interface ScreenConstants {
    // Constants used for the elements of the GUI, all depend on the screen size and width, so just changing these 2 would
    // also make the buttons and such proportionnal.
    int SCREEN_WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()) / 2;
    int SCREEN_HEIGHT = SCREEN_WIDTH/16 * 9;

    //int SCREEN_HEIGHT = ;
    //int SCREEN_WIDTH = ;

    int BUTTON_WIDTH = (int) SCREEN_WIDTH * 9 / 60;
    int BUTTON_HEIGHT = (int) SCREEN_HEIGHT * 9 / 45;

    int BUTTON_SPACE_WIDTH = (int) SCREEN_WIDTH * 1 / 60;
    int BUTTON_SPACE_HEIGHT = (int) SCREEN_HEIGHT * 1 / 45;

    int GRID_START_WIDTH = (int) SCREEN_WIDTH / 6;
    int GRID_START_HEIGHT = (int) SCREEN_HEIGHT / 6;

    int DECK_WIDTH_ORIGIN = (int) SCREEN_WIDTH / 6;
    int DECK_HEIGHT_ORIGIN = (int) SCREEN_HEIGHT * 2 / 60;
    int DECK_WIDTH = (int) SCREEN_WIDTH / 6;
    int DECK_HEIGHT = (int) SCREEN_HEIGHT * 5 / 60;

    int DISCARD_WIDTH_ORIGIN = (int) SCREEN_WIDTH * 4 / 6;
    int DISCARD_HEIGHT_ORIGIN = (int) DECK_HEIGHT_ORIGIN;
    int DISCARD_WIDTH = (int) SCREEN_WIDTH / 6;
    int DISCARD_HEIGHT = (int) DECK_HEIGHT;

    int HAND_WIDTH_ORIGIN = (int) SCREEN_WIDTH /40;
    int HAND_HEIGHT_ORIGIN = (int) SCREEN_HEIGHT /40;
    int HAND_WIDTH = (int) SCREEN_WIDTH /6;
    int HAND_HEIGHT = SCREEN_HEIGHT /10;

    int THROW_WIDTH_ORIGIN = (int) SCREEN_WIDTH /40;
    int THROW_HEIGHT_ORIGIN = (int) SCREEN_HEIGHT /8;
    int THROW_WIDTH = (int) SCREEN_WIDTH /9;
    int THROW_HEIGHT = (int) SCREEN_HEIGHT /10;

    int TASK_WIDTH_ORIGIN = (int) SCREEN_WIDTH /3;
    int TASK_HEIGHT_ORIGIN = (int) SCREEN_HEIGHT * 2/60;
    int TASK_WIDTH = (int) SCREEN_WIDTH/3;
    int TASK_HEIGHT = (int) SCREEN_HEIGHT / 10;
}