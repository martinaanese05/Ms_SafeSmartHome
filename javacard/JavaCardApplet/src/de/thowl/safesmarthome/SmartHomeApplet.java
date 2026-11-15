package de.thowl.safesmarthome;

import javacard.framework.APDU;
import javacard.framework.Applet;
import javacard.framework.ISO7816;
import javacard.framework.ISOException;

public class SmartHomeApplet extends Applet {
    protected SmartHomeApplet() {
        register();
    }

    public static void install(byte[] bArray, short bOffset, byte bLength) {
        new SmartHomeApplet();
    }

    @Override
    public void process(APDU apdu) throws ISOException {
        // SELECT-Befehl behandeln
        if (selectingApplet()) {return;}

        // Empfangenen APDU-Befehl auslesen
        byte[] buffer = apdu.getBuffer();

        // INS-Byte auslesen
        byte instruction = buffer[ISO7816.OFFSET_INS];

        // Weitere Befehle verarbeiten
        switch (instruction) {
            default:
                ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);
        }
    }
}
