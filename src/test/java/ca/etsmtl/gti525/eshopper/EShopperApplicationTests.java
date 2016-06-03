package ca.etsmtl.gti525.eshopper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ca.etsmtl.gti525.espectacles.ESpectaclesApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ESpectaclesApplication.class)
@WebAppConfiguration
public class EShopperApplicationTests {

	@Test
	public void contextLoads() {
	}

}
