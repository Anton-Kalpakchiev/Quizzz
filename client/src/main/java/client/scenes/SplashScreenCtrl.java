package client.scenes;

import com.google.inject.Inject;

public class SplashScreenCtrl {
    private MainCtrl mainCtrl;

    @Inject
    public SplashScreenCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    public void chooseSingleplayer() {
        mainCtrl.chooseSingleplayer();
    }
    public void chooseMultiplayer() {
        mainCtrl.chooseMultiplayer();
    }
}