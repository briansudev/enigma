package enigma;

/** Class that represents a complete enigma machine.
 *  @author Brian Su
 */
class Machine {

    /** Construct a new machine with 5 rotors. */
    public Machine() {
        rotors = new Rotor[5];
    }
    /** Set my rotors to (from left to right) ROTORARRAY.  Initially, the rotor
     *  settings are all 'A'. */
    void replaceRotors(Rotor[] rotorArray) {
        for (int i = 0; i < rotorArray.length; i++) {
            rotors[i] = rotorArray[i];
        }
    }

    /** Set my rotors according to SETTING, which must be a string of four
     *  upper-case letters. The first letter refers to the leftmost
     *  rotor setting.  */
    void setRotors(String setting) {
        char[] config = setting.toCharArray();
        for (int i = 0; i < config.length; i++) {
            rotors[i + 1].set(Rotor.toIndex(config[i]));
        }
    }

    /** Returns the encoding/decoding of MSG, updating the state of
     *  the rotors accordingly. */
    String convert(String msg) {
        char item;
        char[] message = msg.toCharArray();
        char[] decoded = new char[message.length];
        for (int i = 0; i < message.length; i++) {
            advanceRotors();
            decoded[i] = decodeLetter(message[i]);
        }
        String result = new String(decoded);
        return result;
    }

    /** Properly advance rotors. */
    void advanceRotors() {
        if (rotors[4].atNotch()) {
            if (rotors[3].atNotch()) {
                rotors[2].advance();
            }
            rotors[3].advance();
        } else if (rotors[3].atNotch()) {
            rotors[2].advance();
            rotors[3].advance();
        }
        rotors[4].advance();
    }

    /** Return the decoded letter from LETTER. */
    char decodeLetter(char letter) {
        for (int i = 4; i >= 0; i--) {
            letter = rotors[i].decode(letter);
        }
        for (int i = 1; i < 5; i++) {
            letter = rotors[i].decode2(letter);
        }
        return letter;
    }

    /** Initiate an array of rotors. */
    private Rotor[] rotors;
}
