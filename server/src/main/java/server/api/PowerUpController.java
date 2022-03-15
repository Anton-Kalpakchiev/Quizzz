package server.api;

import commons.PowerUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.service.PowerUpService;

import java.util.List;

@RestController
@RequestMapping("/api/powerup")
public class PowerUpController {
    public PowerUpService service;
    public PowerUpController(PowerUpService service){
            this.service = service;
    }
    @GetMapping("")
    public List<PowerUp> getPowerUp() {
        return service.getPowerUpList();
    }
    @PostMapping("")
    PowerUp newPowerUp(@RequestBody PowerUp newpowerup) {
        return service.addNewPowerUp(newpowerup);
    }
}
