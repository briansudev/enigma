package enigma;

import java.io.IOException;

public class Tester {

	public static void main(String[] args) {
		String[] datafive = PermutationData.ROTOR_SPECS[2];
		String[] datafour = PermutationData.ROTOR_SPECS[1];
		String[] datathree = PermutationData.ROTOR_SPECS[0];
		String[] datatwo = PermutationData.ROTOR_SPECS[8];
		String[] dataone = PermutationData.ROTOR_SPECS[10];
		Rotor five = new Rotor(datafive[0], datafive[1], datafive[2], datafive[3]);
		Rotor four = new Rotor(datafour[0], datafour[1], datafour[2], datafour[3]);
		Rotor three = new Rotor(datathree[0], datathree[1], datathree[2], datathree[3]);
		Rotor two = new FixedRotor(datatwo[0], datatwo[1], datatwo[2]);
		Rotor one = new Reflector(dataone[0], dataone[1]);
		int first = five.convertForward(Rotor.toIndex('B'));
		first = four.convertForward(first);
		first = three.convertForward(first);
		first = two.convertForward(first);
		first = one.convertForward(first);
		first = two.convertBackward(first);
		first = three.convertBackward(first);
		first = four.convertBackward(first);
		first = five.convertBackward(first);
		char result = Rotor.toLetter(first);
		System.out.println(result);
		printMessageLine("HELLOMYNAMEISBRIANSUASD");
		System.out.println();
		try {
			System.out.println(standardize("hi my name is brian su and yours is bobby fisher"));
		} catch (IOException excp) {
            System.err.printf("Input error: %s%n", excp.getMessage());
            System.exit(1);
        }
			
	}

	private static void printMessageLine(String msg) {
        char[] line = msg.toCharArray();
        for (int i = 0; i < line.length; i++) {
        	if (i > 0 && i % 5 == 0) {
        		System.out.print(" ");
        	}
        	System.out.print(line[i]);
        }
    }
	
	private static String standardize(String line) throws IOException {
    	line = line.replace(" ", "").toUpperCase();
    	for (char x : line.toCharArray()) {
    		if (! Character.isLetter(x)) {
    			throw new IOException();
    		}
    	}
    	return line;
    }
}
