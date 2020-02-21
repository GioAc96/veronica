package rocks.gioac96.veronica.samples;

import java.io.IOException;
import rocks.gioac96.veronica.Application;
import rocks.gioac96.veronica.http.Request;
import rocks.gioac96.veronica.http.Response;
import rocks.gioac96.veronica.routing.Route;
import rocks.gioac96.veronica.routing.Router;

public class Echo {

    public static void main(String[] args) throws IOException {


        Router<Request, Response> router = Router.builder()
            .route(
                Route.builder()
                    .requestHandler(
                        request -> Response.builder()
                            .body(request.getBody())
                            .build()
                    )
                    .requestMatcher(
                        request -> request.getBody().length() > 0
                    )
                    .build()
            )
            .fallbackRoute(
                Route.builder()
                    .requestHandler(
                        request -> Response.builder()
                            .body("Try to insert something in the request body")
                            .build()
                    )
                    .build()
            )
            .build();

        Application<Request, Response> application = Application.basic(80, router);

        application.start();

    }


}