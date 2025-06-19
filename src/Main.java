import core.model.*;
import core.utils.*;
import core.rules.*;
import core.logic.*;

import javax.swing.*;
import java.util.Map;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        System.out.println("Main started.");

        GameController gameController = new GameController(6);
        gameController.startGame();


    }
}