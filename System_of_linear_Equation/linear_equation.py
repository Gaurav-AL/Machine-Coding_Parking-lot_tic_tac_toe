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

def repaceElements(matrix_a,matrix_b,col):
    new_matrix,row = [],[]
    for i in range(len(matrix_a)):
        for j in range(len(matrix_a[0])):
            if(j == col):
                row.append(matrix_b[i])
            else:
                row.append(matrix_a[i][j])
        new_matrix.append(row)
        row = []
    return new_matrix

# m == n
if(C == R and np.linalg.det(matrix_a) == 0):
    for i in range(C):
        if(np.linalg.det(repaceElements(matrix_a,matrix_b,i)) != 0):
            print("System is Inconsistent")
            exit(0)
    print("System is Consistent and Have Infinite Solution")

        
if(C == R and np.linalg.det(matrix_a) != 0):
    inv_matrix_a = np.linalg.inv(matrix_a)
    matrix_mul = multiplymatrix(inv_matrix_a,matrix_b)
    print("System has Unique Solution :")
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







    





# def getMinor(matrix,r,c):
#     row,col = [],[]
#     for i in range(len(matrix)):
#         for j in range(len(matrix[0])):
#             if(i == r or j == c):
#                 continue
#             else:
#                 row.append(matrix[i][j])
#         if(len(row) != 0):
#             col.append(row)
#             row = []
#     return col


# def get2DMatrixDeterminant(matrix):
#     if(len(matrix) == 2):
#         return (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0])
#     else:
#         return "Not a 2D Matrix"

# def getDeterminant(matrix):
#     count = 1
#     sum = 0
#     for i in range(len(matrix)):
#         for j in range(len(matrix[0])):
#             twodmatrix = getMinor(matrix,i,j) #for 3D matrix
#             minor_det = get2DMatrixDeterminant(twodmatrix)
#             print(twodmatrix,minor_det)
#             if(count % 2 == 0):
#                 sum -= (matrix[i][j] * minor_det)
#             else:
#                 sum += (matrix[i][j] * minor_det)
#         break
#     return sum

# print(getDeterminant(matrix_a), np.linalg.det(matrix_a))