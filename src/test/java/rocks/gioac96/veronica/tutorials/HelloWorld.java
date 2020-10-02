package rocks.gioac96.veronica.tutorials;

import rocks.gioac96.veronica.core.Application;
import rocks.gioac96.veronica.core.Route;
import rocks.gioac96.veronica.core.Router;
import rocks.gioac96.veronica.core.Server;
import rocks.gioac96.veronica.core.Response;
import rocks.gioac96.veronica.core.RequestHandler;

public class HelloWorld {

    public static void main(String[] args) {

        RequestHandler helloWorldHandler = request -> Response.builder()
            .body("Hello World")
            .build();


        Router router = Router.builder()
            .defaultRequestHandler(helloWorldHandler)
            .build();

        int port = 8000;

        Application app = Application.builder()
            .server(Server.builder()
                .port(port)
                .build()
            ).router(router)
            .build();

        app.start();


    }

}
