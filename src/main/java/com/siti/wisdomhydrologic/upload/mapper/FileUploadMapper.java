package com.siti.wisdomhydrologic.upload.mapper;

import com.siti.wisdomhydrologic.upload.entity.FileUploadInformation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by dell on 2019/10/31.
 */
public interface FileUploadMapper {

    @Insert("INSERT INTO `file_upload_information`" +
            "(`file_url`,`origin_file_name`, `upload_user`, `upload_ip_address`) " +
            "VALUES (#{url},#{fileName},#{userId},#{ipAddress}) ")
    int saveFileMsg(@Param("url") String url,
                    @Param("fileName") String fileName,
                    @Param("userId") Integer userId,
                    @Param("ipAddress")String ipAddress);

    @Select("<script>select * from file_upload_information " +
            "<if test = \'id != null \'>where file_id = #{id} </if>" +
            "<if test = \'fileName != null \'>and origin_file_name = #{fileName} </if>" +
            "</script>")
    List<FileUploadInformation> getFiles(@Param("id")Integer id,@Param("fileName") String fileName);

    @Delete("delete from file_upload_information where file_id = #{id} ")
    int delete(@Param("id")Integer id);
}
