package com.study.notes.sort;

public class HeapSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        int n = nums.length - 1;

        for (int k = n / 2; k >= 1; k--) {
            sink(nums, k, n);
        }

        while (n > 1) {
            swap(nums, 1, n--);
            sink(nums, 1, n);
        }
    }


    /**
     * max heap
     * @param nums
     * @param k
     * @param n
     */
    private void sink(T[] nums, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;

            if (j < n && less(nums[j], nums[j + 1])) {
                j++;
            }

            if (!less(nums[k], nums[j])) {
                break;
            }

            swap(nums, k, j);

            k = j;
        }
    }
}
