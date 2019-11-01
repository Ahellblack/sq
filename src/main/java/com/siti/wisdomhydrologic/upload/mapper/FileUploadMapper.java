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
            "(`file_url`, `upload_user`, `upload_ip_address`) " +
            "VALUES (#{url},#{userName},#{ipAddress}) ")
    int saveFileMsg(@Param("url") String url,
                    @Param("userName") String userName,
                    @Param("ipAddress")String ipAddress);

    @Select("<script>select * from file_upload_information" +
            "<if test = \'id != null \'>where file_id = #{id}</if></script>")
    List<FileUploadInformation> getFiles(@Param("id")Integer id);

    @Delete("delete from file_upload_information where file_id = #{id} ")
    int delete(@Param("id")Integer id);
}
