public class Main {
    public static void main(String[] args) {
        RBTree<Integer> rbt = new RBTree<>();
        int[] nums = {26, 41, 17, 14, 21, 30, 47, 10, 16, 19, 23, 28, 38, 7, 12, 15, 20, 35, 39, 3};
        // Inserting nodes
        for(int i = 0; i < nums.length; i++){
            rbt.insert(nums[i]);
        }
        
        System.out.println(rbt.delete(3).data);
        // for(int i = 0; i < nums.length; i++){
        //     Node<Integer> result = rbt.search(nums[i]);
        //     if(result.data == nums[i]){
        //         System.out.println(rbt.nodeInfo(result));
        //     }else{
        //         System.out.println(result.data + " does not exists in the RBTree");
        //     }
        // }

        
    }
}