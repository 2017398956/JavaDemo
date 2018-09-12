package personal.nfl.java.demo.asm.account;

import personal.nfl.java.demo.asm.util.SecurityChecker;

public class Account {

    public Account(){
        System.out.println("Account");
    }

//    public int operation() {
//        System.out.println("Account.operation( )");
//        System.out.println("Account.operation2( )");
//        return 3;
//    }

    public void operation() {
        System.out.println("Account.operation1( )");
        System.out.println("Account.operation3( )");
//        if(SecurityChecker.checkSecurity()){
//            System.out.println("chenggong");
//        }

//        if (SecurityChecker.checkSecurity()) {
//            System.out.println("Hello out 1");
//        }else {
//            System.out.println("Hello out 2");
//            System.out.println("Account.operation( )");
//            System.out.println("Account.operation2( )");
//            System.out.println("Hello out 4");
//        }
//        SecurityChecker.checkSecurity();
    }

//    public int operation(){
//        if(SecurityChecker.checkSecurity()){
//            System.out.println("Account.operation( )");
//            System.out.println("Account.operation2( )");
//            return 1 ;
//        }
//        return 2 ;
//    }

    public Account getValue(){
        return new Account() ;
    }

    private final void willBeDeletedMethod(){
        System.out.println("这个方法将会被 asm 删除");
    }

    private final void willBeClearedMethod(){
        System.out.println("这个方法将会被 asm 清空");
    }

    public boolean shouldBeProxyedMethod(){
        System.out.println("这个方法将会被代理。");
        return true ;
    }
}
