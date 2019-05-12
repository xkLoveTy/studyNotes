package com.study.notes.sort;

public abstract class MergeSort<T extends Comparable<T>> extends Sort<T> {

    protected T[] aux;

    public void merge(T[] nums, int l, int m, int h) {
        int i = l, j = m + 1;

        for (int k = l; k <= h; k++) {
            aux[k] = nums[k];
        }

        for (int k = l; k <= h; k++) {
            if (i > m) {
                nums[k] = aux[j++];
            } else if (j > h) {
                nums[k] = aux[i++];
            } else if (aux[i].compareTo(aux[j]) <= 0) {
                nums[k] = nums[i++];
            } else {
                nums[k] = aux[j++];
            }
        }
    }
}
