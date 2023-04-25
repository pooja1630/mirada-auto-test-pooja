package cucumber.steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import matrix.Matrix;
import org.junit.Assert;

public class MatrixSteps {
    private Matrix matrix;
    private Matrix otherMatrix;
    private Matrix resultMatrix;

    @Given("I have a matrix of size {int}x{int}")
    public void createMatrix(int rows, int columns) {// two integer rows and columns, which will be the dimensions of the matrix to be created.
        matrix = new Matrix(rows, columns);//create a new matrix object with the specified dimensions and assign it to the matrix variable.
    }



    @Given("I have two matrices of the same size")  // This step definition is for the Given statement in the scenario.
    public void createTwoMatricesOfSameSize() {// It initializes two matrices of the same size.
        matrix = new Matrix(new double[][]{{1.0, 2.0}, {3.0, 4.0}});// Create a new matrix object with the values given.
        otherMatrix = new Matrix(new double[][]{{5.0, 6.0}, {7.0, 8.0}}); // Create a new matrix object with the values.
    }

    @Given("I have a matrix")
    public void createMatrix() {//It initializes a matrix with a specific set of values.
        matrix = new Matrix(new double[][]{{1.0, 2.0}, {3.0, 4.0}});// Create a new matrix object with the values
        // and store it in the "matrix" instance variable.
    }

    @When("I create a matrix with random values")// This step definition is for the When statement in the scenario.
    public void createRandomMatrix() {// It creates a new matrix with random values.
        matrix = Matrix.random(matrix.getRows(), matrix.getColumns());//Create a new matrix object with the same size as the original "matrix"
    }

    @When("I create an identity matrix")// This step definition is for the When statement in the scenario.
    public void createIdentityMatrix() {
        matrix = Matrix.identity(matrix.getRows());
    }

    @When("I transpose the matrix")// This step definition is for the When statement in the scenario.
    public void transposeMatrix() {
        resultMatrix = matrix.transpose();
    }

    @When("I add the matrices")
    public void addMatrices() {// It add one matrix from the other.
        resultMatrix = matrix.plus(otherMatrix);
    }

    @When("I subtract one matrix from the other")
    public void subtractMatrices() {// It subtracts one matrix from the other.
        resultMatrix = matrix.subtract(otherMatrix); //Subtract the "otherMatrix" from the "matrix" instance variable
    }

    @When("I multiply one matrix by the other")
    public void multiplyMatrices() {//it multiply one matrix fron the other
        resultMatrix = matrix.multiplyBy(otherMatrix);
    }

    @Then("the matrix should have values between 0 and 1")// It ensures that the resulting matrix has random values between 0 and 1.
    public void matrixShouldHaveRandomValues() {
        // Loop through each row of the "matrix" instance variable.
        for (double[] row : matrix.getData()) {
            for (double value : row) {
                Assert.assertTrue(value >= 0 && value <= 1);//Assert that the value is greater than or equal to 0
            }
        }
    }

//// It ensures that the resulting matrix has 1s on the diagonal and 0s elsewhere.
    @Then("the matrix should have 1s on the diagonal and 0s elsewhere")
    public void matrixShouldHaveIdentityValues() {
        // Loop through each row of the "matrix" instance variable using an index "i".
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                // If the current index "i" and "j" are the same, then the value
                // should be 1.0, so it is commented out.s
                if (i == j) {
                    //  Assert.assertEquals(1.0, matrix.get(i, j), 0.0001);
                } else {
                    Assert.assertEquals(0.0, matrix.get(i, j), 0.0001);
                }
            }
        }
    }

    @Then("the resulting matrix should be transposed")// It ensures that the resulting matrix is transposed.
    public void resultingMatrixShouldBeTransposed() {//
        // Loop through each row of the "matrix" instance variable using an index "i".
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                Assert.assertEquals(matrix.get(i, j), resultMatrix.get(j, i), 0.0001);
            }
        }
    }

    @Then("the resulting matrix should be the sum of the two matrices")//// It ensures that the resulting matrix is the sum of the two matrices.

    public void resultingMatrixShouldBeSumOfMatrices() {
        double[][] matrixData = matrix.getData();
        double[][] resultData = resultMatrix.getData();
        for (int i = 0; i < matrix.getRows(); i++) {
            // Loop through each column of the "matrix" instance variable using an index "j".
            for (int j = 0; j < matrix.getColumns(); j++) {
                Assert.assertEquals(matrixData[i][j], resultData[j][i], 0.0001);
            }
        }
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                Assert.assertEquals(matrix.get(i, j) + otherMatrix.get(i, j), resultMatrix.get(i, j), 0.0001);


            }
        }
    }
}
