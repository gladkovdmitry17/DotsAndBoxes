package gameStates;

import gameScene.DataUpdater;
import gameScene.Game;
import gameScene.SharedData;
import javafx.application.Platform;
import javafx.concurrent.Task;
import sample.Main;

/**
 * Created by Дмитрий on 12/8/2016.
 */
public class ActionState extends State implements DataUpdater {

    SharedData data;

    public ActionState() {
        setData(Main.sharedData);
    }

    @Override
    public void setData(SharedData data) {
        this.data = data;
    }

    @Override
    public void recvNewState(short state) {
        Main.game.setCurrentState(state);
        if (state == Game.WAIT) {
            updateGameStateOnWait();
        }
    }

    private void updateGameStateOnWait() {
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        data.getStringStateProp().set("Wait move of " + Game.getEnemyName());
                    }
                });
                return null;
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }
}
