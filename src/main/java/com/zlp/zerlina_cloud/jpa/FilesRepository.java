package com.zlp.zerlina_cloud.jpa;

import com.zlp.zerlina_cloud.domain.FilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface FilesRepository extends JpaRepository<FilesEntity,Integer>, JpaSpecificationExecutor<FilesEntity>,Serializable {
	FilesEntity findByUidAndFilename(String uid, String filename);
	FilesEntity[] findByUid(String uid);
	void deleteByFilenameAndUid(String filename, String uid);

}
