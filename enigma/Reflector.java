package enigma;

/** Class that represents a reflector in the enigma.
 *  @author Brian Su
 */
class Reflector extends Rotor {

    /** Create a Reflector with ROTORNAME and MAP. */
    public Reflector(String rotorName, String map) {
        super(rotorName, map, "", "");
    }

    @Override
    boolean hasInverse() {
        return false;
    }

    /** Returns a useless value; should never be called. */
    @Override
    int convertBackward(int unused) {
        throw new UnsupportedOperationException();
    }

}
