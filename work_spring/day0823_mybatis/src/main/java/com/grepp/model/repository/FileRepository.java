package com.grepp.model.repository;

import com.grepp.model.dto.FileDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileRepository {
    int insertFiles(@Param("fileDTOList") List<FileDTO> fileDTOList); // SQL 에서 여러개를 한 번에 insert 하는 기능(성능 생각하면 이렇게 해야됨 : 커넥션 연결이 비교적 비싼 리소스)
    int insertFile(FileDTO fileDTO); // 때로는 차라리 디비 연결을 여러번 하는게 유리한 경우도 있다.
    List<FileDTO> selectFiles(int bno);
    FileDTO selectFile(int fno);
}
