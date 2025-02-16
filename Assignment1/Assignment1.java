package Assignment1;

import java.util.Arrays;

public class Assignment1 {

    public static void main(String[] args) {
        int size = 30;
        int[] nums1 = createArray(size);
        int[] nums2 = nums1.clone();

        System.out.println("\nSELECTION SORT");
        System.out.println("Array Before Sorting: " + Arrays.toString(nums1));
        long startTime = System.nanoTime();
        selectionSort(nums1);
        long endTime = System.nanoTime();
        System.out.println("Array After Sorting: " + Arrays.toString(nums1));
        System.out.println("Merge Sort Time: " + (endTime - startTime) / 1e6 + " ms");
        System.out.println("Time complexity: O(n^2)\n");

        System.out.println("\nMERGE SORT");
        System.out.println("Array Before Sorting: " + Arrays.toString(nums2));
        startTime = System.nanoTime();
        mergeSort(nums2, 0, nums2.length - 1);
        endTime = System.nanoTime();
        System.out.println("Array After Sorting: " + Arrays.toString(nums2));
        System.out.println("Merge Sort Time: " + (endTime - startTime) / 1e6 + " ms");
        System.out.println("Time complexity: O(n(log n))\n");
    }

    private static void selectionSort(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }
    }

    private static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private static void merge(int[] nums, int left, int mid, int right) {
        int length1 = mid - left + 1;
        int length2 = right - mid;
        
        int[] leftHalf = new int[length1];
        for (int i = 0; i < length1; i++) {
            leftHalf[i] = nums[left + i];
        }

        int[] rightHalf = new int[length2];
        for (int i = 0; i < length2; i++) {
            rightHalf[i] = nums[mid + 1 + i];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < length1 && j < length2) {
            if (leftHalf[i] <= rightHalf[j]) {
                nums[k] = leftHalf[i++];
            } else {
                nums[k] = rightHalf[j++];
            }
            k++;
        }

        while (i < length1) {
            nums[k++] = leftHalf[i++];
        }

        while (j < length2) {
            nums[k++] = rightHalf[j++];
        }
    }

    private static int[] createArray(int size) {
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = size - 1 - i;
        }
        return nums;
    }
}

