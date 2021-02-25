package dwl.solution4;

/**
 * @author wenlong.ding
 * @date 2021/2/20 15:17
 */
public class Solution4 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            return 0D;
        }
        int len = nums1.length + nums2.length;
        int idx = len / 2, n1 = 0, n2 = 0, pre = 0, c = 0;
        while (true) {
            int curr;
            if (n2 >= nums2.length) {
                curr = nums1[n1];
                n1++;
            } else if (n1 >= nums1.length) {
                curr = nums2[n2];
                n2++;
            } else if (nums1[n1] < nums2[n2]) {
                curr = nums1[n1];
                n1++;
            } else {
                curr = nums2[n2];
                n2++;
            }
            if (c ++ == idx) {
                return len % 2 == 0 ? (pre + curr) / 2.0 : curr;
            }
            pre = curr;
        }
    }

    public static void main(String[] args) {
        double medianSortedArrays = new Solution4().findMedianSortedArrays(new int[]{}, new int[]{});
        System.out.println(medianSortedArrays);
    }

}
