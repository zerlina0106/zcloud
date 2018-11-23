package com.zlp.zerlina_cloud.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "files", schema = "zcloud")
public class FilesEntity {
	private int fid;
	@Column(name = "uid", nullable = false, length = 40)
	private String uid;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	private String filename;
	private String filepath;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fid", nullable = false)
	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	@Basic
	@Column(name = "filename", nullable = true, length = 50)
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Basic
	@Column(name = "filepath", nullable = true, length = 50)
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FilesEntity that = (FilesEntity) o;
		return fid == that.fid &&
				Objects.equals(filename, that.filename) &&
				Objects.equals(filepath, that.filepath);
	}

	@Override
	public int hashCode() {

		return Objects.hash(fid, filename, filepath);
	}
}
