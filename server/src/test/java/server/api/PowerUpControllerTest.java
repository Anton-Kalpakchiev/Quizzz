package server.api;

import commons.EliminateWrongAnswer;
import commons.HalfTime;
import commons.PowerUp;
import org.junit.jupiter.api.Test;
import server.service.PowerUpService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PowerUpControllerTest {
    PowerUpService service = mock(PowerUpService.class);
    PowerUpController controller = new PowerUpController(service);
    List<PowerUp> poweruplist = List.of(
                new HalfTime("a","0:20"),
                new EliminateWrongAnswer("a","0:20"));
    @Test
    void getPowerUpTest(){
        when(service.getPowerUpList()).thenReturn(poweruplist);
        List<PowerUp> testPlay = controller.getPowerUp();
        assertEquals(poweruplist,testPlay);

    }

}