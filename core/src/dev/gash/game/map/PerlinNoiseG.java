package dev.gash.game.map;

public class PerlinNoiseG {

    private static final int PERMUTATION_TABLE_SIZE = 256;
    private static final int PERMUTATION_MASK = PERMUTATION_TABLE_SIZE - 1;
    private static final int[] permutationTable;

    static {
        permutationTable = new int[PERMUTATION_TABLE_SIZE];
        for (int i = 0; i < PERMUTATION_TABLE_SIZE; i++) {
            permutationTable[i] = i;
        }

        // Shuffle the permutation table using the Fisher-Yates algorithm
        for (int i = PERMUTATION_TABLE_SIZE - 1; i > 0; i--) {
            int j = (int) Math.floor(Math.random() * (i + 1));
            int temp = permutationTable[i];
            permutationTable[i] = permutationTable[j];
            permutationTable[j] = temp;
        }
    }

    private final int seed;

    public PerlinNoiseG(int seed) {
        this.seed = seed;
    }

    public double noise(double x, double y) {
        int integerX = (int) Math.floor(x);
        int integerY = (int) Math.floor(y);

        double fractionalX = x - integerX;
        double fractionalY = y - integerY;

        int leftX = permutationTable[(integerX + seed) & PERMUTATION_MASK];
        int rightX = permutationTable[(integerX + 1 + seed) & PERMUTATION_MASK];
        int leftY = permutationTable[(integerY + seed) & PERMUTATION_MASK];
        int rightY = permutationTable[(integerY + 1 + seed) & PERMUTATION_MASK];

        int gradX0 = permutationTable[(leftX + permutationTable[leftY]) & PERMUTATION_MASK];
        int gradX1 = permutationTable[(leftX + permutationTable[leftY + 1]) & PERMUTATION_MASK];
        int gradY0 = permutationTable[(rightX + permutationTable[leftY]) & PERMUTATION_MASK];
        int gradY1 = permutationTable[(rightX + permutationTable[leftY + 1]) & PERMUTATION_MASK];

        double dotYX0 = fade(fractionalX) * dot(gradX0 % 4, fractionalX, gradY0 % 4, fractionalY);
        double dotYX1 = fade(1 - fractionalX) * dot(gradX1 % 4, 1 - fractionalX, gradY1 % 4, fractionalY);
        double dotXY0 = fade(fractionalY) * dot(gradX0 % 4, fractionalX, gradY0 % 4, 1 - fractionalY);
        double dotXY1 = fade(1 - fractionalY) * dot(gradX1 % 4, 1 - fractionalX, gradY1 % 4, 1 - fractionalY);

        return lerp(lerp(dotYX0, dotYX1, fractionalX), lerp(dotXY0, dotXY1, fractionalY), fractionalY);
    }

    private static double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private static double dot(int grad, double x, int grad2, double y) {
        return x * (grad % 4 - 1) + y * (grad2 % 4 - 1);
    }

    private static double lerp(double a, double b, double t) {
        return a + (b - a) * t;
    }
}
