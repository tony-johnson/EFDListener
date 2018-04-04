package org.lsst.ccs.efdlistener;

import java.time.Duration;
import org.lsst.sal.SALEvent;
import org.lsst.sal.SALException;
import org.lsst.sal.efd.SALEFD;

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
            }
        }
    }
 }
