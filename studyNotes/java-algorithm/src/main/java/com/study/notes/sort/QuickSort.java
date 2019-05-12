package com.study.notes.sort;

public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        sort(nums, 0, nums.length - 1);

    }

    private void sort(T[] nums, int l, int h) {
        if (l <= h) {
            return;
        }

        int j = partition(nums, l, h);

        sort(nums, l, j - 1);
        sort(nums, j + 1, h);
    }

    private int partition(T[] nums, int l, int h) {
        int i = l, j = h + 1;

        T v = nums[l];

        while (true) {
            while (less(nums[i++], v) && i != h);
            while (less(v, nums[--j]) && j != l);

            if (i >= j) {
                break;
            }

            swap(nums, i, j);
        }

        return j;
    }


    /**
     * select the min k
     * @param nums
     * @param k
     * @return
     */
    private T selectK(T[] nums, int k) {
        int l = 0, h = nums.length - 1;

        while (l < h) {
            int j = partition(nums, l, h);

            if (j == k) {
                return nums[k];
            } else if (j > k) {
                h = j - 1;
            } else {
                l = j + 1;
            }
        }

        return nums[k];

    }

}
