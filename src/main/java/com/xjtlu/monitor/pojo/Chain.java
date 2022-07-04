package com.xjtlu.monitor.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @TableName chain
 */
@TableName(value ="chain")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chain implements Serializable {
    /**
     *
     */
    @TableId
    private Object id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String url;

    /**
     *
     */
    private String apiToken;

    /**
     *
     */
    private String debankLink;

    /**
     *
     */
    private String hashLink;

    /**
     *
     */
    private String transferLink;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
        Chain other = (Chain) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getApiToken() == null ? other.getApiToken() == null : this.getApiToken().equals(other.getApiToken()))
            && (this.getDebankLink() == null ? other.getDebankLink() == null : this.getDebankLink().equals(other.getDebankLink()))
            && (this.getHashLink() == null ? other.getHashLink() == null : this.getHashLink().equals(other.getHashLink()))
            && (this.getTransferLink() == null ? other.getTransferLink() == null : this.getTransferLink().equals(other.getTransferLink()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getApiToken() == null) ? 0 : getApiToken().hashCode());
        result = prime * result + ((getDebankLink() == null) ? 0 : getDebankLink().hashCode());
        result = prime * result + ((getHashLink() == null) ? 0 : getHashLink().hashCode());
        result = prime * result + ((getTransferLink() == null) ? 0 : getTransferLink().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", url=").append(url);
        sb.append(", apiToken=").append(apiToken);
        sb.append(", debankLink=").append(debankLink);
        sb.append(", hashLink=").append(hashLink);
        sb.append(", transferLink=").append(transferLink);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
