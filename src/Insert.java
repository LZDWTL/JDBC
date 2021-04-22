import java.sql.*;

/**
 * @author 10237
 * @date 2021-04-16 20:45
 */
public class Insert {
    public static void main(String []args){
        /*
        21:ftp
        22:ssh
        1、加载驱动
        如果链接的是数据库，那么就需要加载Oracle的驱动ojdbc6.jar，需要手动添加咂包
        2、创建数据库链接
        3、创建一个preparedStatement，这里创建的是Statement
        4、执行SQL语句
        5、处理异常，关闭JDBC对象资源
        */
        Connection conn=null;
        String url ="jdbc:oracle:thin:@120.77.80.134:1521:orcl";
        Statement stmt=null;  //SQL语句对象
        String dname="端茶的";
        String loc="南海";
        String sql="insert into dept values(60,'"+dname+"','"+loc+"')";  //容易有注入攻击

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            conn= DriverManager.getConnection(url,"caoyinyuan","123456");
            conn= DriverManager.getConnection(url,"scott","tiger");
            System.out.println("连接:"+conn);
            stmt=conn.createStatement();
            int a=stmt.executeUpdate(sql);
            System.out.println("保存了"+a+"条信息");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        finally{
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
