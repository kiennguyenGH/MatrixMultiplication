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

    public static void splitMatrix(int[][] matrix1, int[][] matrix2, int row, int column)
    {
        int theRow = row;
        int theColumn = column;
        for (int i = 0; i < matrix2.length; i++)
        {
            theColumn = column;
            for (int k = 0; k < matrix2.length; k++)
            {
                matrix2[i][k] = matrix1[theRow][theColumn];
                theColumn++;
            }
            theRow++;
        }
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

    public static void addToMatrix(int[][] matrix1, int[][] matrix2, int row, int column)
    {
        int theRow = row;
        int theColumn = column;
        for (int i = 0; i < matrix1.length; i++)
        {
            for (int k = 0; k < matrix1.length; k++)
            {
                matrix2[theRow][theColumn] = matrix1[i][k];
                theColumn++;
            }
            theColumn = column;
            theRow++;
        }
    }

    private static int[][] combineMatrix(int[][] matrix1, int[][] matrix2, int[][] matrix3, int[][] matrix4)
    {
        int[][] finalMatrix = new int[matrix1.length*2][matrix1.length*2];
        
        addToMatrix(matrix1, finalMatrix, 0, 0);
        addToMatrix(matrix2, finalMatrix, 0, finalMatrix.length/2);
        addToMatrix(matrix3, finalMatrix, finalMatrix.length/2, 0);
        addToMatrix(matrix4, finalMatrix, finalMatrix.length/2, finalMatrix.length/2);

        return finalMatrix;
    }

    private static int[][] addMatrix(int[][] matrix1, int[][] matrix2)
    {
        int[][] sum = new int[matrix1.length][matrix1.length];
        for (int i = 0; i < matrix1.length; i++)
        {
            for (int k = 0; k < matrix1.length; k++)
            {
                sum[i][k] = matrix1[i][k] + matrix2[i][k];
            }
        }
        return sum;
    }

    public static int[][] DACMultiplication(int[][] matrix1, int[][] matrix2)
    {
        int[][] multipliedMatrix = new int[matrix1.length][matrix1.length];

        if (matrix1.length <= 1 || matrix2.length <= 1)
        {
            multipliedMatrix[0][0] = matrix1[0][0] * matrix2[0][0];
            return multipliedMatrix;
        }

        int[][] A0 = new int[matrix1.length/2][matrix1.length/2];
        int[][] A1 = new int[matrix1.length/2][matrix1.length/2];
        int[][] A2 = new int[matrix1.length/2][matrix1.length/2];
        int[][] A3 = new int[matrix1.length/2][matrix1.length/2];
        int[][] B0 = new int[matrix1.length/2][matrix1.length/2];
        int[][] B1 = new int[matrix1.length/2][matrix1.length/2];
        int[][] B2 = new int[matrix1.length/2][matrix1.length/2];
        int[][] B3 = new int[matrix1.length/2][matrix1.length/2];

        splitMatrix(matrix1, A0, 0, 0);
        splitMatrix(matrix1, A1, 0, matrix1.length/2);
        splitMatrix(matrix1, A2, matrix1.length/2, 0);
        splitMatrix(matrix1, A3, matrix1.length/2, matrix1.length/2);
        splitMatrix(matrix2, B0, 0, 0);
        splitMatrix(matrix2, B1, 0, matrix2.length/2);
        splitMatrix(matrix2, B2, matrix2.length/2, 0);
        splitMatrix(matrix2, B3, matrix2.length/2, matrix2.length/2);

        int[][] A0B0 = DACMultiplication(A0, B0);
        int[][] A1B2 = DACMultiplication(A1, B2);
        int[][] A0B1 = DACMultiplication(A0, B1);
        int[][] A1B3 = DACMultiplication(A1, B3);
        int[][] A2B0 = DACMultiplication(A2, B0);
        int[][] A3B2 = DACMultiplication(A3, B2);
        int[][] A2B1 = DACMultiplication(A2, B1);
        int[][] A3B3 = DACMultiplication(A3, B3);

        int[][] C0 = addMatrix(A0B0, A1B2);
        int[][] C1 = addMatrix(A0B1, A1B3);
        int[][] C2 = addMatrix(A2B0, A3B2);
        int[][] C3 = addMatrix(A2B1, A3B3);

        multipliedMatrix = combineMatrix(C0, C1, C2, C3);

        return multipliedMatrix;
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
        int[][] test = {{1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}};
        int[][] test2 = {{1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}};
        printMatrix(DACMultiplication(test, test2));
        // printMatrix(classicalMultiplcation(test, test2));
        // printMatrix(test);
    }
}