/*
 * Copyright © 2023 Mirada Medical Ltd.
 * All Rights Reserved.
 */
package matrix;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Arrays;

/**
 * An arbitrary M x N matrix of doubles
 *
 * @author lottie.thomas
 */
@Getter
@EqualsAndHashCode
public final class Matrix {
   private final int rows;
   private final int columns;
   private final double[][] data;

   /**
    * Create a matrix of 0s with the given size
    *
    * @param rows    The number of rows
    * @param columns The number of columns
    */
   public Matrix(int rows, int columns) {
      this.rows = rows;
      this.columns = columns;
      this.data = new double[rows][columns];
   }

   /**
    * Create a matrix from a 2D array
    *
    * @param array The 2D array the matrix should be created from
    */
   public Matrix(double[][] array) {
      if (array.length <= 0 || array[0].length <= 0) {
         throw new IllegalArgumentException("Number of rows and columns must be greater than 0");
      }

      this.rows = array.length;
      this.columns = array[0].length;
      this.data = new double[this.rows][this.columns];
      for (int i = 0; i < this.rows; i++) {
         this.data[i] = Arrays.copyOf(array[i], array[i].length);
      }
   }

   /**
    * Create and return a matrix of the given size, filled with random values
    *
    * @param rows    The number of rows
    * @param columns The number of columns
    * @return A matrix with the given number of rows and columns, filled with random values
    */
   public static Matrix random(int rows, int columns) {
      if (rows <= 0 || columns <= 0) {
         throw new IllegalArgumentException("Number of rows and columns must be greater than 0");
      }

      Matrix a = new Matrix(rows, columns);
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < columns; j++) {
            a.data[i][j] = Math.random();
         }
      }
      return a;
   }

   /**
    * Create and return an identity matrix of the given size
    *
    * @param size The size of the matrix
    * @return An identity matrix of the given size
    */
   public static Matrix identity(int size) {
      if (size <= 0) {
         throw new IllegalArgumentException("Size must be greater than 0");
      }

      Matrix identity = new Matrix(size, size);
      for (int i = 0; i < size; i++) {
         identity.data[i][i] = 1;
      }
      return identity;
   }

   /**
    * Create and return a matrix which is the transpose of this matrix
    *
    * @return A new matrix which is the transpose of this matrix
    */
   public Matrix transpose() {
      Matrix result = new Matrix(this.columns, this.rows);
      for (int i = 0; i < this.rows; i++) {
         for (int j = 0; j < this.columns; j++) {
            result.data[j][i] = this.data[i][j];
         }
      }
      return result;
   }

   /**
    * Create and return a matrix which is the sum of this matrix
    * and the provided matrix
    *
    * @param matrixToAdd The matrix to be added to this matrix
    * @return A new matrix which is the sum of this matrix and
    * the provided matrix
    */
   public Matrix plus(Matrix matrixToAdd) {
      if (matrixToAdd.rows != this.rows || matrixToAdd.columns != this.columns) {
         throw new IllegalArgumentException("The dimensions of the matrices much match");
      }

      Matrix result = new Matrix(this.rows, this.columns);
      for (int i = 0; i < this.rows; i++) {
         for (int j = 0; j < this.columns; j++) {
            result.data[i][j] = this.data[i][j] + matrixToAdd.data[i][j];
         }
      }
      return result;
   }

   /**
    * Create and return a matrix which is the result of subtracting the
    * provided matrix from this matrix
    *
    * @param matrixToSubtract The matrix to subtract from this matrix
    * @return A new matrix which is the result of subtracting the provided
    * matrix from this matrix
    */
   public Matrix subtract(Matrix matrixToSubtract) {
      if (matrixToSubtract.rows != this.rows || matrixToSubtract.columns != this.columns) {
         throw new IllegalArgumentException("The dimensions of the matrices much match");
      }

      Matrix result = new Matrix(this.rows, this.columns);
      for (int i = 0; i < this.rows; i++) {
         for (int j = 0; j < this.columns; j++) {
            result.data[i][j] = this.data[i][j] - matrixToSubtract.data[i][j];
         }
      }
      return result;
   }

   /**
    * Create and return a matrix which is the result of multiplying this
    * matrix with the provided matrix using the dot product
    *
    * @param matrixToMultiplyBy The matrix to multiply this matrix by
    * @return A new matrix which is the result of multiplying this matrix
    * with the provided matrix using the dot product
    */
   public Matrix multiplyBy(Matrix matrixToMultiplyBy) {
      if (matrixToMultiplyBy.columns != this.rows) {
         throw new IllegalArgumentException("The number of rows in this matrix must equal the number of columns in the matrix to multiply by");
      }

      Matrix result = new Matrix(this.rows, matrixToMultiplyBy.columns);
      for (int i = 0; i < result.rows; i++) {
         for (int j = 0; j < result.columns; j++) {
            for (int k = 0; k < this.columns; k++) {
               result.data[i][j] += (this.data[i][k] * matrixToMultiplyBy.data[k][j]);
            }
         }
      }
      return result;
   }

   public double get(int i, int j) {
      return 0;
   }
}
