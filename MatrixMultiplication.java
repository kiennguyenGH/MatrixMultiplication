import java.util.Random;
public class MatrixMultiplication
{

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
        printMatrix(matrix);
    }
}