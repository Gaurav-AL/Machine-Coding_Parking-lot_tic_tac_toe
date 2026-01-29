from django.shortcuts import render
import numpy as np

def index(request):
    if request.method == 'POST':
        R = int(request.POST['R'])
        C = int(request.POST['C'])
        n = int(request.POST['n'])
        method = request.POST.get('method', 'auto')
        
        matrix_a = []
        for i in range(R):
            row = []
            for j in range(C):
                key = f'a_{i}_{j}'
                row.append(float(request.POST[key]))
            matrix_a.append(row)
        
        matrix_b = []
        for i in range(R):
            key = f'b_{i}'
            matrix_b.append(float(request.POST[key]))
        
        output = solve_linear_equation(R, C, n, matrix_a, matrix_b, method)
        return render(request, 'equations/result.html', {'steps': output['steps'], 'result': output['result']})
    return render(request, 'equations/index.html')

def solve_linear_equation(R, C, n, matrix_a, matrix_b, method='auto'):
    variable = ['a','b','c','d','e','f','g','h','i','j']
    result = []
    steps = []
    
    if n > C:
        result.append({'type': 'message', 'content': "System Can't Have more variables than Number of Columns."})
        return {'steps': steps, 'result': result}
    
    if method == 'gaussian':
        return gaussian_elimination(matrix_a, matrix_b, variable, n)
    elif method == 'lu':
        return lu_decomposition(matrix_a, matrix_b, variable, n)
    else:
        # auto method
        return auto_solve(R, C, n, matrix_a, matrix_b, variable)

def auto_solve(R, C, n, matrix_a, matrix_b, variable):
    result = []
    steps = []
    
    def transposeMatrix(matrix_a):
        row, col = [], []
        for i in range(C):
            for j in range(R):
                row.append(matrix_a[j][i])
            col.append(row)
            row = []
        return col
    
    def printOutput(matrix_final):
        output = {}
        for x in range(n):
            output[variable[x]] = matrix_final[x]
        return output
    
    def multiplymatrix(matrix_a, matrix_b):
        return np.matmul(matrix_a, matrix_b)
    
    def repaceElements(matrix_a, matrix_b, col):
        new_matrix, row = [], []
        for i in range(len(matrix_a)):
            for j in range(len(matrix_a[0])):
                if j == col:
                    row.append(matrix_b[i])
                else:
                    row.append(matrix_a[i][j])
            new_matrix.append(row)
            row = []
        return new_matrix
    
    # m == n
    if C == R and np.linalg.det(matrix_a) == 0:
        for i in range(C):
            if np.linalg.det(repaceElements(matrix_a, matrix_b, i)) != 0:
                result.append({'type': 'message', 'content': "System is Inconsistent"})
                return {'steps': steps, 'result': result}
        result.append({'type': 'message', 'content': "System is Consistent and Have Infinite Solution"})
        return {'steps': steps, 'result': result}
        
    if C == R and np.linalg.det(matrix_a) != 0:
        steps.append({'content': 'Using matrix inversion method for square system.', 'explanation': 'For square matrices with non-zero determinant, we can find the inverse and solve x = A⁻¹ b.'})
        inv_matrix_a = np.linalg.inv(matrix_a)
        steps.append({'content': 'Computed inverse of matrix A.', 'explanation': 'A⁻¹ is calculated numerically to allow multiplication.'})
        matrix_mul = multiplymatrix(inv_matrix_a, matrix_b)
        steps.append({'content': 'Multiplied inverse A with vector b to get solution x.', 'explanation': 'x = A⁻¹ b gives the unique solution.'})
        result.append({'type': 'message', 'content': "System has Unique Solution"})
        solution = printOutput(matrix_mul)
        result.append({'type': 'solution', 'content': solution})
        return {'steps': steps, 'result': result}
    
    # m > n
    if R > C:
        steps.append({'content': 'Overdetermined system (more equations than variables), using least squares method.', 'explanation': 'Least squares finds the best approximation when no exact solution exists.'})
        transpose_matrix = transposeMatrix(matrix_a)
        steps.append({'content': 'Transposed matrix A to get A^T.', 'explanation': 'Transpose is needed for the normal equations.'})
        matrix_mul = multiplymatrix(transpose_matrix, matrix_a)
        steps.append({'content': 'Computed A^T * A.', 'explanation': 'This forms the normal matrix, which is square and invertible if columns are independent.'})
        matrix_det = np.linalg.det(matrix_mul)
        matrix_inv = np.linalg.inv(matrix_mul)
        steps.append({'content': 'Inverted A^T * A.', 'explanation': 'Inverting this matrix allows solving the system.'})
        if matrix_det != 0:
            result.append({'type': 'message', 'content': "Columns are Linearly Independent, Proceeding Forward....."})
        else:
            result.append({'type': 'message', 'content': "Columns are Linearly Dependent, Can't Find Solution"})
            return {'steps': steps, 'result': result}
        
        intermediate_matrix = multiplymatrix(transpose_matrix, matrix_b)
        steps.append({'content': 'Computed A^T * b.', 'explanation': 'This is the right-hand side for the normal equations.'})
        matrix_sol = multiplymatrix(matrix_inv, intermediate_matrix)
        steps.append({'content': 'Solved for x using (A^T A)^-1 * A^T b.', 'explanation': 'This gives the least squares solution that minimizes the error.'})
        solution = printOutput(matrix_sol)
        result.append({'type': 'solution', 'content': solution})
        return {'steps': steps, 'result': result}
    
    # m < n
    if R < C:
        steps.append({'content': 'Underdetermined system (fewer equations than variables), using least squares approximation.', 'explanation': 'We find a solution that minimizes the norm.'})
        transpose_matrix = transposeMatrix(matrix_a)
        steps.append({'content': 'Transposed matrix A.', 'explanation': 'Transpose for pseudoinverse calculation.'})
        matrix_mul = multiplymatrix(matrix_a, transpose_matrix)
        steps.append({'content': 'Computed A * A^T.', 'explanation': 'This is used to find the pseudoinverse.'})
        matrix_det = np.linalg.det(matrix_mul)
        matrix_inv = np.linalg.inv(matrix_mul)
        steps.append({'content': 'Inverted A * A^T.', 'explanation': 'Inverting this square matrix.'})
        if matrix_det != 0:
            result.append({'type': 'message', 'content': "Columns are Linearly Independent, Proceeding Forward....."})
        else:
            result.append({'type': 'message', 'content': "Columns are Linearly Dependent, Can't Find Solution"})
            return {'steps': steps, 'result': result}
        
        intermediate_matrix = multiplymatrix(transpose_matrix, matrix_inv)
        steps.append({'content': 'Computed A^T * (A A^T)^-1.', 'explanation': 'This is part of the pseudoinverse.'})
        matrix_sol = multiplymatrix(intermediate_matrix, matrix_b)
        steps.append({'content': 'Solved for x using A^T (A A^T)^-1 b.', 'explanation': 'This gives one possible solution.'})
        solution = printOutput(matrix_sol)
        result.append({'type': 'solution', 'content': solution})
        return {'steps': steps, 'result': result}
    
    return {'steps': steps, 'result': result}

def gaussian_elimination(A, b, variable, n):
    steps = []
    result = []
    import numpy as np
    # Assuming square matrix
    if len(A) != len(A[0]) or len(A) != len(b):
        result.append({'type': 'message', 'content': "Gaussian Elimination requires square matrix."})
        return {'steps': steps, 'result': result}
    
    steps.append({'content': 'Starting Gaussian Elimination.', 'explanation': 'Gaussian elimination transforms the system into row echelon form for easy solving.'})
    # Create augmented matrix
    aug = [row[:] + [b[i]] for i, row in enumerate(A)]
    steps.append({'content': f'Augmented matrix: {aug}', 'explanation': 'Combine A and b into [A|b] for simultaneous operations.'})
    
    # Forward elimination
    for i in range(len(aug)):
        # Find pivot
        if aug[i][i] == 0:
            for j in range(i+1, len(aug)):
                if aug[j][i] != 0:
                    aug[i], aug[j] = aug[j], aug[i]
                    steps.append({'content': f'Swapped row {i} and {j}.', 'explanation': 'Ensure pivot is non-zero for elimination.'})
                    break
        if aug[i][i] == 0:
            result.append({'type': 'message', 'content': "Matrix is singular, cannot proceed."})
            return {'steps': steps, 'result': result}
        
        # Eliminate
        for j in range(i+1, len(aug)):
            factor = aug[j][i] / aug[i][i]
            for k in range(len(aug[j])):
                aug[j][k] -= factor * aug[i][k]
            steps.append({'content': f'Eliminated row {j} using row {i}, factor {factor:.3f}.', 'explanation': 'Subtract multiple of row i from row j to make element below pivot zero.'})
    
    # Back substitution
    x = [0] * len(aug)
    for i in range(len(aug)-1, -1, -1):
        x[i] = aug[i][-1]
        for j in range(i+1, len(aug)):
            x[i] -= aug[i][j] * x[j]
        x[i] /= aug[i][i]
        steps.append({'content': f'Back substituted: x[{i}] = {x[i]:.3f}', 'explanation': 'Solve for variable from bottom to top.'})
    
    solution = {variable[i]: x[i] for i in range(n)}
    result.append({'type': 'message', 'content': "Solution using Gaussian Elimination"})
    result.append({'type': 'solution', 'content': solution})
    return {'steps': steps, 'result': result}

def lu_decomposition(A, b, variable, n):
    steps = []
    result = []
    import numpy as np
    from scipy.linalg import lu
    # Assuming square
    if len(A) != len(A[0]):
        result.append({'type': 'message', 'content': "LU Decomposition requires square matrix."})
        return {'steps': steps, 'result': result}
    
    steps.append({'content': 'Starting LU Decomposition.', 'explanation': 'Decompose A into Lower (L) and Upper (U) triangular matrices for efficient solving.'})
    P, L, U = lu(A)
    steps.append({'content': 'Decomposed A into P, L, U.', 'explanation': 'P is permutation, L lower triangular, U upper triangular such that P A = L U.'})
    
    # Solve Ly = Pb
    Pb = np.dot(P, b)
    y = np.linalg.solve(L, Pb)
    steps.append({'content': 'Solved L y = P b.', 'explanation': 'Forward substitution to find y.'})
    
    # Solve Ux = y
    x = np.linalg.solve(U, y)
    steps.append({'content': 'Solved U x = y.', 'explanation': 'Back substitution to find x.'})
    
    solution = {variable[i]: x[i] for i in range(n)}
    result.append({'type': 'message', 'content': "Solution using LU Decomposition"})
    result.append({'type': 'solution', 'content': solution})
    return {'steps': steps, 'result': result}