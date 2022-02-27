import numpy as np

R = int(input("Number of Rows in matrix A :"))
C = int(input("Number of Columns in matrix A :"))
n = int(input("Enter the Number of Variables :"))
variable = ['a','b','c','d','e','f','g','h','i','j']
if(n > C):
    print("System Can't Have more variables than Number of Columns .")

matrix_a = [[float(input()) for x in range (C)] for y in range(R)]
matrix_b = [float(input()) for x in range(R)]
# print(matrix_a)

if(C == R and np.linalg.det(matrix_a) == 0):
    print("System Has Infinite Soultion or Inconsistent")

def transposeMatrix(matrix_a):
    row ,col = [], []
    for i in range(C):
        for j in range(R):
            row.append(matrix_a[j][i])
        col.append(row)
        row = []
    print(col)
    return col


def printOutput(matrix_final):
    output = {}
    for x in range(n):
        output[variable[x]] =  matrix_final[x]
    print(output)

def multiplymatrix(matrix_a,matrix_b):
    return np.matmul(matrix_a,matrix_b)
# m == n
if(C == R):
    inv_matrix_a = np.linalg.inv(matrix_a)
    matrix_mul = multiplymatrix(inv_matrix_a,matrix_b)
    printOutput(matrix_mul)


# m > n
if(R > C):
    transpose_matrix = transposeMatrix(matrix_a)
    # print(transposeMatrix)
    matrix_mul = multiplymatrix(transpose_matrix,matrix_a)
    matrix_det =  np.linalg.det(matrix_mul)
    matrix_inv = np.linalg.inv(matrix_mul)
    if(matrix_det != 0):
        print("Columns are Linearly Indpendent, Proceeding Forward.....")
    
    else:
        print("Coulmns are Linearly Depenedent,  Can't Find Solution")
        exit(0)
    # (A^T A)^-1  -->> PseudoInverse
    
    intermediate_matrix = multiplymatrix(transpose_matrix, matrix_b)

    matrix_sol = multiplymatrix(matrix_inv,intermediate_matrix)
    printOutput(matrix_sol)

# m < n

if(R < C):
    transpose_matrix = transposeMatrix(matrix_a)
    matrix_mul = multiplymatrix(matrix_a,transpose_matrix)
    matrix_det =  np.linalg.det(matrix_mul)
    matrix_inv = np.linalg.inv(matrix_mul)
    if(matrix_det != 0):
        print("Columns are Linearly Indpendent, Proceeding Forward.....")
    
    else:
        print("Coulmns are Linearly Depenedent,  Can't Find Solution")
        exit(0)
    
    # A^T(A^T.A)^-1.b

    intermediate_matrix = multiplymatrix(transpose_matrix,matrix_inv)
    matrix_sol = multiplymatrix(intermediate_matrix,matrix_b)

    printOutput(matrix_sol)







    





