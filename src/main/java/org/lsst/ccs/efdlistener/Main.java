package org.lsst.ccs.efdlistener;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.CharBuffer;
import java.time.Duration;
import org.lsst.sal.SALEvent;
import org.lsst.sal.SALException;
import org.lsst.sal.efd.SALEFD;
import org.lsst.sal.efd.event.LargeFileObjectAvailableEvent;

/**
 *
 * @author tonyj
 */
public class Main {

    public static void main(String[] args) throws SALException, MalformedURLException, IOException {
        SALEFD efd = SALEFD.create();
        for (;;) {
            SALEvent nextEvent = efd.getNextEvent(Duration.ofSeconds(60));
            if (nextEvent != null) {
                System.out.println("Got " + nextEvent);
                if (nextEvent instanceof LargeFileObjectAvailableEvent) {
                    LargeFileObjectAvailableEvent lfo = (LargeFileObjectAvailableEvent) nextEvent;
                    URI url = URI.create(lfo.getURL());
                    System.out.println("Reading..." + url);
                    try (Reader reader = new InputStreamReader(url.toURL().openStream())) {
                        CharBuffer buffer = CharBuffer.allocate(80);
                        for (;;) {
                            int l = reader.read(buffer);
                            if (l == 0) break;
                            System.out.println(buffer);
                        }
                    }
                }
            }
        }
    }
}
