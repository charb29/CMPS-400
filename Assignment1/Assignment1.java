package Assignment1;

import java.util.Arrays;

public class Assignment1 {

    public static void main(String[] args) {
        int[] nums = {64, 25, 12, 22, 11, 90, 45, 78, 33, 2};
        selectionSort(nums);
    }

    private static void selectionSort(int[] nums) {
        System.out.println("\nArray before sorting: " + Arrays.toString(nums));
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

        System.out.println("Array after sorting: " + Arrays.toString(nums) + "\n");
    }
}

