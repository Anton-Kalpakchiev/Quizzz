package server.service;

import commons.HalfTime;
import commons.PowerUp;
import org.junit.jupiter.api.Test;
import server.database.PowerUpRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PowerUpServiceTest {
    PowerUpRepository repo = mock(PowerUpRepository.class);
    PowerUpService service = new PowerUpService(repo);
    List<PowerUp> poweruplist = List.of(
            new HalfTime("a", "0:20"));

    @Test
    void addNewPowerUpTest() {
        PowerUp p = service.addNewPowerUp(poweruplist.get(0));
        assertEquals(poweruplist.get(0), p);
    }
}