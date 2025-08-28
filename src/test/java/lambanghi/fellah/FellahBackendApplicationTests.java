package lambanghi.fellah;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FellahBackendApplicationTests {
	private static final String BASE_URL = "/api";

	@Test
	void contextLoads() {
		assert(BASE_URL.equals("/api"));
	}

}
