import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//updated to the more efficient O(log(steps)) solution! instead of O(steps)
//however, it takes more memory because I implemented it in Java instead of C++ :(
//but I already had a nice matrix class in Java! So... what can you do
//and multidimensional arrays in C++ were really grossing me out
public class p166e {

	private static class Matrix {
		private long[][] matrix;
		private static final long MOD = 1000000007;

		public Matrix(long[][] matrix) {
			this.matrix = matrix;
		}

		public Matrix pow(long n) {
			// base case, if n = 0, return identity matrix
			if (n == 0)
				return Matrix.identity(2);
			// even case x^(n/2) * x^(n/2)
			Matrix u = this.pow(n / 2);
			u = u.timesMod(u);
			// odd case x^(n-1) * x
			if (n % 2 == 1l)
				u = u.timesMod(this);
			return u;
		}

		public Matrix timesMod(Matrix matrixB) {
			if (this.columns() != matrixB.rows())
				throw new IllegalArgumentException("Mismatched dimensions for matrix multiplication");

			long[][] newMatrix = new long[this.rows()][matrixB.columns()];
			for (int i = 0; i < this.rows(); i++) {
				for (int j = 0; j < matrixB.columns(); j++) {
					long indexVal = 0;
					// each new matrix element is the dot product of row(A) . col(B)
					for (int k = 0; k < this.columns(); k++)
						indexVal += this.matrix[i][k] % Matrix.MOD * matrixB.matrix[k][j] % Matrix.MOD;
					newMatrix[i][j] = indexVal % Matrix.MOD;
				}
			}
			return new Matrix(newMatrix);
		}

		public static Matrix identity(int size) {
			long[][] id = new long[size][size];
			for (int i = 0; i < size; i++)
				id[i][i] = 1;
			return new Matrix(id);
		}

		public int rows() {
			return this.matrix.length;
		}

		public int columns() {
			return this.matrix[0].length;
		}

		public long[][] getArray() {
			return this.matrix;
		}

		@Override
		public String toString() {
			StringBuilder matrixOutput = new StringBuilder();
			for (long[] row : this.matrix) {
				for (long element : row)
					matrixOutput.append(element + " ");
				matrixOutput.append("\n");
			}
			return matrixOutput.toString();
		}
	}

	public static void main(String[] s) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Matrix first = new Matrix(new long[][] { { 0, 1 }, { 3, 2 } });
		Matrix init = new Matrix(new long[][] { { 1 }, { 0 } });
		Matrix ans = first.pow(n).timesMod(init);
		System.out.println(ans.getArray()[0][0]);
	}
}
