package rocks.gioac96.veronica.samples;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static rocks.gioac96.veronica.samples.E2ETest.measureTime;

import org.junit.jupiter.api.Test;
import rocks.gioac96.veronica.core.HttpMethod;
import rocks.gioac96.veronica.core.Request;
import rocks.gioac96.veronica.routing.Router;

public class AsynchronousPostProcessorsTest {

    private final int sleepTime = 50;
    private final Router router = new AsynchronousPostProcessors(sleepTime).getRouter();

    @Test
    void testDurationDifference() {

        Request syncRequest = E2ETest.mockRequest()
            .path("/sync")
            .httpMethod(HttpMethod.GET)
            .provide();
        Request asyncRequest = E2ETest.mockRequest()
            .path("/async")
            .httpMethod(HttpMethod.GET)
            .provide();

        long syncDuration = measureTime(() -> router.handle(syncRequest));
        long asyncDuration = measureTime(() -> router.handle(asyncRequest));

        assertTrue(syncDuration - asyncDuration > sleepTime * 0.9);

    }

}