from django.shortcuts import render
import numpy as np

def index(request):
    if request.method == 'POST':
        R = int(request.POST['R'])
        C = int(request.POST['C'])
        n = int(request.POST['n'])
        
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
        
        result = solve_linear_equation(R, C, n, matrix_a, matrix_b)
        return render(request, 'equations/result.html', {'result': result})
    return render(request, 'equations/index.html')

def solve_linear_equation(R, C, n, matrix_a, matrix_b):
    variable = ['a','b','c','d','e','f','g','h','i','j']
    result = []
    
    if n > C:
        result.append({'type': 'message', 'content': "System Can't Have more variables than Number of Columns."})
        return result
    
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
                return result
        result.append({'type': 'message', 'content': "System is Consistent and Have Infinite Solution"})
        return result
        
    if C == R and np.linalg.det(matrix_a) != 0:
        inv_matrix_a = np.linalg.inv(matrix_a)
        matrix_mul = multiplymatrix(inv_matrix_a, matrix_b)
        result.append({'type': 'message', 'content': "System has Unique Solution"})
        solution = printOutput(matrix_mul)
        result.append({'type': 'solution', 'content': solution})
        return result
    
    # m > n
    if R > C:
        transpose_matrix = transposeMatrix(matrix_a)
        matrix_mul = multiplymatrix(transpose_matrix, matrix_a)
        matrix_det = np.linalg.det(matrix_mul)
        matrix_inv = np.linalg.inv(matrix_mul)
        if matrix_det != 0:
            result.append({'type': 'message', 'content': "Columns are Linearly Independent, Proceeding Forward....."})
        else:
            result.append({'type': 'message', 'content': "Columns are Linearly Dependent, Can't Find Solution"})
            return result
        
        intermediate_matrix = multiplymatrix(transpose_matrix, matrix_b)
        matrix_sol = multiplymatrix(matrix_inv, intermediate_matrix)
        solution = printOutput(matrix_sol)
        result.append({'type': 'solution', 'content': solution})
        return result
    
    # m < n
    if R < C:
        transpose_matrix = transposeMatrix(matrix_a)
        matrix_mul = multiplymatrix(matrix_a, transpose_matrix)
        matrix_det = np.linalg.det(matrix_mul)
        matrix_inv = np.linalg.inv(matrix_mul)
        if matrix_det != 0:
            result.append({'type': 'message', 'content': "Columns are Linearly Independent, Proceeding Forward....."})
        else:
            result.append({'type': 'message', 'content': "Columns are Linearly Dependent, Can't Find Solution"})
            return result
        
        intermediate_matrix = multiplymatrix(transpose_matrix, matrix_inv)
        matrix_sol = multiplymatrix(intermediate_matrix, matrix_b)
        solution = printOutput(matrix_sol)
        result.append({'type': 'solution', 'content': solution})
        return result
    
    return result