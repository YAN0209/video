package com.yan.video.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class VideoGroup implements Serializable {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "视频组名")
    private String name;

    @ApiModelProperty(value = "评分")
    private Float rate;

    @ApiModelProperty(value = "imdbId")
    private String imdbId;

    @ApiModelProperty(value = "别名")
    private String alias;

    @ApiModelProperty(value = "封面")
    private String poster;

    @ApiModelProperty(value = "视频码")
    private String code;

    @ApiModelProperty(value = "删除标志 0未删除 1已删除")
    private boolean deleteTag;

    @ApiModelProperty(value = "锁定 0未锁定 1已锁定")
    private boolean lockTag;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isDeleteTag() {
        return deleteTag;
    }

    public void setDeleteTag(boolean deleteTag) {
        this.deleteTag = deleteTag;
    }

    public boolean isLockTag() {
        return lockTag;
    }

    public void setLockTag(boolean lockTag) {
        this.lockTag = lockTag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", rate=").append(rate);
        sb.append(", imdbId=").append(imdbId);
        sb.append(", alias=").append(alias);
        sb.append(", poster=").append(poster);
        sb.append(", code=").append(code);
        sb.append(", deleteTag=").append(deleteTag);
        sb.append(", lockTag=").append(lockTag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        VideoGroup other = (VideoGroup) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getRate() == null ? other.getRate() == null : this.getRate().equals(other.getRate()))
            && (this.getImdbId() == null ? other.getImdbId() == null : this.getImdbId().equals(other.getImdbId()))
            && (this.getAlias() == null ? other.getAlias() == null : this.getAlias().equals(other.getAlias()))
            && (this.getPoster() == null ? other.getPoster() == null : this.getPoster().equals(other.getPoster()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.isDeleteTag() == other.isDeleteTag())
            && (this.isLockTag() == other.isLockTag());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getRate() == null) ? 0 : getRate().hashCode());
        result = prime * result + ((getImdbId() == null) ? 0 : getImdbId().hashCode());
        result = prime * result + ((getAlias() == null) ? 0 : getAlias().hashCode());
        result = prime * result + ((getPoster() == null) ? 0 : getPoster().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + (isDeleteTag() ? 1231 : 1237);
        result = prime * result + (isLockTag() ? 1231 : 1237);
        return result;
    }
}