import java.util.Random;
public class MatrixMultiplication
{

    //Assumes both matrices are nxn size
    public static int[][] classicalMultiplcation(int[][] matrix1, int[][] matrix2)
    {
        if (matrix1.length == 0 || matrix2.length == 0 || matrix1.length != matrix2[0].length)
        {
            System.out.println("Error: Invalid matrix");
            return new int[0][0];
        }
        int[][] multipliedMatrix = new int[matrix1.length][matrix1.length];
        for (int i = 0; i < matrix1.length; i++)
        {
            for (int k = 0; k < matrix1.length; k++)
            {
                multipliedMatrix[i][k] = 0;
                for (int j = 0; j < matrix1.length; j++)
                {
                    multipliedMatrix[i][k] += (matrix1[i][j] * matrix2[j][k]);
                }
            }
        }
        return multipliedMatrix;
    }

    public static void generateEmpty(int[][] matrix)
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int k = 0; k < matrix[i].length; k++)
            {
                matrix[i][k] = 0;
            }
        }
    }

    public static int[][] DACMultiplication(int[][] matrix1, int[][] matrix2)
    {
        return new int[0][0];
    }

    public static int[][] generateMatrix(int size, int limit)
    {
        Random generator = new Random();
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                matrix[i][j] = (generator.nextInt(limit) + 1);
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix)
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        int[][] matrix = generateMatrix(4, 50);
        int[][] matrix2 = generateMatrix(4, 50);
        int[][] test = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}};
        int[][] test2 = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}};
        generateEmpty(test);
        // printMatrix(classicalMultiplcation(test, test2));
        printMatrix(test);
    }
}