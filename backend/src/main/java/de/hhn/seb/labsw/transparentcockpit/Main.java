package de.hhn.seb.labsw.transparentcockpit;

import de.hhn.seb.labsw.transparentcockpit.backend.springboot.BackendApplicationService;
import de.hhn.seb.labsw.transparentcockpit.backend.startup.Startup;


public class Main {

    public static void main(String[] args) {
        new Startup();
        BackendApplicationService.run();
    }
}
