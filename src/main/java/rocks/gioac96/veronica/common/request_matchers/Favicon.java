package rocks.gioac96.veronica.common.request_matchers;

import rocks.gioac96.veronica.core.HttpMethod;
import rocks.gioac96.veronica.core.RequestMatcher;
import rocks.gioac96.veronica.providers.Builder;

public class Favicon extends Builder<RequestMatcher> {

    @Override
    protected RequestMatcher instantiate() {

        return new MethodAndPathPattern()
            .httpMethod(HttpMethod.GET)
            .pathPattern("/favicon.ico")
            .build();

    }

}