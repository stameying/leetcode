public class quicksort{
	public quicksort(){
		
	}

	public void sort(int[] nums){
		sort(nums,0,nums.length-1);
	}

	public void sort(int[] nums, int left, int right){
		if (left >= right) return;
		int i = left, j = right, pivot = nums[left];
		while ( i != j){
			while (j > i && nums[j] >= pivot) j--;
			while (i < j && nums[i] <= pivot) i++;
			if ( i < j){
				swap(nums,i,j);
			}
		}
		nums[left] = nums[i];
		nums[i] = pivot;
		sort(nums,left,i-1);
		sort(nums,i+1,right);
	}

	public void swap(int[] nums, int left, int right){
		int temp = nums[right];
		nums[right] = nums[left];
		nums[left] = temp;
	}

	public void print_array(int[] nums){
		System.out.println("The sorted array is:");
		for (int i = 0; i < nums.length; i++){
			System.out.print(nums[i]);
			if (i != nums.length - 1) System.out.print(" ");
		}
	}

	public static void main(String[] args){
		int[] input = {12,4,5,2,5,13,8,19,1,7};
		quicksort test = new quicksort();
		test.sort(input);
		test.print_array(input);
	}
	
}