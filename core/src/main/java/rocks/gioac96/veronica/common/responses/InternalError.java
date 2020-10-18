package rocks.gioac96.veronica.common.responses;

import rocks.gioac96.veronica.HttpStatus;
import rocks.gioac96.veronica.Response;
import rocks.gioac96.veronica.providers.Singleton;

public class InternalError
    extends Response.ResponseBuilder
    implements Singleton {

    @Override
    protected void configure() {

        httpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        emptyBody();

        super.configure();

    }

}
