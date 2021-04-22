import oracle.jdbc.OracleTypes;

import java.sql.*;

/**
 * @author 10237
 * @date 2021-04-22 11:31
 */
public class SelectFromProcedure {
    public static void main(String []args){
        Connection conn=null;
        String url ="jdbc:oracle:thin:@120.77.80.134:1521:orcl";
        CallableStatement callableStatement=null;  //SQL语句对象    --调用存储过程用CallableStatement
        String sql="{call proc_page(?,?,?,?,?)}";
        ResultSet rs=null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn= DriverManager.getConnection(url,"scott","tiger");
            callableStatement=conn.prepareCall(sql);  //需要理解
            /**
             * 输入参数
             */
            callableStatement.setString(1,"emp1");  //表名
            callableStatement.setString(2,"3");    //每页多少条记录
            callableStatement.setString(3,"3");    //当前页面

            /**
             * 输出参数
             */
            callableStatement.registerOutParameter(4, OracleTypes.INTEGER);
            callableStatement.registerOutParameter(5, OracleTypes.CURSOR);

            callableStatement.execute();   //执行存储过程

            int totalPage=callableStatement.getInt(4);
            System.out.println("总页数："+totalPage);

            rs=(ResultSet) callableStatement.getObject(5);
            while(rs.next()){
                int employee_id=rs.getInt("EMPNO");        //从游标中取数据，数据库中的NUMBER就是java中的int
                String name=rs.getString("ENAME");   //数据库中的VARCHAT2就是java中的String
                System.out.print(employee_id);
                System.out.println('\t'+name);
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        finally{
            try {
                rs.close();
                callableStatement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
