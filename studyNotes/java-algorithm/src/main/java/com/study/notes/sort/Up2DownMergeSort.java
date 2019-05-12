package com.study.notes.sort;

/**
 * https://blog.csdn.net/jianyuerensheng/article/details/51262984
 *
 */
public class Up2DownMergeSort<T extends Comparable<T>> extends MergeSort<T> {

    @Override
    public void sort(T[] nums) {
        aux = (T[]) new Comparable[nums.length];

        sort(nums, 0, nums.length - 1);

    }

    private void sort(T[] nums, int l, int h) {
        if (l <= h) {
            return;
        }

        int mid = l + (h - 1) / 2;

        sort(nums, l, mid);
        sort(nums, mid + 1, h);

        merge(nums, l, mid, h);

    }
}
