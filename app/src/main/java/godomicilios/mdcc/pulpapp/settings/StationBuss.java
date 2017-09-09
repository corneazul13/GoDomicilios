package godomicilios.mdcc.pulpapp.settings;

import com.squareup.otto.Bus;

/**
 * Created by PROGRAMACION5 on 25/08/2017.
 */

public class StationBuss {
    private static Bus bus = new Bus();

    public static Bus getBus() {
        return bus;
    }
}
