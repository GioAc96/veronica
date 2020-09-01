package rocks.gioac96.veronica.samples;

import rocks.gioac96.veronica.Application;
import rocks.gioac96.veronica.http.CommonResponses;
import rocks.gioac96.veronica.routing.Route;
import rocks.gioac96.veronica.routing.Router;

public class File {

    public static void main(String[] args) {

        Application.basic()
            .port(80)
            .router(Router.builder()
                .fallbackRoute(Route.builder()
                    .handler(request -> CommonResponses.embeddedFile("D:\\OneDrive\\zb13bup\\Desktop\\pgamento-universita-2020-2021.pdf"))
                    .build())
                .build())
            .build()
            .start();

    }

}