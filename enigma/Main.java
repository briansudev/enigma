package enigma;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/** Enigma simulator.
 *  @author Brian Su
 */
public final class Main {

    /** Process a sequence of encryptions and decryptions, as
     *  specified in the input from the standard input.  Print the
     *  results on the standard output. Exits normally if there are
     *  no errors in the input; otherwise with code 1. */
    public static void main(String[] unused) {
        Machine M;
        BufferedReader input =
            new BufferedReader(new InputStreamReader(System.in));

        buildRotors();

        M = null;
        int configSet = 0;
        try {
            while (true) {
                String line = input.readLine();
                if (line == null) {
                    break;
                }
                if (isConfigurationLine(line)) {
                    M = new Machine();
                    configure(M, line);
                    configSet += 1;
                } else if (configSet == 0) {
                    throw new IOException("Machine needs a configuration");
                } else {
                    printMessageLine(M.convert(standardize(line)));
                    System.out.println();
                }
            }
        } catch (IOException excp) {
            System.err.printf("Input error: %s%n", excp.getMessage());
            System.exit(1);
        }
    }

    /** Return true iff LINE is an Enigma configuration line. */
    private static boolean isConfigurationLine(String line) {
        return line.trim().startsWith("*");
    }

    /** Configure M according to the specification given on CONFIG,
     *  which must have the format specified in the assignment.
     * @throws IOException when config is malformed*/
    private static void configure(Machine M, String config) throws IOException {
        String[] cfg = config.split(" ");
        if (cfg.length != 7) {
            throw new IOException("Incorrect number of arguments");
        }
        for (int i = 1; i < cfg.length; i++) {
            cfg[i] = cfg[i].toUpperCase();
        }
        String th = cfg[3];
        String fr = cfg[4];
        String fv = cfg[5];
        if (th.equals(fr) || th.equals(fv) || fr.equals(fv)) {
            throw new IOException("Cannot use the same rotor in two positions");
        }
        Rotor[] rotors = new Rotor[5];
        if (cfg[1].equals("B")) {
            rotors[0] = b;
        } else if (cfg[1].equals("C")) {
            rotors[0] = c;
        } else {
            throw new IOException("Not a reflector: " + cfg[1]);
        }
        if (cfg[2].equals("BETA")) {
            rotors[1] = beta;
        } else if (cfg[2].equals("GAMMA")) {
            rotors[1] = gamma;
        } else {
            throw new IOException("Not a fixed rotor: " + cfg[2]);
        }
        for (int i = 3; i < cfg.length - 1; i++) {
            switch (cfg[i]) {
            case "I":
                rotors[i - 1] = one; break;
            case "II":
                rotors[i - 1] = two; break;
            case "III":
                rotors[i - 1] = three; break;
            case "IV":
                rotors[i - 1] = four; break;
            case "V":
                rotors[i - 1] = five; break;
            case "VI":
                rotors[i - 1] = six; break;
            case "VII":
                rotors[i - 1] = seven; break;
            case "VIII":
                rotors[i - 1] = eight; break;
            default:
                throw new IOException("Not a rotor: " + cfg[i]);
            }
        }
        M.replaceRotors(rotors);
        assert cfg[6].length() == 4;
        M.setRotors(cfg[6].toUpperCase());
    }

    /** Return the result of converting LINE to all upper case,
     *  removing all blanks and tabs.
     * @throws IOException It is an error if LINE contains
     *  characters other than letters and blanks. */
    private static String standardize(String line) throws IOException {
        line = line.replace(" ", "").toUpperCase();
        for (char x : line.toCharArray()) {
            if (!Character.isLetter(x)) {
                throw new IOException("Not a letter: " + x);
            }
        }
        return line;
    }

    /** Print MSG in groups of five (except that the last group may
     *  have fewer letters). */
    private static void printMessageLine(String msg) {
        char[] line = msg.toCharArray();
        for (int i = 0; i < line.length; i++) {
            if (i > 0 && i % 5 == 0) {
                System.out.print(" ");
            }
            System.out.print(line[i]);
        }
    }

    /** Create all the necessary rotors. */
    private static void buildRotors() {
        String[][] data = PermutationData.ROTOR_SPECS;
        one = new Rotor(data[0][0], data[0][1], data[0][2], data[0][3]);
        two = new Rotor(data[1][0], data[1][1], data[1][2], data[1][3]);
        three = new Rotor(data[2][0], data[2][1], data[2][2], data[2][3]);
        four = new Rotor(data[3][0], data[3][1], data[3][2], data[3][3]);
        five = new Rotor(data[4][0], data[4][1], data[4][2], data[4][3]);
        six = new Rotor(data[5][0], data[5][1], data[5][2], data[5][3]);
        seven = new Rotor(data[6][0], data[6][1], data[6][2], data[6][3]);
        eight = new Rotor(data[7][0], data[7][1], data[7][2], data[7][3]);
        beta = new FixedRotor(data[8][0], data[8][1], data[8][2]);
        gamma = new FixedRotor(data[9][0], data[9][1], data[9][2]);
        b = new Reflector(data[10][0], data[10][1]);
        c = new Reflector(data[ELEVEN][0], data[ELEVEN][1]);
    }
    /** Eleven. */
    private static final int ELEVEN = 11;
    /** Initiate all the Rotors. */
    private static Rotor one, two, three, four, five, six, seven, eight;
    /** Initiate the FixedRotors and Reflectors. */
    private static Rotor beta, gamma, b, c;
}

