public class Solution {
    public List < List < String >> solveNQueens(int n) {
        List < List < String >> res = new ArrayList < List < String >> ();
        int[] sol = new int[n];
        findSol(0, n, sol, res);
        return res;
    }

    public void findSol(int curRow, int n, int[] sol, List < List < String >> res) {
        if (curRow == n) {
            // we find a solution
            List < String > solution = generateSol(sol);
            res.add(solution);
            return;
        }
        for (int i = 0; i < n; i++) {
            sol[curRow] = i;
            if (check(sol, curRow)) {
                findSol(curRow + 1, n, sol, res);
            }
        }
        return;
    }

    public boolean check(int[] sol, int curRow) {
        int num = sol[curRow];
        for (int i = 0; i < curRow; i++) {
            if (sol[i] == num) return false;
            if (Math.abs(i - curRow) == Math.abs(sol[i] - sol[curRow])) return false;
        }
        return true;
    }

    public List < String > generateSol(int[] sol) {
        List < String > res = new ArrayList < String > ();
        for (int i = 0; i < sol.length; i++) {
            String line = "";
            for (int j = 0; j < sol.length; j++) {
                if (j == sol[i]) line += 'Q';
                else line += '.';
            }
            res.add(line);
        }
        return res;
    }
}
