package enigma;

/** Class that represents a rotor in the enigma machine.
 *  @author Brian Su
 */
class Rotor {

    /** Size of alphabet used for plaintext and ciphertext. */
    static final int ALPHABET_SIZE = 26;

    /** Construct a new Rotor object with NAME, MAP2, INVERSE2, NOTCH2. */
    public Rotor(String name, String map2, String inverse2, String notch2) {
        rotorName = name;
        map = map2;
        inverse = inverse2;
        notch = notch2;
        _setting = 0;
    }

    /** Assuming that P is an integer in the range 0..25, returns the
     *  corresponding upper-case letter in the range A..Z. */
    static char toLetter(int p) {
        return (char) (p + 'A');
    }

    /** Assuming that C is an upper-case letter in the range A-Z, return the
     *  corresponding index in the range 0..25. Inverse of toLetter. */
    static int toIndex(char c) {
        return c - 'A';
    }

    /** Returns true iff this rotor has a ratchet and can advance. */
    boolean advances() {
        return true;
    }

    /** Returns true iff this rotor has a left-to-right inverse. */
    boolean hasInverse() {
        return true;
    }

    /** Return my current rotational setting as an integer between 0
     *  and 25 (corresponding to letters 'A' to 'Z').  */
    int getSetting() {
        return _setting;
    }

    /** Set getSetting() to POSN.  */
    void set(int posn) {
        assert 0 <= posn && posn < ALPHABET_SIZE;
        _setting = posn;
    }

    /** Return the conversion of P (an integer in the range 0..25)
     *  according to my permutation. */
    int convertForward(int p) {
        return toIndex(map.charAt(p));
    }

    /** Return the conversion of E (an integer in the range 0..25)
     *  according to the inverse of my permutation. */
    int convertBackward(int e) {
        return toIndex(inverse.charAt(e));
    }

    /** Returns true iff I am positioned to allow the rotor to my left
     *  to advance. */
    boolean atNotch() {
        if (notch.length() == 2) {
            return (_setting == toIndex(notch.charAt(0)))
                || (_setting == toIndex(notch.charAt(1)));
        }
        return _setting == toIndex(notch.charAt(0));
    }

    /** Advance me one position. */
    void advance() {
        set((_setting + 1) % ALPHABET_SIZE);
    }

    /** Return the result: Convert a character LETTER using the map. */
    char decode(char letter) {
        int pPlusK = (toIndex(letter) + _setting) % ALPHABET_SIZE;
        int result = convertForward(pPlusK) - _setting + ALPHABET_SIZE;
        result = result % ALPHABET_SIZE;
        return toLetter(result);
    }

    /** Return the result: Convert a character LETTER using the inverse. */
    char decode2(char letter) {
        int pPlusK = (toIndex(letter) + _setting) % ALPHABET_SIZE;
        int result = convertBackward(pPlusK) - _setting + ALPHABET_SIZE;
        result = result % ALPHABET_SIZE;
        return toLetter(result);
    }

    /** Return the rotor's rotorName. */
    String getName() {
        return rotorName;
    }

    /** My current setting (index 0..25, with 0 indicating that 'A'
     *  is showing). */
    private int _setting;
    /** Initiate rotorName, map, inverse, notch. */
    private String rotorName, map, inverse, notch;

}
