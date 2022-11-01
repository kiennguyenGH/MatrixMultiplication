import java.util.Random;
public class MatrixMultiplication
{

    //Assumes both matrices are nxn size
    public static int[][] classicalMultiplication(int[][] matrix1, int[][] matrix2)
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

    public static int[][] subtractMatrix(int[][] matrix1, int[][] matrix2)
    {
        int[][] difference = new int[matrix1.length][matrix1.length];
        for (int i = 0; i < matrix1.length; i++)
        {
            for (int k = 0; k < matrix1.length; k++)
            {
                difference[i][k] = matrix1[i][k] - matrix2[i][k];
            }
        }
        return difference;
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

    public static int[][] strassenMultiplication(int[][] matrix1, int[][] matrix2)
    {
        int[][] multipliedMatrix = new int[matrix1.length][matrix1.length];
    
        if (matrix1.length <= 1 || matrix2.length <= 1)
        {
            multipliedMatrix[0][0] = matrix1[0][0] * matrix2[0][0];
        }
        // if (matrix1.length == 2 || matrix2.length == 2)
        // {
        //     multipliedMatrix[0][0] = (matrix1[0][0] * matrix2[0][0]) + (matrix1[0][1] + matrix2[1][0]);
        //     multipliedMatrix[0][1] = (matrix1[0][0] * matrix2[0][1]) + (matrix1[0][1] + matrix2[1][1]);
        //     multipliedMatrix[1][0] = (matrix1[1][0] * matrix2[0][0]) + (matrix1[1][1] + matrix2[1][0]);
        //     multipliedMatrix[1][1] = (matrix1[1][0] * matrix2[0][1]) + (matrix1[1][1] + matrix2[1][1]);
        // }
        else
        {
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
    
            int[][] P = strassenMultiplication(addMatrix(A0, A3), addMatrix(B0, B3));
            int[][] Q = strassenMultiplication(addMatrix(A2, A3), B0);
            int[][] R = strassenMultiplication(A0, subtractMatrix(B1, B3));
            int[][] S = strassenMultiplication(A3, subtractMatrix(B2, B0));
            int[][] T = strassenMultiplication(addMatrix(A0, A1), B3);
            int[][] U = strassenMultiplication(subtractMatrix(A2, A0), addMatrix(B0, B1));
            int[][] V = strassenMultiplication(subtractMatrix(A1, A3), addMatrix(B2, B3));
    
            int[][] C0 = addMatrix(subtractMatrix(S,T), addMatrix(P, V));
            int[][] C1 = addMatrix(R, T);
            int[][] C2 = addMatrix(Q, S);
            int[][] C3 = addMatrix(subtractMatrix(R,Q), addMatrix(P, U));
    
            multipliedMatrix = combineMatrix(C0, C1, C2, C3);
        }

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

    public static double averageClassical(int size)
    {
        double avg = 0;
        for (int i = 0; i < 1000; i++)
        {
            
            int[][] matrix = generateMatrix(size, 100);
            int[][] matrix2 = generateMatrix(size, 100);
            double avg2 = 0;
            for (int k = 0; k < 20; k++)
            {
                long initialTime = System.nanoTime();
                classicalMultiplication(matrix, matrix2);
                long endTime = System.nanoTime();
                long totalTime = endTime - initialTime;
                // System.out.println((double)totalTime/1000000.0);
                avg2 += ((double)totalTime/1_000_000_000.0);
            }
            avg2 = avg2/20;
            avg += avg2;
        }
        avg = avg/1000;
        return avg;
    }

    public static double averageDAC(int size)
    {
        double avg = 0;
        for (int i = 0; i < 1000; i++)
        {
            
            int[][] matrix = generateMatrix(size, 100);
            int[][] matrix2 = generateMatrix(size, 100);
            double avg2 = 0;
            for (int k = 0; k < 20; k++)
            {
                long initialTime = System.nanoTime();
                DACMultiplication(matrix, matrix2);
                long endTime = System.nanoTime();
                long totalTime = endTime - initialTime;
                // System.out.println((double)totalTime/1000000.0);
                avg2 += ((double)totalTime/1_000_000_000.0);
            }
            avg2 = avg2/20;
            avg += avg2;
        }
        avg = avg/1000;
        return avg;
    }

    public static double averageStrassen(int size)
    {
        double avg = 0;
        for (int i = 0; i < 1000; i++)
        {
            
            int[][] matrix = generateMatrix(size, 100);
            int[][] matrix2 = generateMatrix(size, 100);
            double avg2 = 0;
            for (int k = 0; k < 20; k++)
            {
                long initialTime = System.nanoTime();
                strassenMultiplication(matrix, matrix2);
                long endTime = System.nanoTime();
                long totalTime = endTime - initialTime;
                // System.out.println((double)totalTime/1000000.0);
                avg2 += ((double)totalTime/1_000_000_000.0);
            }
            avg2 = avg2/20.0;
            avg += avg2;
        }
        avg = avg/1000.0;
        return avg;
    }
    public static void main(String[] args)
    {
        for (int i = 2; i <= 1024; i *= 2)
        {
            System.out.println(i + " x " + i + " Matrix:");
            System.out.println("Average time for Classical Matrix Multiplication (in seconds): ");
            System.out.println(averageClassical(i));
            System.out.println("Average time for Divide and Conquer for size (in seconds): ");
            System.out.println(averageDAC(i));
            System.out.println("Average time for Strassen's Divide and Conquer for size (in seconds): ");
            System.out.println(averageStrassen(i));
            System.out.println();
        }
        // System.out.println(averageClassical(4));
        // System.out.println(averageDAC(4));
        // System.out.println(averageStrassen(4));
        // int[][] test = {{1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}};
        // int[][] test2 = {{1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}};
        // int[][] test = generateMatrix(64, 100);
        // int[][] test2 = generateMatrix(64, 100);
        // long initialTime = System.nanoTime();
        // classicalMultiplication(test, test2);
        // long endTime = System.nanoTime();
        // long time = endTime - initialTime;
        // System.out.println((double)time/1_000_000_000.0 + " seconds");
        
        // printMatrix(classicalMultiplication(test, test2));
        // printMatrix(test);
    }
}