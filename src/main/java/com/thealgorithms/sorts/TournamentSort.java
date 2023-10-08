package com.thealgorithms.sorts;

public class TournamentSort {

    private static int[] tournamentSort(int[] arr) {
        int n = arr.length;
        int[] tree = new int[2 * n];

        // We will build the segment tree for the given array
        buildTree(arr, tree, n);

        // Pairwise comparisons and exchanging the elements
        for (int i = n - 1; i > 0; i--) {
            int maxIndex = tree[1] > tree[2] ? 1 : 2;
            arr[i] = tree[maxIndex];

            updateTree(tree, (n + maxIndex) / 2);
        }

        arr[0] = tree[1]; // This will store remaining element in the root

        return arr;
    }

    /* Function to build the tournament tree */
    private static void buildTree(int[] arr, int[] tree, int n) {
        // loop to fill leaves of the tournament tree
        for (int i = 0; i < n; i++) {
            tree[n + i] = arr[i];
        }

        // Now we will build the tournament tree by calculating parents
        for (int i = n - 1; i > 0; i--) {
            tree[i] = Math.max(tree[2 * i], tree[2 * i + 1]);
        }
    }

    // this function will update the tournament tree after each iteration
    private static void updateTree(int[] tree, int index) {
        while (index > 0) {
            tree[index] = Math.max(tree[2 * index], tree[2 * index + 1]);
            index /= 2;
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 4, 7, 2, 1, 8, 5, 3, 6};

        System.out.println("Original Array: " + Arrays.toString(arr));

        int[] sortedArr = tournamentSort(arr);

        System.out.println("Sorted Array: " + Arrays.toString(sortedArr));
    }
}
