package com.example.demo;

import com.example.demo.TestController;
import com.example.demo.entity.SftpAuthority;
import com.example.demo.service.sftp.UploadService;
import com.jcraft.jsch.JSchException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*接口MockMvcBuilder，提供一个唯一的build方法，用来构造MockMvc。主要有两个实现：StandaloneMockMvcBuilder和DefaultMockMvcBuilder，
分别对应两种测试方式，即独立安装和集成Web环境测试（
并不会集成真正的web环境，而是通过相应的Mock API进行模拟测试，无须启动服务器）。
MockMvcBuilders提供了对应的创建方法standaloneSetup方法和webAppContextSetup方法，在使用时直接调用即可。
*/

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = "com.secbro2")
//测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的
@WebAppConfiguration
class DemoApplicationTests {

    /*private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup(){
        //实例一
        mockMvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
       *//* //实例二
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();*//*
    }

    @Test
    public void testHello() throws Exception {
        *//*
         * 1、mockMvc.perform执行一个请求。
         * 2、MockMvcRequestBuilders.get("XXX")构造一个请求。
         * 3、ResultActions.param添加请求传值
         * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
         * 5、ResultActions.andExpect添加执行完成后的断言。
         * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情
         *   比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
         * 7、ResultActions.andReturn表示执行完成后返回相应的结果。
         *//*
        mockMvc.perform(MockMvcRequestBuilders
                .get("/hello")
                // 设置返回值类型为utf-8，否则默认为ISO-8859-1
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .param("name", "Tom"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello Tom!"))
                .andDo(MockMvcResultHandlers.print());

    }*/

    /*有这样一道智力题：“某商店规定：三个空汽水瓶可以换一瓶汽水。小张手上有十个空汽水瓶，她最多可以换多少瓶汽水喝？
    ”答案是5瓶，方法如下：先用9个空瓶子换3瓶汽水，喝掉3瓶满的，喝完以后4个空瓶子，用3个再换一瓶，喝掉这瓶满的，这时候剩2个空瓶子。
    然后你让老板先借给你一瓶汽水，喝掉这瓶满的，喝完以后用3个空瓶子换一瓶满的还给老板。
    如果小张手上有n个空汽水瓶，最多可以换多少瓶汽水喝？
     */
    public static void main(String[]  args){
        List<String> input = new ArrayList<String>();
        List<Integer> list = new ArrayList<Integer>();
        Scanner scanner = new Scanner(System.in);
        String sum = scanner.nextLine();
        int i=1;
        while(!sum.equals("0") && !sum.equals("") && scanner.hasNext() && i<=10)
        {
            input.add(sum);
            int num = Integer.parseInt(sum);
            if(num<1 || num>100){
                break;
            }
            list.add(jiSuan(num));
            sum = scanner.nextLine();
            i++;
        }
        for (int ansewr:list) {
            System.out.println(ansewr);
        }
    }
    public  static int jiSuan(int num){
        int quotient = 0;//商
        int remainder = 0;//余数
        int count = 0;//最终得到的数量
        /*瓶盖总量除以3，当商大于2，则与余数相加并继续除以3，直到商为2*/

        while(num>=2){
            if(num==2){
                num++;
            }
            quotient = num/3;
            remainder = num%3;
            count = count+quotient;
            num = quotient+remainder;
        }
        return count;
    }


    /*输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
    假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
    例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。*/
    @Test
    public TreeNode test(){
        int[] a = {1,2,4,7,3,5,6,8};
        int[] b = {4,7,2,1,5,3,8,6};
        TreeNode root = reConstructBinaryTree(a,b);
        System.out.println(root);
        return root;
    }

    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        TreeNode root = test(pre,0,pre.length-1,in,0,in.length-1);
        return root;
    }
    public TreeNode test(int [] pre,int preStart,int preEnd,int [] in,int inStart,int inEnd){
        TreeNode root = new TreeNode(pre[preStart]);//前序遍历中第一个元素为根节点；
        //从中序遍历中找到该根节点的位置
        for(int i=inStart;i<inEnd;i++){
            if(in[i] == pre[preStart]){
                root.left = test(pre,preStart+1,preStart+i-inStart,in,inStart,i-1);
                root.right = test(pre,preStart+1+i-inStart,preEnd,in,i+1,inEnd);
                break;
            }
        }
        return root;
    }

    /*
	输入一个正整数，输出正整数m，m>9,m的各位乘积等于输入的正整数。
	思路：1、将n分解成最小的因子组，若因子组中存在2位数字，则直接结束方法返回-1；若因子组中全为个位数字
	则将个位数字两两进行相乘，得到较大的各位数字。(方法弊端，获取到所有的最小因子后，较难判断该因子组的所有组合中最小的组合方法)
	      2、因为因子最大只能为9，所以直接从9开始进行遍历，如果可以整除就进行整除。
	由此可以获得每位数字较大的因子组，且同时因子组的数目较短。然后将该因子组从小到大进行排序拼接，则可得到最小的正整数m
	 */
    @Test
    public void  test1(){
        int firstNum = 13;
        StringBuffer result = new StringBuffer();
        if (firstNum <= 9) {
            System.out.println("1"+firstNum);
        }
        for(int i=9;i>1;i--){
            while(firstNum%i==0){
                firstNum =  firstNum/i;
                result.insert(0,i+"");
            }
        }
        if(firstNum!=1){
            System.out.println("-1");
        }else{
            System.out.println(result.toString());
        }
    }

    /**
     * 在vivo产线上，每位职工随着对手机加工流程认识的熟悉和经验的增加，日产量也会不断攀升。
     * 假设第一天量产1台，接下来2天(即第二、三天)每天量产2件，接下来3天(即第四、五、六天)每天量产3件 ... ...
     * 以此类推，请编程计算出第n天总共可以量产的手机数量。
     * */
    @Test
    public void test2(){
        int firstNum = 5;
        int day=1;//天数
        int num = 1;//日产量
        int num1 = 1;//当前日产量生产天数
        int sum = 0;
        while(day<=firstNum){
            for(int i=1;i<=num1;i++){
                sum = sum + num;
                day++;
                if(day>firstNum){
                    break;
                }
            }
            num++;
            num1++;
        }
        System.out.println(sum);
    }

    @Test
    public void  toUploadPage() throws JSchException {
        UploadService uploadService = new UploadService();
        SftpAuthority sftpAuthority = new SftpAuthority("root","120.79.95.178",22);
        sftpAuthority.setPassword("Root1234@");
        uploadService.createChanner(sftpAuthority);
        uploadService.uploadFile(sftpAuthority,"C:/Users/zjc/Desktop/包席菜单（黑白色调）.zip","/uploadFile");
        uploadService.closeChannel();
    }
}
