package rocks.gioac96.veronica.tutorials.multiple_servers;

import rocks.gioac96.veronica.ServerFactory;

public class MyNotSecureServer extends ServerFactory {

    @Override
    public void configure() {

        port(80);

    }

}