package Java_Programs;

import java.util.Arrays;

public class ArrayMaxArrayMin {

	public static void main(String[] args) {

		int arr[] = { 3, 6, 611, 1, 400, 530, 0, 32, 44 };
		Arrays.sort(arr);
		System.out.println("Using sort method Minimum is " + arr[0]);
		System.out.println("Using sort method Maximumis " + arr[arr.length-1]);

		int max = arr[0];
		int min = arr[0];
		for (int i : arr) {
			if (i > max) {
				max = i;

			}
			if (i < min) {
				min = i;
			}
		}
		System.out.println("Max is " + max);
		System.out.println("Min is " + min);

	}
}
