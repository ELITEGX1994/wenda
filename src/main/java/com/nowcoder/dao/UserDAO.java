package com.nowcoder.dao;
/*
 *Created by gx on 2018/08/06
 */
import com.nowcoder.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDAO {
    String TABLE_NAME = " user ";
    String INSERT_FILEDS = " name , password , salt , head_url ";
    String SELECT_FILEDS = "id,"+INSERT_FILEDS;
    @Insert({"insert into",TABLE_NAME,"(",INSERT_FILEDS,") values(#{name},#{password},#{salt},#{headUrl})"})
    int addUser(User user);

    @Select({"select ", SELECT_FILEDS, " from ", TABLE_NAME, " where id=#{id}"})
    User selectById(int id);

    @Update({"update ", TABLE_NAME, " set password=#{password} where id=#{id}"})
    void updatePassword(User user);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);
}
