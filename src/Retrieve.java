import java.sql.*;

/**
 * @author 10237
 * @date 2021-04-11 15:43
 */
public  class Retrieve{
    public static void main(String []args){
        /*
        21:ftp
        22:ssh
        1、加载驱动
        如果链接的是数据库，那么就需要加载Oracle的驱动ojdbc6.jar，需要手动添加咂包
        2、创建数据库链接
        3、创建一个preparedStatement，这里创建的是Statement
        4、执行SQL语句
        5、便利结果集
        6、处理异常，关闭JDBC对象资源
        */
        Connection conn=null;
        String url ="jdbc:oracle:thin:@120.77.80.134:1521:orcl";
        Statement stmt=null;  //SQL语句对象
        String sql="select * from employees where department_id=30";
        ResultSet rs=null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn= DriverManager.getConnection(url,"caoyinyuan","123456");
            System.out.println("连接:"+conn);
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);  //executeQuery会返回一个结果集
            //rs是结果集，又称为游标，就是一个内存区（缓冲区），查询的结果都在缓冲区
            while(rs.next()){
                int employee_id=rs.getInt("EMPLOYEE_ID");        //从游标中取数据，数据库中的NUMBER就是java中的int
                String first_name=rs.getString("FIRST_NAME");   //数据库中的VARCHAT2就是java中的String
                String last_name=rs.getString("LAST_NAME");    //数据库中的VARCHAT2就是java中的String
                System.out.print(employee_id);
                System.out.print('\t'+first_name);
                System.out.println("\t"+last_name);
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        finally{
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}