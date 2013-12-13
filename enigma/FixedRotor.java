package enigma;

/** Class that represents a rotor that has no ratchet and does not advance.
 *  @author Brian Su
 */
class FixedRotor extends Rotor {

    /** Create a FixedRotor Object with ROTORNAME, MAP, and INVERSE. */
    public FixedRotor(String rotorName, String map, String inverse) {
        super(rotorName, map, inverse, "");
    }

    @Override
    boolean advances() {
        return false;
    }

    @Override
    boolean atNotch() {
        return false;
    }

    /** Fixed rotors do not advance. */
    @Override
    void advance() {
    }

}
