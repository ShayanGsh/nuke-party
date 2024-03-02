package dev.gash.game.map;
import java.util.Random;

public class PerlinNoise {
    private int width;
    private int height;
    private int octaveCount;
    private float amplitude;
    private long seed;
    private float[][] noiseGrid;

    public PerlinNoise(int width, int height, int octaveCount, float amplitude, long seed) {
        this.width = width;
        this.height = height;
        this.octaveCount = octaveCount;
        this.amplitude = amplitude;
        this.seed = seed;
        this.noiseGrid = generatePerlinNoise(width, height, octaveCount, amplitude, seed);
    }

    private float[][] generatePerlinNoise(int width, int height, int octaveCount, float amplitude, long seed) {
        float[][] baseNoise = generateWhiteNoise(width, height, seed);
        float[][] perlinNoise = new float[width][height];
        float totalAmplitude = 0.0f;

        // Generate the Perlin noise by adding up the octave noises
        for (int i = octaveCount - 1; i >= 0; i--) {
            amplitude *= 0.5f;
            totalAmplitude += amplitude;
            perlinNoise = addNoise(perlinNoise, generateSmoothNoise(baseNoise, i), amplitude);
        }

        // Normalize the Perlin noise values so that they are between 0 and 1
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                perlinNoise[i][j] /= totalAmplitude;
            }
        }

        return perlinNoise;
    }

    private float[][] generateWhiteNoise(int width, int height, long seed) {
        Random random = new Random(seed);
        float[][] noise = new float[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                noise[i][j] = random.nextFloat();
            }
        }
        return noise;
    }

    private float[][] generateSmoothNoise(float[][] baseNoise, int octave) {
        int width = baseNoise.length;
        int height = baseNoise[0].length;

        float[][] smoothNoise = new float[width][height];
        int samplePeriod = 1 << octave; // calculates 2 ^ k
        float sampleFrequency = 1.0f / samplePeriod;

        for (int i = 0; i < width; i++) {
            //calculate the horizontal sampling indices
            int sample_i0 = (i / samplePeriod) * samplePeriod;
            int sample_i1 = (sample_i0 + samplePeriod) % width; //wrap around
            float horizontal_blend = (i - sample_i0) * sampleFrequency;

            for (int j = 0; j < height; j++) {
                //calculate the vertical sampling indices
                int sample_j0 = (j / samplePeriod) * samplePeriod;
                int sample_j1 = (sample_j0 + samplePeriod) % height; //wrap around
                float vertical_blend = (j - sample_j0) * sampleFrequency;

                //blend the top two corners
                float top = interpolate(baseNoise[sample_i0][sample_j0],
                        baseNoise[sample_i1][sample_j0], horizontal_blend);

                //blend the bottom two corners
                float bottom = interpolate(baseNoise[sample_i0][sample_j1],
                        baseNoise[sample_i1][sample_j1], horizontal_blend);

                //final blend
                smoothNoise[i][j] = interpolate(top, bottom, vertical_blend);
            }
        }

        return smoothNoise;
    }

    private float[][] addNoise(float[][] perlinNoise, float[][] octaveNoise, float amplitude) {
        int width = perlinNoise.length;
        int height = perlinNoise[0].length;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                perlinNoise[i][j] += octaveNoise[i][j] * amplitude;
            }
        }
        return perlinNoise;
    }

    private float interpolate(float x0, float x1, float alpha) {
        return x0 * (1 - alpha) + alpha * x1;
    }

    public void setWidth(int width) {
        this.width = width;
        this.noiseGrid = generatePerlinNoise(width, this.height, this.octaveCount, this.amplitude, seed);
    }

    public void setHeight(int height) {
        this.height = height;
        this.noiseGrid = generatePerlinNoise(this.width, height, this.octaveCount, this.amplitude, seed);
    }

    public void setOctaveCount(int octaveCount) {
        this.octaveCount = octaveCount;
        this.noiseGrid = generatePerlinNoise(this.width, this.height, octaveCount, this.amplitude, seed);
    }

    public void setAmplitude(float amplitude) {
        this.amplitude = amplitude;
        this.noiseGrid = generatePerlinNoise(this.width, this.height, this.octaveCount, amplitude, seed);
    }

    public void setSeed(long seed) {
        this.seed = seed;
        this.noiseGrid = generatePerlinNoise(this.width, this.height, this.octaveCount, this.amplitude, seed);
    }

    public float[][] getNoiseGrid() {
        return this.noiseGrid;
    }
}
