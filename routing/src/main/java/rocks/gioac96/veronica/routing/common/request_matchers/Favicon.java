package rocks.gioac96.veronica.routing.common.request_matchers;

import rocks.gioac96.veronica.core.HttpMethod;
import rocks.gioac96.veronica.routing.RequestMatcher;
import rocks.gioac96.veronica.core.providers.Singleton;

/**
 * Framework's common favicon request matcher.
 */
public class Favicon
    extends RequestMatcher.RequestMatcherBuilder
    implements Singleton {

    @Override
    protected void configure() {

        httpMethod(HttpMethod.GET);
        pathPattern("/favicon.ico");

        super.configure();

    }

}