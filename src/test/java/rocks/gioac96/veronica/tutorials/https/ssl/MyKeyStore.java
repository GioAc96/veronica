package rocks.gioac96.veronica.tutorials.https.ssl;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import rocks.gioac96.veronica.factories.CreationException;
import rocks.gioac96.veronica.factories.Factory;

public class MyKeyStore implements Factory<KeyStore> {

    public String password() {

        return "";

    }

    @Override
    public KeyStore build() throws CreationException {

        try {

            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream("D:\\certs\\dev.gioac96.rocks\\test.jks"), password().toCharArray());

            return keyStore;

        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {

            throw new CreationException(e);

        }

    }

}
