const examples = [
    {
        name: "2x2 Unique Solution",
        description: "A 2x2 system with a unique solution. Equations: x + 2y = 5, 3x + 4y = 6.",
        R: 2,
        C: 2,
        n: 2,
        A: [[1, 2], [3, 4]],
        b: [5, 6],
        expected: "System has Unique Solution: a = -4.0, b = 4.5"
    },
    {
        name: "Inconsistent System",
        description: "A 2x2 system with no solution. Equations: x + y = 1, x + y = 2.",
        R: 2,
        C: 2,
        n: 2,
        A: [[1, 1], [1, 1]],
        b: [1, 2],
        expected: "System is Inconsistent"
    },
    {
        name: "Infinite Solutions",
        description: "A 2x2 system with infinite solutions. Equations: x + y = 1, 2x + 2y = 2.",
        R: 2,
        C: 2,
        n: 2,
        A: [[1, 1], [2, 2]],
        b: [1, 2],
        expected: "System is Consistent and Have Infinite Solution"
    },
    {
        name: "Overdetermined (Least Squares)",
        description: "3 equations, 2 variables. Approximate solution.",
        R: 3,
        C: 2,
        n: 2,
        A: [[1, 1], [1, 2], [1, 3]],
        b: [1, 2, 3],
        expected: "Approximate solution: a ≈ 0.0, b ≈ 1.0"
    },
    {
        name: "Underdetermined",
        description: "2 equations, 3 variables. Infinite solutions, but we solve for least squares.",
        R: 2,
        C: 3,
        n: 3,
        A: [[1, 2, 3], [4, 5, 6]],
        b: [1, 2],
        expected: "Solution: a ≈ -0.5, b ≈ 0.0, c ≈ 0.5"
    }
];

document.addEventListener('DOMContentLoaded', function() {
    const rInput = document.getElementById('R');
    const cInput = document.getElementById('C');
    const nInput = document.getElementById('n');
    const matrixAContainer = document.getElementById('matrixA');
    const vectorBContainer = document.getElementById('vectorB');
    const examplesContainer = document.getElementById('examples');

    function generateMatrixA() {
        const R = parseInt(rInput.value) || 0;
        const C = parseInt(cInput.value) || 0;
        matrixAContainer.innerHTML = '';
        for (let i = 0; i < R; i++) {
            const row = document.createElement('div');
            row.className = 'row';
            for (let j = 0; j < C; j++) {
                const input = document.createElement('input');
                input.type = 'text';
                input.name = `a_${i}_${j}`;
                input.placeholder = `a[${i+1}][${j+1}]`;
                input.addEventListener('input', function() {
                    if (this.value.trim()) {
                        this.classList.add('filled');
                    } else {
                        this.classList.remove('filled');
                    }
                });
                row.appendChild(input);
            }
            matrixAContainer.appendChild(row);
        }
    }

    function generateVectorB() {
        const R = parseInt(rInput.value) || 0;
        vectorBContainer.innerHTML = '';
        for (let i = 0; i < R; i++) {
            const input = document.createElement('input');
            input.type = 'text';
            input.name = `b_${i}`;
            input.placeholder = `b[${i+1}]`;
            input.addEventListener('input', function() {
                if (this.value.trim()) {
                    this.classList.add('filled');
                } else {
                    this.classList.remove('filled');
                }
            });
            vectorBContainer.appendChild(input);
        }
    }

    function loadExample(example) {
        rInput.value = example.R;
        cInput.value = example.C;
        nInput.value = example.n;
        generateMatrixA();
        generateVectorB();
        // Fill the values
        for (let i = 0; i < example.R; i++) {
            for (let j = 0; j < example.C; j++) {
                const input = document.querySelector(`input[name="a_${i}_${j}"]`);
                if (input) input.value = example.A[i][j];
            }
            const bInput = document.querySelector(`input[name="b_${i}"]`);
            if (bInput) bInput.value = example.b[i];
        }
    }

    // Generate examples
    examples.forEach(ex => {
        const div = document.createElement('div');
        div.className = 'example';
        div.innerHTML = `
            <h4>${ex.name}</h4>
            <p>${ex.description}</p>
            <p><strong>Expected Output:</strong> ${ex.expected}</p>
            <button type="button">Load Example</button>
        `;
        const button = div.querySelector('button');
        button.addEventListener('click', () => loadExample(ex));
        examplesContainer.appendChild(div);
    });

    rInput.addEventListener('input', function() {
        generateMatrixA();
        generateVectorB();
    });

    cInput.addEventListener('input', generateMatrixA);

    // Initial generation if values are set
    generateMatrixA();
    generateVectorB();
});