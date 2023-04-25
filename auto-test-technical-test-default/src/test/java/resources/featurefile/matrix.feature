@Regression


Feature: Matrix Operations
  As a developer
  I want to test the Matrix class
  So that I can ensure it works correctly

  Scenario: Create a matrix with random values
    Given I have a matrix of size 3x3
    When I create a matrix with random values
    Then the matrix should have values between 0 and 1

  Scenario: Create an identity matrix
    Given I have a matrix of size 4x4
    When I create an identity matrix
    Then the matrix should have 1s on the diagonal and 0s elsewhere

  Scenario: Transpose a matrix
    Given I have a matrix
    When I transpose the matrix
    Then the resulting matrix should be transposed

  Scenario: Add two matrices
    Given I have two matrices of the same size
    When I add the matrices
    Then the resulting matrix should be the sum of the two matrices

