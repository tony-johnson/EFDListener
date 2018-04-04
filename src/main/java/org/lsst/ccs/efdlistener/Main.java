package org.lsst.ccs.efdlistener;

import java.net.URI;
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
    public static void main(String[] args) throws SALException {
        SALEFD efd = SALEFD.create();
        for (;;) {
            SALEvent nextEvent = efd.getNextEvent(Duration.ofSeconds(60));
            if (nextEvent != null) {
                System.out.println("Got "+nextEvent);
                if (nextEvent instanceof LargeFileObjectAvailableEvent) {
                    LargeFileObjectAvailableEvent lfo = (LargeFileObjectAvailableEvent) nextEvent;
                    URI url = URI.create(lfo.getURL());
                    System.out.println("Reading..."+url);
                    String content = url.toASCIIString();
                    System.out.println(content);
                }
            }
        }
    }
 }
